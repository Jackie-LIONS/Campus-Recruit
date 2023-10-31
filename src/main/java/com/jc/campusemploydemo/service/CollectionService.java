package com.jc.campusemploydemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.CollectionDetail;
import com.jc.campusemploydemo.bean.PositionDetail;
import com.jc.campusemploydemo.bean.Result;

import java.util.List;

public interface CollectionService {
    public Result findCollection(Integer uid,Integer pid);
    public void addCollection(Integer uid,Integer pid);
    public void deleteCollection(Integer uid,Integer pid);
    public List<CollectionDetail> showAllCollection(Integer uid, Integer page, Integer size);

    /**
     * 给用户推荐职位
     * @param uid
     * @param k
     * @return
     */
    public Result recommendKPosition(Integer uid,Integer k);
}
