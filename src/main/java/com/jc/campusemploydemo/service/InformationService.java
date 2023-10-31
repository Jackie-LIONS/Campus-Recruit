package com.jc.campusemploydemo.service;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Information;
import com.jc.campusemploydemo.domain.User;

public interface InformationService {
    public Result updateInfo(Information info,Integer userId);
    public Information selectById(Integer id);
    public Result findAll(User user);
}
