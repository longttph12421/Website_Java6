package com.example.store.service;

import java.util.List;
import java.util.Optional;

import com.example.store.entity.Category;
import com.example.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import com.example.store.utils.CommonConst;

@Component
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAll() {
        return (List<Product>) productRepo.findAll();
    }

    public Page<Product> getPage(int pageInnit, int pageSize) {
        Pageable page = PageRequest.of(pageInnit, pageSize);
        return productRepo.findAll(page);
    }

    public List<Product> findbyName(String name) {
        return productRepo.findByName(name);
    }

    public Product getById(Integer productId) {
        Optional<Product> opt = productRepo.findById(productId);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    public Page<Product> getByName(String name, int pageInnit, int pageSize) {
        Pageable page = PageRequest.of(pageInnit, pageSize);
        return productRepo.findByNameLike(name, page);
    }

    public Page<Product> getByCategory(String categoryID, int pageInnit, int pageSize) {
        Category category = categoryRepo.findById(categoryID).get();
        Pageable page = PageRequest.of(pageInnit, pageSize);
        return productRepo.findByCategory(page, category);
    }

    public int save(Product pr) {
        try {
            productRepo.save(pr).getId();
            return CommonConst.SUCCESS;

        } catch (Exception ex) {
            return CommonConst.ERROR;
        }
    }

    public int updateById(Product SP) {
        try {
            productRepo.save(SP);
            return CommonConst.SUCCESS;
        } catch (Exception ex) {
            return CommonConst.ERROR;
        }
    }

    public int deleteById(Integer deleteId) {

        try {
            productRepo.deleteById(deleteId);
            return CommonConst.SUCCESS;
        } catch (Exception ex) {
            return CommonConst.ERROR;
        }

    }

}
