package com.pm.util;

import java.util.Comparator;
import java.util.List;

import com.pm.domain.system.Permit;

public class PermitComparator implements Comparator<List<Permit>> {
	@Override
	public int compare(List<Permit> list1, List<Permit> list2){
		int result = list1.get(0).getGroup_no() - list2.get(0).getGroup_no() ;
		return result == 0 ? 0 : result/Math.abs(result);
	}

}
