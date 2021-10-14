package com.example.store.restcontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.entity.Product;
import com.example.store.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductRestControler {

	@Autowired
	private ProductService prservice;

	@GetMapping()
	public ResponseEntity<List<Product>> getAll() {

		return ResponseEntity.ok(prservice.getAll());
	}

	// http://localhost:8080/api/product/{name}
	@GetMapping("/name")
	public ResponseEntity<List<Product>> getByName(@RequestParam String name) {
		List<Product> list = prservice.findbyName(name);
		if (list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}

	// http://localhost:8080/api/product/seach?name=
	@GetMapping("/seach/{name}")
	public ResponseEntity<List<Product>> findByName(@PathVariable("name") String name) {
		List<Product> list = prservice.findbyName(name);
		if (list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
		if (prservice.getById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(prservice.getById(id));
	}

	// http://localhost:8080/api/product/get?id=1
	@GetMapping("/get")
	public ResponseEntity<Product> getById2(@RequestParam Integer id) {
		Product pr = prservice.getById(id);
		if (pr == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pr);
	}

	// http://localhost:8080/api/product
	@PostMapping()
	public ResponseEntity<Product> post(@RequestBody Product product) {
		prservice.save(product);
		return ResponseEntity.ok(product);
	}
	// http://localhost:8080/api/product
	@PutMapping("{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Integer id, @RequestBody Product pr) {
		prservice.save(pr);
		return ResponseEntity.ok(pr);
	}

	// http://localhost:8080/api/product/{id}
	@DeleteMapping("/delete/{id}")
	public int delete(@PathVariable("id") Integer id) {
		return prservice.deleteById(id);
	}
}
