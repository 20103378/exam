package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.PageBean;
import com.example.dto.QuestionDto;
import com.example.entity.Paper;
import com.example.entity.Question;
import com.example.service.PaperService;
import com.example.service.QuestionService;
import com.example.utils.PapeUtil;
import com.example.utils.ResponseUtil;
import com.example.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class QuestionController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/questionList")
    public String questionList(Model mod, HttpServletRequest request,String  page,@RequestParam Map<String,Object> params,String paperId) throws Exception {
        String subject =  (String) params.get("subject");
        if(("null").equals(subject)){
            subject = null;
        }

        if(StringUtil.isEmpty(page)){
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 8);
        List<QuestionDto> questionList = questionService.getQuestions(subject, pageBean,ObjectUtils.isEmpty(paperId)? null:Integer.valueOf(paperId));
        int total = questionService.questionCount(subject,ObjectUtils.isEmpty(paperId)? null:Integer.valueOf(paperId));
        String pageCode= PapeUtil.getPagation(request.getContextPath()+"/questionList",total, Integer.parseInt(page), 8,subject);
        mod.addAttribute("pageCode",pageCode);
        mod.addAttribute("questionList",questionList);
        mod.addAttribute("subject",subject);

        List<Paper> paperList = paperService.getPaperList();
        mod.addAttribute("paperList",paperList);

        return "question/questionList";
    }

    @RequestMapping("/questionDelete")
    public void questionDelete(Model mod,String questionId,HttpServletResponse response)throws Exception {
        JSONObject resultJson=new JSONObject();
        if(questionId!=null && !questionId.isEmpty()){
            questionService.deleteQuestion(Integer.valueOf(questionId));
        }
        resultJson.put("success", true);
        ResponseUtil.write(resultJson,response);
    }
    @RequestMapping("/getQuestionById")
    public String getQuestionById(Model mod,String questionId)throws Exception {
        if(questionId!=null && !questionId.isEmpty()){
            QuestionDto question = questionService.getQuestionById(Integer.valueOf(questionId));
            mod.addAttribute("question",question);
        }
        return "question/questionShow";
    }

    @RequestMapping("/preSave")
    public String preSave(Model mod,String questionId) {
        List<Paper> paperList = paperService.getPaperList();
        mod.addAttribute("paperList",paperList);
        String title="";
        if(questionId!=null && !questionId.isEmpty()){
            QuestionDto question = questionService.getQuestionById(Integer.valueOf(questionId));
            mod.addAttribute("question",question);
            title = "修改试题信息";
        }else {
            title = "添加试题信息";
        }
        mod.addAttribute("title",title);
        return "question/questionSave2";
    }





    @RequestMapping(value = "/saveQuestion2", method=RequestMethod.POST)
    public String saveQuestion2(Model mod, HttpServletRequest request,@RequestParam Map<String,Object> params)throws Exception {
        Question question = new Question();
        if(!ObjectUtils.isEmpty(params.get("questionId"))){
            String questionid = (String)params.get("questionId");
            question.setId(Integer.valueOf(questionid));
        }
        question.setAnswer(String.valueOf(params.get("question.answer")));
        question.setJoinTime(new Date());
        question.setOptionA(String.valueOf(params.get("question.optionA")));
        question.setOptionB(String.valueOf(params.get("question.optionB")));
        question.setOptionC(String.valueOf(params.get("question.optionC")));
        question.setOptionD(String.valueOf(params.get("question.optionD")));
        String paperid = (String)params.get("question.paper.id");
        question.setPaperid(Integer.valueOf(paperid));
        question.setType(String.valueOf(params.get("question.type")));
        question.setSubject(String.valueOf(params.get("question.subject")));
        questionService.saveQuestion(question);

        mod.addAttribute("question",questionService.getQuestionById(Integer.valueOf(question.getId())));
        return "question/questionList";
    }

}