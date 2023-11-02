package com.ribs.quizapp.Service;

import com.ribs.quizapp.Dao.QuestionDao;
import com.ribs.quizapp.Dao.QuizDao;
import com.ribs.quizapp.Entity.Question;
import com.ribs.quizapp.Entity.QuestionWrapper;
import com.ribs.quizapp.Entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category,int numQ,String title){
        try{
            List<Question> questions= questionDao.findRandomQuestionsByCategory(category,numQ);

            Quiz quiz= new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

           return new ResponseEntity<String>("success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("unsuccessful", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity <List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try{
                Quiz quiz=quizDao.findById(id).orElseThrow();
                List<Question>questionsFromDB= quiz.getQuestions();
                List<QuestionWrapper>questionsForUser= new ArrayList<>();

            for(Question q: questionsFromDB) {
                QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionsForUser.add(qw);
            }
            return new ResponseEntity<List<QuestionWrapper>>(questionsForUser,HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity <List<QuestionWrapper>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
