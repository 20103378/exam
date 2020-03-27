package com.example.dto;

import com.example.entity.Paper;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QuestionDto implements Serializable {
    private static final long serialVersionUID = -672890975341499495L;
    private Integer qid;

    private String answer;

    private Date joinTime;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String subject;

    private String type;

    private Integer paperid;

    private Paper paper;
}