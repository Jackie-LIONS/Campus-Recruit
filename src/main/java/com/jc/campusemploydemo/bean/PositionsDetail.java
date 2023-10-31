package com.jc.campusemploydemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionsDetail {
    private Integer id;
    private String pName;
    private String pClassify;
    private String pDepartment;
    private String pWorkSite;
}
