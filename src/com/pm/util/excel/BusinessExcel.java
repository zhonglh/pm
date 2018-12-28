package com.pm.util.excel;

import com.pm.domain.business.Salary;
import com.pm.util.ColumeComparator;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalBusinessNameHolder;
import com.pm.util.log.EntityAnnotation;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 */
public class BusinessExcel {
	
	//private static Map<String,List<Colume>> clzColumeMap = new HashMap<String,List<Colume>>();
	
	//protected static SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
	
	/**
	 * 
	 * @param response
	 * @param list	列表
	 * @param clz	对象类型
	 */
	public static <T> void export(HttpServletResponse response,List<T> list,Class<T> clz ){	
		BusinessExcel.export(response, null, list, clz);
	}
	public static <T> void export(HttpServletResponse response,List<String> heads,List<T> list,Class<T> clz ){		
		XlsSimpleExport export = new XlsSimpleExport();
		export(export,heads,list,clz);
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename="+clz.getSimpleName()+".xlsx");		
		export.exportXls(response);
	}
	
	public static <T> void exportPopular(HttpServletResponse response,List<String> heads,List<T> list,Class<T> clz ){		
		XlsSimpleExport export = new XlsSpecificExport();
		export(export,heads,list,clz,false);
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename="+clz.getSimpleName()+".xlsx");		
		export.exportXls(response);
	}
	
	
	
	/**
	 * 
	 * @param response
	 * @param heads				表头， 非标题
	 * @param list				数据，包括标题
	 * @param clz				数据类型
	 * @param addNumber			是否自动增加序号列
	 */
	public static <T> void export(HttpServletResponse response,List<String> heads,List<T> list,Class<T> clz,boolean addNumber ){		
		
		
		XlsSimpleExport export = new XlsSimpleExport();
		export(export,heads,list,clz,addNumber);

		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename="+clz.getSimpleName()+".xlsx");		
		export.exportXls(response);

	}


	
	public static <T> void exportSalary(HttpServletResponse response,List<List<T>> lists,Class<T> clz ){		
		XlsSalaryExport export = new XlsSalaryExport();		
		if(lists == null || lists.isEmpty()){
			List<String> heads = new ArrayList<String>();
			heads.add("北京华路——工资表");
			export(export,heads,new ArrayList<T>(),clz);
		}else {
			for(List<T> list : lists){
				List<String> heads = new ArrayList<String>();
				
				if(list != null && !list.isEmpty()){
					Salary salary = (Salary)list.get(0);
					heads.add("北京华路——"+PubMethod.getMonthChinseStr(salary.getSalary_month())+"工资表(本月"+(int)salary.getShould_work_days()+"个工作日)");
				}else {
					heads.add("北京华路——工资表()");
				}
				export(export,heads,list,clz);
			}		
		}
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename="+clz.getSimpleName()+".xlsx");
		export.exportXls(response);
	}	
	

	
	public static   <T> List<Colume> getColume(Class<T> clz) {	
		return getColume(clz, false,false);
	}
	
	/**
	 * 用于导入
	 * @param clz
	 * @return
	 */
	public static   <T> List<Colume> getImportColume(Class<T> clz) {	
		return getColume(clz, false,true);
	}
	
	public static   <T> List<Colume> getColume(final Class<T> clz,boolean isAll,boolean isImport) {		
		
		synchronized(clz){
			Field[] fs =  PubMethod.getFields(clz);
			
			String businessName = ThreadLocalBusinessNameHolder.getBusinessName();
			
			if(fs == null || fs.length == 0) {
				return null;
			}
			List<Colume> list = new ArrayList<Colume>();
			for(Field f : fs){
				if(f.isAnnotationPresent(EntityAnnotation.class)){
					EntityAnnotation entityAnnotation = f.getAnnotation(EntityAnnotation.class);
					if(!isAll){
						if(entityAnnotation.item_sort() == 0) {
							continue;
						}
						if(isImport) {
							if(entityAnnotation.length() == 0) {
								continue;
							}
						}
					}
					
					if(entityAnnotation.business_name() != null && entityAnnotation.business_name().length() > 0){
						if(businessName != null && businessName.length() >0){
							if(entityAnnotation.business_name().indexOf(businessName) == -1) {
								continue;
							}
						}
					}
					
					
					Colume colume = new Colume();
					colume.setCode(f.getName());
					colume.setName(entityAnnotation.item_name());
					colume.setNumber(entityAnnotation.item_sort());
					colume.setLength(entityAnnotation.length());
					colume.setField(f);
					list.add(colume);
				}
			}
			if(list != null && list.size()>0){
				Collections.sort(list,new ColumeComparator());
			}
			
			int index = 1;
			for(Colume colume : list){
				colume.setNumber(index);
				index ++;
			}
			
			return list;
		}
		
		
	}
	

