package com.wechatweb.demo.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wechatweb.demo.constant.Constant;
import com.wechatweb.demo.entity.AddressInfo;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.entity.admin;
import com.wechatweb.demo.mapper.AddressInfoMapper;
import com.wechatweb.demo.mapper.AdminMapper;
import com.wechatweb.demo.mapper.AppointmentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    AdminMapper mapper;

    public String login(admin entity) {
        admin admin = mapper.login(entity);
        if (ObjectUtils.isNotEmpty(admin)){
            String code = encrypt(entity.getCode());
            String password = encrypt(entity.getPassword());
            return code+password;
        }
        return "用户名或密码错误";
    }

    private static final String slat = "&%5123***&&%%$$#@";
    public static String encrypt (String dataStr){
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
