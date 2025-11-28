package com.micro.questionservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.micro.questionservice.model.Question;

public interface QuestionRepo extends MongoRepository<Question, String>{

	List<Question> findByCategory(String category);

	@Aggregation(pipeline = {
	        "{ $match: { category: ?0 } }",
	        "{ $sample: { size: ?1 } }",
	        "{ $project: { _id: 0, id: 1 } }"
	    })
	List<String> findRandomQuestionByCategory(String category, Integer numQ);

}
