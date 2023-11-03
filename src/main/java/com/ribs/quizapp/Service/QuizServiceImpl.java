package com.ribs.quizapp.Service;

import com.ribs.quizapp.Dao.QuestionDao;
import com.ribs.quizapp.Dao.QuizDao;
import com.ribs.quizapp.Entity.Question;
import com.ribs.quizapp.Entity.QuestionWrapper;
import com.ribs.quizapp.Entity.Quiz;
import com.ribs.quizapp.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
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

           return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("unsuccessful", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try{
                Quiz quiz=quizDao.findById(id).orElseThrow();
                List<Question>questionsFromDB= quiz.getQuestions();
                List<QuestionWrapper>questionsForUser= new ArrayList<>();

            for(Question q: questionsFromDB) {
                QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionsForUser.add(qw);
            }
            return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity <>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz =quizDao.findById(id).orElseThrow();
        List<Question> questions=quiz.getQuestions();
        int right =0;
        int i =0;
        for (Response response:responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

                i++;
            }
        return new ResponseEntity<>(right,HttpStatus.OK);
        }

    }

