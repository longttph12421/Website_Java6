package com.example.store.service;

import com.example.store.entity.Account;
import com.example.store.entity.Authority;
import com.example.store.entity.Category;
import com.example.store.repository.AccountRepository;
import com.example.store.utils.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.store.repository.AuthorityRepository;

import java.util.List;

@Component
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepo;

    @Autowired
    AccountRepository accountRepository;

    public List<Authority> getAll() {
        return authorityRepo.findAll();
    }

    public Authority getById(Integer id) {
        return authorityRepo.findById(id).get();
    }

    public List<Authority> FindAthoritiesOfAdministrations() {
        List<Account> accounts = accountRepository.getAdministrators();
        return authorityRepo.AuthoritiesOf(accounts);
    }

    public Authority save(Authority au) {

        return authorityRepo.save(au);
    }

    public void deleteById(Integer id) {
         authorityRepo.deleteById(id);
    }
}
