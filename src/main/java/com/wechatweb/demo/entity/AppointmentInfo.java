package com.wechatweb.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName(value = "appointment_info")
public class AppointmentInfo {
    @TableId(value = "id")
    private String id;

    //公众号分配微信ID
    private String openid;

    //客户地址
    private String addressid;

    //预约时间
    private String appointmenttime;

    //0-未完成 1-已完成
    private String status;

    //创建时间
    private String createdtime;

    @TableField(exist = false)
    //客户姓名
    private String name;

    @TableField(exist = false)
    //客户电话
    private String phonenumber;

    @TableField(exist = false)
    //客户身份证号
    private String chinaid;

    @TableField(exist = false)
    private String address;
}
