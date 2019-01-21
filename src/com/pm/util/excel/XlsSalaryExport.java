package com.pm.util.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pm.domain.business.Salary;

public class XlsSalaryExport extends XlsSimpleExport {


	protected List<XSSFSheet>	sheets = new ArrayList<XSSFSheet>();
	
	public boolean isWriteTitle = false;
	
	
	
	public XlsSalaryExport() {
		this.workbook = new XSSFWorkbook();
	}
	

	@Override
	public <T> void createSheet(T t, int cols, int rows){
		createSheet(t);
	}
	
	@Override
	public <T> void createSheet(T t){

		if(t != null && t.getClass() == Salary.class){
			Salary salary = (Salary)t;
			this.sheet = workbook.createSheet(String.valueOf(salary.getSalary_month()));
		}else {
			this.sheet = workbook.createSheet();
		}
		this.sheet.setDefaultColumnWidth(15);
		try {
			ExcelUtil.copySheet("salary.xlsx", 1, 3, sheet, 1);
			//isWriteTitle = true;
		} catch (Exception e) {
			isWriteTitle = true;
			e.printStackTrace();
		}
		this.sheet.createFreezePane(2, 4);
		sheets.add(this.sheet);
	}
	
	

	public boolean isWriteTitle() {
	  return isWriteTitle;
	}
	

	public XSSFCellStyle commonStyle(int cellIndex) {
		
		XSSFCellStyle lastCellStyle = null;
		try{
			if(titleRow != null && !this.isWriteTitle()){
				lastCellStyle = titleRow[titleRow.length-1].getCell(cellIndex).getCellStyle();
				XSSFCellStyle firstCellStyle = titleRow[0].getCell(cellIndex).getCellStyle();
				
				lastCellStyle.setFont(firstCellStyle.getFont());	
				return lastCellStyle;
			}
		}catch(Exception e){
			if(lastCellStyle != null) return lastCellStyle;
		}		
		return commonStyle();
	}	
	
	
	/**
	 * 特殊处理的行， 项目名称
	 * @param t
	 * @param columes
	 * @param addNumber
	 * @return
	 */
	public <T> boolean specialHand(T t,List<Colume> columes,boolean addNumber){
		
		if(t.getClass() == Salary.class){
			Salary salary = (Salary)t;
			if(salary.getSalary_id() == null){
				int len = columes.size() + (addNumber ? 1 : 0);
				for(int i = 0; i < len; i++){					
					if(i == 1){
						XSSFFont font1 = getCurrRow().getSheet().getWorkbook().createFont();
						font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
						
						XSSFCell cell = getCurrRow().createCell(i);
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						
						XSSFCellStyle style = commonStyle();
						style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
						style.setFillForegroundColor((short)0x36);
						style.setFillBackgroundColor((short)0x36);
						style.setFont(font1);						
						cell.setCellStyle(style);
						
						cell.setCellValue(salary.getProject_name());	
						break;
					}else {
						XSSFCell cell = getCurrRow().createCell(i);
						//cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						//XSSFCellStyle style = commonStyle();
						//style.setFillForegroundColor((short)41);
						//style.setFillBackgroundColor((short)41);
						//cell.setCellStyle(style);
					}
					
				}
				return true;
			}
		}
		return false;
	}

	
}
