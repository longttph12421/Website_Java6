package com.example.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.store.entity.Category;
import com.example.store.repository.CategoryRepository;
import com.example.store.utils.CommonConst;

@Component
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> getAll() {

		return  categoryRepo.findAll();
	}

	public Category getById(String id) {

		return categoryRepo.findById(id).get();
	}

	public int save(Category ca) {
		try {
			categoryRepo.save(ca);
			return CommonConst.SUCCESS;
		} catch (Exception ex) {
			return CommonConst.ERROR;
		}
	}

	public int updateById(Category ca) {
		try {
			categoryRepo.save(ca);
			return CommonConst.SUCCESS;
		} catch (Exception ex) {
			return CommonConst.ERROR;
		}
	}

	public int deleteById(String id) {

		try {
			categoryRepo.deleteById(id);
			return CommonConst.SUCCESS;
		} catch (Exception ex) {
			return CommonConst.ERROR;
		}

	}
}
