package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : najiang
 * create at:  2020-03-26  03:12
 * @description:
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}