package com.wechatweb.demo.task;

import com.wechatweb.demo.config.WechatConf;
import com.wechatweb.demo.entity.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageTask {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WechatConf wechatConf;
    /**
     * initialDelay: 初始化2s后执行第一次
     * fixedDelay:微信默认Token过期时间为7200s,这里定时7100s执行一次定时任务
     */
    @Scheduled(initialDelay = 2000, fixedDelay = 7100 * 1000)
    public void refreshToken() {
        // 请求方式: GET
        // URL：https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        // 发起一个get请求，返回的数据json文本，使用json工具将json文本直接转化为Class<?>
        AccessToken accessToken = restTemplate.getForObject(
                String.format(WechatConf.GET_TOKEN_URL, wechatConf.getAppId(), wechatConf.getAppsecret()),
                AccessToken.class);
        // 将获取的accessToken注入wechatConf
        wechatConf.setAccessToken(accessToken.getAccessToken());
    }
}