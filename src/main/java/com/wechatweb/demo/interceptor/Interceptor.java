package com.wechatweb.demo.interceptor;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wechatweb.demo.entity.admin;
import com.wechatweb.demo.mapper.AdminMapper;
import com.wechatweb.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            Map map = new HashMap();
            for(Cookie cookie : cookies){
                map.put(cookie.getName(),cookie.getValue());
            }
            if(!map.get("code").equals("") && !map.get("token").equals("")){
                admin admin = adminService.findByCode(map.get("code").toString());
                if (ObjectUtils.isNotEmpty(admin)){
                    String token = adminService.encrypt(admin.getCode()) + adminService.encrypt(admin.getCode());
                    System.out.println("正常用户");
                }
                else {
                    System.out.println("非法用户");
                    response.sendRedirect("/admin/login");
                    return false;
                }
            }
        }
        else {
            System.out.println("非法进入");
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
