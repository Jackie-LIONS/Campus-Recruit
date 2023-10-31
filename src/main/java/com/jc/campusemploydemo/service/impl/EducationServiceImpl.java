package com.jc.campusemploydemo.service.impl;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Education;
import com.jc.campusemploydemo.mapper.EducationMapper;
import com.jc.campusemploydemo.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationMapper educationMapper;

    @Override
    public Result updateEdu(Education edu, Integer uid) {
        edu.setUserId(uid);
//        已查到本人，修改个人学历
//        Integer update = educationMapper.updateByUserIdSelective(edu);
        int update = educationMapper.updateById(edu);
        if (update <= 0){
            return new Result(false,"更新失败");
        }
        return new Result(true,"更新成功",edu);
    }
//          添加学历
    @Override
    public Result addEdu(Education edu, Integer uid) {
        if (edu != null){
            edu.setUserId(uid);
            educationMapper.insert(edu);
            return new Result(true,"个人学历添加成功");
        }
        return new Result(false,"新增个人学历失败");
    }

    @Override
    public Result findByUid(Integer uid) {
        List<Education> list = educationMapper.selectByUserId1(uid);
        if (list.size()>0){
            return new Result(true,"查询成功",list);
        }
        return new Result(false,"查无结果");
    }

    /**
     * 根据主键查询个人学历
     * @param id
     * @return
     */
    @Override
    public Result selectById(Integer id) {
        if (id <= 0 ){
            return new Result(false,"id有误");
        }
        Education education = educationMapper.selectById(id);
        if (education == null){
            return new Result(false,"查无此人");
        }
        return new Result(true,"查询成功",education);
    }

}
