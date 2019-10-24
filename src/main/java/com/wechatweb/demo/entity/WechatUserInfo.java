package com.wechatweb.demo.entity;

import lombok.Data;
import javax.persistence.Table;

@Data
@Table(name = "WechatUserInfo")
public class WechatUserInfo {
    //公众号分配微信ID
    private String openid;

    //微信名
    private String nikename;

    private String sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private String privilege;
}
