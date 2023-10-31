package com.jc.campusemploydemo.controller.backdesk;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.UserService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backdesk/user")
@Api(tags = {"后台用户模块"})
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/showAll")
    public Object showAll(){
        JSONObject jsonObject = new JSONObject();
        Result result = userService.showAll();
        if (result.isFlag()){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,result.getMessage());
            jsonObject.put(Const.NAME,result.getData());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @PostMapping("/update")
    public Object updateUser(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        if (user == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户为空");
            return jsonObject;
        }
        Result result = userService.update(user);
        if (result.isFlag()){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }
}
