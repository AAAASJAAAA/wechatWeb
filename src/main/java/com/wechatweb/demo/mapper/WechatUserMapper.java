package com.wechatweb.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechatweb.demo.entity.WechatUserInfo;
import org.springframework.stereotype.Component;

@Component
public interface WechatUserMapper extends BaseMapper<WechatUserInfo> {
    int listCount();

    WechatUserInfo findUserInfo(String openid);
}
