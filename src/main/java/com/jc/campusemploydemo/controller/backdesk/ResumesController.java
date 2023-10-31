package com.jc.campusemploydemo.controller.backdesk;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.ResumeService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/backstage/resume")
@Api(tags = {"后台查看申请职位模块"})
public class ResumesController {
    @Autowired
    ResumeService resumeService;
    @Autowired
    private HttpSession session;

    @GetMapping("/showAll")
    public Object showAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5")Integer size){
        Result result = resumeService.showAll(page,size);
        JSONObject jsonObject = new JSONObject();
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

    @GetMapping("/showMyCompany")
    public Object showMyCompany(){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");            //获取session中的User对象
        Result result = resumeService.showMyCompany(name.getUid());
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        return jsonObject;
    }
}

