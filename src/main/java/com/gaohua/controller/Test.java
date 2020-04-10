package com.gaohua.controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import cn.edu.hfut.dmic.webcollector.crawldb.DBManager;
import cn.edu.hfut.dmic.webcollector.crawler.Crawler;
import cn.edu.hfut.dmic.webcollector.fetcher.Executor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BerkeleyDBManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Test {

	   public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://cd.esf.fang.com/chushou/3_211037469.htm?channel=2,2&psid=1_1_100");
		// 浏览器前进
        driver.navigate().forward();
	}
}
