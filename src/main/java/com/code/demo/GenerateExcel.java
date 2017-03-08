package com.code.demo;

import java.io.FileOutputStream;
import java.math.BigInteger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class GenerateExcel {
	private static int records = 100000;
	private static BigInteger start = BigInteger.valueOf(100000000000000001L);
	private static String filePath = "D:\\test.xls";
	
	public static void main(String[] args) {
		try {
			Workbook wb = new HSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(filePath);
			
			int sheetNum = 1;
			if (records>50000) {
				sheetNum = (records%50000)==0?(records/50000):((records/50000)+1);
			}
			System.out.println("开始写入Excel文件，记录数："+records+",sheet数："+sheetNum);
			int rowNums = 0;
			for (int i = 1; i <= sheetNum; i++) {
			    Sheet sheet = wb.createSheet();
			    sheet.setDefaultColumnWidth(25);
			    if(i==sheetNum){
			    	rowNums = records-((sheetNum-1)*50000);
				    for (int j = 0; j < rowNums; j++) {
				    	Row row = sheet.createRow(j);
				    	row.createCell(0).setCellValue(String.valueOf(start));
				    	start=start.add(BigInteger.valueOf(1L));
					}
			    }else{
				    for (int j = 0; j < 50000; j++) {
				    	Row row = sheet.createRow(j);
				    	row.createCell(0).setCellValue(String.valueOf(start));
				    	start=start.add(BigInteger.valueOf(1L));
					}
			    }
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
