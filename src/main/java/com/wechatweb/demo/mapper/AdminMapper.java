package com.wechatweb.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wechatweb.demo.entity.admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper extends BaseMapper<admin> {
     admin login(admin entity);
}
