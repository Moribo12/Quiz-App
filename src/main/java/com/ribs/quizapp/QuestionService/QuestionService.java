package com.ribs.quizapp.QuestionService;

import com.ribs.quizapp.Dao.QuestionDao;
import com.ribs.quizapp.QuestionEntity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao ;

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }

    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "deleted";
    }

    public String updateQuestion(Question question,Integer id) {
        questionDao.save(question);
        return  "updated";
    }
}
