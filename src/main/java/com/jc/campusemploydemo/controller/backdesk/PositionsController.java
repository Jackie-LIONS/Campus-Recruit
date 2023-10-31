package com.jc.campusemploydemo.controller.backdesk;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Positions;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.service.PositionsService;
import com.jc.campusemploydemo.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/backstage/pos")
@Api(tags = {"后台职位模块"})
public class PositionsController {
    @Autowired
    private PositionsService positionsService;
    @Autowired
    private HttpSession session;

    @PostMapping("/add")
    public Object addPos(@RequestBody Positions pos){
        User user = (User) session.getAttribute("name");            //获取session中的User对象
        JSONObject jsonObject = new JSONObject();
        pos.setUserId(user.getUid());
        Result result = positionsService.addPos(pos);
        if (!result.isFlag()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @PostMapping("/delete")
    public Object deletePos(Integer id){
        boolean flag = positionsService.delete(id);
        JSONObject jsonObject = new JSONObject();
        if (flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"删除失败");
        return jsonObject;
    }
    @GetMapping("/showAll")
    public Object showAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5")Integer size){
        JSONObject jsonObject = new JSONObject();
        Page<Positions> positionsPage = positionsService.showAll(page, size);
        if (positionsPage == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,"查询成功");
        jsonObject.put(Const.NAME,positionsPage);
        return jsonObject;
    }

    @PostMapping("/update")
    public Object updatePos(@RequestBody Positions positions){
        JSONObject jsonObject = new JSONObject();
        if (positions == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"数据为空");
            return jsonObject;
        }
        Result result = positionsService.updatePos(positions);
        if (result.isFlag()){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @PostMapping("/selectOne")
    public Object selectById(Integer id){
        Result result = positionsService.selectById(id);
        JSONObject jsonObject = new JSONObject();
        if (result.isFlag()){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,result.getMessage());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,result.getMessage());
        return jsonObject;
    }

    @GetMapping("/showCompanyPos")
    public Object showCompanyPos(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer size){
        JSONObject jsonObject = new JSONObject();
        User name = (User) session.getAttribute("name");            //获取session中的User对象
        Result result = positionsService.showCompanyPosition(name.getUid(),page,size);
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        return jsonObject;
    }
}
