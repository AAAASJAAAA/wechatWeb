package com.wechatweb.demo.controller;

import com.wechatweb.demo.constant.Constant;
import com.wechatweb.demo.entity.AddressInfo;
import com.wechatweb.demo.entity.AppointmentInfo;
import com.wechatweb.demo.entity.WechatUserInfo;
import com.wechatweb.demo.service.AppointmentInfoService;
import com.wechatweb.demo.service.WechatUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/apponitment")
public class AppointmentInfoController {
    @Autowired
    AppointmentInfoService appointmentInfoService;

    @Autowired
    WechatUserInfoService wechatUserInfoService;
    //根据openid查询预约列表
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public List findMyAppointmentList(String openid) {
        try {
            List res = appointmentInfoService.findByOpenId(openid);
            return res;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    //插入预约信息
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertAppointmentInfo(AppointmentInfo entity) {
        try {
            UUID id = UUID.randomUUID();
            entity.setId(id.toString().replace("-",""));
            entity.setCreatedtime(Calendar.getInstance().getTimeInMillis());
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
    public String cancelAppointment(AppointmentInfo entity) {
        try {
            appointmentInfoService.cancel(entity);
            return "取消成功";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "取消失败，请联系管理员！";
        }
    }

    //预约完成
    @RequestMapping(value = "/finished", method = RequestMethod.POST)
    public String finished(AppointmentInfo entity) {
        try {
            appointmentInfoService.finished(entity);
            return "执行成功";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "执行成功，请联系管理员！";
        }
    }

    //新增地址
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(AddressInfo entity) {
        try {
            appointmentInfoService.addAddress(entity);
            return "新增地址成功";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "新增地址失败，请联系管理员！";
        }
    }

    //查找我的地址
    @RequestMapping(value = "/findMyAddress", method = RequestMethod.POST)
    public List findMyAddress(AddressInfo entity) {
        try {
            List result = appointmentInfoService.findAddressInfoByOpenId(entity);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //查找我的订单
    @RequestMapping(value = "/findMyAppointment", method = RequestMethod.POST)
    public List findMyAppointment(AppointmentInfo entity) {
        try {
            List result = appointmentInfoService.findMyAppointment(entity);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //用户信息
    @RequestMapping(value = "/findUserInfo", method = RequestMethod.POST)
    public WechatUserInfo findUserInfo(WechatUserInfo entity) {
        try {
            WechatUserInfo result = wechatUserInfoService.findUserInfo(entity);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return new WechatUserInfo();
        }
    }

    //根据addressId和openid查地址详情
    @RequestMapping(value = "/findAddressInfo", method = RequestMethod.POST)
    public AddressInfo findAddressInfo(AddressInfo entity) {
        try {
            AddressInfo result = appointmentInfoService.findAddressInfo(entity);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}