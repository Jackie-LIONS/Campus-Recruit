package com.jc.campusemploydemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private boolean flag; // 结果
    private String message; // 提示信息
    private Object data; // 返回数据

    public Result(boolean flag,String message){
        this(flag,message,null);
    }
}
