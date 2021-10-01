package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
