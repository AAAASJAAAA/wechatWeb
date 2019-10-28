package com.wechatweb.demo.service;

import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.mapper.WechatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatUserInfoService{
    @Autowired
    WechatUserMapper mapper;

    public int listCount() {
        return mapper.listCount();
    }

    public void insertUserInfo(WechatUserInfo entity){
        mapper.insert(entity);
    }

    public WechatUserInfo selectUserInfoById(String id) {
        return mapper.selectById(id);
    }

    //查找用户信息
    public WechatUserInfo findUserInfo(WechatUserInfo entity) {
        String openid = entity.getOpenid();
        return mapper.findUserInfo(openid);
    }
}
