package com.example.dto;

import com.example.entity.Paper;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class QuestionDto implements Serializable {
    private static final long serialVersionUID = -672890975341499495L;
    private Integer qid;
    @NotEmpty(message="答案不能为空！")
    private String answer;

    private Date joinTime;

    @NotEmpty(message="选项一不能为空！")
    private String optionA;
    @NotEmpty(message="选项二不能为空！")
    private String optionB;
    @NotEmpty(message="选项三不能为空！")
    private String optionC;
    @NotEmpty(message="选项四不能为空！")
    private String optionD;

    @NotEmpty(message="考试题目不能为空！")
    private String subject;
    @NotEmpty(message="请选择题目类型！")
    private String type;

    private Integer paperId;

    private Paper paper;
}