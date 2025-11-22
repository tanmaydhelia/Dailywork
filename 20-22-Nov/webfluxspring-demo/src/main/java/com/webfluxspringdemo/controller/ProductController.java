package com.webfluxspringdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webfluxspringdemo.model.Product;
import com.webfluxspringdemo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService ps;
	
	@GetMapping
	public Flux<Product> getAllProducts(){
		return ps.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Mono<Product> getProductById(@PathVariable Integer id){
		return ps.getProductById(id);
	}
	
	@GetMapping("/search")
	public Flux<Product> searchProducts(@RequestParam String name){
		return ps.searchByName(name);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> createProduct(@RequestBody Product product){
		return ps.saveProduct(product);
	}
	
	@PutMapping("/{id}")
	public Mono<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		return ps.getProductById(id).flatMap(i -> {
			i.setName(product.getName());
			i.setPrice(product.getPrice());
			
			return ps.saveProduct(i);
		});
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteProduct(@PathVariable int id){
		return ps.deleteProduct(id);
	}
	
}
