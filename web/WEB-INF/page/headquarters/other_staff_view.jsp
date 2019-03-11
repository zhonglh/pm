<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="staff_id" value="${otherStaff1.staff_id }"/>
			
			<p>
				<label>工号：</label>
				${otherStaff1.staff_no}
			</p>

			<p>
				<label>员工名称：</label>
				${otherStaff1.staff_name}
			</p>
					
			<p>
				<label>所在部门：</label>
				${otherStaff1.dept_name}
			</p>
					

			<p>
				<label>职位类型：</label>				
				<spring:message code="position.type.${otherStaff1.position_type }"/>
			</p>



			<p>
				<label>岗位名称：</label>
				${otherStaff1.job_title}
			</p>

			<p>
				<label>身份证号：</label>
				${otherStaff1.identity_card_number}
			</p>

			<p>
				<label>性别：</label>
				${otherStaff1.sex}
			</p>
			<p>
				<label>生日：</label>
				<fmt:formatDate value="${otherStaff1.birthday }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>民族：</label>
				${otherStaff1.nationality_name }
			</p>


			<p>
				<label>户籍性质：</label>
				${otherStaff1.census_property_name }
			</p>


			<p>
				<label>联系电话：</label>
				${otherStaff1.tel}
			</p>

			<p>
				<label>紧急联系人电话：</label>
				${otherStaff1.pressing_tel}
			</p>

			<p>
				<label>QQ号码：</label>
				${otherStaff1.qq}
			</p>

			<p>
				<label>合同开始时间：</label>
				<fmt:formatDate value="${otherStaff1.contract_start_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>合同结束时间：</label>
				<fmt:formatDate value="${otherStaff1.contract_end_date }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>签约次数：</label>
				${otherStaff1.contract_number}
			</p>

			<p>
				<label>毕业学校：</label>
				${otherStaff1.graduate_school}
			</p>

			<p>
				<label>学历：</label>
				${otherStaff1.educational_name}
			</p>


			<p>
				<label>专业：</label>
				${otherStaff1.specialty_name}
			</p>


			<p>
				<label>毕业时间：</label>
				<fmt:formatDate value="${otherStaff1.graduation_date }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>工作年限：</label>
				<fmt:formatNumber value="${otherStaff1.working_life }" type="number" pattern="#0.00"/>
			</p>


			<p>
				<label>户口所在地：</label>
				${otherStaff1.registered_residence}
			</p>

			<p>
				<label>通讯地址：</label>
				${otherStaff1.postal_address}
			</p>


			<p>
				<label>入职时间：</label>
				<fmt:formatDate value="${otherStaff1.join_datetime }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>离职时间：</label>
				<fmt:formatDate value="${otherStaff1.leave_job_datetime }" pattern="yyyy-MM-dd"/>
			</p>




			<p>
				<label>转正日期：</label>
				<fmt:formatDate value="${otherStaff1.confirmation_date }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>试用期工资：</label>
				<fmt:formatNumber value="${otherStaff1.tryout_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<p>
				<label>正式工资：</label>
				<fmt:formatNumber value="${otherStaff1.official_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<p>
				<label>基本工资：</label>
				<fmt:formatNumber value="${otherStaff1.basic_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>岗位工资：</label>
				<fmt:formatNumber value="${otherStaff1.post_salary }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>绩效津贴：</label>
				<fmt:formatNumber value="${otherStaff1.performance_allowances }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<p>
				<label>电脑补助：</label>
				<fmt:formatNumber value="${otherStaff1.computer_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>餐补(每天)：</label>
				<fmt:formatNumber value="${otherStaff1.meal_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>奖金(税前)：</label>
				<fmt:formatNumber value="${otherStaff1.project_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<p>
				<label>出差补助：</label>
				<fmt:formatNumber value="${otherStaff1.travel_allowance }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<div class="divider"></div>

			<p>
				<label>社保种类：</label>
				${otherStaff1.insurance_radix }
			</p>

			<p>
				<label>社保缴纳单位：</label>
				${otherStaff1.securty_unit }
			</p>

			<div class="divider"></div>

			<p>
				<label>个人缴纳养老险：</label>
				<fmt:formatNumber value="${otherStaff1.endowment_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳医疗险：</label>
				<fmt:formatNumber value="${otherStaff1.medical_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳失业险：</label>
				<fmt:formatNumber value="${otherStaff1.losejob_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳公积金：</label>
				<fmt:formatNumber value="${otherStaff1.reservefund_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人所得税：</label>
				<fmt:formatNumber value="${otherStaff1.incometax_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳养老保险：</label>
				<fmt:formatNumber value="${otherStaff1.endowment_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳医疗保险：</label>
				<fmt:formatNumber value="${otherStaff1.medical_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳失业保险：</label>
				<fmt:formatNumber value="${otherStaff1.losejob_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳生育保险：</label>
				<fmt:formatNumber value="${otherStaff1.maternity_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳工伤保险：</label>
				<fmt:formatNumber value="${otherStaff1.jobharm_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳公积金：</label>
				<fmt:formatNumber value="${otherStaff1.reservefund_bypcompany }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<div class="divider"></div>


			<p>
				<label>额外：</label>
				<fmt:formatNumber value="${otherStaff1.extra }" type="number" pattern="#,###,##0.00#"/>
			</p>



			<p>
				<label>邮箱地址：</label>
				${otherStaff1.email}
			</p>

			<p>
				<label>开户行：</label>
				${otherStaff1.open_account}
			</p>

			<p>
				<label>银行卡号：</label>
				${otherStaff1.bank_card_number}
			</p>


			<p>
				<label>额外支出：</label>
				<fmt:formatNumber value="${otherStaff1.extra_expend }" type="number" pattern="#,###,##0.00#"/>
			</p>

			<p>
				<label>是否允许发送信息：</label>
				<c:if test="${otherStaff1.can_send_info == '0' }">否</c:if>
				<c:if test="${otherStaff1.can_send_info == '1' }">是</c:if>
			</p>


			<p>
				<label>合同种类：</label>
				${otherStaff1.contract_type_name }
			</p>

			<p>
				<label>合同归属：</label>
				${otherStaff1.contract_attach_name }
			</p>

			<p>
				<label>社保说明：</label>
				${otherStaff1.social_security_name }
			</p>




			<p>
				<label>参保城市：</label>
				${otherStaff1.insured_city_name}
			</p>
			<p>
				<label>工作地点：</label>
				${otherStaff1.working_address_name}
			</p>
			<p>
				<label>证书：</label>
				${otherStaff1.certificate_name}
			</p>






			<div class="divider"></div>


			<p>
				<label>子女教育：</label>
				<fmt:formatNumber value="${otherStaff1.children_education }" type="number" pattern="####0.00#"/>
			</p>

			<p>
				<label>住房贷款利息：</label>
				<fmt:formatNumber value="${otherStaff1.housing_loans }" type="number" pattern="####0.00#"/>
			</p>

			<p>
				<label>住房租金：</label>
				<fmt:formatNumber value="${otherStaff1.housing_rent }" type="number" pattern="####0.00#"/>
			</p>

			<p>
				<label>赡养老人：</label>
				<fmt:formatNumber value="${otherStaff1.support_elderly }" type="number" pattern="####0.00#"/>
			</p>

			<p>
				<label>继续教育：</label>
				<fmt:formatNumber value="${otherStaff1.continuing_education }" type="number" pattern="####0.00#"/>
			</p>


			<p>
				<label>成本中心：</label>
				${otherStaff1.cost_center_name}
			</p>

			<p style="margin-top: 20px">
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>${otherStaff1.description }</dd>
			</dl>
			</p>








			<br/>
			<div class="divider"></div>
			<h3 class="contentTitle">部门经历</h3>

			<table id="dept_staff_table" class="list nowrap "  width="100%">
				<thead>
				<tr>
					<th width="120" type="lookup" fieldClass="required" name="items[#index#].dept.dept_name" lookupGroup="items[#index#].dept" suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup"   lookupPk="dept_id" size="20">部门</th>
					<th width="100" type="date" postField="join_dept_datetime" defaultVal="${currDate }"  name="items[#index#].join_dept_datetime"  size="10" fieldClass="date required">加入部门日期</th>
					<th width="100" type="date" postField="leave_dept_datetime"   name="items[#index#].leave_dept_datetime"  size="10" fieldClass="date">离开部门日期</th>
					<th width="350" type="text" postField="description"  name="items[#index#].description" maxlength="100" size="30" fieldClass="text">说明</th>

				</tr>
				</thead>
				<tbody>
				<c:forEach var="deptStaff"  varStatus="status1" items="${deptStaffs}">
					<tr>
						<td height="22"><c:if test="${deptStaff.delete_flag == '1' }"><s></c:if>${deptStaff.dept_name }<c:if test="${deptStaff.delete_flag == '1' }"></s></c:if></td>
						<td><c:if test="${deptStaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${deptStaff.join_dept_datetime }" pattern="yyyy-MM-dd"/><c:if test="${deptStaff.delete_flag == '1' }"></s></c:if></td>
						<td><c:if test="${deptStaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${deptStaff.leave_dept_datetime }" pattern="yyyy-MM-dd"/><c:if test="${deptStaff.delete_flag == '1' }"></s></c:if></td>
						<td>${deptStaff.description }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>


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
					<th width="350" type="text" postField="description"  name="items[#index#].description_3" maxlength="150" size="38" fieldClass="text">备注</th>

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
				<label>制单人：</label>  ${otherStaff1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${otherStaff1.build_datetime }" pattern="yyyy-MM-dd"/>
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
