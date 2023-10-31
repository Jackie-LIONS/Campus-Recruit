package com.jc.campusemploydemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Positions;


public interface PositionsService {
    public Result selectById(Integer id);
    public Page<Positions> showAllOnSocial(Integer page, Integer size);
    public Page<Positions> showAll(Integer page, Integer size);
    public Page<Positions> selectLikeByKeyWord(String pClassify,String p_name,Integer page,Integer size);
    public Page<Positions> showAllOnCampus(Integer page, Integer size);


    public Result addPos(Positions positions);
    public boolean delete(Integer id);
    public Result updatePos(Positions positions);


    public Result showCompanyPosition(Integer uid,Integer page,Integer size);


}
