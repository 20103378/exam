package com.example.demo;

import com.example.dto.QuestionDto;
import com.example.entity.Paper;
import com.example.entity.Question;
import com.example.service.PaperService;
import com.example.service.QuestionService;
import com.example.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

/**
 * 题目管理
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

    /**
     * 根据paperId查询试题列表
     * @param map
     * @param model
     * @param paperId
     * @return
     */
    @RequestMapping("/questionList")
    public String questionList(@RequestParam Map<String, String> map,Model model,Integer paperId){
        if (map.size() == 0 || map.get("currentPage")== null ||map.get("currentPage").isEmpty()) {
            map.put("currentPage", "0");
        }
        // 查询用户列表 及设置分页信息
        List<QuestionDto> questionList = questionService.getQuestionsByPaperId(paperId);
        // 参数为当前页码、每页显示条数、查询的列表集合
        Pager pageInfo = new Pager(Integer.valueOf(map.get("currentPage")), 6, questionList);
        model.addAttribute("questionList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("paperId", paperId);
        if(CollectionUtils.isEmpty(pageInfo.getList())){
            return "redirect:/paperList";
        }
        return "question/questionList";
    }

    /**
     * 根据questionId删除单个试题
     * @param questionId
     * @param paperId
     * @return
     */
    @RequestMapping("/questionDelete")
    public String questionDelete(String questionId,Integer paperId) {
        if(questionId!=null && !questionId.isEmpty()){
            questionService.deleteQuestion(Integer.valueOf(questionId));
        }
        return "redirect:/questionList?paperId="+paperId;
    }

    /**
     * 根据questionId查询单个试题
     * @param mod
     * @param questionId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getQuestionById")
    public String getQuestionById(Model mod,String questionId) {
        if(questionId!=null && !questionId.isEmpty()){
            QuestionDto question = questionService.getQuestionById(Integer.valueOf(questionId));
            mod.addAttribute("question",question);
        }
        return "question/questionShow";
    }

    /**
     * 添加/修改试题中间页面
     * @param mod
     * @param questionId
     * @return
     */
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
            mod.addAttribute("question",new QuestionDto());
            title = "添加试题信息";
        }
        mod.addAttribute("title",title);
        return "question/questionSave";
    }

    /**
     * 添加/修改试题
     * @param question
     * @param attr
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveQuestion", method=RequestMethod.POST)
    public String saveQuestion(@ModelAttribute(value="question") @Valid QuestionDto question, BindingResult result, RedirectAttributes attr, Model model){
        if(result.hasErrors()){
            model.addAttribute("MSG", "出错啦！");
            return "question/error";
        }else{
            model.addAttribute("MSG", "提交成功！");
            Question questionTemp = new Question();
            questionTemp.setType(question.getType());
            questionTemp.setOptionD(question.getOptionD());
            questionTemp.setOptionC(question.getOptionC());
            questionTemp.setOptionB(question.getOptionB());
            questionTemp.setOptionA(question.getOptionA());
            questionTemp.setAnswer(question.getAnswer());
            questionTemp.setJoinTime(new Date());
            questionTemp.setId(question.getQid());
            questionTemp.setSubject(question.getSubject());
            questionTemp.setPaperId(question.getPaperId());
            questionService.saveQuestion(questionTemp);
            if(question.getQid() == null){
                Paper paper = new Paper();
                paper.setId(question.getPaperId());
                attr.addFlashAttribute("paper",paper);
                return "redirect:/paperSavePaper";
            }else {
                return "redirect:/questionList?paperId="+question.getPaperId();
            }
        }
    }


}