package com.gaohua.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;




import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.gaohua.model.BuildingInfo;
import com.gaohua.model.IpProxy;
import com.gaohua.model.TestModule;
import com.gaohua.service.impl.BuildInfoImpl;

/**
 * 创建人： Administrator
 * 创建时间：2020年4月5日下午10:52:55
 * 类描述：获取房天下成都二手房信息
 * 
 */
public interface BuildInfoService {

	List<IpProxy> getIpList() throws Exception;
	
}
