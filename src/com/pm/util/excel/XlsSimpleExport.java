package com.pm.util.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Administrator
 */
public class XlsSimpleExport extends XlsExport {

	protected Map<Integer,XSSFCellStyle> cellStyleMap = new HashMap<Integer,XSSFCellStyle>();

	public XlsSimpleExport() {
		this.workbook = new XSSFWorkbook();
	}
	
	

	
	public XlsSimpleExport(String tem) {
		try {
			InputStream input = new FileInputStream(tem);
			this.workbook = new XSSFWorkbook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sheet = workbook.createSheet();

		this.sheet.createFreezePane(0, 1);
		this.sheet.setDefaultColumnWidth(15);
	}


	/**
	 * 导出Excel文件
	 * 
	 * @throws RuntimeException
	 */
	public void exportXls(String xlsFileName) throws RuntimeException {
		exportXls(xlsFileName,workbook);
	}
	public void exportXls(String xlsFileName,XSSFWorkbook	workbook) throws RuntimeException {
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(xlsFileName);
			workbook.write(fOut);
			fOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("生成导出Excel文件出错!", e);
		} catch (IOException e) {
			throw new RuntimeException("写入Excel文件出错!", e);
		} finally {
			try {
				if (fOut != null) {
					fOut.close();
				}
			} catch (IOException e) {

			}
		}
	}
	

	@Override
	public XSSFCellStyle commonStyle(int cellIndex) {
		XSSFCellStyle cellStyle = cellStyleMap.get(cellIndex);
		if(cellStyle == null){
			cellStyle = commonStyle();
			cellStyleMap.put(cellIndex, cellStyle);
		}
		return cellStyle;
	}	

	
	public void exportXls(HttpServletResponse response) throws RuntimeException {
		exportXls(response,workbook);
	}
	public void exportXls(HttpServletResponse response,XSSFWorkbook	workbook) throws RuntimeException {
		ServletOutputStream os = null;
		SXSSFWorkbook sXSSFWorkbook = null;
		try {
			sXSSFWorkbook = new SXSSFWorkbook(workbook, 500);
			os = response.getOutputStream();
			sXSSFWorkbook.write(os);
			os.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("导出Excel文件出错!", e);
		} catch (IOException e) {
			throw new RuntimeException("导出Excel文件出错!", e);
		} finally {
			try{
				if(sXSSFWorkbook != null) {
					sXSSFWorkbook.close();
				}
			}catch(Exception e){
				
			}
			try {
				if (os != null)	{
					os.close();
				}
			} catch (IOException e) {
			}
		}
	}


}
