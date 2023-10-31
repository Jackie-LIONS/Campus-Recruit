package com.jc.campusemploydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Positions;
import com.jc.campusemploydemo.domain.Resume;
import com.jc.campusemploydemo.mapper.PositionsMapper;
import com.jc.campusemploydemo.mapper.ResumeMapper;
import com.jc.campusemploydemo.service.ResumeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private PositionsMapper positionsMapper;

    @Override
    public boolean add(Resume resume,Integer uid) {
        resume.setUserId(uid);
        return resumeMapper.insert(resume)>0;
    }

    @Override
    public void delete(Integer uid, Integer pid) {
        QueryWrapper<Resume> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.eq("p_id",pid);
        resumeMapper.delete(wrapper);
    }

    @Override
    public Result showByUid(Integer id) {
        QueryWrapper<Resume> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<Resume> resumes = resumeMapper.selectList(wrapper);
        if (resumes == null||resumes.size() == 0){
            return new Result(false,"暂无投递");
        }
        QueryWrapper<Positions> queryWrapper = new QueryWrapper<>();
        List<Integer> list = new ArrayList<>();
        for (Resume resume : resumes) {
            list.add(resume.getPId());
        }
        List<Positions> pos = positionsMapper.selectBatchIds(list);
        return new Result(true,"查询成功",pos);
    }

    @Override
    public Result showAll(int page,int size) {
        Page<Resume> resumes = resumeMapper.showAll(new Page(page,size));
       return new Result(true,"查看成功",resumes);
    }

    @Override
    public Result showMyCompany(Integer uid) {
        List<Resume> resumes = resumeMapper.showMyCompany(uid);
        return new Result(true,"查询成功",resumes);

    }
}
