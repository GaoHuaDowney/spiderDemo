package com.gaohua.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetProxyIp {

	public Elements getRequestMethod(String url) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		//setConnectTimeout(10000)连接超时时间（单位豪秒）
		//setSocketTimeout(10000)读取超时时间（单位豪秒）
		RequestConfig config=RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
		httpGet.setConfig(config);
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64)"
				+ " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
		CloseableHttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();	//获取返回实体
		String html = "";
		if(entity!=null) {
			html = EntityUtils.toString(entity);
		}
		response.close();			//关闭流和和释放资源
		Document doc = Jsoup.parse(html);		//解析网页得到文档对象
		/**
		 * getElementById(String id) 根据id来查询DOM
		 * getElementsByTag(String tagName) 根据tag名称来查询DOM
		 * getElementsByClass(String className) 根据样式名称来查询DOM
		 * getElementsByAttribute(String key) 根据属性名来查询DOM
		 * getElementsByAttributeValue(String key,String value)  根据属性名和属性值来查询DOM
		 */
		Elements tags = doc.select("#ip_list > tbody > tr");
//		Element element = tags.get(0);
//		System.out.println(element.text());
//		System.out.println(tags);
		return tags;
	}
	
	public Map<Integer,String> getElementsByTags(Elements tags) {
		Map<Integer,String> ipAddress = new HashMap<Integer,String>();
		int count = 0;
		//遍历tbody子节点
		for (Element element : tags) {
			//取得ip地址节点
			Elements tdChilds = element.select("tr > td:nth-child(2)");
			//取得端口号节点
			Elements tcpd = element.select("tr > td:nth-child(3)");
			ipAddress.put(++count, tdChilds.text()+":"+tcpd.text());
		}
		System.out.println(ipAddress);
		
		return ipAddress;
	}
	
	public void saveToText(Map<Integer,String> map) {
		try {						
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:\\IpProxy\\IpAddress.txt")),
					"UTF-8"));
			for (int i = 2; i < map.size(); i++) {
				String ipAddess = map.get(i);
	//				FileUtils.writeStringToFile(new File("D:\\cc\\IpAddress.txt"), ipAddess, "utf-8");
				bw.write(ipAddess);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ip地址爬取保存完毕");
	}
	
	public static void main(String[] args) {
		GetProxyIp ht = new GetProxyIp();
		String url = "http://www.xicidaili.com/nn/";
		try {
			Elements elements = ht.getRequestMethod(url);
			Map<Integer, String> elementsByTags = ht.getElementsByTags(elements);
			ht.saveToText(elementsByTags);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
