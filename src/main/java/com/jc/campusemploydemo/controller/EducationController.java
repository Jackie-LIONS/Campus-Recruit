package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Education;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.EducationService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/edu")
@Api(tags = {"个人学历模块"})
public class EducationController {
    @Autowired
    private EducationService edu;
    @Autowired
    private HttpSession session;

    @PostMapping("/updateById")
    @ApiOperation(value="根据主键更新学历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object updateEdu(@RequestBody Education education){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");            //获取session中的User对象
        if (name == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"您好，您的登录已过期，请重新登录");
            return jsonObject;
        }
        Result result = edu.updateEdu(education,name.getUid());
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }
    
    @PostMapping("/add")
    @ApiOperation(value="添加学历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object addEdu(@RequestBody Education education){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");            //获取session中的User对象
        if (name == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"您好，您的登录已过期，请重新登录");
            return jsonObject;
        }
        Result result = edu.addEdu(education, name.getUid());
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @GetMapping("/showAll")
    @ApiOperation(value="查看个人学历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object showAll(){
        JSONObject jsonObject = new JSONObject();
        User user = (User)session.getAttribute(Const.NAME);
        Result result = edu.findByUid(user.getUid());
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,result.getMessage());
            jsonObject.put(Const.NAME,result.getData());
            return jsonObject;
    }
}
