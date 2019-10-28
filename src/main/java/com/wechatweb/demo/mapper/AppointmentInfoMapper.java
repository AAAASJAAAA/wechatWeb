package com.wechatweb.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.entity.WechatUserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppointmentInfoMapper extends BaseMapper<AppointmentInfo> {
    List<AppointmentInfo> selectListByOpenId(AppointmentInfo entity);
}
