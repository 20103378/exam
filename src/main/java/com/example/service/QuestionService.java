package com.example.service;

import com.example.domain.PageBean;
import com.example.dto.QuestionDto;
import com.example.entity.Question;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
public interface QuestionService {

    void deleteQuestion(Integer questionId);
    List<QuestionDto> getQuestions(String subject, PageBean pageBean,Integer paperId ) throws Exception;
    int questionCount(String subject,Integer paperId) throws Exception;

    QuestionDto getQuestionById(Integer questionId);

    void saveQuestion(Question question);
}
