package com.mongodemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodemo.model.Student;

public interface StudentRepository extends MongoRepository<Student, Integer>{
	
}
