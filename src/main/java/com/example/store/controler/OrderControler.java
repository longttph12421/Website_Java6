package com.example.store.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.store.entity.Order;
import com.example.store.service.OrderService;
import com.example.store.utils.CommonConst;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderControler {
    @Autowired
    OrderService orderSV;

    @RequestMapping("/checkout")
    public String CheckOut(Model model) {
        return "/order/checkOut";
    }

    @RequestMapping("/list")
    public String List(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderSV.getByUsername(username));
        return "/order/orderList";
    }

    @RequestMapping("/detail/{id}")
    public String Details(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", orderSV.getById(id));
        return "order/orderDetail";
    }
}
