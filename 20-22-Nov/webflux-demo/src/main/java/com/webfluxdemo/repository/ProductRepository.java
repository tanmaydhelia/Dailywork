package com.webfluxdemo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.webfluxdemo.model.Product;

import reactor.core.publisher.Flux;



@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer>{
	Flux<Product> findByNameContainingIgnoreCase(String namePart);
}
