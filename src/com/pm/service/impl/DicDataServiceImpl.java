package com.pm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IDicDataDao;
import com.pm.domain.business.DicData;
import com.pm.domain.business.DicType;
import com.pm.service.IDicDataService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;

@Component
public class DicDataServiceImpl implements  IDicDataService {

	@Autowired IDicDataDao dicdataDao;
	
	

	public void preDicData(DicData dicdata){
		
		DicData preDicDate = dicdataDao.getDicDataByPre(dicdata);
		if(preDicDate != null && preDicDate.getId() != null){
			int dicSort = dicdata.getDic_sort();
			dicdata.setDic_sort(preDicDate.getDic_sort());
			dicdataDao.updateDicDataSort(dicdata);
			
			preDicDate.setDic_sort(dicSort);
			dicdataDao.updateDicDataSort(preDicDate);
			
		}
	}
	
	public void nextDicData(DicData dicdata){
		
		DicData nextDicDate = dicdataDao.getDicDataByNext(dicdata);
		if(nextDicDate != null && nextDicDate.getId() != null){
			int dicSort = dicdata.getDic_sort();
			dicdata.setDic_sort(nextDicDate.getDic_sort());
			dicdataDao.updateDicDataSort(dicdata);
			
			nextDicDate.setDic_sort(dicSort);
			dicdataDao.updateDicDataSort(nextDicDate);
			
		}
	}
	
	public void initDicDataSort(DicData dicdata){
		List<DicData> dicDatas = this.getDicDataByType(dicdata);
		if(dicDatas == null || dicDatas.isEmpty()) return;
		int count = dicDatas.size();
		for(int i=0; i < count ; i++){
			DicData temp = dicDatas.get(i);
			temp.setDic_sort(i+1);
			dicdataDao.updateDicDataSort(temp);
		}
	}
	

	public List<DicType> getAllDicType(){
		return dicdataDao.getAllDicType();
	}	

	public DicType getDicType(String id){
		return dicdataDao.getDicType(id);
	}
	
	
	
	@Override
	public int addDicData(DicData dicdata) {
		int max = dicdataDao.getMaxDicDataByType(dicdata);
		dicdata.setDic_sort(max);
		return dicdataDao.addDicData(dicdata);
	}

	@Override
	public int updateDicData(DicData dicdata) {
		return dicdataDao.updateDicData(dicdata);
	}
	

	public void recoverDicData(DicData[] dicdatas){
		for(DicData dicdata : dicdatas){
			int max = dicdataDao.getMaxDicDataByType(dicdata);
			dicdata.setDic_sort(max);
			dicdataDao.recoverDicData(dicdata);
		}
	}

	@Override
	public void deleteDicData(DicData[] dicdatas) {
		for(DicData dicdata : dicdatas){
			dicdataDao.deleteDicData(dicdata);
		}
	}

	@Override
	public DicData getDicData(String id) {
		return dicdataDao.getDicData(id);
	}
	

	@Override
	public List<DicData> getDicDataByType(DicData dicdata){
		return dicdataDao.getDicDataByType(dicdata);
	}
	
	@Override
	public <T> T get(String id) {		
		return (T)getDicData(id);
	}

	@Override
	public Pager<DicData> queryDicData(
		DicData dicdata,
		Pager<DicData> pager){
		return dicdataDao.queryDicData(dicdata,  pager);
	}
	
	

	@Override
	public Map<String, Map<String,DicData>> queryAllDicData(){
		
		Map<String, Map<String,DicData>> map = new HashMap<String, Map<String,DicData>>();
		DicData search = new DicData();
		search.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<DicData> page1 = dicdataDao.queryDicData(search,  PubMethod.getPagerByAll(DicData.class));
		if(page1.getResultList() == null) return map;
		for(DicData dicData: page1.getResultList()){
			Map<String,DicData> dataMap = map.get(dicData.getDic_type_id());
			if(dataMap == null){				
				dataMap = new HashMap<String,DicData>();
				map.put(dicData.getDic_type_id(), dataMap);
			}
			dataMap.put(dicData.getId(), dicData);
			dataMap.put(dicData.getDic_data_name(), dicData);
		}
		return map;		
	}




}