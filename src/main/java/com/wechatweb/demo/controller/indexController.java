package com.wechatweb.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/")
public class indexController {

    //主页跳转
    @RequestMapping("")
    public String home(){
        return "home/index";
    }

    //登记跳转
    @RequestMapping("/cleaning_index")
    public String clean(){
        return "home/cleaning_index";
    }

    //我的页面跳转
    @RequestMapping("/index_center")
    public String mine(){
        return "home/index_center";
    }
}
