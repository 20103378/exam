package com.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Paper {
    private Integer id;

    private Date joinDate;

    private String paperName;
}