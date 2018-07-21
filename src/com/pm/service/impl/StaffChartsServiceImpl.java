package com.pm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.el.ElUtil;
import com.common.utils.PersonalTaxComputer;
import com.pm.dao.IStaffChartsDao;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffExEntity;
import com.pm.domain.business.StaffSalaryDetail;
import com.pm.domain.system.ItemDefine;
import com.pm.service.IStaffChartsService;
import com.pm.util.PubMethod;
import com.pm.util.charts.ChartsUtil;
import com.pm.util.charts.TypeConversion;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumItemDirection;
import com.pm.vo.UserPermit;
import com.pm.vo.echarts.series.Data;
import com.pm.vo.echarts.series.Pie;
import com.pm.vo.echarts.series.Series;
import com.pm.vo.reports.SmallItem;
import com.pm.vo.reports.StaffCharts;
import com.pm.vo.reports.StaffCountVO;

@Component
public class StaffChartsServiceImpl implements IStaffChartsService {

	@Autowired
	IStaffChartsDao staffChartsDao;
	
	
	
	@Override
	public List<StaffCharts> getChilds(Map<String, Object> param) {
		return staffChartsDao.getChilds(param);
	}

	@Override
	public List<StaffCharts> getParents(Map<String, Object> param) {
		return staffChartsDao.getParents(param);
	}
	

	@Override
	public List<StaffCountVO> getStaffSalaryCatalog(){
		return staffChartsDao.getStaffSalaryCatalog();
	}

	@Override
	public List<StaffCountVO> getStaffChilds(){
		return staffChartsDao.getStaffChilds();
	}
	

	public Pager<StaffCountVO> queryStaffExEntity(StaffCountVO staffCountVO,  UserPermit userPermit,Pager<StaffCountVO> pager){
		return staffChartsDao.queryStaffExEntity(staffCountVO, userPermit, pager);
	}


	@Override
	public Series getOriginalModel1(double taxRate, Map<String, Object> ctx, Map<String, Object> marketMap,
			StaffExEntity staffExEntity, StaffCost staffCost) {

		double sum = 0;
		List<SmallItem> list1 = new ArrayList<SmallItem>(); 
		SmallItem smallItem = new SmallItem();
		smallItem.setName("正式工资");
		smallItem.setValue(staffCost.getOfficial_salary());
		sum += smallItem.getValue();
		list1.add(smallItem);
		
		if(staffCost.getComputer_allowance() >0){
			smallItem = new SmallItem();
			smallItem.setName("电脑补助");
			smallItem.setValue(staffCost.getComputer_allowance());
			sum += smallItem.getValue();
			list1.add(smallItem);
		}

		if(staffCost.getProject_allowance() >0){
			smallItem = new SmallItem();
			smallItem.setName("奖金");
			smallItem.setValue(staffCost.getProject_allowance());
			sum += smallItem.getValue();
			list1.add(smallItem);
		}
		

		if(staffCost.getMeal_allowance() >0){
			smallItem = new SmallItem();
			smallItem.setName("餐补");
			smallItem.setValue(staffCost.getMeal_allowance()*22);
			sum += smallItem.getValue();
			list1.add(smallItem);
		}
		


		if(staffCost.getTravel_allowance() >0){
			smallItem = new SmallItem();
			smallItem.setName("出差补助");
			smallItem.setValue(staffCost.getTravel_allowance());
			sum += smallItem.getValue();
			list1.add(smallItem);
		}
		
		Pie pie = new Pie();
		pie.setName(BusinessUtil.orgName[0]+"("+sum+")");
		pie.setData(TypeConversion.toDatas(list1));
		pie.setSum(sum);
		return pie;
	}

