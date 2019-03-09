package com.pm.util.excel.exports;

import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;


/**
 * 凭证导出
 * @author zhonglihong
 * @date 2017年10月9日 下午11:26:14
 */
public class XlsSpecificExport extends XlsSimpleExport{
	


	@Override
	public <T> void createSheet(T t, int cols, int rows) {
		this.sheet = workbook.createSheet();
		this.sheet.setDefaultColumnWidth(15);
		this.sheet.createFreezePane(2, rows);
	}	
	

	@Override
	public void setCell(int index, String value) {
		if(value == null || value.isEmpty()){
			
		}else {
			XSSFCell cell = getCurrRow().createCell(index);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			//cell.setCellStyle(commonStyle(index));
			cell.setCellValue(value);
		}
	}
	
	@Override
	public void setCell(int index, Date value) {
		if (value != null) {
			XSSFCell cell = getCurrRow().createCell(index);
			cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);		
			
			XSSFCellStyle cellStyle = commonStyle(index); // 建立新的cell样式
			//XSSFDataFormat format = getXSSFWorkbook().createDataFormat();
			cellStyle.setDataFormat(14); 
			cell.setCellStyle(cellStyle); 
			
		}
	}

	@Override
	public void setCell(int index, java.sql.Timestamp value) {
		if (value != null) {
			XSSFCell cell = getCurrRow().createCell(index);
			cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);		
			
			XSSFCellStyle cellStyle = commonStyle(index); // 建立新的cell样式
			//XSSFDataFormat format = getXSSFWorkbook().createDataFormat();
			cellStyle.setDataFormat(14); 
			cell.setCellStyle(cellStyle); 
		}
	}
	

	private void setCell(int index, double value, XlsFormatEm formatEm) {
		if(value != 0 ) {
			XSSFCell cell = getCurrRow().createCell(index);
			cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);
			XSSFCellStyle cellStyle = commonStyle(index); // 建立新的cell样式
			XSSFDataFormat format = getXSSFWorkbook().createDataFormat();
			cellStyle.setDataFormat(format.getFormat(formatEm.getPattern())); // 设置cell样式为定制的浮点数格式
			//cellStyle.setDataFormat((short)176);
			cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
		}
	}

	@Override
	public void setCell(int index, double value) {
		setCell(index, value, XlsFormatEm.DOUBLE);
	}
	
	
	@Override
	public XSSFCellStyle commonStyle() {
		return getXSSFWorkbook().createCellStyle();		
	}
	
}
