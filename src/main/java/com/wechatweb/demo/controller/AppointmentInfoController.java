package com.wechatweb.demo.controller;

import com.wechatweb.demo.constant.Constant;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.service.AppointmentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Slf4j
@Controller
@RequestMapping("/apponitment")
public class AppointmentInfoController {
    @Autowired
    AppointmentInfoService appointmentInfoService;


    //根据openid查询预约列表
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public List findMyAppointmentList(String openid) {
        try {
            return appointmentInfoService.findByOpenId(openid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    //插入预约信息
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertAppointmentInfo(AppointmentInfo entity) {
        try {
            UUID id = UUID.randomUUID();
            entity.setId(id.toString().replace("-",""));
            entity.setCreated_time(Calendar.getInstance().getTimeInMillis());
            entity.setStatus(Constant.APPOINTMENT_STATUS_NOT_FINISHED);
            appointmentInfoService.insertAppointmentInfo(entity);
            return "预约成功，客服将于10分钟之内联系您，谢谢配合。";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "预约失败，请联系管理员！";
        }
    }

    //更改预约信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateAppointmentInfo(AppointmentInfo entity) {
        try {
            appointmentInfoService.updateById(entity);
            return "修改成功";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "修改失败，请联系管理员！";
        }
    }

    //取消预约
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public String cancelAppointment(AppointmentInfo entity) {
        try {
            appointmentInfoService.cancel(entity);
            return "取消成功";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "取消失败，请联系管理员！";
        }
    }
}