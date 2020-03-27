package com.example.service;

import com.example.dto.PaperDto;
import com.example.entity.Paper;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 */
public interface PaperService {
    PaperDto getPaperById(Integer paperId);

    List<Paper> getPaperList();

    Boolean deletePaper(Integer paperId);

    void savePaper(Paper paper);

    Paper getByPaperName(String paperName);
}
