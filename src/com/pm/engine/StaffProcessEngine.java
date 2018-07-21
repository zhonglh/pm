package com.pm.engine;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.StaffChilds;
import com.pm.domain.business.StaffExEntity;
import com.pm.service.IStaffChildsService;
import com.pm.service.IStaffSalaryDetailService;

/**
 * 处理人员上下级关系, 各种费用明细
 * @author zhonglihong
 * @date 2016年12月12日 下午3:26:00
 */
public class StaffProcessEngine {
	
	private static IStaffChildsService staffChildsService = SpringContextUtil.getBean(IStaffChildsService.class);
	private static IStaffSalaryDetailService staffSalaryDetailService = SpringContextUtil.getBean(IStaffSalaryDetailService.class);
	
	
	//处理员工上下级关系
	public static void processChild(String staff_id){
		staffChildsService.doProcessStaffChilds(staff_id);
	}
	public static void processChild(StaffExEntity staffEx){
		processChild(staffEx.getId());
	}
	//处理员工上下级关系
	public static void processChild(){
		//全部整理
		staffChildsService.doProcessStaffChilds(null);
	}
	
	
	
	

	//处理薪资明细项
	public static void processSalaryDetail(String staff_id){
		staffSalaryDetailService.doProcessStaffSalaryDetailByStaff(staff_id);
	}
	public static void processSalaryDetail(StaffExEntity staffEx){
		processSalaryDetail(staffEx.getId());
	}

	//处理薪资明细项
	public static void processSalaryDetail(){
		staffSalaryDetailService.doProcessStaffSalaryDetailByStaff(null);
	}


	
	
	
	public static void addStaffEx(StaffExEntity staffEx){
		processChild(staffEx);
		processSalaryDetail(staffEx);
	}

	public static void updateStaffEx(String staff_id){
		StaffExEntity staffEx = new StaffExEntity();
		staffEx.setStaff_id(staff_id);
		updateStaffEx(staffEx);
	}

	public static void updateStaffEx(StaffExEntity staffEx){
		processChild(staffEx);
		processSalaryDetail(staffEx);
	}
	
	public static void updateAllStaffEx(){
		processChild();
		processSalaryDetail();
	}
	
	/*

	public static void deleteStaffEx(String staff_id){
		if(staff_id == null ) return ;
		

		StaffChilds staffChilds = new StaffChilds();
		staffChilds.setStaff_id(staff_id);
		
		staffChildsService.deleteStaffChilds(new StaffChilds[]{staffChilds});
		staffSalaryDetailService.doProcessStaffSalaryDetailByStaff(staff_id);
		
	}
	*/
	

}



