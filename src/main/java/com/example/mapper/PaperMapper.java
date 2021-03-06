package com.example.mapper;

import com.example.entity.Paper;

import java.util.List;

public interface PaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

    List<Paper> getPaperList();

    Paper getByPaperName(String paperName);
}