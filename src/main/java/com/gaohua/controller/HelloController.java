package com.gaohua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaohua.model.TestModule;
import com.gaohua.service.MysqlService;

@RestController
@RequestMapping("/mysql/database_gao")
public class HelloController {
	
	@Autowired
	private MysqlService mysqlService;
	
	@RequestMapping("/hello")
	public Object hello(){
		return "hello springboot";
	}
	
	
	@PostMapping(value ="/select")
	public List<TestModule> select() throws Exception{
		return mysqlService.select();
	}
	
}
