package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.IDKit;
import com.pm.dao.IPersonnelMonthlyCalculationDao;
import com.pm.dao.ISalaryDao;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.Salary;
import com.pm.service.IProjectStaffCostService;
import com.pm.service.ISalaryService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.vo.UserPermit;

/**
 * @author Administrator
 */
@Component
public class SalaryServiceImpl implements ISalaryService{


	@Autowired
	private ISalaryDao salaryDao;

	@Autowired
	private IProjectStaffCostService projectStaffCostService;
	

	@Autowired
	private IPersonnelMonthlyCalculationDao personnelMonthlyCalculationDao;
	

	@Override
	public Pager<Salary> querySalaryGroup(Salary salary, UserPermit userPermit, Pager<Salary> pager){
		return salaryDao.querySalaryGroup(salary, userPermit, pager);
	}

	@Override
	public void addSalary(Salary salary) {
		salaryDao.addSalary(salary);		
	}
	

	@Override
	public void addSalary(List<Salary> salarys) {		
		
		if(salarys == null || salarys.isEmpty()) {
			return ;
		}
		Salary deleteSalary = new Salary();
		deleteSalary.setProject_id(salarys.get(0).getProject_id());
		deleteSalary.setSalary_month(salarys.get(0).getSalary_month());
		salaryDao.deleteSalary(deleteSalary);
		
		for(Salary salary : salarys){
			salaryDao.addSalary(salary);	
		}
	}


	@Override
	public void updateSalary(List<Salary> salarys) {
		for(Salary salary : salarys){
			salaryDao.updateSalary(salary);		
		}
	}

	@Override
	public void deleteSalary(Salary[] salarys) {
		
		for(Salary salary : salarys){
			salaryDao.deleteSalary(salary);
		}		
	}

	@Override
	public void verifySalary(Salary[] salarys) {
		
		for(Salary salary : salarys){
			int size = salaryDao.verifySalary(salary);

			if(size == 0) {
				throw new PMException (CommonErrorConstants.e029901);
			}
		}	
		
		
		List<ProjectStaffCost> list = projectStaffCostService.computeProjectStaffCost(salarys);
		for(ProjectStaffCost projectStaffCost : list){
			projectStaffCost.setProject_staffcost_id(IDKit.generateId());
			projectStaffCost.setBuild_datetime(PubMethod.getCurrentDate());
			projectStaffCost.setBuild_userid(salarys[0].getVerify_userid());
			projectStaffCost.setBuild_username(salarys[0].getVerify_username());			
		}
		projectStaffCostService.addProjectStaffCost(list);
		
	}
	
	
	

	@Override
	public void unVerify(String id) {
		Salary salary = salaryDao.getSalary(id);		

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Salary salarySearch = new Salary();
		salarySearch.setProject_id(salary.getProject_id());
		salarySearch.setSalary_month(salary.getSalary_month());
		
		Pager<Salary> pager = this.querySalary(salarySearch, userPermit, PubMethod.getPagerByAll(null, Salary.class));
		
		if(pager.getResultList() != null){
			for(Salary salary1 : pager.getResultList()){
				projectStaffCostService.deleteProjectStaffCost(new Salary[]{salary1});
				int size = salaryDao.unVerifySalary(salary1);

				if(size == 0) {
					throw new PMException (CommonErrorConstants.e029902);
				}
			}
		}
		
	}
	

	@Override
	public Salary getSalary(String salary_id) {
		return salaryDao.getSalary(salary_id);
	}
	
	@Override
	public List<Salary> getSalaryByProjectMonth(Salary salary) {
		
		if(salary.getSalary_month() == 0) {
			return null;
		}
		if(salary.getProject_salary_number() == 0){
			/*int salary_month = salary.getSalary_month();
			if(salary_month%100 == 1){
				salary_month = (salary_month/100 - 1) * 100 + 12;
			}else{
				salary_month = salary_month - 1;
			}
			salary.setSalary_pre_month(salary_month);*/			
		}
		//初始化工资不用上月工资数据
		salary.setSalary_pre_month(-1);
		return salaryDao.getSalaryByProjectMonth(salary);
		
	}

	@Override
	public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(Salary search){
		return personnelMonthlyCalculationDao.getCurrSalaryByWorkAttendance(search);
	}

		

	@Override
	public Pager<Salary> querySalary(
			Salary salary, 
			UserPermit userPermit,
			Pager<Salary> pager) {		
		return salaryDao.querySalary(salary, userPermit, pager);
	}

}
