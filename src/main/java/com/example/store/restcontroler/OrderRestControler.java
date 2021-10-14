package com.example.store.restcontroler;

import com.example.store.entity.Order;
import com.example.store.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/order")
public class OrderRestControler {
    @Autowired
    OrderService orderService;
    @GetMapping()
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<Order> getById2(@RequestParam Long id) {
        Order order = orderService.getById(id);
        if (order == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping()
    public ResponseEntity<Order> add(@RequestBody JsonNode orderData) {
        return ResponseEntity.ok(orderService.save(orderData));
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam Long id) {
        return orderService.deleteById(id);
    }
}
