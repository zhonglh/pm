package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.IDKit;
import com.pm.dao.IOtherSalaryDao;
import com.pm.dao.IPersonnelMonthlyCalculationDao;
import com.pm.dao.ISalaryDao;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.OtherSalary;
import com.pm.service.IOtherSalaryService;
import com.pm.service.IProjectStaffCostService;
import com.pm.service.ISalaryService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class OtherSalaryServiceImpl implements IOtherSalaryService {


	@Autowired
	private IOtherSalaryDao salaryDao;

	

	@Override
	public Pager<OtherSalary> querySalaryGroup(OtherSalary salary, UserPermit userPermit, Pager<OtherSalary> pager){
		return salaryDao.querySalaryGroup(salary, userPermit, pager);
	}

	@Override
	public void addSalary(OtherSalary salary) {
		salaryDao.addSalary(salary);		
	}
	

	@Override
	public void addSalary(List<OtherSalary> salarys) {		
		
		if(salarys == null || salarys.isEmpty()) {
			return ;
		}
		OtherSalary deleteSalary = new OtherSalary();
		deleteSalary.setDept_id(salarys.get(0).getDept_id());
		deleteSalary.setSalary_month(salarys.get(0).getSalary_month());
		salaryDao.deleteSalary(deleteSalary);
		
		for(OtherSalary salary : salarys){
			salaryDao.addSalary(salary);	
		}
	}


	@Override
	public void updateSalary(List<OtherSalary> salarys) {
		for(OtherSalary salary : salarys){
			salaryDao.updateSalary(salary);		
		}
	}

	@Override
	public void deleteSalary(OtherSalary[] salarys) {
		
		for(OtherSalary salary : salarys){
			salaryDao.deleteSalary(salary);
		}		
	}

	@Override
	public void verifySalary(OtherSalary[] salarys) {
		
		for(OtherSalary salary : salarys){
			int size = salaryDao.verifySalary(salary);

			if(size == 0) {
				throw new PMException (CommonErrorConstants.e029901);
			}
		}
		
	}
	
	
	

	@Override
	public void unVerify(String id) {
		OtherSalary salary = salaryDao.getSalary(id);		

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		OtherSalary salarySearch = new OtherSalary();
		salarySearch.setDept_id(salary.getDept_id());
		salarySearch.setSalary_month(salary.getSalary_month());
		
		Pager<OtherSalary> pager = this.querySalary(salarySearch, userPermit, PubMethod.getPagerByAll(null, OtherSalary.class));
		
		if(pager.getResultList() != null){
			for(OtherSalary salary1 : pager.getResultList()){
				//todo 要加上
				//projectStaffCostService.deleteProjectStaffCost(new OtherSalary[]{salary1});
				int size = salaryDao.unVerifySalary(salary1);

				if(size == 0) {
					throw new PMException (CommonErrorConstants.e029902);
				}
			}
		}
		
	}
	

	@Override
	public OtherSalary getSalary(String salary_id) {
		return salaryDao.getSalary(salary_id);
	}
	
	@Override
	public List<OtherSalary> getSalaryByDeptMonth(OtherSalary salary) {
		
		if(salary.getSalary_month() == 0) {
			return null;
		}
		if(salary.getDept_salary_number() == 0){
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
		return salaryDao.getSalaryByDeptMonth(salary);
		
	}


	@Override
	public List<OtherSalary> getAccumulatedSalary(int startSalaryMonth , int endSalaryMonth , List<String> staffCostIds){
		return salaryDao.getAccumulatedSalary(startSalaryMonth,endSalaryMonth,staffCostIds);
	}

	/*@Override
	public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(OtherSalary search){
		return personnelMonthlyCalculationDao.getCurrSalaryByWorkAttendance(search);
	}*/

		

	@Override
	public Pager<OtherSalary> querySalary(
			OtherSalary salary, 
			UserPermit userPermit,
			Pager<OtherSalary> pager) {		
		return salaryDao.querySalary(salary, userPermit, pager);
	}

}
