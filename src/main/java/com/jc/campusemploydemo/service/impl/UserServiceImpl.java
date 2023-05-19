package com.jc.campusemploydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.mapper.UserMapper;
import com.jc.campusemploydemo.service.UserService;
import com.jc.campusemploydemo.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Result register(User user) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("username",user.getUsername());
        List<User> list1 = userMapper.selectList(queryWrapper1);
        if(list1.size()>0){
            return  new Result(false,"该用户名已存在");
        }
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("email",user.getUsername());
        List<User> list2 = userMapper.selectList(queryWrapper2);
        if (list2.size()>0){
            return new Result(false,"该邮箱已存在");
        }
        //注册成功
        user.setPassword(Md5Utils.md5(user.getPassword()));
        userMapper.insert(user);
        return new Result(true,"注册成功");
    }

    /**
     * 使用用户名字或者邮箱登录
     * @param name
     * @param password
     * @return
     */
    @Override
    public Result login(String name, String password) {
        User user = null;
        if(user == null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",name);
            user = userMapper.selectOne(queryWrapper);
        }
        if(user == null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",name);
            user = userMapper.selectOne(queryWrapper);
        }
        if(user == null){           //没有查到用户
            return  new Result(false,"用户名或者邮箱不存在");
        }
//        验证密码
        String md5 = Md5Utils.md5(password);
        boolean flag = user.getPassword().equalsIgnoreCase(md5);
        if(!flag){
            return  new Result(false,"密码错误");
        }
        return new Result(true,"登录成功",user);
    }

    @Override
    public Result showAll() {
        List<User> users = userMapper.selectList(null);
        if (users == null||users.size() == 0){
            return new Result(false,"列表无用户");
        }
        return new Result(true,"查询成功",users);
    }

    @Override
    public Result update(User user) {
        int i = userMapper.updateById(user);
        if (i>0){
            return new Result(true,"更新成功");
        }
        return new Result(false,"更新失败");
    }

    @Override
    public Result ManagingUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("role_id",2);
        List<User> users = userMapper.selectList(queryWrapper);
        return new Result(true,"查询成功",users);
    }

}
