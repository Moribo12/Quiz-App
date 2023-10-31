package com.ribs.quizapp.QuestionController;

import com.ribs.quizapp.QuestionEntity.Question;
import com.ribs.quizapp.QuestionService.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
     public List<Question>getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
     }
     @PostMapping("/add")
     public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
     }
     @DeleteMapping("/{id}")
     public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
     }
     @PutMapping("/{id}")
     public String updateQuestion( @RequestBody Question question,@PathVariable Integer id){
        return questionService.updateQuestion(question,id);
     }

}
