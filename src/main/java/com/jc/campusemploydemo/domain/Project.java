package com.jc.campusemploydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("project")
public class Project {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String projectName;
    private String fromTime;
    private String toTime;
    private String describes;
}
