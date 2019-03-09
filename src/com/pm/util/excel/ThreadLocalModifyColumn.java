package com.pm.util.excel;

import java.util.List;

/**
 * 本地线程类， 用于记录需要改的列信息
 * @author zhonglihong
 * @date 2016年5月29日 下午11:33:58
 */
public class ThreadLocalModifyColumn {


	private static ThreadLocal<List<Column>> columns = new ThreadLocal<List<Column>>(){
		@Override
		public List<Column> initialValue(){
			return null;
		}
	};
	
	
	public static void setColumns(List<Column> columnList){
		columns.set(null);
		columns.set(columnList);
	}
	

	public static List<Column> getColumns(){
		return columns.get();
	}

}
