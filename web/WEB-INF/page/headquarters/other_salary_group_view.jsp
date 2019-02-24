<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/OtherSalaryGroupAction.do?method=verifySalary" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		

			<p>
				<label>工资月份：</label>
				
				<label>${salary1.salary_month } <input type="hidden" name="salary_month" value="${salary1.salary_month }" /></label>
			</p>


			<p>
				<label>部门名称：</label>
				<input type="hidden"   name="dept_id" value="${salary1.dept_id }"/>
				<input name="dept_name" type="hidden" value="${salary1.dept_name }"  />
				<label>${salary1.dept_name }</label>
			</p>
			
						
			
			<div class="divider"></div>
			
			<table class="list nowrap" width="6300" >
				<thead>
					<tr>
						
						<th width="30" nowrap>序号</th>						
						<th width="80" nowrap>姓名</th>
						<th width="80" nowrap>基本工资</th>	
						<th width="80" nowrap>岗位工资</th>	
						<th width="80" nowrap>绩效津贴</th>	
						<th width="80" nowrap>出差补助</th>	
						<th width="80" nowrap>奖金(税)</th>	
						<th width="80" nowrap>应出勤天数</th>	
						<th width="100" nowrap>实际工作天数</th>	
						<th width="60" nowrap>带薪假</th>	
						<th width="60" nowrap>出差</th>						
						<th width="80" nowrap>事假天数</th>
						<th width="80" nowrap>病假天数</th>

						<th width="80" nowrap>待岗天数</th>
						<th width="80" nowrap>产假天数</th>

						<th width="80" nowrap>旷工天数</th>
						<th width="80" nowrap>迟到天数</th>
						<th width="80" nowrap>加班天数</th>
						<th width="80" nowrap>病假工资</th>

						<th width="80" nowrap>待岗工资</th>
						<th width="80" nowrap>产假工资</th>

						<th width="80" nowrap>旷工工资</th>
						<th width="80" nowrap>迟到工资</th>
						<th width="80" nowrap>出差补贴</th>
						<th width="80" nowrap>电脑补贴</th>
						<th width="80" nowrap>额外补贴</th>
						<th width="80" nowrap>加班补贴</th>
						<th width="60" nowrap>餐补</th>
						<th width="120" nowrap>实发奖金(税前)</th>
						<!-- 
						<th width="120" nowrap>额外支出</th>
						 -->
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
						<th width="120" nowrap>住房贷款利息</th>
						<th width="80" nowrap>住房租金</th>
						<th width="80" nowrap>赡养老人</th>
						<th width="80" nowrap>继续教育</th>

						<th width="150" nowrap>当月五险一金代扣款</th>
						<th width="120" nowrap>当月应纳税所得额</th>

						<th width="120" nowrap>累计税前收入额</th>
						<th width="170" nowrap>累计个税累计减除费用</th>
						<th width="120" nowrap>累计子女教育</th>
						<th width="140" nowrap>累计住房贷款利息</th>
						<th width="120" nowrap>累计住房租金</th>
						<th width="120" nowrap>累计赡养老人</th>
						<th width="120" nowrap>累计继续教育</th>
						<th width="150" nowrap>累计五险一金代扣款</th>
						<th width="150" nowrap>累计应纳税所得额</th>
						<th width="120" nowrap>累计应扣缴税额</th>
						<th width="120" nowrap>累计已预缴税额</th>
						<th width="120" nowrap>累计应补（退）税额</th>

						<th width="130" nowrap>当月应扣所得税</th>
						<th width="80" nowrap>奖金（后）</th>
						<th width="80" nowrap>补税工资</th>
						<th width="80" nowrap><b>实发工资</b></th>
						<th width="280" nowrap><b>说明</b></th>
						<th width="80" nowrap><b>核单人</b></th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="salary"  varStatus="status1" items="${list}">
					<tr>						
						
						<td nowrap>
							<input type="hidden" id="id" name="id" value="0"  />
							<input name="salary_id${status1.index + 1 }" value="${salary.salary_id}" type="hidden" />	
							${status1.index + 1 }
							
							
							<c:if test="${operation_check != null && operation_check != '' }">
								<c:if test="${salary.verify_userid == null ||  salary.verify_userid == ''}">
									<input name="salary_id" value="${salary.salary_id }" type="hidden"/>
								</c:if>
							</c:if>
						</td>
						<td nowrap>
							<input type="hidden"   name="staff_id${status1.index + 1 }" value="${salary.staff_id }"/>
							<input type="hidden"   name="staff_name${status1.index + 1 }" value="${salary.staff_name }"/>						
							${salary.staff_name }
						</td>
						<td>${salary.basic_salary }</td>
						<td>${salary.post_salary }</td>
						<td>${salary.performance_allowances }</td>
						<td>${salary.travel_allowance }</td>
						<td>${salary.tax_bonus }</td>
						<td>${salary.should_work_days }</td>
						<td>${salary.work_days }</td>
						<td>${salary.paid_leave_days }</td>
						<td>${salary.business_trip_days }</td>
						<td>${salary.personal_leave_days }</td>
						<td>${salary.sick_leave_days }</td>
						<td>${salary.waiting_post_days }</td>
						<td>${salary.maternity_leave_days }</td>
						<td>${salary.neglect_work_days }</td>
						<td>${salary.late_days }</td>
						<td>${salary.weekend_overtime_days }</td>
						<td>${salary.sick_leave_salary }</td>
						<td>${salary.waiting_post_salary }</td>
						<td>${salary.maternity_leave_salary }</td>
						<td>${salary.neglect_work_salary }</td>
						<td>${salary.late_salary }</td>
						<td>${salary.actual_travel_allowance }</td>
						<td>${salary.actual_computer_allowance }</td>
						<td>${salary.actual_extra_allowance }</td>
						<td>${salary.overtime_allowance }</td>
						<td>${salary.meals_allowance }</td>
						<td>${salary.actual_tax_bonus }</td>
						<!-- 
						<td><b>${salary.extra_expend }</b></td>
						 -->
						<td><b>${salary.should_salary }</b></td>
						
						<td>${salary.pension_insurance }</td>
						<td>${salary.endowment_insurance_bycompany }</td>
						<td>${salary.medical_Insurance }</td>
						<td>${salary.medical_insurance_bycompany }</td>						
						<td>${salary.unemployment_insurance }</td>
						<td>${salary.losejob_insurance_bycompany }</td>
						<td>${salary.maternity_insurance_bycompany }</td>
						<td>${salary.jobharm_insurance_bycompany }</td>
						<td>${salary.accumulation_fund }</td>
						<td>${salary.reservefund_bypcompany }</td>


						<td>${salary.children_education }</td>
						<td>${salary.housing_loans }</td>
						<td>${salary.housing_rent }</td>
						<td>${salary.support_elderly }</td>
						<td>${salary.continuing_education }</td>

						<td>${salary.deductions_cost }</td>
						<td>${salary.taxable_income }</td>

						<td>${salary.accumulated_pretax_income }</td>
						<td>${salary.accumulated_tax_deduction }</td>
						<td>${salary.accumulated_children_education }</td>
						<td>${salary.accumulated_housing_loans }</td>
						<td>${salary.accumulated_housing_rent }</td>
						<td>${salary.accumulated_support_elderly }</td>
						<td>${salary.accumulated_continuing_education }</td>
						<td>${salary.accumulated_deductions_cost }</td>
						<td>${salary.accumulated_taxable_income }</td>
						<td>${salary.accumulated_deductible_taxpaid }</td>
						<td>${salary.accumulated_prepaid_tax }</td>
						<td>${salary.accumulated_replenishment_tax }</td>



						<td>${salary.personal_income_tax }</td>
						<td>${salary.actual_bonus }</td>
						<td>${salary.overdue_tax_salary }</td>
						<td><b>${salary.actual_salary }</b></td>
						<td title="${salary.description }">${salary.description }</td>
						<td><b>${salary.verify_username }</b></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
						
			
			<div class="divider"></div>
			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>			

	
	
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
