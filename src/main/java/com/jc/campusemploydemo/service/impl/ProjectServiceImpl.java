package com.jc.campusemploydemo.service.impl;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Project;
import com.jc.campusemploydemo.mapper.ProjectMapper;
import com.jc.campusemploydemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Result addProject(Project project,Integer uid) {
        project.setUserId(uid);
        int insert = projectMapper.insert(project);
        if (insert <= 0){
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }

    @Override
    public Result updateProject(Project project, Integer uid) {
        project.setUserId(uid);
        Integer update = projectMapper.updateByUserIdSelective(project);
        if (update <= 0){
            return new Result(false,"更新失败");
        }
        return new Result(true,"更新成功",project);

    }

    @Override
    public Result deleteProject(Integer id) {
        int flag = projectMapper.deleteById(id);
        if (flag <= 0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @Override
    public Result findAll(Integer uid) {
        List<Project> byUserId = projectMapper.findByUserId(uid);
        if (byUserId.size()>0){
            return new Result(true,"查询成功",byUserId);
        }
        return new Result(false,"查询失败");
    }
}
