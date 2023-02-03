package org.polytech.toeic.service;

import org.polytech.toeic.entity.Question;
import org.polytech.toeic.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository)
    {
        this.questionRepository = questionRepository;
    }


    public Optional<Question> getQuestionById(int id)
    {
        return questionRepository.findById(id);
    }

    //Toutes les questions d'un set de questions
    public List<Question> getIdBySet(Integer setId)
    {
        if(setId==0|| setId==null)
        {
            return questionRepository.findAll();
        }
        else
        {
            return questionRepository.findAllBySet(setId);
        }
    }
    public List<Question> findAll()
    {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionByType(String type){
        List<Question> questions = questionRepository.findAllByType(type);
        Collections.shuffle(questions);
        if (questions.size()>9){
        List<Question> finalQuestions = new ArrayList<Question>();
            for (int i = 0; i<10 ;i++){
                finalQuestions.add(questions.get(i));
            }
            return finalQuestions;
        }
        return questions;
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
    public Question addQuestion(Question question)
    {
        questionRepository.save(question);
        return question;
    }

    public void updateQuestion(Question question)
    {
        questionRepository.save(question);
    }
}
