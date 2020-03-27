package com.example.dto;

import com.example.entity.Question;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PaperDto implements Serializable{

    private static final long serialVersionUID = -672890975341499494L;

    private Integer id;

    private Date joinDate;

    private String paperName;

    private List<Question> questions = new ArrayList<>();

}