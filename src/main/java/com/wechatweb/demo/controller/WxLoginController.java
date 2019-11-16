package com.wechatweb.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.wechatweb.demo.SignUtil;
import com.wechatweb.demo.config.WechatConf;
import com.wechatweb.demo.entity.AccessToken;
import com.wechatweb.demo.entity.MessageTemplate;
import com.wechatweb.demo.entity.Result;
import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.service.WechatUserInfoService;
import com.wechatweb.demo.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;


@Slf4j
@Controller
@RequestMapping("/wxAuth")
public class WxLoginController {
//    final private String appID = "wx08988f2cc05f7950";
//    final private String appsecret = "80ca16ce870d1ff3c661e65c74522bcc";
    @Autowired
    WechatUserInfoService wechatUserInfoService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WechatConf wechatConf;

    @RequestMapping("/tokenCheck")
    @ResponseBody
    public void tokenCheck(HttpServletRequest request,HttpServletResponse response){
        //token验证代码段
        try{
            log.info("请求已到达，开始校验token");
            if (request.getParameter("signature") != null) {
                String signature = request.getParameter("signature");
                String timestamp = request.getParameter("timestamp");
                String nonce = request.getParameter("nonce");
                String echostr = request.getParameter("echostr");
                log.info(signature);
                log.info(timestamp);
                log.info(nonce);
                log.info(echostr);
                if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                    log.info("数据源为微信后台，将echostr[{}]返回！",echostr);
                    response.getOutputStream().println(echostr);
                }
            }
        }catch (IOException e){
            log.error("校验出错");
            e.printStackTrace();
        }
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public Object sendMessage() {
        MessageTemplate messageTemplate = new MessageTemplate();
        // 设置模板id
        messageTemplate.setTemplateId("cXmJiOI1Wf8vMRpkf9tuDhTdpMpzftGN3ZYh89ctHeE");
        // 设置接收用户openId
        messageTemplate.setToUser("oRgAbuNFcOq0FoFYIKzEI6FVY1lc");
        //点击详情跳转的地址
        messageTemplate.setUrl("http://www.baidu.com");

        //设置模板dada参数
        messageTemplate.getData().put("topic", MessageTemplate.initData("您有新的订单了，请及时查看！\n", ""));
        messageTemplate.getData().put("remark", MessageTemplate.initData("请点击查看", ""));
        //调用微信接口，发送模板消息
        Result result = restTemplate.postForObject(String.format(WechatConf.PUSH_MESSAGE_URL, wechatConf.getAccessToken()),
                messageTemplate, Result.class);
        return result;
    }


    @RequestMapping("/login")
    @ResponseBody
    public void wxLogin(HttpServletResponse response) throws IOException {
        //请求获取code的回调地址
        //用线上环境的域名或者用内网穿透，不能用ip
        String callBack = wechatConf.Local_host + "/wxAuth/callBack";
//        请求地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + wechatConf.getAppId() +
                "&redirect_uri=" + URLEncoder.encode(callBack) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(url);
    }

    //	回调方法
    @RequestMapping("/callBack")
    public String wxCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        //获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + wechatConf.getAppId() +
                "&secret=" + wechatConf.getAppsecret() +
                "&code=" + code +
                "&grant_type=authorization_code";

        String result = HttpClientUtil.doGet(url);
        log.info("请求获取access_token:{}", result);
        //返回结果的json对象
        JSONObject resultObject = JSON.parseObject(result);

        //请求获取userInfo
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + resultObject.getString("access_token") +
                "&openid=" + resultObject.getString("openid") +
                "&lang=zh_CN";

        String resultInfo = HttpClientUtil.doGet(infoUrl);

        //此时已获取到userInfo，再根据业务进行处理
        log.info("请求获取userInfo:{}", resultInfo);
        WechatUserInfo user = JSON.parseObject(resultInfo, WechatUserInfo.class);
        if (ObjectUtils.isEmpty(wechatUserInfoService.selectUserInfoById(user.getOpenid()))) {
            wechatUserInfoService.insertUserInfo(user);
        }
        return "redirect:/" + "?openid=" + user.getOpenid();
    }
}