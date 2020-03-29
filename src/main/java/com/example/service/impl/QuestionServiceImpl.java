package com.example.service.impl;

import com.example.domain.PageBean;
import com.example.dto.QuestionDto;
import com.example.entity.Question;
import com.example.mapper.QuestionMapper;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public void deleteQuestion(Integer questionId) {
        questionMapper.deleteByPrimaryKey(questionId);

    }

    @Override
    public QuestionDto getQuestionById(Integer questionId) {
        return questionMapper.getQuestionById(questionId);
    }

    @Override
    public void saveQuestion(Question question) {
        if(question.getId()!=null){
            questionMapper.updateByPrimaryKeySelective(question);
        }else {
            questionMapper.insertSelective(question);
        }

    }

    @Override
    public List<QuestionDto> getQuestionsByPaperId(Integer paperId) {
        return questionMapper.getQuestionsByPaperId(paperId);
    }
}
