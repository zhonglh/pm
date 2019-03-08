package com.pm.util.excel.imports;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;

import com.common.utils.DateKit;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pm.util.PubMethod;

/**
 * @author Administrator
 */
public class ExcelRead  {
	
	private static final String EXCEL_VALUES = "excel_value"; 
	private static final String EXCEL_FORMULAS = "excel_formulas"; 
	
	
	public static void main(String[] args){

		    Workbook wb = null;   
		    Sheet sheet = null;   
		    Row row = null; 
	        try {   
	      	  String path = "c:\\StaffCost (9).xlsx";
	        	FileInputStream input = new FileInputStream(path);
	        	boolean flag = false;
	        	/**2003 excel*/
	        	if(flag){
	        		wb = new HSSFWorkbook(input);   
	        	}else{
	        		wb = new XSSFWorkbook(input);   
	        	}
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        }   
	        sheet = wb.getSheetAt(0);   
	        row = sheet.getRow(0);   
	        //标题总列数   
	        int colNum = row.getPhysicalNumberOfCells();   
	        String[] title = new String[colNum];   
	        for (int i=0; i<colNum; i++) {   
	        }   
	    
	}


	public static List<String[]> readExcel(File file, boolean xlsx,int startRow) throws Exception {
		return readExcel(new FileInputStream(file),xlsx,startRow);
	}
	
	
	public static Workbook getWorkbook(InputStream inputStream){

		Workbook wb = null;
		try{
			if (!(inputStream.markSupported())) {
	        	inputStream = new PushbackInputStream(inputStream, 8);
	        }
	        if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {
	            wb = new HSSFWorkbook(inputStream);
	        } else if (POIXMLDocument.hasOOXMLHeader(inputStream)) {
	            wb = new XSSFWorkbook(OPCPackage.open(inputStream));
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return wb;
		
	}
	
	
	public static List<String[]> getFormulasByDatas(HashMap<String, List<String[]>> data) {
		if(data == null || data.isEmpty()) {
			return null;
		}
		return data.get(EXCEL_FORMULAS);
	}


	public static List<String[]> getValuesByDatas(HashMap<String, List<String[]>> data) {
		if(data == null || data.isEmpty()) {
			return null;
		}
		return data.get(EXCEL_VALUES);
	}	
	
	
	/**
	 * 获取Excel 列的值和公式
	 * @param cell
	 * @return String[]
	 */
	private static String[] getCellContent(Cell cell) {
		String cellValue = "";
		String cellFormula = "";
		java.text.DecimalFormat df=new java.text.DecimalFormat("0.00");//保留2位小数
		
		
		
		if (cell != null) {
			if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){ //数值类型,日期类型在上面,可以在这处理
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					Date d1 = cell.getDateCellValue();
					if(d1 != null) {
						cellValue = DateKit.fmtDateToYMD(d1);
					}
				}else{  					
					cellValue = String.valueOf(df.format(cell.getNumericCellValue()));					
				}
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){ //字符串类型
				cellValue = cell.getStringCellValue();
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){ //有公式的单元格
				
				try{
					//公式内容
					cellFormula = cell.getCellFormula(); 
					
					//列的值
					cellValue = String.valueOf(df.format(cell.getNumericCellValue()));
				}catch(Exception e){
					cellValue = cell.getStringCellValue();
				}
			}else{
				//cellValue = "";
			}
			
			if(cellValue != null ) {
				cellValue = cellValue.trim();
			}
			return new String[]{cellValue,cellFormula};
		} else {
			return null;
		}
	}
	
	
	private static List<String[]> getSheetData(Sheet sheet,int startRow){
		
		

		Iterator<Row> rows = sheet.rowIterator();
		
		HashMap<String,List<String[]>> allDataHashMap = new HashMap<String,List<String[]>>();
		
		List<String[]> valuesList = new ArrayList<String[]>();
		List<String[]> formulasList = new ArrayList<String[]>();
		

		int columnLength = 0;
		int rowIndex = 0;
		
		while (rows.hasNext()) {
			
			Row row = (Row) rows.next();					
			
			if(rowIndex >= (startRow-1) ) {
				

				List<String> valueList = new ArrayList<String>();
				List<String> formulaList = new ArrayList<String>();
				
				if(rowIndex == (startRow-1) ){
					//计算标题数据
					Iterator<Cell> cells = row.cellIterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();
						String cellValueFormula[] = getCellContent(cell);
						if(cellValueFormula!=null && cellValueFormula.length==2){
							valueList.add(cellValueFormula[0]);
							formulaList.add(cellValueFormula[1]);
						}
					}
					valuesList.add( 	PubMethod.List2Array(valueList));
					formulasList.add(	PubMethod.List2Array(formulaList));
					columnLength = valueList.size();
				}else {
					//计算内容数据
					for(int i=0; i < columnLength ; i++ ){
						Cell cell = row.getCell(i);
						String cellValueFormula[] = getCellContent(cell);
						if(cellValueFormula!=null && cellValueFormula.length==2){								
							valueList.add(cellValueFormula[0]);
							formulaList.add(cellValueFormula[1]);
						}else {
							valueList.add(null);
							formulaList.add(null);
						}
					}
					if(!isAllEmpty(valueList)){
						valuesList.add( 	PubMethod.List2Array(valueList));
						formulasList.add(	PubMethod.List2Array(formulaList));
					}
				}
			

			}
			


			rowIndex++;

		}
		
		allDataHashMap.put(EXCEL_VALUES, valuesList);
		allDataHashMap.put(EXCEL_FORMULAS, formulasList);
		
		return valuesList;
		
		
	}




	public static Map<String,List<String[]>> readExcelAllSheet(InputStream inputStream, boolean xlsx, int startRow) throws Exception {

		Workbook wb = null;
		try{		
			
			wb = getWorkbook(inputStream);			
			Map<String,List<String[]>> map = new HashMap<String,List<String[]>>();
			int sheetNumber = wb.getNumberOfSheets();			
			for(int i=0; i<sheetNumber; i++){
				Sheet sheet = wb.getSheetAt(i);	 
				List<String[]> list = getSheetData(sheet,startRow);
				map.put(sheet.getSheetName(), list);
			}			
		    return map;

		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("文件类型错误，请导入xls类型的文件");
		}finally{
			try{
				if(inputStream!=null) {
					inputStream.close();
				}
			}catch(Exception e){
				;
			}
			try{
				if(wb!=null) {
					wb.close();
				}
			}catch(Exception e){
				;
			}
		}
	}
	

	public static List<String[]> readExcel(InputStream inputStream, boolean xlsx, int startRow) throws Exception {

		Workbook wb = null;
		try{
			
			
			wb = getWorkbook(inputStream);
			
	        Sheet sheet = wb.getSheetAt(0);	        
			
		    return getSheetData(sheet,startRow);

		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("文件类型错误，请导入xls类型的文件");
		}finally{
			try{
				if(inputStream!=null) {
					inputStream.close();
				}
			}catch(Exception e){
				;
			}
			try{
				if(wb!=null) {
					wb.close();
				}
			}catch(Exception e){
				;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 是否全部为空
	 * @param list
	 * @return
	 */
	private static boolean isAllEmpty(List<String> list){
		if(list == null || list.isEmpty()) {
			return true;
		}
		for(String str : list){
			if(str != null && !str.isEmpty()) {
				return false;
			}
		}
		return true;
	}


	

}
