package com.example.store.restcontroler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.store.entity.Category;
import com.example.store.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
public class CategoryRestControler {
	@Autowired
	private CategoryService cateservice;

	@GetMapping()
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(cateservice.getAll());
	}

	@GetMapping("/get")
	public ResponseEntity<Category> getById2(@RequestParam String id) {
		Category ca = cateservice.getById(id);
		if (ca == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(ca);
	}

	@PostMapping()
	public ResponseEntity<Category> post(@RequestBody Category category) {
		cateservice.save(category);
		return ResponseEntity.ok(category);
	}

	@PutMapping("{id}")
	public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody Category category) {
		cateservice.save(category);
		return ResponseEntity.ok(category);
	}

	@DeleteMapping("/delete/{id}")
	public int delete(@PathVariable("id") String id) {
		return cateservice.deleteById(id);
	}
}
