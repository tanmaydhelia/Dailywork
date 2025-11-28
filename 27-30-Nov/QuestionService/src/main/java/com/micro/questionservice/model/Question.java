package com.micro.questionservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "Question")
@Data
public class Question {
	
	@Id
    private String id;
	
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String difficultyLevel;
	private String rightAnswer;
	private String category;
	
}