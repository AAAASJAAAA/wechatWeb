package com.wechatweb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
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
        for (AppointmentInfo appointment:result) {
            Long time = Long.valueOf(appointment.getCreatedtime());
            appointment.setCreatedtime(stampToDate(time));
        }
        return result;
    }

    //根据id修改信息
    public Object ordered(String id){
        AppointmentInfo entity = new AppointmentInfo();
        JSONObject jsonObject = new JSONObject();
        entity = mapper.selectById(id);
        if (entity.getStatus().equals(Constant.APPOINTMENT_STATUS_GOTED)){
            entity.setId(id);
            entity.setStatus(Constant.APPOINTMENT_STATUS_NOT_FINISHED);
            mapper.updateById(entity);
            jsonObject.put("msg" , "业务执行成功");
            return jsonObject;
        }
//        jsonObject.put("msg" , "业务执行失败");
        return null;
    }

    //根据id取消预约
    public void cancel(AppointmentInfo entity){
        entity.setStatus(Constant.APPOINTMENT_STATUS_CANCLLED);
        mapper.updateById(entity);
    }

    //根据id
    public void updateById(AppointmentInfo entity){
        mapper.updateById(entity);
    }

    //预约上门完成
    public Object finished(String id){
        AppointmentInfo entity = new AppointmentInfo();
        JSONObject jsonObject = new JSONObject();
        entity = mapper.selectById(id);
        if (entity.getStatus().equals(Constant.APPOINTMENT_STATUS_NOT_FINISHED)){
            entity.setId(id);
            entity.setStatus(Constant.APPOINTMENT_STATUS_FINISHED);
            mapper.updateById(entity);
            jsonObject.put("msg" , "业务执行成功");
            return jsonObject;
        }
//        jsonObject.put("msg" , "业务执行失败");
        return null;
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
        List<AppointmentInfo> result = mapper.selectListByOpenId(entity);
        return result;
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
