package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.campusemploydemo.domain.Campus;
import com.jc.campusemploydemo.domain.Project;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project> {
    public Project selectByUserId(Integer userId);
    public List<Project> findByUserId(Integer userId);
    public Integer updateByUserIdSelective(Project project);
}
