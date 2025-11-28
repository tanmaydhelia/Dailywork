package com.micro.quizservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.micro.quizservice.model.Quiz;

public interface QuizRepo extends MongoRepository<Quiz, String>{
	
}
