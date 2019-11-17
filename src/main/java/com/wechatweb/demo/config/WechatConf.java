package com.wechatweb.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatConf {
    // 获取accessToken的接口
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static final String Local_host = "http://sunshijie1998.eicp.net";

    // 发送消息的接口
    public static final String PUSH_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    private String appId;

    private String appsecret;

    // 发送消息的接口的访问凭证
    private String accessToken;
}
