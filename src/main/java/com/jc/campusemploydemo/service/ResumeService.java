package com.jc.campusemploydemo.service;

import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.domain.Resume;

public interface ResumeService {
    public boolean add(Resume resume,Integer uid);
    public void delete(Integer uid, Integer pid);
    public Result showByUid(Integer id);
    public Result showAll(int size,int page);
    public Result showMyCompany(Integer uid);
}
