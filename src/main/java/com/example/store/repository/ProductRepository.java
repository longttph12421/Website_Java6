package com.example.store.repository;

import java.util.List;

import com.example.store.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.example.store.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Override
	List<Product> findAll();

	Page<Product> findAll(Pageable page);

	List<Product> findByName(String name);

	@Query("SELECT pr FROM Product pr WHERE pr.name LIKE %:name%")
	Page<Product> findByNameLike(@Param("name") String name, Pageable page);

	Page<Product> findByCategory(Pageable page , Category category);
}
