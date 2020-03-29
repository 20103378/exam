package com.example.mapper;

import com.example.dto.QuestionDto;
import com.example.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    List<Question> selectByPaperId(Integer paperId);

    void deleteByPaperId(Integer paperId);

    QuestionDto getQuestionById(Integer questionId);

    List<QuestionDto> getQuestionsByPaperId(Integer paperId);
}