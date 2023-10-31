package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.campusemploydemo.domain.Positions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionsMapper extends BaseMapper<Positions> {
    public List<Positions> findAll(@Param("pFlag") Integer flag);            //查询社会招聘还是校园招聘
    public List<Positions> selectAll();             //查询全部
    public Integer findCollectUidAndPid(@Param("user_id")Integer uid,@Param("p_id")Integer pid);
    public void addCollection(@Param("user_id") Integer uid,@Param("p_id")Integer pid,Integer flag);
    public Positions selectByPrimaryKey(@Param("id") Integer id);
    public Integer findMaxId();
}
