package com.jc.campusemploydemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.User;
import com.jc.campusemploydemo.mapper.UserMapper;
import com.jc.campusemploydemo.service.InformationService;
import com.jc.campusemploydemo.service.UserService;
import com.jc.campusemploydemo.utils.Const;
import com.jc.campusemploydemo.utils.MailUtils;
import com.jc.campusemploydemo.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Api(tags = {"登录模块"})
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Producer checkCode;

    @Autowired
    private HttpSession session;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/register")
   @ApiOperation(value="注册",notes = "code= 0 : 注册失败  code= 1: 注册成功，前端根据接口code值来判断跳转页面")
    public Object register(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        Result register = userService.register(user);
        if(user == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"user 为空");
            return jsonObject;
        }
        if(!register.isFlag()){         //注册失败
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,register.getMessage());
            return jsonObject;
        }

//        注册成功
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,register.getMessage());
        return jsonObject;
    }

    @PostMapping("/login")
    @ApiOperation(value="登录",notes = "code= 0 : 失败  code= 1: 成功，前端根据接口code值来判断跳转页面")
    public Object login(String name, String password,String checkCode){
        JSONObject jsonObject = new JSONObject();
        Result flag = userService.login(name, password);
        String code = (String) session.getAttribute("checkCode");
        if(checkCode.equalsIgnoreCase(code)){
            System.out.println("code:---->"+code);
            System.out.println("checkCode:----》"+checkCode);
            if(!flag.isFlag()){
                jsonObject.put(Const.CODE,0);
                jsonObject.put(Const.MSG,flag.getMessage());
                return jsonObject;
            }
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,flag.getMessage());
            session.setAttribute(Const.NAME,flag.getData());
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"验证码错误，请重新输入");
        return jsonObject;

    }

    @GetMapping("/checkCode")
    @ApiOperation(value="接收验证码" )
    public void getCheckCode(HttpServletResponse resp, HttpServletRequest req){
        //服务器通知浏览器不要缓存
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");
//        生成验证码
        String code = checkCode.createText();
//        生成验证码图片
        BufferedImage image = checkCode.createImage(code);
        //    将图片传入session
        req.getSession().setAttribute("checkCode",code);
//        session.setAttribute(" checkCode", code);
        //    将图片输出到前端(图片+格式)
        resp.setContentType("image/png");
        try {
            ServletOutputStream outputStream = resp.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("响应验证码失败");
        }
    }

    @GetMapping("/logout")
    @ApiOperation(value="注销登录" )
    public String logout(){
        session.removeAttribute(Const.NAME);
        return "redicet:/user/login";
    }

    @GetMapping("/showManagerAndUser")
    @ApiOperation(value = "管理员查询所有的非管理员")
    public Object showManagerAndUser(){
        Result result = userService.ManagingUsers();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Const.CODE,1);
        jsonObject.put(Const.MSG,result.getMessage());
        jsonObject.put(Const.NAME,result.getData());
        return jsonObject;
    }

    @PostMapping("/sendCode")
    @ApiOperation(value = "发送修改密码的验证码")
    public Object sendCode(String email){
        JSONObject jsonObject = new JSONObject();
//        生成激活码
        String codeText = checkCode.createText();
//        存激活码到session
        session.setAttribute(Const.codeText,codeText);
        session.setAttribute(Const.mail,email);
        String text = "您好，本次的验证码是："+codeText+"——>1分钟内有效";
        mailUtils.sendMail(email, text,"修改密码验证");
        jsonObject.put(Const.CODE,1);
        return jsonObject;
    }
    @PostMapping("alterPassword")
    @ApiOperation(value = "修改密码",notes = "username: 账号, code: 验证码  password：新密码" )
    public Object alterPassword(@RequestBody UserDTO userDTO){
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        String mail = (String) session.getAttribute("mail");
        queryWrapper.eq("email",mail);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户名和邮箱不对应");
            return jsonObject;
        }
        String code = (String) session.getAttribute(Const.codeText);
        if (code.equalsIgnoreCase(userDTO.getCode())){
            userDTO.setPassword(Md5Utils.md5(userDTO.getPassword()));
            user.setPassword(userDTO.getPassword());
            userMapper.updateById(user);
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Const.CODE,0);
        jsonObject.put(Const.MSG,"验证码错误,修改失败");
        return jsonObject;
    }



}
