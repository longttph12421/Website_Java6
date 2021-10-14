package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.store.repository.RoleRepository;

import java.util.List;

@Component
public class RoleService {
	
	@Autowired
	RoleRepository roleRepo;

	public List<Role> getAll() {
		return (List<Role>) roleRepo.findAll();
	}

	public Role findByid(String id){
		return roleRepo.findById(id).get();
	}
}
