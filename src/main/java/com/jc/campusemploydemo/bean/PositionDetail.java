package com.jc.campusemploydemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDetail {
    private Integer id;
    private String pName;           //职位名
    private String pClassify;       //分类
    private String pDescribe;       //描述
    private String pResponsibility; //职能
    private String pRequest;        //要求
    private String pDepartment;     //所属部门
    private String workSite;        //工作地点
    private Integer flag;               //
}
