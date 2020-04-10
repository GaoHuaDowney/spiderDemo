package com.gaohua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gaohua.model.TestModule;

@Mapper
public interface TestMapperDao {
	
	List<TestModule> selectUser();
}
