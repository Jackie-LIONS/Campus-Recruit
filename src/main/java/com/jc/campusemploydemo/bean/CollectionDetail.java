package com.jc.campusemploydemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDetail {
    private Integer id;
    private Integer count;
    private String pName;
    private String pClassify;
    private String pDescribe;
    private String pResponsibility;
    private String pRequest;
    private String pWorkSite;
    private String pDepartment;
    private Integer pFlag;           //1为校外，2为校内
}
