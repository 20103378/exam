package com.example.demo;

import com.example.dto.PaperDto;
import com.example.dto.QuestionDto;
import com.example.entity.Paper;
import com.example.service.PaperService;
import com.example.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * 试卷管理
 * @author : najiang
 * create at:  2020-03-21  13:55
 * @description:
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;

    /**
     * 所有试卷列表
     * @param map
     * @param model
     * @return
     */
    @RequestMapping("/paperList")
    public String paperList(@RequestParam Map<String, String> map,Model model) {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "0");
        }
        // 查询用户列表 及设置分页信息
        List<Paper> paperList = paperService.getPaperList();
        // 参数为当前页码、每页显示条数、查询的列表集合
        Pager pageInfo = new Pager(Integer.valueOf(map.get("currentPage")), 6, paperList);
        model.addAttribute("paperList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "paper/paperList";
    }

    /**
     * 添加或修改试卷中间页面
     * @param mod
     * @param paperId
     * @return
     */
    @RequestMapping("/paperPreSave")
    public String paperPreSave(Model mod, String paperId) {
        if (paperId != null && !paperId.isEmpty()) {
            PaperDto paper = paperService.getPaperById(Integer.valueOf(paperId));
            mod.addAttribute("paper", paper);
            mod.addAttribute("title", "修改试卷");
            return "paper/paperUpdate";
        } else {
            mod.addAttribute("title", "添加试卷");
            mod.addAttribute("paper", new Paper());
            return "paper/paperUpdate";
        }
    }

    /**
     * 添加或修改试卷
     * @param paper
     * @param mod
     * @return
     */
    @RequestMapping("/paperSavePaper")
    public String paperSavePaper(@ModelAttribute(value="paper") Paper paper, Model mod) {
        System.out.println(paper);
        if(paperService.getByPaperName(paper.getPaperName()) != null){
            return "redirect:/paperList";
        }else {
                paper.setJoinDate(new Date(System.currentTimeMillis()));
                paperService.savePaper(paper);
                mod.addAttribute("title", "添加题目");
                QuestionDto questionDto = new QuestionDto();
                questionDto.setPaperId(paper.getId());
                mod.addAttribute("question",questionDto);
                return "question/questionSave";
        }
    }
}