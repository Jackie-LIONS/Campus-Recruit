package com.jc.campusemploydemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Collection {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer pId;
    private Integer flag;           //1代表校外，2代表校内
}
