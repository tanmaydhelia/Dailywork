package com.webfluxspringdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webfluxspringdemo.model.Product;
import com.webfluxspringdemo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	

	
	public Flux<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Mono<Product> getProductById(int id){
		return productRepository.findById(id);
	}
	
	public Flux<Product> searchByName(String name){
		return productRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Mono<Product> saveProduct(Product product){
		return productRepository.save(product);
	}
	
	public Mono<Void> deleteProduct(int id){
		return productRepository.deleteById(id);
	}
} 
