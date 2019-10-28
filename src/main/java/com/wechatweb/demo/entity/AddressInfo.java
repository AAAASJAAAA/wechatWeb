package com.wechatweb.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "address_info")
public class AddressInfo {
    @TableId(value = "id")
    private String id;

    //公众号分配微信ID
    private String openid;

    //客户姓名
    private String name;

    //客户地址
    private String address;

    //客户电话
    private String phonenumber;

    //客户身份证号
    private String chinaid;
}
