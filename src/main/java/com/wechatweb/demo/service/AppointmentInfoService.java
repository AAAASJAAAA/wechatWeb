package com.wechatweb.demo.service;

import com.wechatweb.demo.constant.Constant;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.mapper.AppointmentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentInfoService {
    @Autowired
    AppointmentInfoMapper mapper;

    public void insertAppointmentInfo(AppointmentInfo entity){
        mapper.insert(entity);
    }

    public List findByOpenId(String open_id){
        return mapper.selectListByOpenId(open_id);
    }

    //根据id修改信息
    public void updateById(AppointmentInfo entity){
        mapper.updateById(entity);
    }

    //根据id取消预约
    public void cancel(AppointmentInfo entity){
        entity.setStatus(Constant.APPOINTMENT_STATUS_CANCLLED);
        mapper.updateById(entity);
    }
}
