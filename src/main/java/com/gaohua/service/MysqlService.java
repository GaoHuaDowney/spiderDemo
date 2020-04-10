package com.gaohua.service;

import java.util.List;

import com.gaohua.model.TestModule;

public interface MysqlService {

	List<TestModule> select() throws Exception;

}
