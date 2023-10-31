package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.campusemploydemo.domain.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationMapper extends BaseMapper<Information> {
    public Information selectByUserId(Integer userId);
    public  Integer insertSelective(Information info);
    public Integer updateByUserIdSelective(Information info);
}
