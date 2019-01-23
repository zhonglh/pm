package com.pm.util;

import java.util.Comparator;

import com.pm.util.excel.Column;

public class ColumnComparator implements Comparator<Column> {
	@Override
	public int compare(Column column1, Column column2){
		int result = column1.getNumber() - column2.getNumber() ;
		if(result == 0){
			return column1.getName().compareTo(column2.getName());
		}else {
			return  result;
		}
	}

}
