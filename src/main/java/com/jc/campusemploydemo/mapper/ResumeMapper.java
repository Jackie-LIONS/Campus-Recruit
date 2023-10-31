package com.jc.campusemploydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.campusemploydemo.domain.Resume;

import java.util.List;

public interface ResumeMapper extends BaseMapper<Resume> {
    Page<Resume> showAll(Page<Resume> page);
    List<Resume> showMyCompany(Integer uid);
}
