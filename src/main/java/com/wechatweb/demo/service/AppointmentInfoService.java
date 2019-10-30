package com.wechatweb.demo.service;

import com.wechatweb.demo.constant.Constant;
import com.wechatweb.demo.entity.AddressInfo;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.mapper.AddressInfoMapper;
import com.wechatweb.demo.mapper.AppointmentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentInfoService {
    @Autowired
    AppointmentInfoMapper mapper;

    @Autowired
    AddressInfoMapper addressInfoMapper;

    public void insertAppointmentInfo(AppointmentInfo entity){
        mapper.insert(entity);
    }

    public List findByOpenId(String open_id){
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        appointmentInfo.setOpenid(open_id);
        List<AppointmentInfo> result = mapper.selectListByOpenId(appointmentInfo);
//        for (AppointmentInfo appointment:result) {
//            appointment.setCreatedtime();stampToDate(appointment.getCreatedtime());
//        }
        return result;
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

    //预约上门完成
    public void finished(AppointmentInfo entity){
        entity.setStatus(Constant.APPOINTMENT_STATUS_FINISHED);
        mapper.updateById(entity);
    }

    //新增地址
    public void addAddress(AddressInfo entity){
        UUID id = UUID.randomUUID();
        entity.setId(id.toString().replace("-",""));
        addressInfoMapper.insert(entity);
    }

    //查找我的地址
    public List findAddressInfoByOpenId(AddressInfo entity){
        return addressInfoMapper.findAddressInfoByOpenId(entity.getOpenid());
    }
    //查找我的订单
    public List findMyAppointment(AppointmentInfo entity) {
        return mapper.selectListByOpenId(entity);
    }

    //查找地址详情
    public AddressInfo findAddressInfo(AddressInfo entity) {
        return addressInfoMapper.findAddressInfo(entity);
    }
    //查找全部订单 status
    public List<AppointmentInfo> findALLOrders(AppointmentInfo entity) {
        return mapper.selectListByOpenId(entity);
    }

    public static String stampToDate(Long s){

        String res;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long lt = new Long(s);

        Date date = new Date(lt);

        res = simpleDateFormat.format(date);

        return res;

    }
}
