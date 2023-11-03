package com.ribs.quizapp.Service;

import com.ribs.quizapp.Entity.QuestionWrapper;
import com.ribs.quizapp.Entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String category, int numQ, String title);
    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);
    ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);
}
