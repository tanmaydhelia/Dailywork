package com.micro.quizservice.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Response {
	@Id
    private String id;
	private String userAnswer;
}
