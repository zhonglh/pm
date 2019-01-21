package com.pm.util.excel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.common.utils.DateKit;
import com.pm.vo.DepartStatisticsItem;

/**
 * @author Administrator
 */
public class DepartStatisticsExcel extends BusinessExcel{
	
	
	public static <T> void export(HttpServletResponse response,List<String> heads,List<List<DepartStatisticsItem>> lists ){		
		
		String currtime = DateKit.fmtDateToStr(new Date(),"yyyyMMddHHmmss");
		
		XlsSimpleExport export = new XlsSimpleExport();
		export(export,heads,lists);
		
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition","attachment;filename=departStat"+currtime+".xlsx");		
		export.exportXls(response);
	}
	

	
	
	public static <T> void export(XlsExport export,List<String> heads,List<List<DepartStatisticsItem>> lists) {
		
		
		try{
			
			int rowIndex = 0;
			int headLength = 0;
			
			if(heads != null) {
				headLength = heads.size();
			}
			
			rowIndex = headLength;
			
			export.createSheet((lists == null || lists.isEmpty()) ? null : lists.get(0),1,headLength+1);			
			
			exportHeads(export,heads);
			
	
			exprotContent(export, lists, rowIndex);
		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}

	}
	

	private static <T> void exprotContent(XlsExport export, List<List<DepartStatisticsItem>> lists, int rowIndex ) {		

		
		if(lists == null) {
			return ;
		}
		
		DecimalFormat    df   = new DecimalFormat("######0.0000");   

		
		int index = 0;
		
		for(List<DepartStatisticsItem> list: lists){
			if(list == null || list.isEmpty()) {
				continue;
			}
			export.createRow(export.getCurrXSSFSheet(),rowIndex++);
			
			
			
			
			
			int colnumber = 0;
			for (DepartStatisticsItem departStatistics : list) {
				try {
					
					if(departStatistics == null) {
						export.setCell(colnumber , "");
						continue;
					}
					
					Object value = departStatistics.getVal();
					
					
					
					if(colnumber == 0 || index == 0) {
						value = departStatistics.getItemName();
					}else{
							if(departStatistics.getVal() == 0){
								value =  new Double(0);
							}
					
					}
					
					if(value != null && value instanceof Double){
						if(!"%".equals(departStatistics.getFormatter())){
							export.setCurrencyCell(colnumber  , (Double)value ,
									(colnumber == 0) ? XSSFCellStyle.ALIGN_LEFT : XSSFCellStyle.ALIGN_RIGHT,
									"B".equals(departStatistics.getItemFormatter())	? true : false	);
						}else {
							
							value = df.format((Double)value/100);
							export.setPercentCell(colnumber  , new Double((String)value) ,
									(colnumber == 0) ? XSSFCellStyle.ALIGN_LEFT : XSSFCellStyle.ALIGN_RIGHT,
									"B".equals(departStatistics.getItemFormatter())	? true : false	);
						}
					}else{
						export.setCell(colnumber  , value == null ? "" : value.toString() ,
								(colnumber == 0) ? XSSFCellStyle.ALIGN_LEFT : XSSFCellStyle.ALIGN_RIGHT,
								"B".equals(departStatistics.getItemFormatter())	? true : false	);
					}
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
				colnumber ++;
			}
			
			

			index ++;
		}
	}	

}
