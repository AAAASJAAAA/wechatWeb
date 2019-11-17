package com.wechatweb.demo.service;

import com.wechatweb.demo.config.WechatConf;
import com.wechatweb.demo.entity.MessageTemplate;
import com.wechatweb.demo.entity.Result;
import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.mapper.WechatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WechatUserInfoService{
    @Autowired
    WechatUserMapper mapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WechatConf wechatConf;

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

    public Object sendMessage(String tempID,String openId,String url) {
        MessageTemplate messageTemplate = new MessageTemplate();
        // 设置模板id
        messageTemplate.setTemplateId(tempID);
        // 设置接收用户openId
        messageTemplate.setToUser(openId);
        //点击详情跳转的地址
        messageTemplate.setUrl(url);

        //设置模板dada参数
        messageTemplate.getData().put("topic", MessageTemplate.initData("您有新的订单了，请及时查看！\n", ""));
        messageTemplate.getData().put("remark", MessageTemplate.initData("请点击查看", ""));
        //调用微信接口，发送模板消息
        Result result = restTemplate.postForObject(String.format(WechatConf.PUSH_MESSAGE_URL, wechatConf.getAccessToken()),
                messageTemplate, Result.class);
        return result;
    }
}
