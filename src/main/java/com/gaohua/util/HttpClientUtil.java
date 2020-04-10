package com.gaohua.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {

	 public String processHtmlJson(String url) throws IOException {
	        //1.生成httpclient，相当于该打开一个浏览器
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        CloseableHttpResponse response = null;
		    
	        List<String>result = new ArrayList<String>();

	        //2.创建get请求，相当于在浏览器地址栏输入 网址
	        HttpGet request = new HttpGet(url);
	        try {
	            //3.执行get请求，相当于在输入地址栏后敲回车键
	            response = httpClient.execute(request);
	           
	            //4.判断响应状态为200，进行处理
	            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                //5.获取响应内容
	                HttpEntity httpEntity = response.getEntity();
	                String html = EntityUtils.toString(httpEntity, "utf-8");
	                
	               // System.out.println(html);
	        
	                Matcher m = Pattern.compile("t3=\'(.*?)\'").matcher(html);
	                while(m.find()){
	                	result.add(m.group());
	                }
	                System.out.print("result:"+result.get(0).substring(4, result.get(0).length()-1)+"\n");
	            } else {
	                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
	                System.out.println("返回状态不是200");
	                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	            }
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            //6.关闭
	            HttpClientUtils.closeQuietly(response);
	            HttpClientUtils.closeQuietly(httpClient);
	        }
	        return result.get(0).substring(4, result.get(0).length()-1).toString();
	    }
}
