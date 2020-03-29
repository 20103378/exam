package com.example.demo;

import com.example.dto.PaperDto;
import com.example.entity.Paper;
import com.example.entity.Question;
import com.example.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author : najiang
 * create at:  2020-03-21  13:55
 * @description:
 */
@Controller
public class ExamController {
    @Autowired
    private PaperService paperService;

    /**
     * 查看试卷详情
     * @param paper
     * @param mod
     * @param params
     * @return
     */
    @RequestMapping("/getDetailPaper")
    public String searchStu(@ModelAttribute(value="paper") PaperDto paper,Model mod,@RequestParam Map<String, Object> params) {
        List<Question> squestionList = new ArrayList<>();
        List<Question> mquestionList = new ArrayList<>();
        Integer paperId = params.get("paperId")==null? paper.getId():(params.get("paperId")==null ? null:
                Integer.valueOf(String.valueOf((params.get("paperId")))));
            List<Paper> paperList = paperService.getPaperList();
            mod.addAttribute("paperList", paperList);
            if (ObjectUtils.isEmpty(paperId) && !CollectionUtils.isEmpty(paperList)) {
                paperId = paperList.get(0).getId();
            }

        paper = paperService.getPaperById(paperId);
        List<Question> questionList = paper.getQuestions();
        Iterator<Question> it = questionList.iterator();
        while (it.hasNext()) {
            Question q = it.next();
            if ("1".equals(q.getType())) {
                squestionList.add(q);
            } else {
                mquestionList.add(q);
            }
        }
        mod.addAttribute("squestionList", squestionList);
        mod.addAttribute("mquestionList", mquestionList);
        mod.addAttribute("paper", paper);
        return "exam/paper";
    }

    /**
     * 删除整个试卷
     * @param paperId
     * @return
     */
    @RequestMapping("/paperDelete")
    public String paperDelete(String paperId){
        if (paperId != null && !paperId.isEmpty()) {
            paperService.deletePaper(Integer.valueOf(paperId));
        }
        return "redirect:/paperList";
    }
}