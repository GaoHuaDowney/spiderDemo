package com.gaohua.model;

import java.io.Serializable;


/**
 * 创建人： Administrator
 * 创建时间：2020年4月6日上午9:56:04
 * 类描述：房屋信息类
 * 
 */
public class BuildingInfo implements Serializable{

	private String buildInfo = ""; //房屋基本信息
	private String buildName = "";  //小区名字
	private String buildLocal = "";  //小区位置
	private String buildUnitPrice = ""; //售卖单价
	private String buildArea = "";  //售房面积
	private String buildTotalPrice = ""; //房屋总价
	private String buildUrl = ""; //房屋链接
	private String buildPic = ""; //房屋图片
	private String buildPosition = "";//房屋朝向
	private String highLevel = ""; //是否为高层（分为：高层、低层、中层）
	private String buildYear = ""; //小区年代
	private String buildType = ""; //住宅还是公寓
	private String buildModel = ""; //户型
	private String buildRegion = "";//小区区域
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getBuildLocal() {
		return buildLocal;
	}
	public void setBuildLocal(String buildLocal) {
		this.buildLocal = buildLocal;
	}
	public String getBuildUnitPrice() {
		return buildUnitPrice;
	}
	public void setBuildUnitPrice(String buildUnitPrice) {
		this.buildUnitPrice = buildUnitPrice;
	}
	public String getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}
	public String getBuildInfo() {
		return buildInfo;
	}
	public void setBuildInfo(String buildInfo) {
		this.buildInfo = buildInfo;
	}
	public String getBuildTotalPrice() {
		return buildTotalPrice;
	}
	public void setBuildTotalPrice(String buildTotalPrice) {
		this.buildTotalPrice = buildTotalPrice;
	}
	public String getBuildUrl() {
		return buildUrl;
	}
	public void setBuildUrl(String buildUrl) {
		this.buildUrl = buildUrl;
	}
	public String getBuildPic() {
		return buildPic;
	}
	public void setBuildPic(String buildPic) {
		this.buildPic = buildPic;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getBuildPosition() {
		return buildPosition;
	}
	public void setBuildPosition(String buildPosition) {
		this.buildPosition = buildPosition;
	}
	public String getBuildModel() {
		return buildModel;
	}
	public void setBuildModel(String buildModel) {
		this.buildModel = buildModel;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String getHighLevel() {
		return highLevel;
	}
	public void setHighLevel(String highLevel) {
		this.highLevel = highLevel;
	}
	public String getBuildRegion() {
		return buildRegion;
	}
	public void setBuildRegion(String buildRegion) {
		this.buildRegion = buildRegion;
	}
	
}
