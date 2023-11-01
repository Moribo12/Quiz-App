package com.ribs.quizapp.Service;

import com.ribs.quizapp.Dao.QuestionDao;
import com.ribs.quizapp.Entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao ;

    public ResponseEntity<List<Question>>getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<String>("success",HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Unsuccessful",HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<String>deleteQuestion(Integer id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<String>("Deleted",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Failed",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> updateQuestion(Question question,Integer id) {
        try {
            questionDao.save(question);
            return new ResponseEntity<String>("updated", HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<String>("Denied",HttpStatus.NOT_MODIFIED);
    }
}
