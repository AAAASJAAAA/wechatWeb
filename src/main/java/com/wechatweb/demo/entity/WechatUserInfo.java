package com.wechatweb.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "wechat_user_info")
public class WechatUserInfo{

    //公众号分配微信ID
    @TableId(value = "openid")
    private String openid;

    //微信名
    private String nickname;

    private String sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private String privilege;
}
