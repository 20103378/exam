package com.example.domain;

import lombok.Data;

/**
 * Created by Administrator on 2018/7/1.
 */
@Data
public class PageBean {

    private int page; // 第几页
    private int pageSize; // 每页记录数


    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }


}