	public static <T> void export(XlsExport export,List<String> heads,List<T> list, Class<T> clz) {
		export(export,heads,list,clz, true);
	}
	
	

		
	
	/**
	 * 
	 * @param export
	 * @param list
	 * @param clz
	 * @param addNumber	是否需要增加 "序号" 列
	 */
	public static <T> void export(XlsExport export,List<String> heads,List<T> list, Class<T> clz,  boolean addNumber ) {
		
		
		try{
			
			int rowIndex = 0;
			int headLength = 0;
			
			if(heads != null) {
				headLength = heads.size();
			}
			
			rowIndex = headLength;
			
			export.createSheet((list == null || list.isEmpty()) ? null : list.get(0),0,headLength+1);
			List<Colume> columes = null;
			
			int position = addNumber ? 0 : 1;
			
			
			XSSFRow[] titleRows = null;
			
			if(export.isWriteTitle()) {
				
				// header
				export.createRow(export.getCurrXSSFSheet(),rowIndex++);
				
				export.getCurrRow().setHeight((short)400);
				if(addNumber) {
					export.setTitleCell(0, getNumberName());
				}
				
				columes = getColume(clz);
				for (Colume colume : columes) {
					export.setTitleCell(colume.getNumber() - position , colume.getName());
				}
				
				titleRows = new XSSFRow[1];
				titleRows[0] =export.getCurrXSSFSheet().getRow((rowIndex-1));
				
			}else {
				rowIndex = export.getCurrXSSFSheet().getLastRowNum() + 1;	
				if(rowIndex > 0){
					titleRows = new XSSFRow[rowIndex-headLength];
					for(int i=0;i<rowIndex-headLength ; i++){
						titleRows[i] = export.getCurrXSSFSheet().getRow(i+headLength);
					}
				}
			}
			
			export.setTitleRow(titleRows);
			
			
			exportHeads(export,heads);
			
	
			exprotContent(export, list, clz, rowIndex, columes,addNumber);
		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}
	
	
	/**
	 * 根据最后一行的列数， 合并前面行的列， 表头居住显示
	 * @param export
	 * @param heads
	 */
	public static <T> void exportHeads(XlsExport export,List<String> heads ) {
		if(heads == null || heads.isEmpty() ) {
			return ;
		}
		
		XSSFCellStyle cellStyle1 = getXSSFCellStyle1(export.getCurrXSSFSheet().getWorkbook());
		XSSFCellStyle cellStyle2 = getXSSFCellStyle2(export.getCurrXSSFSheet().getWorkbook());
		XSSFRow hssfRow = export.getCurrXSSFSheet().getRow(export.getCurrXSSFSheet().getLastRowNum());	
		int cellLength = 13 ;
		if(hssfRow != null) {
			cellLength = hssfRow.getLastCellNum();
		}
		if(cellLength > 13) {
			cellLength = 13;
		}
		 
		for(int i=0;i<heads.size();i++){
			export.createRow(export.getCurrXSSFSheet(),i);
			XSSFCell cell = export.getCurrRow().createCell(0);
			if(i == 0) {
				cellStyle1.setFillForegroundColor(IndexedColors.YELLOW.index);
				cell.setCellStyle(cellStyle1);

				export.getCurrRow().setHeightInPoints(20);  
				export.getCurrRow().setHeight((short)700);
			} else {
				cell.setCellStyle(cellStyle2);
			}
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(heads.get(i));
			
			for(int index = 1 ; index<cellLength;index ++){
				cell = export.getCurrRow().createCell(index);
				cell.setCellStyle(cellStyle2);
			}

			
			CellRangeAddress cellRangeAddress = new CellRangeAddress(i,i,0,(short)(cellLength-1));
			export.getCurrXSSFSheet().addMergedRegion(cellRangeAddress);
			
		}	
		
		
	}
	
	
	public static XSSFCellStyle getXSSFCellStyle1(XSSFWorkbook wb){
		XSSFCellStyle cellStyle = wb.createCellStyle(); 		  
		  
		short   index     = 0x9;
		cellStyle.setFillForegroundColor(index);// 设置背景色    
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);    		
		  
		  
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框    		  
		  
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中    	
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 居中    	
		
		  
		  
		XSSFFont font = wb.createFont();    
		font.setFontName("黑体");    
		font.setFontHeightInPoints((short) 16);//设置字体大小    
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); 
		
