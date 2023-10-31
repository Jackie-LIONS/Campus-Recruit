package com.jc.campusemploydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Campus;
import com.jc.campusemploydemo.domain.Education;
import com.jc.campusemploydemo.mapper.CampusMapper;
import com.jc.campusemploydemo.mapper.EducationMapper;
import com.jc.campusemploydemo.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusMapper campusMapper;

    @Override
    public Result updateCam(Campus cam) {
        if (cam != null){
            campusMapper.updateById(cam);
            return new Result(true,"更新成功");
        }
        return new Result(false,"更新失败");
    }

    /**
     * 查看在校情况
     * @return
     */
    @Override
    public Result showCam(Integer uid) {
        QueryWrapper<Campus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        List<Campus> campuses = campusMapper.selectList(wrapper);
        if (campuses.isEmpty()){
            return new Result(false,"查询为空");
        }
        return new Result(true,"查询成功",campuses);
    }

    @Override
    public Result addCam(Campus campus, Integer uid) {
        if (campus != null){
            campus.setUserId(uid);
            campusMapper.insert(campus);
            return new Result(true,"添加成功");
        }
        return new Result(false,"信息为空");
    }

    @Override
    public Result deleteCam(Integer id) {
        int i = campusMapper.deleteById(id);
        if (i<= 0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }
}
