package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Information;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.InformationService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/info")
@Api(tags = {"个人信息模块"})
public class InformationController {
    @Autowired
    private InformationService informationService;
    @Autowired
    private HttpSession session;


    @PostMapping("/update")
    @ApiOperation(value="更新基本信息",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object updateInfo(@RequestBody Information info){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");
        if (name == null){
           jsonObject.put(Const.CODE,0);
           jsonObject.put(Const.MSG,"您好，您的登录已过期，请重新登录");
           return jsonObject;
        }
        Result result = informationService.updateInfo(info, name.getUid());
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @PostMapping("/uploadPic")
    @ApiOperation(value="上传照片",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object uploadUserPic(@RequestParam("photo") MultipartFile upFile) {
        JSONObject jsonObject = new JSONObject();
//        上传失败
        if (upFile.isEmpty()) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "文件上传失败");
            return jsonObject;
        }
        //        文件名 = 当前时间到毫秒+原来的文件文件名
        String fileName = System.currentTimeMillis() + upFile.getOriginalFilename();
//        文件路径
        String filePath = "D:\\DataStorage\\IdeaData\\campusemploydemo\\campusemploydemo\\src\\main\\resources\\static\\img\\";
        //        如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //        实际的文件地址(前端上传之后的地址)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //        存储到数据库里的相对文件地址
        String storePath = "/img/userPic" + fileName;
        try {
            upFile.transferTo(dest);            // 用来把 MultipartFile 转换换成 File
            User user = (User) session.getAttribute("name");
            Information information = informationService.selectById(user.getUid());


            information.setPhoto(storePath);
            Result flag = informationService.updateInfo(information, user.getUid());
            if (flag.isFlag()) {
                jsonObject.put(Const.CODE, 1);
                jsonObject.put(Const.MSG, "上传成功");
                jsonObject.put("pic", storePath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "上传失败" + ": " + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }
    @PostMapping("/uploadResume")
    @ApiOperation(value="上传附件",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object uploadResumeFile(@RequestParam("file") MultipartFile upFile) {
        JSONObject jsonObject = new JSONObject();
//        上传失败
        if (upFile.isEmpty()) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "文件上传失败");
            return jsonObject;
        }
        //        文件名 = 当前时间到毫秒+原来的文件文件名
        String fileName = System.currentTimeMillis() + upFile.getOriginalFilename();
//        文件路径
        String filePath = "D:\\DataStorage\\IdeaData\\campusemploydemo\\campusemploydemo\\src\\main\\resources\\static\\resume\\";
        //        如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //        实际的文件地址(前端上传之后的地址)
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //        存储到数据库里的相对文件地址
        String storePath = "/img/userPic" + fileName;
        try {
            upFile.transferTo(dest);            // 用来把 MultipartFile 转换换成 File
            User user = (User) session.getAttribute("name");
            Information information = informationService.selectById(user.getUid());

            information.setFiles(storePath);
            Result flag = informationService.updateInfo(information, user.getUid());
            if (flag.isFlag()) {
                jsonObject.put(Const.CODE, 1);
                jsonObject.put(Const.MSG, "上传成功");
                jsonObject.put("pic", storePath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "上传失败" + ": " + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }

    @GetMapping("/showAll")
    @ApiOperation(value="展示基本信息",notes = "如果返回只有一个数字，则是roleId")
    public Object showAll(){
        User user = (User) session.getAttribute("name");
        System.out.println("打印个人："+user);

        Result result = informationService.findAll(user);
        JSONObject jsonObject = new JSONObject();
            jsonObject.put(Const.CODE, 1);
            jsonObject.put(Const.MSG, result.getMessage());
            jsonObject.put(Const.NAME, result.getData());
            return jsonObject;
    }

}

