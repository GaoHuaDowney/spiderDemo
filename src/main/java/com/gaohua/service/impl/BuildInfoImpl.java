package com.gaohua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaohua.dao.BuildInfoDao;
import com.gaohua.model.IpProxy;
import com.gaohua.service.BuildInfoService;

@Service
public class BuildInfoImpl implements BuildInfoService{

	@Autowired
	public BuildInfoDao buildInfoDao;
	@Override
	public List<IpProxy> getIpList() {
		// TODO Auto-generated method stub
		return buildInfoDao.getIpList();
	}

}
