package com.pm.util.excel;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.common.utils.DateKit;

/**
 * @author Administrator
 */
public abstract class XlsExport {

	protected XSSFWorkbook workbook;

	protected XSSFSheet sheet;

	protected XSSFRow row;
	

	protected XSSFRow[] titleRow;

	// 设置cell编码解决中文高位字节截断
	// private static short XLS_ENCODING = XSSFWorkbook.ENCODING_UTF_16;

	protected static enum XlsFormatEm {
		DATE("yyyy/MM/dd"), NUMBER("0.00"),DOUBLE("##0.00"), CURRENCY("#,##0.00"), PERCENT("0.00%");
		private final String pattern;

		XlsFormatEm(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return this.pattern;
		}
	}

	public <T> void createSheet(T t) {
		this.sheet = workbook.createSheet();
		this.sheet.setDefaultColumnWidth(15);
		this.sheet.createFreezePane(0, 1);
	}
	

	public <T> void createSheet(T t, int cols, int rows) {
		this.sheet = workbook.createSheet();
		this.sheet.setDefaultColumnWidth(15);
		this.sheet.createFreezePane(cols, rows);
	}	

	/**
	 * 增加一行
	 * 
	 * @param index
	 *              行号
	 */
	public void createRow(XSSFSheet sheet, int index) {
		setCurrRow(sheet.createRow(index));
	}
	
	
	
	
	
	


	public void setTitleCell(int index, String value) {
		XSSFCell cell = getCurrRow().createCell(index);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		XSSFCellStyle hssfCellStyle = commonStyle();
		
		hssfCellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.index);

		
		Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); //粗体
            
		
		hssfCellStyle.setFont(font);
		cell.setCellStyle(hssfCellStyle);
		
		cell.setCellValue(value);
	}	
	

	/**
	 * 设置单元格
	 * 
	 * @param index
	 *              列号
	 * @param value
	 *              单元格填充值
	 */
	public void setCell(int index, String value) {
		XSSFCell cell = getCurrRow().createCell(index);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(commonStyle(index));
		cell.setCellValue(value);
	}
	

	public void setCell(int index, Date value) {
		XSSFCell cell = getCurrRow().createCell(index);
		if (value != null) {
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(commonStyle(index));
			cell.setCellValue(DateKit.fmtDateToYMD(value));			
		}
	}

	public void setCell(int index, java.sql.Timestamp value) {
		XSSFCell cell = getCurrRow().createCell(index);
		if (value != null) {
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(commonStyle(index));
			cell.setCellValue(DateKit.fmtDateToYMD(value));			
		}
	}

	public void setCell(int index, int value) {
		XSSFCell cell = getCurrRow().createCell(index);
		cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellStyle(commonStyle(index));
		cell.setCellValue(value);
	}

	private void setCell(int index, double value, XlsFormatEm formatEm) {
		XSSFCell cell = getCurrRow().createCell(index);
		cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		XSSFCellStyle cellStyle = commonStyle(index); // 建立新的cell样式
		XSSFDataFormat format = getXSSFWorkbook().createDataFormat();
		cellStyle.setDataFormat(format.getFormat(formatEm.getPattern())); // 设置cell样式为定制的浮点数格式
		cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}

	public void setCell(int index, double value) {
		setCell(index, value, XlsFormatEm.NUMBER);
	}

	public void setCurrency(int index, double value) {
		setCell(index, value, XlsFormatEm.CURRENCY);
	}

	public void setPercent(int index, double value) {
		setCell(index, value, XlsFormatEm.PERCENT);
	}
	


	public XSSFCellStyle commonStyle() {
		XSSFCellStyle cellStyle = getXSSFWorkbook().createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		
		
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		setBorder(cellStyle, HSSFColor.BLACK.index,
				XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	

	
	public XSSFCellStyle firstCommonStyle(int cellIndex) {
		
		try{
			if(titleRow != null){
				return this.titleRow[titleRow.length-1].getCell(cellIndex).getCellStyle();
			}
		}catch(Exception e){
			;
		}		
		return commonStyle();
	}	
	
	
	public abstract XSSFCellStyle commonStyle(int cellIndex) ;

	public void setBorder(XSSFCellStyle style, short color, short borderType) {

		style.setBorderBottom(borderType);
		style.setBorderLeft(borderType);
		style.setBorderTop(borderType);
		style.setBorderRight(borderType);

		style.setRightBorderColor(color);
		style.setTopBorderColor(color);
		style.setLeftBorderColor(color);
		style.setBottomBorderColor(color);
	}
	
	
	
	
	/**
	 * 设置单元格内容
	 * @param index
	 * @param value
	 * @param alignment
	 * @param boldweight
	 */
	public void setCell(int index, String value, short alignment, boolean boldweight) {
		
		XSSFCell cell = getCurrRow().createCell(index);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		
		XSSFCellStyle cellStyle = getXSSFWorkbook().createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		
		if(boldweight) {
			XSSFFont font2 = getXSSFWorkbook().createFont();  
			font2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示  		  
			cellStyle.setFont(font2);//选择需要用到的字体格式  
		}		
		
		cellStyle.setAlignment(alignment);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		setBorder(cellStyle, HSSFColor.BLACK.index,	XSSFCellStyle.BORDER_THIN);
		
		
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}
	
	/**
	 * 设置单元格内容
	 * @param index
	 * @param value
	 * @param alignment
	 * @param boldweight
	 * @param formatEm
	 */
	public void setCell(int index, Double value, short alignment, boolean boldweight, XlsFormatEm formatEm) {
		
		XSSFCell cell = getCurrRow().createCell(index);
		cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
		
		XSSFCellStyle cellStyle = getXSSFWorkbook().createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		
		if(boldweight) {
			XSSFFont font2 = getXSSFWorkbook().createFont();  
			font2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示  		  
			cellStyle.setFont(font2);//选择需要用到的字体格式  
		}		
		
		cellStyle.setAlignment(alignment);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		

		XSSFDataFormat format = getXSSFWorkbook().createDataFormat();
		cellStyle.setDataFormat(format.getFormat(formatEm.getPattern()));
		
		setBorder(cellStyle, HSSFColor.BLACK.index,	XSSFCellStyle.BORDER_THIN);
		
		
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}
	
	
	public void setNumberCell(int index, Double value, short alignment, boolean boldweight) {
		this.setCell(index, value, alignment,boldweight , XlsFormatEm.NUMBER);
	}

	public void setCurrencyCell(int index, Double value, short alignment, boolean boldweight) {
		this.setCell(index, value, alignment,boldweight , XlsFormatEm.CURRENCY);
	}

	public void setPercentCell(int index, Double value, short alignment, boolean boldweight) {
		this.setCell(index, value, alignment,boldweight , XlsFormatEm.PERCENT);
	}

	
	
	public <T> boolean specialHand(T t,List<Colume> columes,boolean addNumber){
		return false;
	}
	
	/**
	 * 返回是否需要写头信息
	 * @return
	 */
	public boolean isWriteTitle() {
		return true;
	}

	public XSSFSheet getCurrXSSFSheet() {
		return this.sheet;
	}

	public void setCurrXSSFSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public XSSFRow getCurrRow() {
		return this.row;
	}

	public void setCurrRow(XSSFRow row) {
		this.row = row;
	}

	public XSSFWorkbook getXSSFWorkbook() {
		return workbook;
	}

	public XSSFRow[] getTitleRow() {
		return titleRow;
	}

	public void setTitleRow(XSSFRow[] titleRow) {
		this.titleRow = titleRow;
	}

}
