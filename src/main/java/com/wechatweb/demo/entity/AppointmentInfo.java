package com.wechatweb.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;

@Data
@TableName(value = "appointment_info")
public class AppointmentInfo {
    @TableId(value = "id")
    private String id;

    //公众号分配微信ID
    private String openid;

    //客户姓名
    private String name;

    //客户地址
    private String addressid;

//    //客户电话
//    private String phone;
//
//    //客户身份证号
//    private String chinaid;

    //预约时间
    private String appointmenttime;

    //0-未完成 1-已完成
    private String status;

    //创建时间
    private Long createdtime;
}
