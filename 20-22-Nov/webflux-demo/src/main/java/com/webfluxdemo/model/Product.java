package com.webfluxdemo.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Product {
	
	@Id
	private int id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private int price;
}
