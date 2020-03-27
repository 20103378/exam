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
    public List<QuestionDto> getQuestions(String subject, PageBean pageBean,Integer paperId) throws Exception{
        int start = (pageBean.getPage() - 1) * pageBean.getPageSize();
        return questionMapper.getQuestions(subject, start,pageBean.getPageSize(),paperId);
    }

    @Override
    public int questionCount(String subject,Integer paperId) throws Exception {
        return questionMapper.questionCount(subject,paperId);
    }

    @Override
    public QuestionDto getQuestionById(Integer questionId) {
        return questionMapper.getQuestionById(questionId);
    }

    @Override
    public void saveQuestion(Question question) {
        if(question.getId()!=null){
            questionMapper.updateByPrimaryKey(question);
        }else {
            questionMapper.insert(question);
        }

    }
}
