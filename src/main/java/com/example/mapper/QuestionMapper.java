package com.example.mapper;

import com.example.dto.QuestionDto;
import com.example.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectByPaperId(Integer paperId);

    void deleteByPaperId(Integer paperId);

    List<QuestionDto> getQuestions(String subject, Integer start, Integer pageSize,Integer paperId);

    int questionCount(@Param("subject") String subject,@Param("paperId")Integer paperId);

    QuestionDto getQuestionById(Integer questionId);
}