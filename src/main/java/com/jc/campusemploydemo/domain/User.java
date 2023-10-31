package com.jc.campusemploydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId (value="uid",type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private String activeCode;
    private String flag;
    private Integer roleId;
}
