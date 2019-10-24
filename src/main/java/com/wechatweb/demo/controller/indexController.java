package com.wechatweb.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mysql.cj.util.StringUtils;
import com.wechatweb.demo.SignUtil;
import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.service.WechatUserInfoService;
import com.wechatweb.demo.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@Slf4j
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(){
        return "home/index";
    }


}
