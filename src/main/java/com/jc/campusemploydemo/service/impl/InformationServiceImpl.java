package com.jc.campusemploydemo.service.impl;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Information;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.mapper.InformationMapper;
import com.jc.campusemploydemo.mapper.UserMapper;
import com.jc.campusemploydemo.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result updateInfo(Information info,Integer userId) {
        info.setUserId(userId);                //设置info的uid为userId
        Information information = informationMapper.selectByUserId(userId);
        //新增个人信息
        if(information == null){
            int result = informationMapper.insert(info);
            if(result <= 0){
                return new Result(false,"新增个人信息失败");
            }
            return new Result(true,"新增成功",info);
        }
//        修改个人信息
        info.setUpdateTime(new Date());
        Integer update = informationMapper.updateByUserIdSelective(info);
        if (update <= 0){
            return new Result(false,"更新失败");
        }
        return new Result(true,"更新成功",info);
    }
    @Override
    public Information selectById(Integer id){
        return informationMapper.selectByUserId(id);
    }
    @Override
    public Result findAll(User user) {
        Information information = informationMapper.selectByUserId(user.getUid());
        if (information == null){
            Information information1 = new Information();
            information1.setRid(user.getRoleId());
            information1.setUsername(user.getUsername());
            information1.setUserId(user.getUid());
            information1.setEmail(user.getEmail());
            informationMapper.insert(information1);
            return new Result(true,"查询成功",information1);
        }
        System.out.println("打印个人信息：" + information);
//        User user = userMapper.selectById(uid);
        information.setRid(user.getRoleId());
        information.setUsername(user.getUsername());
        return new Result(true,"查询成功",information);
    }
}
