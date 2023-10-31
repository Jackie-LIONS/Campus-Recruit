package com.jc.campusemploydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Positions;
import com.jc.campusemploydemo.mapper.PositionsMapper;
import com.jc.campusemploydemo.service.PositionsService;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsServiceImpl implements PositionsService {
    @Autowired
    private PositionsMapper record;

    /**
     * 分页查询校外的职位
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Positions> showAllOnSocial(Integer page, Integer size) {
        QueryWrapper<Positions> wrapper = new QueryWrapper<>();
        wrapper.eq("p_flag",1);
        wrapper.orderByAsc("id");
        Page selectPage = record.selectPage(new Page(page, size), wrapper);
        return selectPage;
    }
    /**
     * 分页查询校内的职位
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Positions> showAllOnCampus(Integer page, Integer size) {
        QueryWrapper<Positions> wrapper = new QueryWrapper<>();
        wrapper.eq("p_flag",1);
        wrapper.orderByAsc("id");
        Page selectPage = record.selectPage(new Page(page, size), wrapper);
        return selectPage;
    }

    @Override
    public Result addPos(Positions positions) {
        int flag = record.insert(positions);
        if (flag <0){
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }

    @Override
    public boolean delete(Integer id) {
        return record.deleteById(id)>0;
    }

    @Override
    public Result updatePos(Positions positions) {
        int i = record.updateById(positions);
        if (i>0){
            return new Result(true,"更新成功");
        }
        return new Result(false,"更新失败");
    }

    /**
     * 查询公司发布的招聘信息
     * @param uid
     * @return
     */
    @Override
    public Result showCompanyPosition(Integer uid,Integer page,Integer size) {
        QueryWrapper<Positions> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        Page selectPage = record.selectPage(new Page(page, size), wrapper);
        return new Result(true,"查询成功",selectPage);
    }

    /**
     * 查询全部职业信息
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Positions> showAll(Integer page, Integer size) {
        QueryWrapper<Positions> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        Page selectPage = record.selectPage(new Page(page,size), wrapper);
        return selectPage;
    }
    /**
     * 根据条件查询
     * @param pClassify
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Positions> selectLikeByKeyWord( String pClassify,String p_name,Integer page,Integer size) {
        QueryWrapper<Positions> queryWrapper = new QueryWrapper<>();
        if (p_name!= null){
            queryWrapper.like("p_name",p_name);
        }
        if (pClassify!= null){
            queryWrapper.like("p_classify",pClassify);
        }
        queryWrapper.orderByDesc("id");
        Page page1 = record.selectPage(new Page(page,size),queryWrapper);
        return page1;
    }

    /**
     * 根据id查找职位
     * @param id
     * @return
     */
    @Override
    public Result selectById(Integer id) {
        Positions positions = record.selectById(id);
        if(positions != null){
            return new Result(true,"查找成功",positions);
        }
        return new Result(false,"查找失败");
    }
}
