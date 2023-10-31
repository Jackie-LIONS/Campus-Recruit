package com.jc.campusemploydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jc.campusemploydemo.bean.PositionsDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("resume")
public class Resume {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String  username;
    private String email;
    private String phone;
    private Integer pId;
    private Date createTime;
    private Date updateTime;
    private Integer r;
    @TableField(exist = false)
    private PositionsDetail positions;
}