	@Override
	public Series getOriginalModel2(double taxRate, Map<String, Object> ctx, Map<String, Object> marketMap,
			StaffExEntity staffExEntity, StaffCost staffCost) {

		List<SmallItem> list = new ArrayList<SmallItem>(); 
		SmallItem smallItem = new SmallItem();
		smallItem.setName("社保个人部分");
		double social_insurance_personal = ChartsUtil.getSocialInsurancePersonal(staffCost);	
		social_insurance_personal = PubMethod.getNumberFormatByDouble(social_insurance_personal);
		smallItem.setValue(social_insurance_personal);
		list.add(smallItem);
		Pie pie = new Pie();
		pie.setName(BusinessUtil.orgName[1]+"("+social_insurance_personal+")");
		pie.setData(TypeConversion.toDatas(list));
		pie.setSum(social_insurance_personal);
		return pie;
	}

	@Override
	public Series getOriginalModel3(double taxRate, Map<String, Object> ctx, StaffExEntity staffExEntity,
			StaffCost staffCost, double should_salary) {

		List<SmallItem> list = new ArrayList<SmallItem>(); 

		
		double taxrate = PersonalTaxComputer.getIncomeTax( should_salary );
		double actual_salary = PubMethod.getNumberFormatByDouble(should_salary - taxrate);
		

		SmallItem smallItem = new SmallItem();
		smallItem.setName("实发工资");
		smallItem.setValue(actual_salary);
		list.add(smallItem);
		
		smallItem = new SmallItem();
		smallItem.setName("个税");		
		smallItem.setValue(taxrate );
		list.add(smallItem);

		
		Pie pie = new Pie();
		pie.setName(BusinessUtil.orgName[2]+"("+(should_salary)+")");
		pie.setData(TypeConversion.toDatas(list));
		pie.setSum(should_salary);
		return pie;
	}
	
	

	@Override
	public Series getMarketModel1(double taxRate, Map<String, Object> ctx, Map<String, Object> marketMap,
			StaffExEntity staffExEntity, StaffCost staffCost, List<StaffSalaryDetail>staffSalaryDetails) {
		

		List<SmallItem> list = new ArrayList<SmallItem>(); 
		double sum = 0;
		List<StaffCharts> staffChartss = staffChartsDao.getChilds(marketMap);
		if(staffChartss != null && staffChartss.size() >0){
			for(StaffCharts staffCharts : staffChartss){
				double after_tax_income = staffCharts.getQustomerquotes() * (1 - taxRate);
				SmallItem smallItem = new SmallItem();
				smallItem.setName(StringUtils.isEmpty(staffCharts.getName_())?staffCharts.getStaff_name() : staffCharts.getName_());
				smallItem.setValue(PubMethod.getNumberFormatByDouble( staffCharts.getRatio_() * after_tax_income));
				list.add(smallItem);
				sum += smallItem.getValue();
			}
		}			
		
		if(staffSalaryDetails != null && staffSalaryDetails.size() >0){
			for(StaffSalaryDetail staffSalaryDetail : staffSalaryDetails){

				
				//只处理收入部分
				if(staffSalaryDetail.getItem_direction().equals(EnumItemDirection.Income.getCode())){
					SmallItem smallItem = new SmallItem();
					smallItem.setName(staffSalaryDetail.getItem_name());
					double value = staffSalaryDetail.getBal();
					smallItem.setValue(value);
					sum += value;
					list.add(smallItem);
				}
			}
		}		

		SmallItem smallItem = new SmallItem();
		smallItem.setName("税后技术费用");
		double after_tax_income = staffCost.getQustomerquotes() * (1 - taxRate);
		smallItem.setValue(PubMethod.getNumberFormatByDouble( after_tax_income ));
		list.add(smallItem);
		sum += smallItem.getValue();

		sum = PubMethod.getNumberFormatByDouble(sum);
		Pie pie = new Pie();
		pie.setSum(sum);
		pie.setName(BusinessUtil.orgName[0]+"("+sum+")");
		if(list.size() >0){
			pie.setData(TypeConversion.toDatas(list));
		}else {
			Data data = new Data();
			data.setValue(0);
			data.setName("无");
			pie.setData(new Data[]{data});		

		}
		
		return pie;
	}

