package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.utils.NumberKit;
import com.pm.dao.IProjectStaffCostDao;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.Salary;
import com.pm.service.IProjectStaffCostService;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProjectStaffCostServiceImpl implements IProjectStaffCostService {


	@Autowired IProjectStaffCostDao projectStaffCostDao;
	
	@Override
	public void addProjectStaffCost(List<ProjectStaffCost> projectStaffCosts) {
		for(ProjectStaffCost projectStaffCost : projectStaffCosts){
			projectStaffCostDao.addProjectStaffCost(projectStaffCost);
		}

	}

	@Override
	public void deleteProjectStaffCost(Salary[] salarys) {
		for(Salary salary : salarys){
			projectStaffCostDao.deleteProjectStaffCost(salary);
		}
	}

	
	@Override
	public Pager<ProjectStaffCost> queryProjectStaffCost(
			ProjectStaffCost projectStaffCost, UserPermit userPermit,
			Pager<ProjectStaffCost> pager) {
		Pager<ProjectStaffCost> projectStaffCostPage =  projectStaffCostDao.queryProjectStaffCost(projectStaffCost, userPermit, pager);
		ProjectStaffCost projectStaffCostSum = projectStaffCostDao.queryProjectStaffCostSum(projectStaffCost, userPermit);
		projectStaffCostPage.setResultObj(projectStaffCostSum);
		return projectStaffCostPage;
	}

	@Override
	public ProjectStaffCost getProjectStaffCost( ProjectStaffCost projectStaffCost) {
		return projectStaffCostDao.getProjectStaffCost(projectStaffCost);
	}

	@Override
	public List<ProjectStaffCost> getProjectStaffCost(Salary[] salarys) {		
		return projectStaffCostDao.getProjectStaffCost(salarys);
	}

	@Override
	public List<ProjectStaffCost> computeProjectStaffCost(Salary[] salarys) {
		List<ProjectStaffCost> list = new ArrayList<ProjectStaffCost>();
		for(Salary salary : salarys){
			List<ProjectStaffCost> tempList = projectStaffCostDao.computeProjectStaffCost(salary);
			if(tempList == null || tempList.size() == 0){
				throw new RuntimeException(salary.toSimpleString() + " 的考勤记录已经删除, 无法生成项目人员成本信息。");
			}else if(tempList.size() == 1) {				
				list.addAll(tempList);
			}else {
				
				double work_days = 0;
				for(ProjectStaffCost projectStaffCost : tempList){
					work_days += projectStaffCost.getWork_days();
				}


				double sum_personal_income_tax = 0;
				double sum_amount = 0;
				double sum_insurance_amount = 0;
				double sum_pub_funds_amount = 0;
				double sum_all_amount = 0;

				int count = tempList.size();



				if(work_days == 0){
					for(int index = 1 ; index < count ; index ++){
						ProjectStaffCost projectStaffCost = tempList.get(index);

						double personal_income_tax = NumberKit.getNumberFormatByDouble(projectStaffCost.getPersonal_income_tax()/tempList.size());
						double amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getAmount()/tempList.size());
						double insurance_amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getInsurance_amount()/tempList.size());
						double pub_funds_amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getPub_funds_amount()/tempList.size());
						double all_amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getAll_amount()/tempList.size());

						projectStaffCost.setPersonal_income_tax(personal_income_tax);
						projectStaffCost.setAmount(amount);
						projectStaffCost.setInsurance_amount(insurance_amount);
						projectStaffCost.setPub_funds_amount(pub_funds_amount);
						projectStaffCost.setAll_amount(all_amount);

						sum_personal_income_tax += personal_income_tax;
						sum_amount += amount;
						sum_insurance_amount += insurance_amount;
						sum_pub_funds_amount += pub_funds_amount;
						sum_all_amount += all_amount;
					}




				}else {
					for(int index = 1 ; index < count ; index ++){
						ProjectStaffCost projectStaffCost = tempList.get(index);
						double personal_income_tax = NumberKit.getNumberFormatByDouble(projectStaffCost.getPersonal_income_tax()*projectStaffCost.getWork_days()/work_days);
						double amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getAmount()*projectStaffCost.getWork_days()/work_days);
						double insurance_amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getInsurance_amount()*projectStaffCost.getWork_days()/work_days);
						double pub_funds_amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getPub_funds_amount()*projectStaffCost.getWork_days()/work_days);
						double all_amount = NumberKit.getNumberFormatByDouble(projectStaffCost.getAll_amount()*projectStaffCost.getWork_days()/work_days);

						projectStaffCost.setPersonal_income_tax(personal_income_tax);
						projectStaffCost.setAmount(amount);
						projectStaffCost.setInsurance_amount(insurance_amount);
						projectStaffCost.setPub_funds_amount(pub_funds_amount);
						projectStaffCost.setAll_amount(all_amount);

						sum_personal_income_tax += personal_income_tax;
						sum_amount += amount;
						sum_insurance_amount += insurance_amount;
						sum_pub_funds_amount += pub_funds_amount;
						sum_all_amount += all_amount;
					}
				}


				ProjectStaffCost projectStaffCost = tempList.get(0);
				projectStaffCost.setPersonal_income_tax(projectStaffCost.getPersonal_income_tax() - sum_personal_income_tax);
				projectStaffCost.setAmount(projectStaffCost.getAmount() - sum_amount);
				projectStaffCost.setInsurance_amount(projectStaffCost.getInsurance_amount() - sum_insurance_amount);
				projectStaffCost.setPub_funds_amount(projectStaffCost.getPub_funds_amount() - sum_pub_funds_amount);
				projectStaffCost.setAll_amount(projectStaffCost.getAll_amount() - sum_all_amount);

				list.addAll(tempList);
				
			}
				
		}
		return list;
	}

}
