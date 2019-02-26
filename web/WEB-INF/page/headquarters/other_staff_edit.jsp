<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/OtherStaffAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="staff_id" value="${otherStaff1.staff_id }"/>
			<input type="hidden" name="version_no" value="${ otherStaff1.version_no }"/>


			<input type="hidden" id="DEFAULT_BASIC_SALARY" name="DEFAULT_BASIC_SALARY" value="${DEFAULT_BASIC_SALARY }"/>

			<input type="hidden" id="tryout_basic_salary" name="tryout_basic_salary" value="${otherStaff1.tryout_basic_salary }"/>
			<input type="hidden" id="tryout_post_salary" name="tryout_post_salary" value="${otherStaff1.tryout_post_salary }"/>
			<input type="hidden" id="tryout_performance_allowances" name="tryout_performance_allowances" value="${otherStaff1.tryout_performance_allowances }"/>


			<p>
				<label>工号：</label>
				<input name="staff_no" class="required" type="text" size="30" maxlength="20" value="${otherStaff1.staff_no}" />
			</p>

			<p>
				<label>员工名称：</label>
				<input name="staff_name" class="required" type="text" size="30" maxlength="30" value="${otherStaff1.staff_name}" />
			</p>

			<!--
			<c:if test="otherStaff1.staff_id == null || otherStaff1.staff_id == ''">
			<p>
				<label>所在部门：</label>
				<input type="hidden" size="2"  name="dept.dept_id" value="${otherStaff1.dept_id }"/>
				<input name="dept.dept_name" class="text" type="text" size="30"  value="${otherStaff1.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup" lookupGroup="dept"/>	
			</p>
			</c:if>
			<c:if test="otherStaff1.staff_id != null && otherStaff1.staff_id != ''">
				<p>
					<label>所在部门：</label>
					<input type="hidden" size="2"  name="dept.dept_id" 	value="${otherStaff1.dept_id }"/>
					<input type="hidden" size="2"  name="dept.dept_name" value="${otherStaff1.dept_name }" />
					${otherStaff1.dept_name }
				</p>
			</c:if>
			-->
					

			<p>
				<label>职位类型：</label>
				<select name="position_type" style="width:185px">
					<option value="1" <c:if test="${otherStaff1.position_type == '1' }">selected</c:if>><spring:message code="position.type.1"/></option>
					<option value="2" <c:if test="${otherStaff1.position_type == '2' }">selected</c:if>><spring:message code="position.type.2"/></option>
					<option value="3" <c:if test="${otherStaff1.position_type == '3' }">selected</c:if>><spring:message code="position.type.3"/></option>
					<option value="4" <c:if test="${otherStaff1.position_type == '4' }">selected</c:if>><spring:message code="position.type.4"/></option>
					<option value="5" <c:if test="${otherStaff1.position_type == '5' }">selected</c:if>><spring:message code="position.type.5"/></option>
					<option value="6" <c:if test="${otherStaff1.position_type == '6' }">selected</c:if>><spring:message code="position.type.6"/></option>
					<option value="7" <c:if test="${otherStaff1.position_type == '7' }">selected</c:if>><spring:message code="position.type.7"/></option>
					<option value="8" <c:if test="${otherStaff1.position_type == '8' }">selected</c:if>><spring:message code="position.type.8"/></option>
					<option value="9" <c:if test="${otherStaff1.position_type == '9' }">selected</c:if>><spring:message code="position.type.9"/></option>
					<option value="10" <c:if test="${otherStaff1.position_type == '10' }">selected</c:if>><spring:message code="position.type.10"/></option>
				</select>
			</p>



			<p>
				<label>岗位名称：</label>
				<input name="job_title" class="text" type="text" size="30" maxlength="36" value="${ otherStaff1.job_title}" />
			</p>

			<p>
				<label>身份证号：</label>
				<input name="identity_card_number" class="text idcard" type="text" size="30" maxlength="18" minlength="18" value="${ otherStaff1.identity_card_number}" onblur="javaScript:idCardProce(this);" style="text-transform:uppercase;" />
			</p>

			<p>
				<label>性别：</label>
				<input id="sex" name="sex" class="text" type="text" size="30" value="${ otherStaff1.sex }" readonly="readonly" />
			</p>
			<p>
				<label>生日：</label>
				<input id="birthday" name="birthday" class="text" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.birthday }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>

			<p>
				<label>民族：</label>
				<input type="hidden" name="nation.id" value="${ otherStaff1.nationality }"/>
				<input name="nation.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.nationality_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="nation"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=NATIONALITY" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=NATIONALITY" mask="true" width="500" height="260" rel="add_NATIONALITY"  target="dialog"><span>添加</span></a>
			</p>


			<p>
				<label>户籍性质：</label>
				<input type="hidden" name="hp.id" value="${ otherStaff1.census_property }"/>
				<input name="hp.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.census_property_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="hp"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=HOUSEHOLDPROPERTY" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=HOUSEHOLDPROPERTY" mask="true" width="500" height="260" rel="add_HOUSEHOLDPROPERTY"  target="dialog"><span>添加</span></a>
			</p>


			<p>
				<label>联系电话：</label>
				<input name="tel" class="text"  type="text" size="30" maxlength="30" maxlength="20" value="${ otherStaff1.tel}" />
			</p>

			<p>
				<label>紧急联系人电话：</label>
				<input name="pressing_tel" class="text"  type="text" size="30" maxlength="20" value="${ otherStaff1.pressing_tel}" />
			</p>

			<p>
				<label>QQ号码：</label>
				<input name="qq" class="digits"  type="text" size="30" maxlength="20" value="${ otherStaff1.qq}" />
			</p>

			<p>
				<label>合同开始时间：</label>
				<input name="contract_start_date" class="date" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.contract_start_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>
			<p>
				<label>合同结束时间：</label>
				<input name="contract_end_date" class="date" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.contract_end_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>

			<p>
				<label>签约次数：</label>
				<input name="contract_number" class="digits"  type="text" size="30" maxlength="3" value="${ otherStaff1.contract_number}" />
			</p>

			<p>
				<label>毕业学校：</label>
				<input name="graduate_school" class="text" type="text" size="30" maxlength="120" value="${ otherStaff1.graduate_school}" />
			</p>

			<p>
				<label>学历：</label>
				<input type="hidden" name="edu.id" value="${ otherStaff1.educational }"/>
				<input name="edu.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.educational_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="edu"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=EDUCATIONAL" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=EDUCATIONAL" mask="true" width="500" height="260" rel="add_EDUCATIONAL"  target="dialog"><span>添加</span></a>

			</p>


			<p>
				<label>专业：</label>
				<input type="hidden" name="specialty.id" value="${ otherStaff1.specialty }"/>
				<input name="specialty.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.specialty_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="specialty"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=SPECIALTY" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=SPECIALTY" mask="true" width="500" height="260" rel="add_SPECIALTY"  target="dialog"><span>添加</span></a>

			</p>

			<p>
				<label>毕业时间：</label>
				<input name="graduation_date" class="date" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.graduation_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>


			<p>
				<label>工作年限：</label>
				<input name="working_life" class="number" type="text" size="30" maxlength="10" max=100 value="<fmt:formatNumber value="${ otherStaff1.working_life }" type="number" pattern="#0.0000"/>" />
			</p>



			<p>
				<label>户口所在地：</label>
				<input name="registered_residence" class="text" type="text" size="30" maxlength="200" value="${ otherStaff1.registered_residence}" />
			</p>

			<p>
				<label>通讯地址：</label>
				<input name="postal_address" class="text" type="text" size="30" maxlength="200" value="${ otherStaff1.postal_address}" />
			</p>


			<p>
				<label>入职时间：</label>
				<input name="join_datetime" class="date" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.join_datetime }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>

			<p>
				<label>离职时间：</label>
				<input name="leave_job_datetime" class="date" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.leave_job_datetime }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>


			<p>
				<label>转正日期：</label>
				<input name="confirmation_date" class="date" type="text" size="30" value="<fmt:formatDate value="${ otherStaff1.confirmation_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>

			<p>
				<label>试用期工资：</label>
				<input id="tryout_salary" name="tryout_salary" class="number" type="text" size="30" maxlength="10" onblur="tryoutSalaryProcess(this,${DEFAULT_BASIC_SALARY})" value="<fmt:formatNumber value="${ otherStaff1.tryout_salary }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>正式工资：</label>
				<input id="official_salary" name="official_salary" class="number" type="text" size="30" maxlength="10" onblur="officialSalaryProcess(this,${DEFAULT_BASIC_SALARY})" value="<fmt:formatNumber value="${ otherStaff1.official_salary }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>基本工资：</label>
				<input id="basic_salary"  name="basic_salary" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.basic_salary }" type="number" pattern="####0.00#"/>" readonly="readonly" />
			</p>
			<p>
				<label>岗位工资：</label>
				<input id="post_salary" name="post_salary" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.post_salary }" type="number" pattern="####0.00#"/>" readonly="readonly" />
			</p>
			<p>
				<label>绩效津贴：</label>
				<input id="performance_allowances" name="performance_allowances" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.performance_allowances }" type="number" pattern="####0.00#"/>" readonly="readonly" />
			</p>

			<p>
				<label>电脑补助：</label>
				<input name="computer_allowance" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.computer_allowance }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>餐补(每天)：</label>
				<input name="meal_allowance" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.meal_allowance }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>奖金(税前)：</label>
				<input name="project_allowance" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.project_allowance }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>出差补助：</label>
				<input name="travel_allowance" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.travel_allowance }" type="number" pattern="####0.00#"/>" />
			</p>

			<div class="divider"></div>

			<p>
				<label>社保种类：</label>
				<input type="hidden"  name="grade.insurance_grade_id" value="${ otherStaff1.insurance_grade_id }"/>
				<input type="hidden" id="base_cardinal"  name="grade.base_cardinal" value="${ otherStaff1.base_cardinal }"/>
				<input name="grade.insurance_radix" class="text" type="text" size="30"
					   value="<c:if test="${ otherStaff1.insurance_radix ==null }"></c:if><c:if test="${ otherStaff1.insurance_radix !=null }">${ otherStaff1.insurance_radix }</c:if>"
					   readonly="readonly" lookupPk="insurance_grade_id" callback="gradeChangeProcess"
					   suggestFields="insurance_radix,endowment_insurance_bypersonal,medical_insurance_bypersonal,losejob_insurance_bypersonal,reservefund_bypersonal,incometax_bypersonal,endowment_insurance_bycompany,medical_insurance_bycompany,losejob_insurance_bycompany,maternity_insurance_bycompany,jobharm_insurance_bycompany,reservefund_bypcompany,base_cardinal"
					   suggestUrl="${webroot }/InsuranceGradeAction.do?method=lookup" lookupGroup="grade"/>
			</p>


			<p>
				<label>社保缴纳单位：</label>
				<input name="securty_unit" class="text" type="text" size="30" value="${ otherStaff1.securty_unit }" />
			</p>

			<div class="divider"></div>

			<p>
				<label>个人缴纳养老险：</label>
				<input name="grade.endowment_insurance_bypersonal" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.endowment_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>个人缴纳医疗险：</label>
				<input name="grade.medical_insurance_bypersonal" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.medical_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>个人缴纳失业险：</label>
				<input name="grade.losejob_insurance_bypersonal" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.losejob_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>个人缴纳公积金：</label>
				<input name="grade.reservefund_bypersonal" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.reservefund_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>个人所得税：</label>
				<input name="grade.incometax_bypersonal" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.incometax_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公司缴纳养老保险：</label>
				<input name="grade.endowment_insurance_bycompany" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.endowment_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公司缴纳医疗保险：</label>
				<input name="grade.medical_insurance_bycompany" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.medical_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公司缴纳失业保险：</label>
				<input name="grade.losejob_insurance_bycompany" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.losejob_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公司缴纳生育保险：</label>
				<input name="grade.maternity_insurance_bycompany" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.maternity_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公司缴纳工伤保险：</label>
				<input name="grade.jobharm_insurance_bycompany" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.jobharm_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公司缴纳公积金：</label>
				<input name="grade.reservefund_bypcompany" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.reservefund_bypcompany }" type="number" pattern="####0.00#"/>" />
			</p>


			<div class="divider"></div>


			<p>
				<label>额外：</label>
				<input name="extra" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.extra }" type="number" pattern="####0.00#"/>" />
			</p>


			<p>
				<label>邮箱地址：</label>
				<input name="email" class="email" type="text" size="30" maxlength="60" minlength="6" value="${ otherStaff1.email}" />
			</p>

			<p>
				<label>开户行：</label>
				<input name="open_account" class="text" type="text" size="30" maxlength="100"  value="${ otherStaff1.open_account}" />
			</p>

			<p>
				<label>银行卡号：</label>
				<input name="bank_card_number" class="text" type="text" size="30"  value="${ otherStaff1.bank_card_number}" />
			</p>


			<p>
				<label>额外支出：</label>
				<input name="extra_expend" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.extra_expend }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>是否允许发送信息：</label>
				<select name="can_send_info">
					<option value="1" <c:if test="${ otherStaff1.can_send_info == '1' }">selected</c:if>>是</option>
					<option value="0" <c:if test="${ otherStaff1.can_send_info == '0' }">selected</c:if>>否</option>
				</select>
			</p>



			<p>
				<label>合同种类：</label>
				<input type="hidden" name="contract_type.id" value="${ otherStaff1.contract_type }"/>
				<input name="contract_type.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.contract_type_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="contract_type"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CONTRACT_TYPE" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=CONTRACT_TYPE" mask="true" width="500" height="260" rel="add_CONTRACT_TYPE"  target="dialog"><span>添加</span></a>
			</p>

			<p>
				<label>合同归属：</label>
				<input type="hidden" name="contract_attach.id" value="${ otherStaff1.contract_attach }"/>
				<input name="contract_attach.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.contract_attach_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="contract_attach"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CONTRACT_ATTACH" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=CONTRACT_ATTACH" mask="true" width="500" height="260" rel="add_CONTRACT_ATTACH"  target="dialog"><span>添加</span></a>
			</p>



			<p>
				<label>社保说明：</label>
				<input type="hidden" name="social_security.id" value="${ otherStaff1.social_security }"/>
				<input name="social_security.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.social_security_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="social_security"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=SOCIAL_SECURITY" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=SOCIAL_SECURITY" mask="true" width="500" height="260" rel="add_SOCIAL_SECURITY"  target="dialog"><span>添加</span></a>
			</p>




			<p>
				<label>社保城市：</label>
				<input type="hidden" name="insuredcity.id" value="${ otherStaff1.insured_city }"/>
				<input name="insuredcity.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.insured_city_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="insuredcity"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=INSURED_CITY" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=INSURED_CITY" mask="true" width="500" height="260" rel="add_INSURED_CITY"  target="dialog"><span>添加</span></a>

			</p>



			<p>
				<label>工作地点：</label>
				<input type="hidden" name="workingaddress.id" value="${ otherStaff1.working_address }"/>
				<input name="workingaddress.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.working_address_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="workingaddress"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=WORKING_ADDRESS" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=WORKING_ADDRESS" mask="true" width="500" height="260" rel="add_WORKING_ADDRESS"  target="dialog"><span>添加</span></a>

			</p>



			<p>
				<label>证书：</label>
				<input type="hidden" name="certificate.id" value="${ otherStaff1.certificate }"/>
				<input name="certificate.dic_data_name" class="text" type="text" size="28"  value="${ otherStaff1.certificate_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="certificate"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CERTIFICATE" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=CERTIFICATE" mask="true" width="500" height="260" rel="add_CERTIFICATE"  target="dialog"><span>添加</span></a>

			</p>



			<div class="divider"></div>


			<p>
				<label>子女教育：</label>
				<input name="children_education" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.children_education }" type="number" pattern="####0.00#"/>" />
			</p>


			<p>
				<label>住房贷款利息：</label>
				<input name="housing_loans" class="number" type="text" size="30" maxlength="10" max="1000" value="<fmt:formatNumber value="${ otherStaff1.housing_loans }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>住房租金：</label>
				<input name="housing_rent" class="number" type="text" size="30" maxlength="10" max="1500" value="<fmt:formatNumber value="${ otherStaff1.housing_rent }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>赡养老人：</label>
				<input name="support_elderly" class="number" type="text" size="30" maxlength="10" max="2000" value="<fmt:formatNumber value="${ otherStaff1.support_elderly }" type="number" pattern="####0.00#"/>" />
			</p>


			<p>
				<label>继续教育：</label>
				<input name="continuing_education" class="number" type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${ otherStaff1.continuing_education }" type="number" pattern="####0.00#"/>" />
			</p>


			<p style="margin-top: 20px">
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd><textarea name="description" cols="95" rows="4" maxlength="300">${ otherStaff1.description }</textarea></dd>
			</dl>
			</p>




			<div class="divider"></div>


			<h3 class="contentTitle">加薪记录</h3>

			<table id="raise_record_table" class="list nowrap itemDetail" addButton="新建加薪记录" width="100%">
				<thead>
				<tr>
					<th width="120" type="date" postField="raise_time"  name="items[#index#].raise_time_1" format="yyyy-MM-dd"  size="12" fieldClass="date required">加薪时间</th>
					<th width="100" type="text" postField="amount"  name="items[#index#].amount_1" maxlength="10" size="10" fieldClass="number required">加薪金额</th>
					<th width="450" type="text" postField="description"  name="items[#index#].description_1" maxlength="150" size="62" fieldClass="text">备注</th>
					<th type="del" >操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="raiserecord"  varStatus="status1" items="${raiserecords}">
					<tr>
						<td><fmt:formatDate value="${raiserecord.raise_time }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatNumber value="${raiserecord.amount }" type="number" pattern="####0.00#"/></td>
						<td title="${raiserecord.description }">${raiserecord.description }</td>
						<td width="80" nowrap>
							<a href="void(0);" onclick="edit_raiserecords(this,'${raiserecord.id }');return false;" ><span>编辑</span></a>
							<a title="确实要<font color='red'><b>彻底</b></font>删除这条记录吗?" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem"  href="${webroot}/OtherStaffAction.do?method=deleteStaffRaiseRecord&id=${raiserecord.id}&rownum=${status1.index}" class="delete"><span>删除</span></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="divider"></div>




			<h3 class="contentTitle">奖惩记录</h3>

			<table id="reward_penalty_table" class="list nowrap itemDetail" addButton="新建奖惩记录" width="100%">
				<thead>
				<tr>
					<th width="120" type="date" postField="rp_time"  name="items[#index#].rp_time_2" format="yyyy-MM-dd"  size="12" fieldClass="date required">奖惩时间</th>
					<th width="100" type="text" postField="amount"  name="items[#index#].amount_2" maxlength="10" size="10" fieldClass="number required">奖惩金额</th>
					<th width="450" type="text" postField="description"  name="items[#index#].description_2" maxlength="150" size="62" fieldClass="text">备注</th>
					<th type="del" >操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="rewardpenalty"  varStatus="status1" items="${rewardpenaltys}">
					<tr>
						<td><fmt:formatDate value="${rewardpenalty.rp_time }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatNumber value="${rewardpenalty.amount }" type="number" pattern="####0.00#"/></td>
						<td title="${rewardpenalty.description }">${rewardpenalty.description }</td>
						<td width="80" nowrap>
							<a href="void(0);" onclick="edit_rewardpenaltys(this,'${rewardpenalty.id }');return false;" ><span>编辑</span></a>
							<a title="确实要<font color='red'><b>彻底</b></font>删除这条记录吗?" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem"  href="${webroot}/OtherStaffAction.do?method=deleteStaffRewardPenalty&id=${rewardpenalty.id}&rownum=${status1.index}" class="delete"><span>删除</span></a>

						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<br />
			<div class="divider"></div>

			<h3 class="contentTitle">绩效考核</h3>
			<table id="assessment_table" class="list nowrap itemDetail" addButton="新建绩效考核" width="100%">
				<thead>
				<tr>
					<th width="120" type="date" postField="assessment_time"  name="items[#index#].assessment_time_3" format="yyyy-MM-dd"  size="12" fieldClass="date required">考核时间</th>
					<th width="100" type="text" postField="attendance_rate"  name="items[#index#].attendance_rate_3" maxlength="6" size="10" fieldClass="number required">出勤率(%)</th>
					<th width="100" type="text" postField="score"  name="items[#index#].score_3" maxlength="4" size="10" fieldClass="digits required">绩效评分</th>
					<th width="350" type="text" postField="description"  name="items[#index#].description_3" maxlength="150" size="38" fieldClass="text">备注</th>
					<th type="del" >操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="assessment"  varStatus="status1" items="${assessments}">
					<tr>
						<td><fmt:formatDate value="${assessment.assessment_time }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatNumber value="${assessment.attendance_rate }" type="number" pattern="####0.00#"/></td>
						<td><fmt:formatNumber value="${assessment.score }" type="number" pattern="####0"/></td>
						<td title="${assessment.description }">${assessment.description }</td>
						<td width="80" nowrap>
							<a href="void(0);" onclick="edit_assessments(this,'${assessment.id }');return false;" ><span>编辑</span></a>
							<a title="确实要<font color='red'><b>彻底</b></font>删除这条记录吗?" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem"  href="${webroot}/OtherStaffAction.do?method=deleteStaffAssessment&id=${assessment.id}&rownum=${status1.index}" class="delete"><span>删除</span></a>

						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>


			<br />
			<div class="divider"></div>





			<h3 class="contentTitle">职位晋升</h3>
			<table id="positions_table" class="list nowrap itemDetail" addButton="新建职位晋升" width="100%">
				<thead>
				<tr>
					<th width="120" type="date" postField="positions_time"  name="items[#index#].positions_time_4" format="yyyy-MM-dd"  size="12" fieldClass="date required">考核时间</th>
					<th width="100" type="text" postField="level"  name="items[#index#].level_4" maxlength="10" size="8" fieldClass="text required">级别</th>
					<th width="450" type="text" postField="description"  name="items[#index#].description_4" maxlength="150" size="62" fieldClass="text">备注</th>
					<th type="del" >操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="positions"  varStatus="status1" items="${positionss}">
					<tr>
						<td><fmt:formatDate value="${positions.positions_time }" pattern="yyyy-MM-dd"/></td>
						<td>${positions.level }</td>
						<td title="${positions.description }">${positions.description }</td>
						<td width="80" nowrap>
							<a href="void(0);" onclick="edit_positions(this,'${positions.id }');return false;" ><span>编辑</span></a>
							<a title="确实要<font color='red'><b>彻底</b></font>删除这条记录吗?" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem"  href="${webroot}/OtherStaffAction.do?method=deleteStaffPositions&id=${positions.id}&rownum=${status1.index}" class="delete"><span>删除</span></a>

						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>




			<br />
			<div class="divider"></div>



			<h3 class="contentTitle">部门经历</h3>
			<table id="dept_staff_table" class="list nowrap itemDetail" addButton="新建部门经历" width="100%">
				<thead>
				<tr>
					<th width="120" type="lookup" notLookUp="1" fieldClass="required" name="items[#index#].dept.dept_name" lookupGroup="items[#index#].dept" suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup"   lookupPk="dept_id" size="12">部门</th>
					<th width="100" type="date" postField="join_dept_datetime" defaultVal="${currDate }"  name="items[#index#].join_dept_datetime"  size="10" fieldClass="date required">加入部门日期</th>
					<th width="100" type="date" postField="leave_dept_datetime"   name="items[#index#].leave_dept_datetime"  size="10" fieldClass="date">离开部门日期</th>
					<th width="350" type="text" postField="description"  name="items[#index#].description" maxlength="100" size="38" fieldClass="text">说明</th>
					<th type="del" >操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="deptStaff"  varStatus="status1" items="${deptStaffs}">
					<tr>
						<td height="22"><c:if test="${deptStaff.delete_flag == '1' }"><s></c:if>${deptStaff.dept_name }<c:if test="${deptStaff.delete_flag == '1' }"></s></c:if></td>
						<td><c:if test="${deptStaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${deptStaff.join_dept_datetime }" pattern="yyyy-MM-dd"/><c:if test="${deptStaff.delete_flag == '1' }"></s></c:if></td>
						<td><c:if test="${deptStaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${deptStaff.leave_dept_datetime }" pattern="yyyy-MM-dd"/><c:if test="${deptStaff.delete_flag == '1' }"></s></c:if></td>
						<td>${deptStaff.description }</td>
						<td  nowrap>
							<c:if test="${deptStaff.delete_flag == '0' }">
								<a href="void(0);" onclick="edit_dept_staff(this,'${deptStaff.dept_staff_id }');return false;" ><span>编辑</span></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>



			<br />
			<div class="divider"></div>


			<p>
				<label>制单人：</label>  ${ otherStaff1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${ otherStaff1.build_datetime }" pattern="yyyy-MM-dd"/>
			</p>




		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
