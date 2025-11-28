package com.micro.questionservice.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {
	@Id
    private String id;
	
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
}