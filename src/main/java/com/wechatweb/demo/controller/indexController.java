package com.wechatweb.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
    @RequestMapping("/floor")
    public String clean(){
        return "home/floor";
    }

    //地址
    @RequestMapping("/address")
    public String address(){
        return "home/address";
    }

    //添加地址
    @RequestMapping("/address_add")
    public String address_add(){
        return "home/address_add";
    }

    //订单
    @RequestMapping("/myorder")
    public String order(){
        return "home/myorder";
    }

    //我的页面跳转
    @RequestMapping("/index_center")
    public String mine(){
        return "home/index_center";
    }
}
