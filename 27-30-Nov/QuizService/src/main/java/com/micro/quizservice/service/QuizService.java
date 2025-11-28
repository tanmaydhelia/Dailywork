package com.micro.quizservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micro.quizservice.feign.QuizInterface;
import com.micro.quizservice.model.QuestionWrapper;
import com.micro.quizservice.model.Quiz;
import com.micro.quizservice.model.Response;
import com.micro.quizservice.repository.QuizRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizService {
	
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		
//		RestTemplate
		
		// call the generateQuestionsURL
//		List<String> questions ;
		
		List<String> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		
		quizRepo.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(String quizId) {
       Quiz quiz = quizRepo.findById(quizId).get();
              
       List<String> questionIds = quiz.getQuestionIds();
       ResponseEntity<List<QuestionWrapper>> wrappedQuestions = quizInterface.getQuestionsfFromId(questionIds);       
       return wrappedQuestions;
    }

	public ResponseEntity<Integer> submitQuiz(String quizId, List<Response> responses) {
	    
		ResponseEntity<Integer> score = quizInterface.getScore(responses);

	    return score;
	}

}
