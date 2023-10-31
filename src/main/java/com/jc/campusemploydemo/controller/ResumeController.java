package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Resume;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.ResumeService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/resume")
@Api(tags = {"投简历模块"})
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private HttpSession session;

    @PostMapping("/add")
    @ApiOperation(value="投递简历",notes = "code= 0 : 注册失败  code= 1: 注册成功，前端根据接口code值来判断跳转页面")
    public Object addResume(@RequestBody Resume resume){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        boolean flag = resumeService.add(resume,name.getUid());
        if (flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"投递成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"投递失败");
        return jsonObject;
    }
    @PostMapping("/delete")
    @ApiOperation(value="撤销投递简历",notes = "code= 0 : 注册失败  code= 1: 注册成功，前端根据接口code值来判断跳转页面")
    public Object deleteResume(Integer id){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        resumeService.delete(name.getUid(),id);
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"撤销成功");
            return jsonObject;
    }
    @GetMapping("show")
    @ApiOperation(value="展示已经投递简历信息",notes = "code= 0 : 注册失败  code= 1: 注册成功，前端根据接口code值来判断跳转页面")
    public Object show(){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        Result result = resumeService.showByUid(name.getUid());
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.NAME,result.getData());
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;

    }
}
