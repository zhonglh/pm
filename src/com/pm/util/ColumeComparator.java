package com.pm.util;

import java.util.Comparator;

import com.pm.util.excel.Colume;

public class ColumeComparator implements Comparator<Colume> {
	public int compare(Colume colume1,Colume colume2){
		int result = colume1.getNumber() - colume2.getNumber() ;
		if(result == 0){
			return colume1.getName().compareTo(colume2.getName());
		}else {
			return  result;
		}
	}

}
