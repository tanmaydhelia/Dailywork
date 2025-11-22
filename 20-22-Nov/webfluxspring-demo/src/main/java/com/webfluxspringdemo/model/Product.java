package com.webfluxspringdemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document
public class Product {
	
	@Id
	private int id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private int price;
}
