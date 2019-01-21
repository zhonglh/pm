package com.pm.util.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	
	public static void  copySheet(String file, int startRow, int endRow,XSSFSheet distSt,int position) throws IOException{	
		FileInputStream inputStream = null;
		try{
			
			URL url = ExcelUtil.class.getClassLoader().getResource(file);
			if(url == null){				
				url = ExcelUtil.class.getResource("/"+file);				
				if(url == null) {
					url = ExcelUtil.class.getResource(file);
				}
				if(url == null) {
					url = Thread.currentThread().getContextClassLoader().getResource(file);
				}
			}
			
			inputStream = new FileInputStream(url.getPath());

			ExcelUtil.copySheet(inputStream, startRow, endRow, distSt, position);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			try{
				if(inputStream != null) {
					inputStream.close();
				}
			}catch(Exception e){}
		}
	}
	
	public static void  copySheet(InputStream inputStream, int startRow, int endRow,XSSFSheet distSt,int position) throws IOException{		
			
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);
			copyRows(sheet,startRow,endRow,distSt,position);
	}
	
	//to same sheet
	public static void copyRows(XSSFSheet sourceSt, int startRow, int endRow, XSSFSheet distSt,int pPosition) {
		int pStartRow = startRow - 1;
		int pEndRow = endRow - 1;
		int targetRowFrom;
		int targetRowTo;
		int columnCount;
		CellRangeAddress region = null;
		int i;
		int j;

		if (pStartRow == -1 || pEndRow == -1) {
			return;
		}
		
		
		
		// set the column height and value
		for (i = pStartRow; i <= pEndRow; i++) {
			XSSFRow sourceRow = sourceSt.getRow(i);
			columnCount = sourceRow.getLastCellNum();
			if (sourceRow != null) {
				XSSFRow newRow = distSt.createRow(pPosition + i);
				newRow.setHeight(sourceRow.getHeight());
				for (j = 0; j < columnCount; j++) {
					XSSFCell templateCell = sourceRow.getCell(j);
					if (templateCell != null) {
						XSSFCell newCell = newRow.createCell(j);
						copyCell(templateCell, newCell);
					}
				}
			}
		}
		
		
		
		int len = sourceSt.getNumMergedRegions();

		for (i = 0; i < len; i++) {
			region = sourceSt.getMergedRegion(i);
			if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
				targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
				targetRowTo = region.getLastRow() - pStartRow + pPosition;

				CellRangeAddress newRegion = new CellRangeAddress(0,0,0,0);

				newRegion.setFirstRow(targetRowFrom);
				newRegion.setFirstColumn(region.getFirstColumn());
				newRegion.setLastRow(targetRowTo);
				newRegion.setLastColumn(region.getLastColumn());
				distSt.addMergedRegion(newRegion);
			}
		}
		
				
	}
	
	
	private static void copyCellStyle(XSSFCell srcCell,XSSFCellStyle srcCellStyle, XSSFCell distCell,XSSFCellStyle distCellStyle){
		
		if(srcCellStyle == null || distCellStyle == null) {
			return ;
		}
		distCellStyle.setAlignment(srcCellStyle.getAlignment());
		distCellStyle.setBorderBottom(srcCellStyle.getBorderBottom());
		distCellStyle.setFillBackgroundColor(srcCellStyle.getFillBackgroundColor());
		distCellStyle.setFillForegroundColor(srcCellStyle.getFillForegroundColor());
		distCellStyle.setFillPattern(srcCellStyle.getFillPattern());
		distCellStyle.setRightBorderColor(srcCellStyle.getRightBorderColor());
		distCellStyle.setRotation(srcCellStyle.getRotation());
		distCellStyle.setIndention(srcCellStyle.getIndention());
		distCellStyle.setLeftBorderColor(srcCellStyle.getLeftBorderColor());
		distCellStyle.setLocked(srcCellStyle.getLocked());
		distCellStyle.setRightBorderColor(srcCellStyle.getRightBorderColor());
		distCellStyle.setRotation(srcCellStyle.getRotation());
		distCellStyle.setTopBorderColor(srcCellStyle.getTopBorderColor());
		distCellStyle.setVerticalAlignment(srcCellStyle.getVerticalAlignment());		

		distCellStyle.setBorderBottom((short) 1);
		distCellStyle.setBorderTop((short) 1);
		distCellStyle.setBorderLeft((short) 1);
		distCellStyle.setBorderRight((short) 1);
		
		XSSFFont font1 = srcCellStyle.getFont();
		XSSFFont font2 = distCell.getSheet().getWorkbook().createFont();
		font2.setBoldweight(font1.getBoldweight());
		font2.setCharSet(font1.getCharSet());
		font2.setColor(font1.getColor());
		font2.setFontHeight(font1.getFontHeight());
		font2.setFontHeightInPoints(font1.getFontHeightInPoints());
		font2.setFontName(font1.getFontName());
		font2.setItalic(font1.getItalic());
		font2.setStrikeout(font1.getStrikeout());
		font2.setTypeOffset(font1.getTypeOffset());
		font2.setUnderline(font1.getUnderline());
		
		distCellStyle.setFont(font2);
		
		
	}

	private static void copyCell(XSSFCell srcCell, XSSFCell distCell) {

		XSSFCellStyle distCellStyle = distCell.getSheet().getWorkbook().createCellStyle();
		copyCellStyle(srcCell,srcCell.getCellStyle(), distCell,distCellStyle);
		distCell.setCellStyle(distCellStyle);
		
		
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		int srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);

		if (srcCellType == XSSFCell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(srcCell)) {
				distCell.setCellValue(srcCell.getDateCellValue());
			} else {
				distCell.setCellValue(srcCell.getNumericCellValue());
			}
		} else if (srcCellType == XSSFCell.CELL_TYPE_STRING) {
			distCell.setCellValue(srcCell.getRichStringCellValue());
		} else if (srcCellType == XSSFCell.CELL_TYPE_BLANK) {

		} else if (srcCellType == XSSFCell.CELL_TYPE_BOOLEAN) {
			distCell.setCellValue(srcCell.getBooleanCellValue());
		} else if (srcCellType == XSSFCell.CELL_TYPE_ERROR) {
			distCell.setCellErrorValue(srcCell.getErrorCellValue());
		} else if (srcCellType == XSSFCell.CELL_TYPE_FORMULA) {
			distCell.setCellFormula(srcCell.getCellFormula());
		} else {

		}
	}

}
