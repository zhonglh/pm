package com.pm.excel.persoonnelmonthly;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.common.exceptions.PMException;
import com.common.utils.AjaxJson;
import com.common.utils.IDKit;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.PersonnelMonthlyBase;
import com.pm.domain.business.StaffCost;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumPersonnelMonthlyType;

public abstract class AbstractPMExcel<T extends PersonnelMonthlyBase> {
	IApplyApproveService applyApproveService = null;
	
	public AbstractPMExcel(IApplyApproveService applyApproveService){
		this.applyApproveService = applyApproveService;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getT(){
		return (Class<T> )((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
	}
	
	public List<T> getPersonnelMonthly(
			final Map<String,List<String[]>> map,
			final int the_month,
			EnumPersonnelMonthlyType enumType,
			Map<String,InsuranceGrade> insuranceGrades ,
			final Map<String,StaffCost>  staffCostNoMap,
			final Map<String,List<StaffCost>>  staffCostNameMap
			){

		try{			
			List<String[]> baseList = map.get(enumType.getName());
			if(baseList != null && !baseList.isEmpty()){
				List<T> bases = PubMethod.stringArray2List(baseList, getT());
				if(bases != null && bases.size() > 0){
					if("000000".equals(bases.get(0).getStaff_no())) {
						bases.remove(0);
					}
				}
				
				if(bases != null && bases.size() > 0){
					for(T base : bases){
						StaffCost staffCost = checkPersonnelMonthlyBase(base,the_month,enumType,staffCostNoMap,staffCostNameMap);
						checkPersonnelMonthly(base,staffCost,insuranceGrades);
					}
				}
				return bases;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList<T>();
	}
	

	public List<T> savePersonnelMonthly(
			final Map<String,List<String[]>> map,
			final int the_month,
			EnumPersonnelMonthlyType enumType,
			Map<String,InsuranceGrade> insuranceGrades ,
			final Map<String,StaffCost>  staffCostNoMap,
			final Map<String,List<StaffCost>>  staffCostNameMap,
			User sessionUser, AjaxJson ajaxJson) {
		
		List<T> bases = this.getPersonnelMonthly(map, the_month, enumType, insuranceGrades, staffCostNoMap, staffCostNameMap);
		if(bases == null ) {
			return new ArrayList<T>();
		}
		
		for(T base : bases){
			if(StringUtils.isEmpty(base.getErrorInfo())){
				try{
					base.setId(IDKit.generateId());
					base.setBuild_datetime(PubMethod.getCurrentDate());
					base.setBuild_userid(sessionUser.getUser_id());
					base.setBuild_username(sessionUser.getUser_name());
					int count = addPersonnelMonthly(base);
					if(count == 0){
						base.setErrorInfo("已经有此记录");
						ajaxJson.setOk(false);
						continue;
					}
					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.PERSONNELMONTHLYBASE.name(), base.getId(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
				}catch(PMException e){
					base.setErrorInfo(e.getErrmsg());
					ajaxJson.setOk(false);
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().toLowerCase().indexOf("key")!=-1 || e.getMessage().toLowerCase().indexOf("index")!=-1) {
						base.setErrorInfo("已经有此记录");
					}else {
						base.setErrorInfo(e.getMessage());
					}
					ajaxJson.setOk(false);
				}
			}else {
				ajaxJson.setOk(false);
			}
		}
		return bases;
	}	


	private StaffCost checkPersonnelMonthlyBase(
			PersonnelMonthlyBase personnelMonthlyBase,	
			int the_month,
			EnumPersonnelMonthlyType type,
			Map<String,StaffCost>  staffCostNoMap , 
			Map<String,List<StaffCost>>  staffCostNameMap){
		
		personnelMonthlyBase.setMonthly_type(type.getId());
		personnelMonthlyBase.getMonthly_type_name();
		personnelMonthlyBase.setThe_month(the_month);

		StaffCost staffCost = null;
		if(StringUtils.isEmpty(personnelMonthlyBase.getStaff_no()) && StringUtils.isEmpty(personnelMonthlyBase.getStaff_name())){
			personnelMonthlyBase.setErrorInfo(personnelMonthlyBase.getErrorInfo() + "先输入工号或者姓名;");
		}else if(!StringUtils.isEmpty(personnelMonthlyBase.getStaff_no())){
			staffCost = staffCostNoMap.get(personnelMonthlyBase.getStaff_no());
			if(staffCost == null) {
				personnelMonthlyBase.setErrorInfo(personnelMonthlyBase.getErrorInfo() + "工号错误;");
			}
		}else if(!StringUtils.isEmpty(personnelMonthlyBase.getStaff_name())){
			List<StaffCost> scs = staffCostNameMap.get(personnelMonthlyBase.getStaff_name());
			if(scs == null || scs.isEmpty())  {
				personnelMonthlyBase.setErrorInfo(personnelMonthlyBase.getErrorInfo() + "姓名错误;");
			}
			else if(scs.size() > 1)  {
				personnelMonthlyBase.setErrorInfo(personnelMonthlyBase.getErrorInfo() + "姓名有重名;");
			}
			else {
				staffCost = scs.get(0);
			}
		}
		
		if(staffCost == null) {
			return null;
		}
		
		personnelMonthlyBase.setStaff_id(staffCost.getStaff_id());
		personnelMonthlyBase.setStaff_no(staffCost.getStaff_no());
		personnelMonthlyBase.setStaff_name(staffCost.getStaff_name());	
		personnelMonthlyBase.setProject_name(staffCost.getProject_name());		
		personnelMonthlyBase.setProject_id(staffCost.getProject_id());			
		return staffCost;			
	}
	
	public abstract boolean checkPersonnelMonthly(T base , StaffCost staffCost , Map<String,InsuranceGrade> insuranceGrades);
	public abstract int addPersonnelMonthly(T base);

}
