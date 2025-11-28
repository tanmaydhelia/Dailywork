package com.micro.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.questionservice.model.Question;
import com.micro.questionservice.model.QuestionWrapper;
import com.micro.questionservice.model.Response;
import com.micro.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment env;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getQuestions() {
		return questionService.getAllQuestions();
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}

	//generate
	@GetMapping("generate")
	public ResponseEntity<List<String>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
		
		return questionService.getQuestionsForQuiz(categoryName, numQuestions);
	}
	
	//getQuestion(questionID)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsfFromId(@RequestParam List<String> questionsId){
		
		System.out.println("Port used: "+ env.getProperty("local.server.port"));
		return questionService.getQuestionsFromId(questionsId);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
}