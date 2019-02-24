package com.pm.service.impl;

import com.common.beans.Pager;
import com.pm.dao.IOtherStaffCostDao;
import com.pm.domain.business.OtherStaffCost;
import com.pm.domain.business.OtherSalary;
import com.pm.service.IOtherStaffCostService;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OtherStaffCostServiceImpl implements IOtherStaffCostService {


	@Autowired IOtherStaffCostDao otherStaffCostDao;
	
	@Override
	public void addOtherStaffCost(List<OtherStaffCost> otherStaffCosts) {
		for(OtherStaffCost otherStaffCost : otherStaffCosts){
			otherStaffCostDao.addOtherStaffCost(otherStaffCost);
		}

	}

	@Override
	public void deleteOtherStaffCost(OtherSalary[] salarys) {
		for(OtherSalary salary : salarys){
			otherStaffCostDao.deleteOtherStaffCost(salary);
		}
	}

	
	@Override
	public Pager<OtherStaffCost> queryOtherStaffCost(
			OtherStaffCost otherStaffCost, UserPermit userPermit,
			Pager<OtherStaffCost> pager) {
		Pager<OtherStaffCost> otherStaffCostPage =  otherStaffCostDao.queryOtherStaffCost(otherStaffCost, userPermit, pager);
		OtherStaffCost otherStaffCostSum = otherStaffCostDao.queryOtherStaffCostSum(otherStaffCost, userPermit);
		otherStaffCostPage.setResultObj(otherStaffCostSum);
		return otherStaffCostPage;
	}

	@Override
	public OtherStaffCost getOtherStaffCost( OtherStaffCost otherStaffCost) {
		return otherStaffCostDao.getOtherStaffCost(otherStaffCost);
	}

	@Override
	public List<OtherStaffCost> getOtherStaffCost(OtherSalary[] salarys) {		
		return otherStaffCostDao.getOtherStaffCost(salarys);
	}

	@Override
	public List<OtherStaffCost> computeOtherStaffCost(OtherSalary[] salarys) {
		List<OtherStaffCost> list = new ArrayList<OtherStaffCost>();
		for(OtherSalary salary : salarys){
			List<OtherStaffCost> tempList = otherStaffCostDao.computeOtherStaffCost(salary);
			if(tempList == null || tempList.size() == 0){
				throw new RuntimeException(salary.toSimpleString() + " 的总部人员考勤记录已经删除, 无法生成总部人员成本信息。");
			}else if(tempList.size() == 1) {				
				list.addAll(tempList);
			}else {
				
				double work_days = 0;
				for(OtherStaffCost otherStaffCost : tempList){
					work_days += otherStaffCost.getWork_days();
				}
				
				if(work_days == 0){
					for(OtherStaffCost otherStaffCost : tempList){
						otherStaffCost.setAmount(otherStaffCost.getAmount()/tempList.size());
						otherStaffCost.setInsurance_amount(otherStaffCost.getInsurance_amount()/tempList.size());
						otherStaffCost.setPub_funds_amount(otherStaffCost.getPub_funds_amount()/tempList.size());
						otherStaffCost.setAll_amount(otherStaffCost.getAll_amount()/tempList.size());
					}	
				}else {
					for(OtherStaffCost otherStaffCost : tempList){
						otherStaffCost.setPersonal_income_tax(otherStaffCost.getPersonal_income_tax()*otherStaffCost.getWork_days()/work_days);
						otherStaffCost.setAmount(otherStaffCost.getAmount()*otherStaffCost.getWork_days()/work_days);
						otherStaffCost.setInsurance_amount(otherStaffCost.getInsurance_amount()*otherStaffCost.getWork_days()/work_days);
						otherStaffCost.setPub_funds_amount(otherStaffCost.getPub_funds_amount()*otherStaffCost.getWork_days()/work_days);
						otherStaffCost.setAll_amount(otherStaffCost.getAll_amount()*otherStaffCost.getWork_days()/work_days);
					}	
				}				

				list.addAll(tempList);
				
			}
				
		}
		return list;
	}

}
