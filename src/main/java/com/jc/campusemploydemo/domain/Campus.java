package com.jc.campusemploydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("campus")
public class Campus {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String  awDepartment;       //获奖单位
    private String award;       //获奖情况
    private String awTime;
}
