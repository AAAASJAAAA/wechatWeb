package com.wechatweb.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin")
public class admin {
    @TableId(value = "id")
    private String id;

    private String code;

    private String password;
}
