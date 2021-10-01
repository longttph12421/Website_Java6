package com.example.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.store.entity.OrderDetail;
import com.example.store.repository.OrderDetailRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.store.entity.Order;
import com.example.store.repository.OrderRepository;
import com.example.store.utils.CommonConst;

@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderDetailRepository orderDetailRepo;

    public List<Order> getAll() {
        return (List<Order>) orderRepo.findAll();
    }

    public Page<Order> getPage(int pageInnit, int pageSize) {
        Pageable page = PageRequest.of(pageInnit, pageSize);
        return orderRepo.findAll(page);
    }


    public Order getById(Long id) {
        Optional<Order> opt = orderRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
    public List<Order> getByUsername(String username){
        return orderRepo.findByUsername(username);
    }

    public Order save(JsonNode orderData) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.convertValue(orderData, Order.class);
        orderRepo.save(order);
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> orderDetails = objectMapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(detail -> detail.setOrder(order)).collect(Collectors.toList());
        orderDetailRepo.saveAll(orderDetails);
        System.out.println(orderData.get("orderDetails"));
        return order;
    }

    public int deleteById(Long id) {
        try {
            orderRepo.deleteById(id);
            return CommonConst.SUCCESS;
        } catch (Exception ex) {
            return CommonConst.ERROR;
        }
    }
}
