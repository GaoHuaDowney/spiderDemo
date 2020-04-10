package com.gaohua.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;

import com.gaohua.model.BuildingInfo;

@Component
public class WriteExcelUtil {
	
	private Logger logger;
	
/*	public static void main(String[] args) {
		BuildingInfo build1 = new BuildingInfo();
		BuildingInfo build2 = new BuildingInfo();
		BuildingInfo build3 = new BuildingInfo();
		build1.setBuildInfo("1");
		build2.setBuildInfo("2");
		build3.setBuildInfo("3");
		List<BuildingInfo> buildList = new ArrayList<BuildingInfo>();
		buildList.add(build1);
		buildList.add(build2);
		buildList.add(build3);
		
		writeExcel(buildList);
	}*/
	
	 public int writeExcel(List<BuildingInfo> buildList){ 
		 try{
	        FileInputStream fs=new FileInputStream("d://房天下数据/房天下数据.xls");  //获取d://test.xls  
	        POIFSFileSystem ps=new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息  
	        HSSFWorkbook wb=new HSSFWorkbook(ps);    
	        HSSFSheet sheet= null; //获取到工作表，因为一个excel可能有多个工作表  
	        HSSFRow row= null; //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值  
        	sheet=wb.getSheetAt(0);
	        row = sheet.getRow(0);
	        FileOutputStream out=new FileOutputStream("d://房天下数据/房天下数据.xls");  //向d://test.xls中写数据 
	        for (BuildingInfo buildInfo : buildList) {
		        System.out.println(sheet.getLastRowNum()+" "+row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格  
		        row=sheet.createRow((short)(sheet.getLastRowNum()+1)); //在现有行号后追加数据  
		        row.createCell(0).setCellValue(buildInfo.getBuildInfo()); //设置第一个（从0开始）单元格的数据  
		        row.createCell(1).setCellValue(buildInfo.getBuildName()); //设置第二个（从0开始）单元格的数据  
		        row.createCell(2).setCellValue(buildInfo.getBuildLocal());
		        row.createCell(3).setCellValue(buildInfo.getBuildUnitPrice());
		        row.createCell(4).setCellValue(buildInfo.getBuildArea());
		        row.createCell(5).setCellValue(buildInfo.getBuildTotalPrice());
		        row.createCell(6).setCellValue("");
		        row.createCell(7).setCellValue("");
		        row.createCell(8).setCellValue(buildInfo.getBuildPosition());
		        row.createCell(9).setCellValue(buildInfo.getHighLevel());
		        row.createCell(10).setCellValue(buildInfo.getBuildYear());
		        row.createCell(11).setCellValue("");
		        row.createCell(12).setCellValue(buildInfo.getBuildModel());
		        row.createCell(13).setCellValue(buildInfo.getBuildRegion());                   		 
	        }
	        	out.flush(); 
	        	wb.write(out);
	        	out.close();  
	        	wb.close();
	        	System.out.println(row.getPhysicalNumberOfCells()+" "+row.getLastCellNum());  
	        	return 1;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	//logger.info("数据写入失败了。。。这条数据就不写了吧");
	    	return 0;
	    }  
	 }
}

