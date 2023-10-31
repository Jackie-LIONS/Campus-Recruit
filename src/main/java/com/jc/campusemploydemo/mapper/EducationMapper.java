package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.campusemploydemo.domain.Education;

import java.util.List;

public interface EducationMapper extends BaseMapper<Education> {
    public List<Education> selectByUserId1(Integer userId);
    public Education selectByUserId(Integer userId);
    public Integer updateByUserIdSelective(Education education);

}
