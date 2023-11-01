package com.ribs.quizapp.Controller;

import com.ribs.quizapp.Entity.Question;
import com.ribs.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
     public ResponseEntity<List<Question>>getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
     }
     @PostMapping("/add")
     public ResponseEntity<String>addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
     }
     @PutMapping("/{id}")
     public ResponseEntity<String> updateQuestion( @RequestBody Question question,@PathVariable Integer id){
        return questionService.updateQuestion(question,id);
     }

}
