package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.CollectionDetail;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.CollectionService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/collection")
@Api(tags = {"用户收藏模块"})
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private HttpSession session;

    @PostMapping("/add")
    @ApiOperation(value="收藏",notes = "这个收藏我用了个请求转发的办法，如果不行来找我哦,需要输入请求头")
    public String addCollection(Integer pid,
                                @RequestHeader("header") String header){

        User user = (User)session.getAttribute(Const.NAME);
        collectionService.addCollection(user.getUid(),pid);
        return "redirect:"+header;
    }
    @PostMapping("/delete")
    @ApiOperation(value="取消收藏")
    public Object deleteCollection(Integer id){
        User user = (User)session.getAttribute(Const.NAME);
        collectionService.deleteCollection(user.getUid(),id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,"取消成功");
        return jsonObject;
    }

    @GetMapping("/showAll")
    @ApiOperation(value="查看我的收藏")
    public Object showAllCollection(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "5")Integer size){
        JSONObject jsonObject = new JSONObject();
        User user = (User)session.getAttribute(Const.NAME);
        List<CollectionDetail> detailPage = collectionService.showAllCollection(user.getUid(), page, size);
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,"查看成功");
        jsonObject.put(Const.NAME,detailPage);
        return jsonObject;
    }

    /**
     * 返回推荐岗位给用户
     * @return
     */
    @GetMapping("/recommend")
    @ApiOperation(value = "推荐岗位")
    public Object returnCommend(){
        User user = (User)session.getAttribute(Const.NAME);
        System.out.println(user);
        Result result = collectionService.recommendKPosition(user.getUid(), 5);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        return jsonObject;
    }
}
