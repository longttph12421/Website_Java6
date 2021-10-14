package com.example.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.store.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	@Override
	List<Category> findAll();

}
