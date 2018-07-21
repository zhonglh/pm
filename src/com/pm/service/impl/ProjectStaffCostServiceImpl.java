package com.pm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IProjectStaffCostDao;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.Salary;
import com.pm.service.IProjectStaffCostService;
import com.pm.vo.UserPermit;


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
		return projectStaffCostDao.queryProjectStaffCost(projectStaffCost, userPermit, pager);
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
				
				if(work_days == 0){
					for(ProjectStaffCost projectStaffCost : tempList){
						projectStaffCost.setAmount(projectStaffCost.getAmount()/tempList.size());
						projectStaffCost.setInsurance_amount(projectStaffCost.getInsurance_amount()/tempList.size());
						projectStaffCost.setPub_funds_amount(projectStaffCost.getPub_funds_amount()/tempList.size());
						projectStaffCost.setAll_amount(projectStaffCost.getAll_amount()/tempList.size());
					}	
				}else {
					for(ProjectStaffCost projectStaffCost : tempList){
						projectStaffCost.setPersonal_income_tax(projectStaffCost.getPersonal_income_tax()*projectStaffCost.getWork_days()/work_days);
						projectStaffCost.setAmount(projectStaffCost.getAmount()*projectStaffCost.getWork_days()/work_days);
						projectStaffCost.setInsurance_amount(projectStaffCost.getInsurance_amount()*projectStaffCost.getWork_days()/work_days);
						projectStaffCost.setPub_funds_amount(projectStaffCost.getPub_funds_amount()*projectStaffCost.getWork_days()/work_days);
						projectStaffCost.setAll_amount(projectStaffCost.getAll_amount()*projectStaffCost.getWork_days()/work_days);
					}	
				}				

				list.addAll(tempList);
				
			}
				
		}
		return list;
	}

}
