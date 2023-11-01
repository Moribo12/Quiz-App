package com.ribs.quizapp.Service;

import com.ribs.quizapp.Dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category,int numQ, String title){
        try{
           return new ResponseEntity<String>("sucess", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("unsucessful", HttpStatus.NOT_ACCEPTABLE);
    }
}
