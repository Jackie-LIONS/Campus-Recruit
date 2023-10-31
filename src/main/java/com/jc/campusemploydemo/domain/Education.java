package com.jc.campusemploydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("education")
public class Education implements Serializable {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String school;
    private String level;
    private String fromTime;
    private String toTime;          //毕业时间
}
