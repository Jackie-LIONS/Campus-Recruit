package com.jc.campusemploydemo.controller.backdesk;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.service.EducationService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backstage/edu")
@Api(tags = {"后台学历模块模块"})
public class EducationsController {
    @Autowired
    private EducationService educationService;

    @GetMapping("/showAll")
    public Object showAll(Integer uid){
        JSONObject jsonObject = new JSONObject();
        Result result = educationService.findByUid(uid);
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

}
