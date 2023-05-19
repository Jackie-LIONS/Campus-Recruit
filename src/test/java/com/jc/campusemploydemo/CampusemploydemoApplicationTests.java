package com.jc.campusemploydemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.Producer;
import com.jc.campusemploydemo.bean.PositionDetail;
import com.jc.campusemploydemo.bean.Result;
import com.jc.campusemploydemo.controller.InformationController;
import com.jc.campusemploydemo.domain.*;
import com.jc.campusemploydemo.mapper.*;
import com.jc.campusemploydemo.service.*;
import com.jc.campusemploydemo.utils.MailUtils;
import com.jc.campusemploydemo.utils.Md5Utils;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CampusemploydemoApplicationTests {
//	@Autowired
//	InformationMapper info;
//	@Autowired
//	ProjectService pro;
//	@Autowired
//	PositionsMapper positionsMapper;
//
//	@Autowired
//	PositionsService positionsService;
//	@Autowired
//	CampusService campusService;
//	@Autowired
//	ResumeService resumeService;
////	@Autowired
//	CollectionMapper collectionMapper;
//	@Autowired
//	ResumeMapper resumeMapper;
//	@Autowired
//	private CollectionService collectionService;
//	@Autowired
//	private UserService service;
//
//	@Autowired
//	private InformationService informationService;
//	@Autowired
//	private UserMapper userMapper;

//	@Autowired
//	private Producer checkCode;

	@Test
	void contextLoads() {
		String s = Md5Utils.md5("1234567");
		System.out.println(s);

	}
}
