package com.gaohua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaohua.dao.TestMapperDao;
import com.gaohua.model.TestModule;
import com.gaohua.service.MysqlService;

@Service
public class MysqlServiceImpl implements MysqlService{

	@Autowired
	private TestMapperDao testMapper;
	
	@Override
	public List<TestModule> select() {
		return testMapper.selectUser();
	}

}
