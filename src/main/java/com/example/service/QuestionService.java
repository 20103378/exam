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

    QuestionDto getQuestionById(Integer questionId);

    void saveQuestion(Question question);

    List<QuestionDto> getQuestionsByPaperId(Integer paperId);
}
