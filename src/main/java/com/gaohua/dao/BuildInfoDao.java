package com.gaohua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gaohua.model.IpProxy;

@Mapper
public interface BuildInfoDao {

	List<IpProxy> getIpList();

}
