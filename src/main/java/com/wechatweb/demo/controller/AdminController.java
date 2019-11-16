package com.wechatweb.demo.controller;

import com.wechatweb.demo.entity.admin;
import com.wechatweb.demo.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(admin entity){
        try {
            return adminService.login(entity);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}