package com.pm.service;

import java.util.List;
import java.util.Map;

import com.common.beans.Pager;
import com.pm.domain.business.DicData;
import com.pm.domain.business.DicType;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IDicDataService extends IGeneralLogService{
	
	
	public void preDicData(DicData dicdata);
	public void nextDicData(DicData dicdata);
	public void initDicDataSort(DicData dicdata);
	

	public List<DicType> getAllDicType();
	public DicType getDicType(String id);

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_DICDATA)
	public int addDicData(DicData dicdata) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_DICDATA)
	public int updateDicData(DicData dicdata) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_DICDATA)
	public void deleteDicData(DicData[] dicdatas) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_RECOVER,entity_type=LogConstant.ENTITY_DICDATA)
	public void recoverDicData(DicData[] dicdatas) ;
	

	public DicData getDicData(String id) ;	
	
	public List<DicData> getDicDataByType(DicData dicdata);

	public List<DicData> getAllDicDataByType(DicData dicdata);
	
	public Map<String, Map<String,DicData>> queryAllDicData();

	public Pager<DicData> queryDicData(DicData dicdata,  Pager<DicData> pager);

}