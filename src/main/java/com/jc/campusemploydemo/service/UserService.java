package com.jc.campusemploydemo.service;

import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.bean.Result;
import org.springframework.web.bind.annotation.RestController;

public interface UserService {
    public Result register(User user);
    public Result login(String name,String password);

    public Result showAll();
    public Result update(User user);
//    管理员管理用户
    public Result ManagingUsers();
}
