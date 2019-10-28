package com.wechatweb.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechatweb.demo.entity.AddressInfo;
import com.wechatweb.demo.entity.AppointmentInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressInfoMapper extends BaseMapper<AddressInfo> {

    List<AddressInfo> findAddressInfoByOpenId(String openid);

    AddressInfo findAddressInfo(AddressInfo entity);
}
