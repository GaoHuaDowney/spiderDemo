package com.gaohua.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaohua.model.BuildingInfo;
import com.gaohua.model.IpProxy;
import com.gaohua.service.BuildInfoService;
import com.gaohua.util.HttpClientUtil;
import com.gaohua.util.WriteExcelUtil;

/*
* 创建人： Administrator
* 创建时间：2020年4月5日下午10:52:55
* 类描述：获取房天下成都二手房信息
* 
*/
@RestController
@RequestMapping("/buildInfo")
public class BuildInfoController {

	@Autowired
	public HttpClientUtil httpClient;
	
	@Autowired
	public WriteExcelUtil excelUtil;
	
	@Autowired
	public BuildInfoService buildService;
	public static Log logger= LogFactory.getLog(BuildInfoController.class);
	
	//房天下信息网址
 	private static String url = "https://cd.esf.fang.com";
 	
	/**
 	 * 
 	 * @param url
 	 * 获取房屋信息
 	 * @return
	 * @throws IOException 
 	 */
	@RequestMapping("/insertBuildInfo")
	public List<BuildingInfo> getBuildInfoList() throws IOException {
		Random r = new Random();
		List<IpProxy>ipList = getIpProxyList();
		 int p=(int)(Math.random()*(ipList.size()-1));
 	  	  int q = (int)(Math.random()*14);
        System.setProperty("https.proxySet", "true");
        System.getProperties().put("https.proxyHost", ipList.get(p).getIp_address());
        System.getProperties().put("https.proxyPort", ipList.get(p).getIp_port());
	  	  logger.info("当前num="+p+",当前ip:"+ipList.get(p).getIp_address()+":"+ipList.get(p).getIp_port()+"\n");
/*		for(int i = 0;i<ipList.size();i++){
			int num=(int)(Math.random()*100);
			System.out.print("随机数："+num+"\n");
			System.out.print("ip:"+ipList.get(i).getIp_address()+"\n");
			System.out.print("port:"+ipList.get(i).getIp_port()+"\n");
		}*/
		//用于代理ip
		String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
				"Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
		// TODO Auto-generated method stub
		//int j = r.nextInt(14);
		
		boolean isStop = false;
		Long startDate=System.currentTimeMillis();
  	  	Document doc = null;
 
  	  	//定义List装房屋信息
  	  	List<BuildingInfo> buildList=new ArrayList<>();
  	  	Map<String, String> buildDateMap=new LinkedHashMap();
  	  //	int num = 1;
  	  	int count = 1;
  	  	boolean judge = false;
  	  	Date now = new Date();
  	  	System.out.print("url:"+url+"\n");
 	  	//Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36").timeout(100000).followRedirects(true);
  	  	for(int i = 1;i<=100;i++){//循环取数
  	  		if(!judge){
  	  			i = 1;
  	  		}	 
  	  		if(i == 1){
  	  			try{//userAgent(ua[j])
  	  				doc = Jsoup.connect(url).timeout(60000).method(Connection.Method.GET).followRedirects(true).get();
  	  				judge = true;
  	  				logger.info("第"+i+"页连接成功\n");
  	  			}catch (IOException e) {
			       // e.printStackTrace();
			        logger.info("当前ip不可用");
			       // ipList.remove(num);
			        logger.info("删除当前ip....进行下一次循环");
			        judge = false;
			        continue;
			  }
  	  	 }else{
  	  		//分页连接（页面js动态加载，创建后获取url再连接一次）
  	  		 try{
  	  		doc = Jsoup.connect(url+"/house/i3"+i+"/").timeout(60000).method(Connection.Method.GET).followRedirects(true).get();
  	  		//创建新的访问连接
			doc = Jsoup.connect(doc.getElementsByClass("btn-redir").attr("href")).timeout(60000).method(Connection.Method.GET).followRedirects(true).get();
			logger.info("第"+i+"页连接成功\n");
  	  		 }catch(Exception e){
  	  			 logger.info("第"+i+"页连接失败。。");
  	  			 continue;
  	  		 }
  	  	 }
		 Elements elements = doc.getElementsByTag("dl");//找到所有dl标签
		 logger.info("当前页一共有"+elements.size()+"条数据，开始进入遍历。。");		 
		 for (Element element : elements) {
		 	 //定义房屋类
		  	 BuildingInfo buildInfo = new BuildingInfo();
			 if (element.child(0).attr("class").equals("floatl")) {
				 count = count + 1;
				 System.out.print("count:"+count+"\n");
				 //链接
				 buildInfo.setBuildUrl(url+element.child(0).child(0).attr("href"));
				 //基本信息
				 buildInfo.setBuildInfo(element.child(0).child(0).child(0).attr("alt"));
				 //户型
				 buildInfo.setBuildModel(splitBuildInfo(element.child(1).getElementsByClass("tel_shop").text(),0));
				 //房屋面积
				 buildInfo.setBuildArea(splitBuildInfo(element.child(1).getElementsByClass("tel_shop").text(),1));
				 //层高
				 buildInfo.setHighLevel(splitBuildInfo(element.child(1).getElementsByClass("tel_shop").text(),2));
				 //朝向
				 buildInfo.setBuildPosition(splitBuildInfo(element.child(1).getElementsByClass("tel_shop").text(),3));
				 //年代
				 buildInfo.setBuildYear(splitBuildInfo(element.child(1).getElementsByClass("tel_shop").text(),4));			 //小区名字
				 //房屋单价
				 buildInfo.setBuildUnitPrice(element.child(2).child(1).text());
				 //房屋总价
				 buildInfo.setBuildTotalPrice(element.child(2).child(0).text());
				 //小区名字
				 buildInfo.setBuildName(element.child(1).child(2).child(0).attr("title"));
				 //小区位置
				 buildInfo.setBuildLocal(element.child(1).child(2).child(1).text());
				 //小区所在区域
				 String childUrl = httpClient.processHtmlJson(url+element.child(1).child(0).child(0).attr("href"));
				 System.out.print("childUrl:"+url+element.child(1).child(0).child(0).attr("href")+"?"+childUrl+"\n");
				 //创建新的访问连接
				 try{
					 doc = Jsoup.connect(url+element.child(1).child(0).child(0).attr("href")+"?"+childUrl).timeout(6000).method(Connection.Method.GET).followRedirects(true).get();				 
					 logger.info("doc1连接成功\n");
				 }catch(Exception e){
					 logger.info("doc1连接失败。。。");
					 continue;
				 }
				 if(doc.getElementById("address")!=null){
					 Element address = doc.getElementById("address");
					 System.out.print("address:"+address.child(0).text()+"\n");
					 buildInfo.setBuildRegion(address.child(0).text());
				 }else{
		    	  	 
		      	  	try{
		      	  		//创建新的访问连接
		      	  		doc = Jsoup.connect(doc.getElementsByClass("btn-redir").attr("href")).timeout(60000).method(Connection.Method.GET).followRedirects(true).get();
		      	  		logger.info("doc2连接成功\n");
		      	  		Element address = doc.getElementById("address");			 
						 System.out.print("address:"+address.child(0).text()+"\n");
						 buildInfo.setBuildRegion(address.child(0).text());
					 }catch(Exception e){
						 logger.info("doc2连接失败。。更换ip退出本次循环进行下一轮");
						 int num=(int)(Math.random()*(ipList.size()-1));
			      	  	  int j = (int)(Math.random()*14);
			              System.setProperty("https.proxySet", "true");
			              System.getProperties().put("https.proxyHost", ipList.get(num).getIp_address());
			              System.getProperties().put("https.proxyPort", ipList.get(num).getIp_port());
			      	  	  logger.info("当前num="+num+",当前ip:"+ipList.get(num).getIp_address()+":"+ipList.get(num).getIp_port()+"\n");
						 continue;
					 }
				 }
				buildList.add(buildInfo);
			}
		}
		logger.info("数据爬取完毕，写文件中。。。。");
		try{
				excelUtil.writeExcel(buildList);
			}catch(Exception e){
				logger.info("数据写入文件异常----");
				continue;
			}
		logger.info("第"+i+"页数据已经写入完毕，现在读取第"+(i+1)+"页数据。。");
  	  }
		System.out.print("end\n");
		return null;
	}
	
	
	//定义一个函数处理“|”分割的房屋信息
	private String splitBuildInfo(String str,int index){
		String arrInfo = "";
		if(!"".equals(str)&&!" ".equals(str)&&null!=str&&!"".equals(index+"")
				&&!" ".equals(index+"")&&null!=index+""){
			String arr[] = str.split("\\|");
			arrInfo = arr[index];
			if(index==1){
				//爬取的数据平方米部分有乱码，所以处理只保留数字
				String regEx="[^(0-9).]"; 
				Pattern p = Pattern.compile(regEx);  
				Matcher m = p.matcher(arrInfo);
				arrInfo = m.replaceAll("").trim();
			}
			logger.info("打印信息：---------》"+arrInfo);
		}else{
			logger.error("error:-------->str或index为空");
		}		
		return arrInfo;
	}
	
	
	//定义函数取数据库中的ip信息
	private List<IpProxy> getIpProxyList(){
		List<IpProxy> ipList = new ArrayList<IpProxy>();
		try {
			ipList = buildService.getIpList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipList;
	}
}
