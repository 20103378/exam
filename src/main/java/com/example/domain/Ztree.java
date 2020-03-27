package com.example.domain;

import lombok.Data;

import java.util.List;

/**
 * @author : najiang
 * create at:  2020-03-20  00:50
 * @description:
 */
@Data
public class Ztree {
    private String text;
    private String href;
    private List<Ztree> nodes;
}