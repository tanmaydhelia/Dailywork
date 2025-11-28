package com.micro.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micro.questionservice.model.Question;
import com.micro.questionservice.model.QuestionWrapper;
import com.micro.questionservice.model.Response;
import com.micro.questionservice.repository.QuestionRepo;

@Service
public class QuestionService {
	
	@Autowired
    private QuestionRepo questionRepository;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<Question> addQuestion(Question question) {
		try {
			return new ResponseEntity<>(questionRepository.save(question),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<List<String>> getQuestionsForQuiz(String categoryName, Integer numQuestions){
		
		List<String> questions= questionRepository.findRandomQuestionByCategory(categoryName,numQuestions);
		return new ResponseEntity<>(questions, HttpStatus.OK);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<String> questionsId) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		
		List<Question> questions = new ArrayList<>();
		
		for(String id: questionsId) {
			questions.add(questionRepository.findById(id).get());
		}
		
		for(Question q : questions) {
			QuestionWrapper w = new QuestionWrapper();
			w.setId(q.getId());
			w.setQuestionTitle(q.getQuestionTitle());
			w.setOption1(q.getOption1());
			w.setOption2(q.getOption2());
			w.setOption3(q.getOption3());
			w.setOption4(q.getOption4());
			
			wrappers.add(w);
		}
		
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {	    
	   
	    Integer score = 0;
	    // Compare correct answers
	    for (Response r : responses) {
	    	
	    	Question q = questionRepository.findById(r.getId()).get();
	    	
	        if(r.getUserAnswer().equals(q.getRightAnswer())) {
	        	score+=1;
	        }
	    }

	    return ResponseEntity.ok(score);
	}
	
	
}

