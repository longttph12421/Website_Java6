package com.example.store.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.store.entity.Account;

@Service
public class MyUserDetailSevice implements UserDetailsService {
	@Autowired
	AccountService accountRepo;
	@Autowired
	BCryptPasswordEncoder pass;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = accountRepo.findByUsername(username);
			String Password = account.getPassword();
			String[] Role = account.getAuthorities().stream().map(auth -> auth.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);

			return User.withUsername(username).password(pass.encode(Password)).roles(Role).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "notfound");
		}
	}
}
