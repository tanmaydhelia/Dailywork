package com.webfluxspringdemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.webfluxspringdemo.model.Product;

import reactor.core.publisher.Flux;



@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer>{
	Flux<Product> findByNameContainingIgnoreCase(String namePart);
}
