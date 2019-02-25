package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.utils.NumberKit;
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


				double sum_personal_income_tax = 0;
				double sum_amount = 0;
				double sum_insurance_amount = 0;
				double sum_pub_funds_amount = 0;
				double sum_all_amount = 0;

				int count = tempList.size();

				if(work_days == 0){
					for(int index = 1 ; index < count ; index ++){
						OtherStaffCost otherStaffCost = tempList.get(index);

						double personal_income_tax = NumberKit.getNumberFormatByDouble(otherStaffCost.getPersonal_income_tax()/tempList.size());
						double amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getAmount()/tempList.size());
						double insurance_amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getInsurance_amount()/tempList.size());
						double pub_funds_amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getPub_funds_amount()/tempList.size());
						double all_amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getAll_amount()/tempList.size());

						otherStaffCost.setPersonal_income_tax(personal_income_tax);
						otherStaffCost.setAmount(amount);
						otherStaffCost.setInsurance_amount(insurance_amount);
						otherStaffCost.setPub_funds_amount(pub_funds_amount);
						otherStaffCost.setAll_amount(all_amount);

						sum_personal_income_tax += personal_income_tax;
						sum_amount += amount;
						sum_insurance_amount += insurance_amount;
						sum_pub_funds_amount += pub_funds_amount;
						sum_all_amount += all_amount;
					}




				}else {
					for(int index = 1 ; index < count ; index ++){
						OtherStaffCost otherStaffCost = tempList.get(index);
						double personal_income_tax = NumberKit.getNumberFormatByDouble(otherStaffCost.getPersonal_income_tax()*otherStaffCost.getWork_days()/work_days);
						double amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getAmount()*otherStaffCost.getWork_days()/work_days);
						double insurance_amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getInsurance_amount()*otherStaffCost.getWork_days()/work_days);
						double pub_funds_amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getPub_funds_amount()*otherStaffCost.getWork_days()/work_days);
						double all_amount = NumberKit.getNumberFormatByDouble(otherStaffCost.getAll_amount()*otherStaffCost.getWork_days()/work_days);

						otherStaffCost.setPersonal_income_tax(personal_income_tax);
						otherStaffCost.setAmount(amount);
						otherStaffCost.setInsurance_amount(insurance_amount);
						otherStaffCost.setPub_funds_amount(pub_funds_amount);
						otherStaffCost.setAll_amount(all_amount);

						sum_personal_income_tax += personal_income_tax;
						sum_amount += amount;
						sum_insurance_amount += insurance_amount;
						sum_pub_funds_amount += pub_funds_amount;
						sum_all_amount += all_amount;
					}	
				}


				OtherStaffCost otherStaffCost = tempList.get(0);
				otherStaffCost.setPersonal_income_tax(otherStaffCost.getPersonal_income_tax() - sum_personal_income_tax);
				otherStaffCost.setAmount(otherStaffCost.getAmount() - sum_amount);
				otherStaffCost.setInsurance_amount(otherStaffCost.getInsurance_amount() - sum_insurance_amount);
				otherStaffCost.setPub_funds_amount(otherStaffCost.getPub_funds_amount() - sum_pub_funds_amount);
				otherStaffCost.setAll_amount(otherStaffCost.getAll_amount() - sum_all_amount);


				list.addAll(tempList);
				
			}
				
		}
		return list;
	}

}
