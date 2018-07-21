package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.DicData;
import com.pm.domain.business.DicType;

public interface IDicDataDao {
	
	
	public int getMaxDicDataByType(DicData dicdata);	
	public DicData getDicDataByPre(DicData dicdata) ;
	public DicData getDicDataByNext(DicData dicdata) ;
	
	
	public List<DicType> getAllDicType();
	public DicType getDicType(String id);

	public int addDicData(DicData dicdata) ;

	public int updateDicData(DicData dicdata) ; 
	public int updateDicDataSort(DicData dicdata) ; 
	

	public void recoverDicData(DicData dicdata) ;

	public void deleteDicData(DicData dicdata) ;


	public DicData getDicData(String id) ;	
	

	public List<DicData> getDicDataByType(DicData dicdata);

	public Pager<DicData> queryDicData(DicData dicdata,  Pager<DicData> pager);

}