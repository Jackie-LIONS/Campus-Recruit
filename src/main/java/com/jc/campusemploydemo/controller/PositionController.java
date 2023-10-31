package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Positions;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.CollectionService;
import com.jc.campusemploydemo.service.PositionsService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/pos")
@Api(tags = {"职位模块"})
public class PositionController {
    @Autowired
    private PositionsService positionsService;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private HttpSession session;

    @GetMapping("/showOnSocial")
    @ApiOperation(value="查看校外岗位",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面，flag为1是校外岗位")
    public Object showOnSocial(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "5") Integer size){
        JSONObject jsonObject = new JSONObject();
        Page<Positions> detailPage = positionsService.showAllOnSocial(page, size);
        if(detailPage.getTotal()>0){
            jsonObject.put(Const.CODE,"1");
            jsonObject.put(Const.MSG,"查看成功");
            jsonObject.put(Const.NAME,detailPage);
            return jsonObject;
        }
        jsonObject.put(Const.CODE,"0");
        jsonObject.put(Const.MSG,"页面为空");
        return jsonObject;
    }
    @GetMapping("/showOnCampus")
    @ApiOperation(value="查看校外岗位",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面，flag为2是校内岗位")
    public Object showOnCampus(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "5") Integer size){
        JSONObject jsonObject = new JSONObject();
        Page<Positions> detailPage = positionsService.showAllOnCampus(page, size);
        if(detailPage.getTotal()>0){
            jsonObject.put(Const.CODE,"1");
            jsonObject.put(Const.MSG,"查看成功");
            jsonObject.put(Const.NAME,detailPage);
            return jsonObject;
        }
        jsonObject.put(Const.CODE,"0");
        jsonObject.put(Const.MSG,"页面为空");
        return jsonObject;
    }

    @GetMapping("/showAll")
    @ApiOperation(value="查看全部岗位",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object showAll(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "5") Integer size){

        JSONObject jsonObject = new JSONObject();
        Page<Positions> detailPage = positionsService.showAll(page, size);

            jsonObject.put(Const.CODE,"1");
            jsonObject.put(Const.MSG,"查看成功");
            jsonObject.put(Const.NAME,detailPage);
            return jsonObject;
    }

    @GetMapping("/selectById")
    @ApiOperation(value="根据id查看岗位详情，这个查看详情的同时还判断用户有没有收藏该岗位哦，仪仪仪写的时候得加个按钮写是否收藏的",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object selectOne( @RequestParam("id") Integer pid){
        JSONObject jsonObject = new JSONObject();
        Result result = positionsService.selectById(pid);
        User user = (User) session.getAttribute(Const.NAME);
        Result collection = collectionService.findCollection(user.getUid(), pid);
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,"0");
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        if (!collection.isFlag()){                      //查询成功没有收藏
            jsonObject.put(Const.CODE,"1");
            jsonObject.put(Const.MSG,result.getMessage());
            jsonObject.put(Const.NAME,result.getData());
            jsonObject.put("flag",collection.getMessage());
            return jsonObject;
        }
//        查询成功并且已经收藏
        jsonObject.put(Const.CODE,"1");
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        jsonObject.put("flag",collection.getMessage());
        return jsonObject;
    }

    @GetMapping("/likeByCondition")
    @ApiOperation(value="（这些参数不一定都要输入的哦,可以用于搜索框）pName：岗位名，pClassify：岗位分类",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object checkLikeCondition( String pClassify,String p_name, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size){
        Page<Positions> positionsPage = positionsService.selectLikeByKeyWord(pClassify,p_name,page, size);
        JSONObject jsonObject = new JSONObject();
        if(positionsPage.getTotal()>0){
            jsonObject.put(Const.CODE,"1");
            jsonObject.put(Const.MSG,"查看成功");
            jsonObject.put(Const.NAME,positionsPage);
            return jsonObject;
        }
        jsonObject.put(Const.CODE,"0");
        jsonObject.put(Const.MSG,"页面为空");
        return jsonObject;
    }
}
