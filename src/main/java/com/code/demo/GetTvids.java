package com.code.demo;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class GetTvids {
	
	private static String filePath = "D:\\tvids.xls";
	private static String mysqlUrl = "jdbc:mysql://10.60.1.248:3306/epg?useUnicode=true&characterEncoding=utf-8";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("成功加载MySql驱动！");
			
			conn = DriverManager.getConnection(mysqlUrl, "epg", "epg");
			String sql = "SELECT tvid FROM tv_subscriber_local WHERE terminal_provider = 'BESTVOEM'"
					+ " AND SERIES_CODE = 'Series_1' AND subscriber_group_code = 'OTT_Inside_AHHUI'";
			preStatement = conn.prepareStatement(sql);
			
			rs = preStatement.executeQuery();
			List<String> tvids = new ArrayList<String>();
			while (rs.next()) {
				String tvid = rs.getString(1);
				tvids.add(tvid.split("\\$")[1]);
			}
			exportExcel(tvids);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(preStatement!=null)preStatement.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void exportExcel(List<String> list){
		try {
			int records = list.size();
			Workbook wb = new HSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(filePath);
			
			int sheetNum = 1;
			if (records>50000) {
				sheetNum = (records%50000)==0?(records/50000):((records/50000)+1);
			}
			System.out.println("开始写入Excel文件，记录数："+records+",sheet数："+sheetNum);
			int rowNums = 0;
			int listIndex = 0;
			for (int i = 1; i <= sheetNum; i++) {
			    Sheet sheet = wb.createSheet();
			    sheet.setDefaultColumnWidth(25);
			    if(i==sheetNum){
			    	rowNums = records-((sheetNum-1)*50000);
				    for (int j = 0; j < rowNums; j++) {
				    	Row row = sheet.createRow(j);
				    	row.createCell(0).setCellValue(list.get(j+listIndex));
					}
			    }else{
				    for (int j = 0; j < 50000; j++) {
				    	Row row = sheet.createRow(j);
				    	row.createCell(0).setCellValue(list.get(j+listIndex));
					}
			    }
			    listIndex=listIndex+(i*50000);
			    System.out.println("第"+i+"张sheet写入完成...");
			}
			wb.write(fileOut);
			fileOut.close();
			wb.close();
			System.out.println("写入完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
