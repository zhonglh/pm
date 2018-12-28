<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/SalaryGroupAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		



			<p>
				<label>工资月份：</label>
				
				<label>${salary1.salary_month } <input type="hidden" name="salary_month" value="${salary1.salary_month }" /></label>
			</p>	
			
			<p>
				<label>项目名称：</label>
				<input type="hidden"   name="project_id" value="${salary1.project_id }"/>
				<input name="project_no" type="hidden" value="${salary1.project_no }"  />
				<input name="project_name" type="hidden" value="${salary1.project_name }"  />
				<label>${salary1.project_name }</label>
			</p>
			
						
			
			<div class="divider"></div>
			
			<table class="list nowrap salaryComputer" width="4340" layoutH="175">
				<thead>
					<tr>
					
						<th width="22"><input type="checkbox" group="id" class="checkboxCtrl" /></th>
						<th width="40" nowrap>序号</th>						
						<th width="80" nowrap>姓名</th>
						<th width="80" nowrap>基本工资</th>	
						<th width="80" nowrap>岗位工资</th>	
						<th width="80" nowrap>绩效津贴</th>	
						<th width="80" nowrap>出差补助</th>	
						<th width="80" nowrap>奖金(税)</th>	
						<th width="80" nowrap>应出勤天数</th>	
						<th width="90" nowrap>实际工作天数</th>	
						<th width="60" nowrap>带薪假</th>	
						<th width="60" nowrap>出差</th>						
						<th width="80" nowrap>事假天数</th>
						<th width="80" nowrap>病假天数</th>
						<th width="80" nowrap>旷工天数</th>
						<th width="80" nowrap>迟到天数</th>
						<th width="80" nowrap>加班天数</th>
						<th width="80" nowrap>病假工资</th>
						<th width="80" nowrap>旷工工资</th>
						<th width="80" nowrap>迟到工资</th>
						<th width="80" nowrap>出差补贴</th>
						<th width="80" nowrap>电脑补贴</th>
						<th width="80" nowrap>额外补贴</th>
						<th width="80" nowrap>加班补贴</th>
						<th width="60" nowrap>餐补</th>
						<th width="120" nowrap>实发奖金(税前)</th>
						<!-- <th width="80" nowrap>额外支出</th>	 -->
						<th width="80" nowrap><b>应发工资</b></th>
											
						<th width="80" nowrap>养老个人</th>
						<th width="80" nowrap>养老单位</th>
						<th width="80" nowrap>医疗个人</th>
						<th width="80" nowrap>医疗单位</th>
						<th width="80" nowrap>失业个人</th>
						<th width="80" nowrap>失业单位</th>
						<th width="80" nowrap>生育单位</th>
						<th width="80" nowrap>工伤单位</th>
						<th width="100" nowrap>公积金个人</th>
						<th width="100" nowrap>公积金单位</th>


						<th width="80" nowrap>子女教育</th>
						<th width="80" nowrap>继续教育</th>
						<th width="120" nowrap>住房贷款利息</th>
						<th width="80" nowrap>住房租金</th>
						<th width="80" nowrap>赡养老人</th>



						<th width="120" nowrap>准许扣除的费用</th>
						<th width="120" nowrap>应纳税所得额</th>
						<th width="80" nowrap>所得税</th>
						<th width="80" nowrap>奖金（后）</th>
						<th width="80" nowrap>补税工资</th>
						<th width="80" nowrap><b>实发工资</b></th>
						<th width="280" nowrap><b>说明</b></th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="salary"  varStatus="status1" items="${list}">
					<tr > 
						<td><input name="id" value="${status1.index + 1 }" type="checkbox" checked="checked" /></td>
						<td nowrap>${status1.index + 1 }</td>
						<td nowrap>
							<input type="hidden"   name="staff_id${status1.index + 1 }" value="${salary.staff_id }"/>
							<input type="hidden"   name="staff_name${status1.index + 1 }" value="${salary.staff_name }"/>	
							<c:if test="${salary.showhl == 1 }"><font color="red"><b></c:if>					
							${salary.staff_name }
							<c:if test="${salary.showhl == 1 }"></b></font></c:if>
							
						</td>
						
						<td><input type="text" size="7" maxlength="10"  id="basic_salary" name="basic_salary${status1.index + 1 }" class="number required" value="${salary.basic_salary }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="post_salary" name="post_salary${status1.index + 1 }" class="number required" value="${salary.post_salary }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="performance_allowances" name="performance_allowances${status1.index + 1 }" class="number required" value="${salary.performance_allowances }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="travel_allowance" name="travel_allowance${status1.index + 1 }" class="number required" value="${salary.travel_allowance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="tax_bonus" name="tax_bonus${status1.index + 1 }" class="number required" value="${salary.tax_bonus }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="should_work_days" name="should_work_days${status1.index + 1 }" class="number required" value="${salary.should_work_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="work_days" name="work_days${status1.index + 1 }" class="number required" value="${salary.work_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="paid_leave_days" name="paid_leave_days${status1.index + 1 }" class="number required" value="${salary.paid_leave_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="business_trip_days" name="business_trip_days${status1.index + 1 }" class="number required" value="${salary.business_trip_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="personal_leave_days" name="personal_leave_days${status1.index + 1 }" class="number required" value="${salary.personal_leave_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="sick_leave_days" name="sick_leave_days${status1.index + 1 }" class="number required" value="${salary.sick_leave_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="neglect_work_days" name="neglect_work_days${status1.index + 1 }" class="number required" value="${salary.neglect_work_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="late_days" name="late_days${status1.index + 1 }" class="number required" value="${salary.late_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="weekend_overtime_days" name="weekend_overtime_days${status1.index + 1 }" class="number required" value="${salary.weekend_overtime_days }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="sick_leave_salary" name="sick_leave_salary${status1.index + 1 }" class="number required" value="${salary.sick_leave_salary }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="neglect_work_salary" name="neglect_work_salary${status1.index + 1 }" class="number required" value="${salary.neglect_work_salary }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="late_salary" name="late_salary${status1.index + 1 }" class="number required" value="${salary.late_salary }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="actual_travel_allowance" name="actual_travel_allowance${status1.index + 1 }" class="number required" value="${salary.actual_travel_allowance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="actual_computer_allowance" name="actual_computer_allowance${status1.index + 1 }" class="number required" value="${salary.actual_computer_allowance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="actual_extra_allowance" name="actual_extra_allowance${status1.index + 1 }" class="number required" value="${salary.actual_extra_allowance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="overtime_allowance" name="overtime_allowance${status1.index + 1 }" class="number required" value="${salary.overtime_allowance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="meals_allowance" name="meals_allowance${status1.index + 1 }" class="number required" value="${salary.meals_allowance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="actual_tax_bonus" name="actual_tax_bonus${status1.index + 1 }" class="number required" value="${salary.actual_tax_bonus }" /></td>
						<!-- 
						<td><input type="text" size="7" maxlength="10"  id="extra_expend" name="extra_expend${status1.index + 1 }" class="number required" value="${salary.extra_expend }" /></td>
						 -->
						<td><input type="text" size="7" maxlength="10"  id="should_salary" name="should_salary${status1.index + 1 }" class="number required" value="${salary.should_salary }" /></td>
						
						<td><input type="text" size="7" maxlength="10"  id="pension_insurance" name="pension_insurance${status1.index + 1 }" class="number required" value="${salary.pension_insurance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="endowment_insurance_bycompany" name="endowment_insurance_bycompany${status1.index + 1 }" class="number required" value="${salary.endowment_insurance_bycompany }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="medical_Insurance" name="medical_Insurance${status1.index + 1 }" class="number required" value="${salary.medical_Insurance }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="medical_insurance_bycompany" name="medical_insurance_bycompany${status1.index + 1 }" class="number required" value="${salary.medical_insurance_bycompany }" /></td>						
						<td><input type="text" size="7" maxlength="10"  id="unemployment_insurance" name="unemployment_insurance${status1.index + 1 }" class="number required" value="${salary.unemployment_insurance }" /></td>						
						<td><input type="text" size="7" maxlength="10"  id="losejob_insurance_bycompany" name="losejob_insurance_bycompany${status1.index + 1 }" class="number required" value="${salary.losejob_insurance_bycompany }" /></td>		
						<td><input type="text" size="7" maxlength="10"  id="maternity_insurance_bycompany" name="maternity_insurance_bycompany${status1.index + 1 }" class="number required" value="${salary.maternity_insurance_bycompany }" /></td>	
						<td><input type="text" size="7" maxlength="10"  id="jobharm_insurance_bycompany" name="jobharm_insurance_bycompany${status1.index + 1 }" class="number required" value="${salary.jobharm_insurance_bycompany }" /></td>					
						<td><input type="text" size="7" maxlength="10"  id="accumulation_fund" name="accumulation_fund${status1.index + 1 }" class="number required" value="${salary.accumulation_fund }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="reservefund_bypcompany" name="reservefund_bypcompany${status1.index + 1 }" class="number required" value="${salary.reservefund_bypcompany }" /></td>



						<td><input type="text" size="7" maxlength="10"  id="children_education" name="children_education${status1.index + 1 }" class="number required" value="${salary.children_education }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="continuing_education" name="continuing_education${status1.index + 1 }" class="number required" value="${salary.continuing_education }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="housing_loans" name="housing_loans${status1.index + 1 }" class="number required" value="${salary.housing_loans }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="housing_rent" name="housing_rent${status1.index + 1 }" class="number required" value="${salary.housing_rent }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="support_elderly" name="support_elderly${status1.index + 1 }" class="number required" value="${salary.support_elderly }" /></td>

						<td><input type="text" size="7" maxlength="10"  id="deductions_cost" name="deductions_cost${status1.index + 1 }" class="number required" value="${salary.deductions_cost }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="taxable_income" name="taxable_income${status1.index + 1 }" class="number required" value="${salary.taxable_income }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="personal_income_tax" name="personal_income_tax${status1.index + 1 }" class="number required" value="${salary.personal_income_tax }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="actual_bonus" name="actual_bonus${status1.index + 1 }" class="number required" value="${salary.actual_bonus }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="overdue_tax_salary" name="overdue_tax_salary${status1.index + 1 }" class="number required" value="${salary.overdue_tax_salary }" /></td>
						<td><input type="text" size="7" maxlength="10"  id="actual_salary" name="actual_salary${status1.index + 1 }" class="number required" value="${salary.actual_salary }" /></td>						
						<td>
							<input type="text" size="33" maxlength="150"  id="description" name="description${status1.index + 1 }" class="text" value="${salary.description }" />
							<input type="hidden" id="computer_allowance" name="computer_allowance${status1.index + 1 }" class="text" value="${salary.computer_allowance }" />
							<input type="hidden" id="meal_allowance" name="meal_allowance${status1.index + 1 }" class="text" value="${salary.meal_allowance }" />
						</td>						
						
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
						
			
			<div class="divider"></div>
			<p>
				<label>制单人： 	</label> <label>${salary1.build_username } </label>
			</p>
			<p>
				<label>制单日期： 	</label> <label><fmt:formatDate value="${salary1.build_datetime }" pattern="yyyy-MM-dd" /> </label>
			</p>
			


		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">制作工资单</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
