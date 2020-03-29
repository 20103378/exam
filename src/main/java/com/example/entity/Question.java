package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Question implements Serializable {
    private static final long serialVersionUID = -672890975341499495L;
    private Integer id;

    private String answer;

    private Date joinTime;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String subject;

    private String type;

    private Integer paperId;
}