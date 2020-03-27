package com.example.service.impl;

import com.example.dto.PaperDto;
import com.example.entity.Paper;
import com.example.entity.Question;
import com.example.mapper.PaperMapper;
import com.example.mapper.QuestionMapper;
import com.example.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private QuestionMapper questionMapper;



    @Override
    public PaperDto getPaperById(Integer paperId){
        Paper paper = paperMapper.selectByPrimaryKey(paperId);
        List<Question> questions  = questionMapper.selectByPaperId(paperId);
        PaperDto paperDto = new PaperDto();
        paperDto.setId(paperId);
        paperDto.setPaperName(paper.getPaperName());
        paperDto.setJoinDate(paper.getJoinDate());
        paperDto.setQuestions(questions);
        return paperDto;
    }

    @Override
    public List<Paper> getPaperList() {
        return paperMapper.getPaperList();
    }

    @Override
    public Boolean deletePaper(Integer paperId) {
        questionMapper.deleteByPaperId(paperId);
        paperMapper.deleteByPrimaryKey(paperId);
        return true;
    }

    @Override
    public void savePaper(Paper paper) {
        if(paper.getId()!=null){
            paperMapper.updateByPrimaryKey(paper);
        }else {
            paperMapper.insert(paper);
        }
    }

    @Override
    public Paper getByPaperName(String paperName) {
        return paperMapper.getByPaperName(paperName);
    }
}
