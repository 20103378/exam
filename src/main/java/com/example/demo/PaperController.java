package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.dto.PaperDto;
import com.example.entity.Paper;
import com.example.entity.Question;
import com.example.service.PaperService;
import com.example.service.QuestionService;
import com.example.utils.ResponseUtil;
import com.example.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author : najiang
 * create at:  2020-03-21  13:55
 * @description:
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/stuPaperList")
    public String stuPaperList(Model mod) {
        List<Paper> paperList = paperService.getPaperList();
        mod.addAttribute("paperList", paperList);
        return "exam/selectPaper";
    }


    @RequestMapping("/paperList")
    public String paperList(Model mod) {
        List<Paper> paperList = paperService.getPaperList();
        mod.addAttribute("paperList", paperList);
        return "paper/paperList";
    }

    @RequestMapping("/paperPreSave")
    public String paperPreSave(Model mod, String paperId) {
        String title = "";
        if (paperId != null && !paperId.isEmpty()) {
            PaperDto paper = paperService.getPaperById(Integer.valueOf(paperId));
            mod.addAttribute("paper", paper);
            title = "修改试卷";
        } else {
            title = "添加试卷";
        }
        mod.addAttribute("title", title);
        return "paper/paperSave";
    }

    @RequestMapping("/paperSavePaper")
    public String paperSavePaper(Model mod, @RequestParam Map<String, Object> params) {
        System.out.println(params);
        Paper paper = new Paper();
        if (StringUtil.isNotEmpty(String.valueOf(params.get("paperId")))) {
            paper.setId(Integer.parseInt(String.valueOf(params.get("paperId"))));
        } else {
            paper.setJoinDate(new Date(System.currentTimeMillis()));
        }
        paper.setPaperName(String.valueOf(params.get("paper.paperName")));
        if (paperService.getByPaperName(paper.getPaperName()) == null) {
            paperService.savePaper(paper);
            List<Paper> paperList = paperService.getPaperList();
//            mod.addAttribute("paperList",paperList);
            mod.addAttribute("paperid", paper.getId());
            mod.addAttribute("title", "添加题目");

            return "question/questionSave";
        } else {
            return "paper/paperSave";
        }

    }


    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public String saveQuestion(Model mod, HttpServletRequest request, @RequestParam Map<String, Object> params) throws Exception {
        Question question = new Question();
        question.setAnswer(String.valueOf(params.get("question.answer")));
        question.setJoinTime(new Date());
        question.setOptionA(String.valueOf(params.get("question.optionA")));
        question.setOptionB(String.valueOf(params.get("question.optionB")));
        question.setOptionC(String.valueOf(params.get("question.optionC")));
        question.setOptionD(String.valueOf(params.get("question.optionD")));
        String paperid = (String) params.get("question.paper.id");
        question.setPaperid(Integer.valueOf(paperid));
        question.setType(String.valueOf(params.get("question.type")));
        question.setSubject(String.valueOf(params.get("question.subject")));
        questionService.saveQuestion(question);

        mod.addAttribute("paperid", paperid);
        return "question/questionSave";
    }


    @RequestMapping("/paperDelete")
    public void paperDelete(Model mod, String paperId, HttpServletResponse response) throws Exception {
        JSONObject resultJson = new JSONObject();
        if (paperId != null && !paperId.isEmpty()) {
            paperService.deletePaper(Integer.valueOf(paperId));
        }
        resultJson.put("success", true);
        ResponseUtil.write(resultJson, response);
    }

    @RequestMapping("/getDetailPaper")
    public String searchStu(Model mod, String paperId,Integer searchType) {
        List<Question> squestionList = new ArrayList<Question>();
        List<Question> mquestionList = new ArrayList<Question>();
        if(ObjectUtils.isEmpty(searchType)){
            List<Paper> paperList = paperService.getPaperList();
            mod.addAttribute("paperList", paperList);
            if (!StringUtil.isNotEmpty(paperId) && !CollectionUtils.isEmpty(paperList)) {
                paperId = paperList.get(0).getId().toString();
            }
        }
        PaperDto paper = paperService.getPaperById(Integer.valueOf(paperId));
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
        mod.addAttribute("paperName", paper.getPaperName());
        return "exam/paper";

    }
}