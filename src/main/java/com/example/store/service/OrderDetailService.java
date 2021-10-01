package com.example.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.store.repository.OrderDetailRepository;

@Component
public class OrderDetailService {

	@Autowired
    OrderDetailRepository orderdetailRepo;
}
