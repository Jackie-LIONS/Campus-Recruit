package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Campus;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.CampusService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/campus")
@Api(tags = {"个人在校情况模块"})
public class CampusController {
    @Autowired
    private CampusService campusService;
    @Autowired
    private HttpSession session;

    @PostMapping("/addCampus")
    @ApiOperation(value="添加在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object addCam(@RequestBody Campus campus){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");            //获取session中的User对象
        if (name == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"您好，您的登录已过期，请重新登录");
            return jsonObject;
        }
        Result result = campusService.addCam(campus, name.getUid());
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }
    @PostMapping("/updateCampus")
    @ApiOperation(value="更新在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object updateCam(@RequestBody Campus campus){
        JSONObject jsonObject = new JSONObject();
        Result result = campusService.updateCam(campus);
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @GetMapping("/showCam")
    @ApiOperation(value="查看在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object showCam(){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");            //获取session中的User对象
        if (name == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"您好，您的登录已过期，请重新登录");
            return jsonObject;
        }
        Result result = campusService.showCam(name.getUid());

        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        return jsonObject;
    }
    @GetMapping("/deleteCam")
    @ApiOperation(value="删除在校获奖情况信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object delete(Integer id){
        JSONObject jsonObject = new JSONObject();

        if (id<=0){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"参数有误");
            return jsonObject;
        }
        Result result = campusService.deleteCam(id);
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        return jsonObject;
    }
}
