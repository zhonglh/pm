<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/StaffCostAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="staff_id" value="${staffCost1.staff_id }"/>
		
		
			<p>
				<label>员工工号：</label>
				${staffCost1.staff_no}
			</p>
			
			<p>
				<label>员工名称：</label>
				${staffCost1.staff_name}
			</p>
			<p>
				<label>岗位名称：</label>
				${staffCost1.job_title}
			</p>			

			<p>
				<label>招聘专员名称：</label>
				${staffCost1.recruiter_name }
			</p>	
			
			<p>
				<label>性别：</label>				
				${staffCost1.sex}			
			</p>
			<p>
				<label>生日：</label>
				<fmt:formatDate value="${staffCost1.birthday }" pattern="yyyy-MM-dd"/>
			</p>
			
			<p>
				<label>民族：</label>
				${staffCost1.nationality_name }
			</p>
			
			
			<p>
				<label>户籍性质：</label>
				${staffCost1.census_property_name }
			</p>
			
			<p>
				<label>身份证号：</label>
				${staffCost1.identity_card_number}
			</p>
			
			<p>
				<label>联系电话：</label>
				${staffCost1.tel}
			</p>
			
			<p>
				<label>紧急联系人电话：</label>
				${staffCost1.pressing_tel}
			</p>
			
			<p>
				<label>QQ号码：</label>				
				${staffCost1.qq}
			</p>
			
			<p>
				<label>合同开始时间：</label>
				<fmt:formatDate value="${staffCost1.contract_start_date }" pattern="yyyy-MM-dd"/>
			</p>	
			<p>
				<label>合同结束时间：</label>
				<fmt:formatDate value="${staffCost1.contract_end_date }" pattern="yyyy-MM-dd"/>
			</p>				
			
			<p>
				<label>签约次数：</label>
				${staffCost1.contract_number}
			</p>
			
			<p>
				<label>毕业学校：</label>
				${staffCost1.graduate_school}
			</p>			
			
			<p>
				<label>学历：</label>
				${staffCost1.educational_name}
			</p>
			
			
			<p>
				<label>专业：</label>
				${staffCost1.specialty_name}
			</p>
			
			
			<p>
				<label>毕业时间：</label>
				<fmt:formatDate value="${staffCost1.graduation_date }" pattern="yyyy-MM-dd"/>
			</p>
			
			<p>
				<label>工作年限：</label>
				<fmt:formatNumber value="${staffCost1.working_life }" type="number" pattern="#0.00"/>
			</p>
			
			
			<p>
				<label>户口所在地：</label>
				${staffCost1.registered_residence}
			</p>
			
			<p>
				<label>通讯地址：</label>
				${staffCost1.postal_address}				
			</p>		
						
			
			<p>
				<label>入职时间：</label>
				<fmt:formatDate value="${staffCost1.join_datetime }" pattern="yyyy-MM-dd"/>
			</p>			
						
			<p>
				<label>离职时间：</label>
				<fmt:formatDate value="${staffCost1.leave_job_datetime }" pattern="yyyy-MM-dd"/>				
			</p>
			
			<p>
				<label>人员总成本：</label>
				<fmt:formatNumber value="${staffCost1.totalcost }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>客户最初报价：</label>
				<fmt:formatNumber value="${staffCost1.firstquotes }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>客户最新报价：</label>
				<fmt:formatNumber value="${staffCost1.qustomerquotes }" type="number" pattern="#,###,##0.00#"/>
			</p>	
			
					
			<p>
				<label>转正日期：</label>
				<fmt:formatDate value="${staffCost1.confirmation_date }" pattern="yyyy-MM-dd"/>
			</p>
			
			<p>
				<label>试用期工资：</label>
				<fmt:formatNumber value="${staffCost1.tryout_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>正式工资：</label>
				<fmt:formatNumber value="${staffCost1.official_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>基本工资：</label>
				<fmt:formatNumber value="${staffCost1.basic_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>			
			<p>
				<label>岗位工资：</label>
				<fmt:formatNumber value="${staffCost1.post_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>			
			<p>
				<label>绩效津贴：</label>
				<fmt:formatNumber value="${staffCost1.performance_allowances }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>电脑补助：</label>
				<fmt:formatNumber value="${staffCost1.computer_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>餐补(每天)：</label>
				<fmt:formatNumber value="${staffCost1.meal_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>奖金(税前)：</label>
				<fmt:formatNumber value="${staffCost1.project_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<p>
				<label>出差补助：</label>
				<fmt:formatNumber value="${staffCost1.travel_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>			
			
			<div class="divider"></div>
			
			<p>
				<label>社保种类：</label>
				${staffCost1.insurance_radix }
			</p>
				
			<p>
				<label>社保缴纳单位：</label>
				${staffCost1.securty_unit }
			</p>
			
			<div class="divider"></div>
			
				<p>
				<label>个人缴纳养老险：</label>
				<fmt:formatNumber value="${staffCost1.endowment_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳医疗险：</label>
				<fmt:formatNumber value="${staffCost1.medical_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳失业险：</label>
				<fmt:formatNumber value="${staffCost1.losejob_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳公积金：</label>
				<fmt:formatNumber value="${staffCost1.reservefund_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人所得税：</label>
				<fmt:formatNumber value="${staffCost1.incometax_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳养老保险：</label>
				<fmt:formatNumber value="${staffCost1.endowment_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳医疗保险：</label>
				<fmt:formatNumber value="${staffCost1.medical_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳失业保险：</label>
				<fmt:formatNumber value="${staffCost1.losejob_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳生育保险：</label>
				<fmt:formatNumber value="${staffCost1.maternity_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳工伤保险：</label>
				<fmt:formatNumber value="${staffCost1.jobharm_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳公积金：</label>
				<fmt:formatNumber value="${staffCost1.reservefund_bypcompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<div class="divider"></div>		

			
			<p>
				<label>额外：</label>
				<fmt:formatNumber value="${staffCost1.extra }" type="number" pattern="#,###,##0.00#"/>
			</p>		
			
			<p>
				<label>是否外协人员：</label>
				<c:if test="${staffCost1.outsource_staff == '0' }">否</c:if>
				<c:if test="${staffCost1.outsource_staff == '1' }">是</c:if>				
			</p>		
			
			<p>
				<label>邮箱地址：</label>
				${staffCost1.email}
			</p>
			
			<p>
				<label>开户行：</label>
				${staffCost1.open_account}
			</p>	
	
			<p>
				<label>银行卡号：</label>
				${staffCost1.bank_card_number}
			</p>						
								
			<p>
				<label>主管：</label>				
				${staffCost1.lead_name }
			</p>
			
			<p>
				<label>额外支出：</label>				
				<fmt:formatNumber value="${staffCost1.extra_expend }" type="number" pattern="#,###,##0.00#"/>
			</p>			
			
			<p>
				<label>是否允许发送信息：</label>
				<c:if test="${staffCost1.can_send_info == '0' }">否</c:if>
				<c:if test="${staffCost1.can_send_info == '1' }">是</c:if>	
			</p>
					
			
			<p>
				<label>合同种类：</label>	
				${staffCost1.contract_type_name }
			</p>		
			
			<p>
				<label>合同归属：</label>	
				${staffCost1.contract_attach_name }
			</p>		
			
			<p>
				<label>社保说明：</label>	
				${staffCost1.social_security_name }
			</p>		
			
			
			
			
			<p>
				<label>参保城市：</label>
				${staffCost1.insured_city_name}
			</p>
			<p>
				<label>工作地点：</label>
				${staffCost1.working_address_name}
			</p>
			<p>
				<label>证书：</label>
				${staffCost1.certificate_name}
			</p>
			
			<p>
				<dl class="nowrap">
					<dt>备注：</dt>
					<dd>${staffCost1.description }</dd>
				</dl>
			</p>	
			
			
			
			
			
			
			


			<div class="divider"></div>

			<h3 class="contentTitle">加薪记录</h3>
			
			<table id="raise_record_table" class="list nowrap" width="100%">
				<thead>
					<tr>
						<th width="120" type="date" postField="raise_time"  name="items[#index#].raise_time_1" format="yyyy-MM-dd"  size="12" fieldClass="date required">加薪时间</th>
						<th width="100" type="text" postField="amount"  name="items[#index#].amount_1" maxlength="10" size="10" fieldClass="number required">加薪金额</th>
						<th width="450" type="text" postField="description"  name="items[#index#].description_1" maxlength="150" size="62" fieldClass="text">备注</th>
						
					</tr>												
				</thead>
				<tbody>
					<c:forEach var="raiserecord"  varStatus="status1" items="${raiserecords}">
					<tr>
						<td><fmt:formatDate value="${raiserecord.raise_time }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatNumber value="${raiserecord.amount }" type="number" pattern="####0.00#"/></td>
						<td title="${raiserecord.description }">${raiserecord.description }</td>
						
					</tr>
					</c:forEach>								
				</tbody>
			</table>			
			<div class="divider"></div>
			
			


			<h3 class="contentTitle">奖惩记录</h3>
			
			<table id="reward_penalty_table" class="list nowrap" width="100%">
				<thead>
					<tr>
						<th width="120" type="date" postField="rp_time"  name="items[#index#].rp_time_2" format="yyyy-MM-dd"  size="12" fieldClass="date required">奖惩时间</th>
						<th width="100" type="text" postField="amount"  name="items[#index#].amount_2" maxlength="10" size="10" fieldClass="number required">奖惩金额</th>
						<th width="450" type="text" postField="description"  name="items[#index#].description_2" maxlength="150" size="62" fieldClass="text">备注</th>
						
					</tr>												
				</thead>
				<tbody>								
					<c:forEach var="rewardpenalty"  varStatus="status1" items="${rewardpenaltys}">
					<tr>
						<td><fmt:formatDate value="${rewardpenalty.rp_time }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatNumber value="${rewardpenalty.amount }" type="number" pattern="####0.00#"/></td>
						<td title="${rewardpenalty.description }">${rewardpenalty.description }</td>
						
					</tr>
					</c:forEach>								
				</tbody>
			</table>			
			
			<br />
			<div class="divider"></div>			
			
			<h3 class="contentTitle">绩效考核</h3>			
			<table id="assessment_table" class="list nowrap" width="100%">
				<thead>
					<tr>
						<th width="120" type="date" postField="assessment_time"  name="items[#index#].assessment_time_3" format="yyyy-MM-dd"  size="12" fieldClass="date required">考核时间</th>
						<th width="100" type="text" postField="attendance_rate"  name="items[#index#].attendance_rate_3" maxlength="6" size="10" fieldClass="number required">出勤率(%)</th>
						<th width="100" type="text" postField="score"  name="items[#index#].score_3" maxlength="4" size="10" fieldClass="digits required">绩效评分</th>
						<th width="220" type="text" postField="description"  name="items[#index#].description_3" maxlength="150" size="38" fieldClass="text">备注</th>
					
					</tr>												
				</thead>
				<tbody>								
					<c:forEach var="assessment"  varStatus="status1" items="${assessments}">
					<tr>
						<td><fmt:formatDate value="${assessment.assessment_time }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatNumber value="${assessment.attendance_rate }" type="number" pattern="####0.00#"/></td>
						<td><fmt:formatNumber value="${assessment.score }" type="number" pattern="####0"/></td>
						<td title="${assessment.description }">${assessment.description }</td>
					
					</tr>
					</c:forEach>								
				</tbody>
			</table>			
			
			
			<br />
			<div class="divider"></div>
			
			
			
			<h3 class="contentTitle">项目经历</h3>
			<table id="project_staff_table" class="list nowrap"  width="100%">
				<thead>
					<tr>
						<th width="180">时间段</th>
						<th width="200">项目名称</th>
						<th width="120">客户所在部门</th>
						<th width="300">说明</th>	
					</tr>												
				</thead>
				<tbody>								
					<c:forEach var="project_staff"  varStatus="status1" items="${project_staffs}">
					<tr>
						
						<td>${project_staff.start_end_dates }</td>
						<td>${project_staff.project_name }</td>
						<td>${project_staff.client_dept }</td>
						<td title="${project_staff.description }">${project_staff.description }</td>
					</tr>
					</c:forEach>								
				</tbody>
			</table>			

			
			<br />
			<div class="divider"></div>
				
			
			<h3 class="contentTitle">职位晋升</h3>			
			<table id="positions_table" class="list nowrap" width="100%">
				<thead>
					<tr>
						<th width="120" type="date" postField="positions_time"  name="items[#index#].positions_time_4" format="yyyy-MM-dd"  size="12" fieldClass="date required">考核时间</th>
						<th width="100" type="text" postField="level"  name="items[#index#].level_4" maxlength="10" size="8" fieldClass="text required">级别</th>
						<th width="450" type="text" postField="description"  name="items[#index#].description_4" maxlength="150" size="62" fieldClass="text">备注</th>
						
					</tr>												
				</thead>
				<tbody>								
					<c:forEach var="positions"  varStatus="status1" items="${positionss}">
					<tr>
						<td><fmt:formatDate value="${positions.positions_time }" pattern="yyyy-MM-dd"/></td>
						<td>${positions.level }</td>
						<td title="${positions.description }">${positions.description }</td>
						
					</tr>
					</c:forEach>								
				</tbody>
			</table>			
			
			
			
			
			
			
			
			
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${staffCost1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${staffCost1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>
		</div>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
