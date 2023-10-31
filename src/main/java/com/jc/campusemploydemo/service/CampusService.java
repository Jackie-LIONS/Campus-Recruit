package com.jc.campusemploydemo.service;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Campus;

public interface CampusService {
    public Result updateCam(Campus cam);
    public  Result showCam(Integer uid);
    public Result addCam(Campus campus,Integer uid);
    public Result deleteCam(Integer id);
}