	@Override
	public Series getMarketModel2(double taxRate, Map<String, Object> ctx, Map<String, Object> marketMap,
			StaffExEntity staffExEntity, StaffCost staffCost,List<StaffSalaryDetail> staffSalaryDetails) {

		

		List<SmallItem> list = new ArrayList<SmallItem>(); 
		ElUtil elUtil = (ElUtil)ctx.get(ElUtil.class.getSimpleName());
		
		double sum = 0;


		if(staffSalaryDetails != null && staffSalaryDetails.size() >0){
			for(StaffSalaryDetail staffSalaryDetail : staffSalaryDetails){				
				//只处理支出部分
				if(staffSalaryDetail.getItem_direction().equals(EnumItemDirection.Pay.getCode())){
					SmallItem smallItem = new SmallItem();
					smallItem.setName(staffSalaryDetail.getItem_name());
					double value = staffSalaryDetail.getBal();
					smallItem.setValue(value);
					sum += value;
					list.add(smallItem);
				}
			}
		}		
		
		List<StaffCharts> staffChartss = staffChartsDao.getParents(marketMap);
		if(staffChartss != null && staffChartss.size() >0){
			for(StaffCharts staffCharts : staffChartss){
				double after_tax_income = staffCharts.getQustomerquotes() * (1 - taxRate);
				SmallItem smallItem = new SmallItem();
				smallItem.setName(StringUtils.isEmpty(staffCharts.getName_())?staffCharts.getStaff_name() : staffCharts.getName_());
				smallItem.setValue(PubMethod.getNumberFormatByDouble( staffCharts.getRatio_() * after_tax_income ));
				list.add(smallItem);
				sum += smallItem.getValue();
			}
		}
		
		sum = PubMethod.getNumberFormatByDouble(sum);
		Pie pie = new Pie();
		pie.setSum(sum);
		pie.setName(BusinessUtil.orgName[1]+"("+sum+")");
		if(list.size() >0){
			pie.setData(TypeConversion.toDatas(list));
		}else {
			Data data = new Data();
			data.setValue(0);
			data.setName("无");
			pie.setData(new Data[]{data});		
		}
		
		return pie;
	}

	@Override
	public Series getMarketModel3(double taxRate, Map<String, Object> ctx, StaffExEntity staffExEntity,
			StaffCost staffCost, double should_salary) {
		
		List<SmallItem> list = new ArrayList<SmallItem>();
		
		double personal_income_tax = PersonalTaxComputer.getIncomeTax(
					should_salary - ChartsUtil.getSocialInsurancePersonal(staffCost) - staffExEntity.getCredit_tax_amount()
				);
		
		double actual_salary = PubMethod.getNumberFormatByDouble(
					should_salary - personal_income_tax - 
					ChartsUtil.getSocialInsurancePersonal(staffCost) - ChartsUtil.getsocialInsuranceCompany(staffCost)
				);
		
		

		SmallItem smallItem1 = new SmallItem();
		smallItem1.setName("实发工资");
		smallItem1.setValue(actual_salary);
		list.add(smallItem1);
		
		SmallItem smallItem = new SmallItem();
		smallItem.setName("个税");
		smallItem.setValue(personal_income_tax);
		list.add(smallItem);
		

		

		SmallItem smallItem2 = new SmallItem();
		smallItem2.setName("社保个人部分");
		smallItem2.setValue(ChartsUtil.getSocialInsurancePersonal(staffCost));
		list.add(smallItem2);
		

		SmallItem smallItem3 = new SmallItem();
		smallItem3.setName("社保公司部分");
		smallItem3.setValue(ChartsUtil.getsocialInsuranceCompany(staffCost));
		list.add(smallItem3);
		
		
		
		Pie pie = new Pie();
		pie.setSum(should_salary);
		pie.setName(BusinessUtil.orgName[2]+"("+PubMethod.getNumberFormatByDouble(should_salary)+")");
		if(list.size() >0){
			pie.setData(TypeConversion.toDatas(list));
		}else {
			Data data = new Data();
			data.setValue(0);
			data.setName("无");
			pie.setData(new Data[]{data});		
		}
		
		return pie;
		
	}

}
