package com.jc.campusemploydemo.service;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Project;

public interface ProjectService {
    public Result addProject(Project project,Integer uid);
    public Result updateProject(Project project,Integer uid);
    public Result deleteProject(Integer id);
    public Result findAll(Integer uid);
}
