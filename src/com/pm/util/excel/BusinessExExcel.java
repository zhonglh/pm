package com.pm.util.excel;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * @author Administrator
 */
public class BusinessExExcel extends BusinessExcel {
	
	
	/**
	 * 
	 * @param response
	 * @param lists	表头
	 */
	public static void export(HttpServletResponse response,List<List<?>> lists){	
		export(response, null, lists);
	}
	public static void export(HttpServletResponse response,List<String> heads,List<List<?>> lists ){	
		
		
		XlsSimpleExport export = new XlsSimpleExport();
		export(export,heads,lists);
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".xlsx");		
		export.exportXls(response);
	}
	


	public static  void export(XlsExport export,List<String> heads,List<List<?>> lists) {
		export(export,heads,lists, true);
	}
	
	
	/**
	 * 
	 * @param response
	 * @param heads				表头， 非标题
	 * @param lists				数据，包括标题
	 * @param addNumber			是否自动增加序号列
	 */
	public static void export(HttpServletResponse response,List<String> heads,List<List<?>> lists,boolean addNumber ){			

			
		XlsSimpleExport export = new XlsSimpleExport();
		export(export,heads,lists,addNumber);

		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".xlsx");		
		export.exportXls(response);

	}


	

		
	
	/**
	 * 
	 * @param export
	 * @param heads
	 * @param lists
	 * @param addNumber	是否需要增加 "序号" 列
	 */
	public static void export(XlsExport export,List<String> heads,List<List<?>> lists,  boolean addNumber ) {
		
		
		try{
			
			int rowIndex = 0;
			int headLength = 0;
			
			if(heads != null) {
				headLength = heads.size();
			}
			
			rowIndex = headLength;
			
			export.createSheet(null,0,headLength);
			
			
			if(lists != null && lists.size() >0){
				//输出第一个列表的标题
				List<Column> columns = exportTitle(export, lists.get(0),  addNumber,  rowIndex);
				
				//根据标题 输出表头
				exportHeads(export,heads);			
	
				//输出第一个列表的内容
				exprotContent(export, lists.get(0), ++rowIndex, columns, addNumber ) ;
				
				
				//第一个列表已经输出过了
				int firstSize = lists.get(0).size();
				lists.remove(0);
	
				exportList(export,lists, addNumber, firstSize+rowIndex-1);
			}else {
				exportHeads(export,heads);		
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}
	private static void exportList(XlsExport export, List<List<?>> lists, boolean addNumber, int rowIndex) {
		

		
		for(List<?> list : lists){			
	
			
			List<Column> columns = exportTitle(export, list,  addNumber,  ++rowIndex);
				

			exprotContent(export, list, ++rowIndex, columns, addNumber ) ;
			
			rowIndex = list.size() + rowIndex -1;
		
		}
	}
	

	private static List<Column> exportTitle(XlsExport export, List<?> list, boolean addNumber, int rowIndex) {

		List<Column> columns = null;
		XSSFRow[] titleRows = null;
		

		int position = addNumber ? 0 : 1;
		
		if(export.isWriteTitle()) {
			
			// header
			export.createRow(export.getCurrXSSFSheet(),(rowIndex));
			
			export.getCurrRow().setHeight((short)400);
			if(addNumber) {
				export.setTitleCell(0, getNumberName());
			}
			
			columns = getColumn(list.get(0).getClass());
			for (Column column : columns) {
				export.setTitleCell(column.getNumber() - position , column.getName());
			}
			
			titleRows = new XSSFRow[1];
			titleRows[0] =export.getCurrXSSFSheet().getRow((rowIndex));
			
		}
		
		export.setTitleRow(titleRows);
		
		return columns;
	}


	protected static void exprotContent(XlsExport export, List<?> list,
                                        int rowIndex, List<Column> columns, boolean addNumber ) {

		int position = addNumber ? 0 : 1;
		
		if(list == null || list.isEmpty()) {
			return ;
		}
		
		if(columns == null) {
			columns = getColumn(list.get(0).getClass());
		}
		
		int index = 1;
		
		for(Object t : list){
			if(t == null) {
				continue;
			}
			export.createRow(export.getCurrXSSFSheet(),rowIndex++);
			
			if(export.specialHand(t, columns, addNumber)) {
				continue;
			}
			
			if(addNumber) {
				export.setCell(0 , index);
			}
			
			index ++;
			
			for (Column column : columns) {
				column.getField().setAccessible(true);
				try {
					Object value = column.getField().get(t);
					if(value == null) {
						export.setCell(column.getNumber()- position , "");
						continue;
					}
					
					setCellValue(export, position, column, value);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			}
		}
	}
	
	

	
	
	


}
