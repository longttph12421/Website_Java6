package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
