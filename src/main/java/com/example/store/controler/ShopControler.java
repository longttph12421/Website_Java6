package com.example.store.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.store.entity.Product;
import com.example.store.service.ProductService;
import com.example.store.utils.CommonConst;

@Controller
@RequestMapping("/shop")
public class ShopControler {
    @Autowired
    ProductService productSV;

    @GetMapping
    public String getProduct(Model model, @RequestParam(defaultValue = "0") int Page) {
        if (Page < 0)
            Page = 0;
        Page<Product> page = productSV.getPage(Page, CommonConst.PAGE_SIZE);

        if (page.getTotalPages() < Page)
            page = productSV.getPage(page.getTotalPages(), CommonConst.PAGE_SIZE);
        page.getNumber();
        model.addAttribute("page", page);
        return "shop/shoppage";
    }

    @GetMapping("/seach")
    public String getByName(Model model, @RequestParam(defaultValue = "0") int Page, @RequestParam String name) {

        if (Page < 0)
            Page = 1;
        Page<Product> page = productSV.getByName(name, Page, CommonConst.PAGE_SIZE);
        if (page.getTotalPages() < Page)
            page = productSV.getByName(name, page.getTotalPages(), CommonConst.PAGE_SIZE);
        model.addAttribute("page", page);
        return "shop/shoppage";
    }

    @GetMapping("/product/{id}")
    public String getproduct(Model model, @PathVariable Integer id) {
        Product product = productSV.getById(id);
        Page<Product> page = productSV.getByCategory(product.getCategory().getId(), 0, CommonConst.PAGE_SIZE);
        model.addAttribute("page", page);
        model.addAttribute("Product", product);
        return "shop/details";
    }

    @GetMapping("/category/{id}")
    public String getproducts(Model model, @RequestParam(defaultValue = "0") int Page, @PathVariable String id) {
        if (Page < 0)
            Page = 1;
        Page<Product> page = productSV.getByCategory(id, Page, CommonConst.PAGE_SIZE);
        if (page.getTotalPages() < Page)
            page = productSV.getByCategory(id, page.getTotalPages(), CommonConst.PAGE_SIZE);
        model.addAttribute("page", page);
        return "shop/shoppage";
    }

    @GetMapping("/cart")
    public String getcart(Model model) {

        return "shop/shoppingcart";
    }
}
