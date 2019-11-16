package com.wechatweb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wechatweb.demo.entity.admin;
import com.wechatweb.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper mapper;

    public Object login(admin entity) {
        admin admin = mapper.login(entity);
        JSONObject jsonObject = new JSONObject();
        if (ObjectUtils.isNotEmpty(admin)){
            String code = encrypt(entity.getCode());
            String password = encrypt(entity.getPassword());
            jsonObject.put("msg","登录成功");
            jsonObject.put("token",code+password);
            jsonObject.put("code",entity.getCode());
        }
        else {
            jsonObject.put("msg","用户名或密码错误");
        }
        return jsonObject;
    }

    public admin findByCode(String code) {
        admin admin = mapper.selectById(code);
        if (ObjectUtils.isNotEmpty(admin)){
            return admin;
        }
        return null;
    }


    public String encrypt (String dataStr){
        String slat = "&%5123***&&%%$$#@";
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
