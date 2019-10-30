package com.wechatweb.demo.controller;

import com.wechatweb.demo.constant.Constant;
import com.wechatweb.demo.entity.AddressInfo;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.entity.admin;
import com.wechatweb.demo.service.AdminService;
import com.wechatweb.demo.service.AppointmentInfoService;
import com.wechatweb.demo.service.WechatUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(admin entity){
        try {
            return adminService.login(entity);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}