package com.jc.campusemploydemo.service;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Education;

public interface EducationService {
    public Result updateEdu(Education edu,Integer uid);
    public Result addEdu(Education edu,Integer uid);
    public Result findByUid(Integer uid);
    public Result selectById(Integer id);
}