		cellStyle.setFont(font);
		
		return cellStyle;
	}
	

	
	public static XSSFCellStyle getXSSFCellStyle2(XSSFWorkbook wb){
		XSSFCellStyle cellStyle = wb.createCellStyle(); 		  
		short   index     = 0x9;
		cellStyle.setFillForegroundColor(index); // 设置背景色    
		
		
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);    		
		  
		  
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框    		  
		  
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT); // 居中    		
		  
		  
		XSSFFont font = wb.createFont();    
		font.setFontName("黑体");    
		font.setFontHeightInPoints((short) 10);//设置字体大小    

		cellStyle.setFont(font);
		
		return cellStyle;
	}	



	protected static <T> void exprotContent(XlsExport export, List<T> list,
			Class<T> clz, int rowIndex, List<Colume> columes, boolean addNumber ) {		

		int position = addNumber ? 0 : 1;
		
		if(list == null) {
			return ;
		}
		
		if(columes == null) {
			columes = getColume(clz);
		}
		
		int index = 1;
		
		for(T t : list){
			if(t == null) {
				continue;
			}
			export.createRow(export.getCurrXSSFSheet(),rowIndex++);
			
			if(export.specialHand(t, columes, addNumber)) {
				continue;
			}
			
			if(addNumber) {
				export.setCell(0 , index);
			}
			
			index ++;
			
			for (Colume colume : columes) {
				colume.getField().setAccessible(true);
				try {
					Object value = colume.getField().get(t);
					if(value == null) {
						export.setCell(colume.getNumber()- position , "");
						continue;
					}
					
					setCellValue(export, position, colume, value);					
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			}
		}
	}
	
	
	protected static void setCellValue(XlsExport export, int position, Colume colume, Object value) {
		if(colume.getField().getType().equals(String.class)){
			export.setCell(colume.getNumber()- position , String.valueOf(value));
		}else if(colume.getField().getType().equals(java.sql.Timestamp.class)){
			export.setCell(colume.getNumber()- position , (java.sql.Timestamp)value);
		}else if(colume.getField().getType().equals(java.util.Date.class)){
			export.setCell(colume.getNumber()- position , (java.util.Date)value);
		}else if(colume.getField().getType().equals(java.sql.Date.class)){
			export.setCell(colume.getNumber()- position , (java.sql.Date)value);
		}else if(colume.getField().getType().equals(int.class)){
			export.setCell(colume.getNumber()- position , (int)value);
		}else if(colume.getField().getType().equals(double.class)){
			export.setCell(colume.getNumber()- position , (double)value);						
		}else if(colume.getField().getType().equals(long.class)){
			export.setCell(colume.getNumber()- position , (long)value);						
		}else {						
			export.setCell(colume.getNumber()- position ,value.toString());		
		}
	}	
	
	
	

	
	
	public static String getNumberName(){
		return "序号";
	}
	
	


}
