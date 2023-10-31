package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Project;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.ProjectService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/project")
@Api(tags = {"项目经历模块"})
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private HttpSession session;

    @PostMapping("/add")
    @ApiOperation(value="添加项目经历",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object addProject(@RequestBody Project project){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        if (name == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"您好，您的登录已过期，请重新登录");
            return jsonObject;
        }
        Result result = projectService.addProject(project, name.getUid());
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }
    @PostMapping("/update")
    @ApiOperation(value="更改项目经历信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object updatePro(@RequestBody Project project){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        Result result = projectService.updateProject(project, name.getUid());
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }
    @GetMapping("/delete")
    @ApiOperation(value="删除项目经历信息,根据项目id删除",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object delete(@RequestParam("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        Result result = projectService.deleteProject(id);
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
    @ApiOperation(value="展示项目经历",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object showAll(){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        Result result = projectService.findAll(name.getUid());
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.NAME,result.getData());
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
    }

}
