package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.campusemploydemo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Integer showUserCount();

}
