package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.campusemploydemo.domain.Campus;

public interface CampusMapper extends BaseMapper<Campus> {
    public Campus selectByUserId(Integer userId);
    public Integer updateByUserIdSelective(Campus campus);
}
