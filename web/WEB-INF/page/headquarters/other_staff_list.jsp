<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/OtherStaffAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/OtherStaffAction.do?method=list" method="post">
	<div class="searchBar">



		<ul class="searchContent">

			<li>
				<label>工号：</label>
				<input type="text" name="staff_no" value="${param.staff_no }"/>
			</li>
			<li>
				<label>名称：</label>
				<input type="text" name="staff_name" value="${param.staff_name }"/>
			</li>

			<li>
				<label>在职状态：</label>
				<select name="delete_flag" style="width:153px">
					<option value="">全部</option>
					<option value="0" <c:if test="${param.delete_flag == '0' }">selected</c:if>>在职</option>
					<option value="1" <c:if test="${param.delete_flag == '1' }">selected</c:if>>离职</option>
				</select>
			</li>
		</ul>

		<ul class="searchContent">
		


			
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${staff.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${staff.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			</li>


			<li>
				<label>时间段：</label>
				<input name="date1" class="date" type="text" size="7" value="${param.date1 }" readonly="readonly" />
				<input name="date2" class="date" type="text" size="7" value="${param.date2 }" readonly="readonly" />
			</li>
			<li>
				<label>职位类型：</label>
				<select name="position_type" style="width:153px">
					<option value="">全部</option>
					<option value="1" <c:if test="${otherStaff.position_type == '1' }">selected</c:if>><spring:message code="position.type.1"/></option>
					<option value="2" <c:if test="${otherStaff.position_type == '2' }">selected</c:if>><spring:message code="position.type.2"/></option>
					<option value="3" <c:if test="${otherStaff.position_type == '3' }">selected</c:if>><spring:message code="position.type.3"/></option>
					<option value="4" <c:if test="${otherStaff.position_type == '4' }">selected</c:if>><spring:message code="position.type.4"/></option>
					<option value="5" <c:if test="${otherStaff.position_type == '5' }">selected</c:if>><spring:message code="position.type.5"/></option>
					<option value="6" <c:if test="${otherStaff.position_type == '6' }">selected</c:if>><spring:message code="position.type.6"/></option>
					<option value="7" <c:if test="${otherStaff.position_type == '7' }">selected</c:if>><spring:message code="position.type.7"/></option>
					<option value="8" <c:if test="${otherStaff.position_type == '8' }">selected</c:if>><spring:message code="position.type.8"/></option>
					<option value="9" <c:if test="${otherStaff.position_type == '9' }">selected</c:if>><spring:message code="position.type.9"/></option>
					<option value="10" <c:if test="${otherStaff.position_type == '10' }">selected</c:if>><spring:message code="position.type.10"/></option>
				</select>
			</li>	
			
			<li style="width:40px">
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>
		</ul>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/OtherStaffAction.do?method=toEdit" mask="true" width="850" height="480" rel="other_staff_add" target="dialog"><span>添加总部人员</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">						
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/OtherStaffAction.do?method=deleteOtherStaff" class="delete"><span>删除</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/OtherStaffAction.do?method=toEdit&staff_id={staff_id}" rel="other_staff_edit" width="850" height="480" mask="true" target="dialog" warn="请选择一条数据"><span>修改总部人员</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/OtherStaffAction.do?method=toView&staff_id={staff_id}" mask="true" width="850" height="480" rel="view_project_data"  target="dialog" warn="请选择一条数据"><span>显示明细</span></a></li>
			</c:if>		
			
			
			<li class="line">line</li>

			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/OtherStaffAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			
			<li class="line">line</li>

			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="add" href="${webroot }/OtherStaffAction.do?method=toExcel" mask="true" width="850" height="480" rel="other_staff_add" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>

			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/OtherStaffAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>


			
		</ul>
	</div>
	<table class="table" width="6600" layoutH="138">
		<thead>
			<tr>
				<th width="25"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="40">序号</th>
				<th width="100">工号</th>
				<th width="100">名称</th>
				<th width="120">所在部门</th>
				<th  width="120">职位类型</th>
				<th width="100">岗位名称</th>


				<th width="40">性别</th>
				<th width="100">生日</th>
				<th width="100">民族</th>
				<th width="120">户籍性质</th>
				<th width="160">身份证号码</th>


				<th width="140">联系电话</th>
				<th width="140">紧急联系电话</th>
				<th width="100">QQ号码</th>
				<th width="120">合同开始时间</th>
				<th width="120">合同结束时间</th>
				<th width="80">续签次数</th>
				<th width="160">毕业学校</th>
				<th width="60">学历</th>
				<th width="100">专业</th>
				<th width="100">毕业时间</th>
				<th width="80">工作年限</th>
				<th width="200">户口所在地</th>
				<th width="200">通讯地址</th>

				<th width="120">入职时间</th>
				<th width="120">离职时间</th>



				<th width="100">转正日期</th>
				<th width="100">试用期工资</th>
				<th width="100">正式工资</th>
				<th width="70">基本工资</th>
				<th width="70">岗位工资</th>
				<th width="70">绩效津贴</th>




				<th width="80">电脑补助</th>
				<th width="50">餐补</th>
				<th width="100">奖金(税前)</th>
				<th width="80">出差补助</th>
				<th width="140">社保种类</th>
				<th width="200">社保缴纳单位</th>
				<th width="160">个人缴纳养老保险</th>
				<th width="160">个人缴纳医疗保险</th>
				<th width="160">个人缴纳失业保险</th>
				<th width="140">个人缴纳公积金</th>
				<th width="140">缴纳个人所得税</th>
				<th width="160">公司缴纳养老保险</th>
				<th width="160">公司缴纳医疗保险</th>
				<th width="160">公司缴纳失业保险</th>
				<th width="160">公司缴纳生育保险</th>
				<th width="160">公司缴纳工伤保险</th>
				<th width="140">公司缴纳公积金</th>


				<th width="60">额外</th>
				<th width="200">邮箱</th>
				<th width="200">开户行</th>
				<th width="150">银行卡号</th>
				<th width="80">额外支出</th>
				<th width="140">是否允许发送信息</th>



				<th width="100">合同种类</th>
				<th width="100">合同归属</th>
				<th width="100">社保说明</th>
				<th width="100">社保城市</th>
				<th width="240">工作地点</th>
				<th width="80">证书</th>

				<th width="80">子女教育</th>
				<th width="120">住房贷款利息</th>
				<th width="80">住房租金</th>
				<th width="80">赡养老人</th>
				<th width="80">继续教育</th>

				<th width="600">备注</th>

				<th width="100">创建人</th>
				<th width="120">创建时间</th>
						
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="otherStaff"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${otherStaff.staff_id }">				
				<td>
					<c:if test="${otherStaff.delete_flag == '0' }">
					<input name="ids" value="${otherStaff.staff_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${otherStaff.staff_no }</td>
				<td>
					<c:if test="${curr_ym ==  otherStaff.confirmation_month }">
					<font color="red">
						</c:if>
						<c:if test="${curr_ym ==  otherStaff.contract_end_month }">
						<font color="blue">
							</c:if>
								${ otherStaff.staff_name }
							<c:if test="${curr_ym ==  otherStaff.confirmation_month }">
						</font>
						</c:if>
						<c:if test="${curr_ym ==  otherStaff.contract_end_month }">
					</font>
					</c:if>
				</td>
				<td>${otherStaff.dept_name }</td>
				<td><spring:message code="position.type.${otherStaff.position_type }" /></td>







				<td>${ otherStaff.job_title }</td>
				<td>${ otherStaff.sex }</td>
				<td><fmt:formatDate value="${ otherStaff.birthday }" pattern="yyyy-MM-dd"/></td>
				<td>${ otherStaff.nationality_name }</td>
				<td>${ otherStaff.census_property_name }</td>
				<td>${ otherStaff.identity_card_number }</td>



				<td>${ otherStaff.tel }</td>
				<td>${ otherStaff.pressing_tel }</td>
				<td>${ otherStaff.qq }</td>
				<td><fmt:formatDate value="${ otherStaff.contract_start_date }" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${curr_ym ==  otherStaff.contract_end_month }">
					<font color="blue">
						</c:if>
						<fmt:formatDate value="${ otherStaff.contract_end_date }" pattern="yyyy-MM-dd"/>
						<c:if test="${curr_ym ==  otherStaff.contract_end_month }">
					</font>
					</c:if>
				</td>
				<td>${ otherStaff.contract_number }</td>
				<td>${ otherStaff.graduate_school }</td>
				<td>${ otherStaff.educational_name }</td>

				<td>${ otherStaff.specialty_name }</td>
				<td><fmt:formatDate value="${ otherStaff.graduation_date }" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatNumber value="${ otherStaff.working_life }" type="currency" pattern="#0.00"/></td>



				<td>${ otherStaff.registered_residence }</td>
				<td>${ otherStaff.postal_address }</td>

				<td><fmt:formatDate value="${ otherStaff.join_datetime }" pattern="yyyy-MM-dd"/></td>
				<td><font color="red"><fmt:formatDate value="${ otherStaff.leave_job_datetime }" pattern="yyyy-MM-dd"/></font></td>

				<td>
					<c:if test="${curr_ym ==  otherStaff.confirmation_month }">
					<font color="red">
						</c:if>
						<fmt:formatDate value="${ otherStaff.confirmation_date }" pattern="yyyy-MM-dd"/>
						<c:if test="${curr_ym ==  otherStaff.confirmation_month }">
					</font>
					</c:if>
				</td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.tryout_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.official_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.basic_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.post_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.performance_allowances }" type="currency" pattern="###,###,##0.00"/></td>





				<td align="right"><fmt:formatNumber value="${ otherStaff.computer_allowance }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.meal_allowance }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.project_allowance }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.travel_allowance }" type="currency" pattern="###,###,##0.00"/></td>
				<td>${ otherStaff.insurance_radix }</td>
				<td>${ otherStaff.securty_unit }</td>

				<td align="right"><fmt:formatNumber value="${ otherStaff.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.incometax_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/></td>


				<td align="right"><fmt:formatNumber value="${ otherStaff.extra }" type="currency" pattern="###,###,##0.00"/></td>

				<td>${ otherStaff.email }</td>
				<td>${ otherStaff.open_account }</td>
				<td>${ otherStaff.bank_card_number }</td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.extra_expend }" type="currency" pattern="###,###,##0.00"/></td>

				<td>
					<c:if test="${ otherStaff.can_send_info == 0}"><font color="red"><b></c:if>
					<spring:message code="boolean.${ otherStaff.can_send_info }"/>
					<c:if test="${ otherStaff.can_send_info == 0}"></b></font></c:if>
				</td>





				<td>${ otherStaff.contract_type_name }</td>
				<td>${ otherStaff.contract_attach_name }</td>
				<td>${ otherStaff.social_security_name }</td>
				<td>${ otherStaff.insured_city_name }</td>
				<td title="${ otherStaff.working_address_name }">${ otherStaff.working_address_name }</td>
				<td>${ otherStaff.certificate_name }</td>


				<td align="right"><fmt:formatNumber value="${ otherStaff.children_education }" type="currency" pattern="#0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.housing_loans }" type="currency" pattern="#0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.housing_rent }" type="currency" pattern="#0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.support_elderly }" type="currency" pattern="#0.00"/></td>
				<td align="right"><fmt:formatNumber value="${ otherStaff.continuing_education }" type="currency" pattern="#0.00"/></td>

				<td title="${ otherStaff.description }">${ otherStaff.description }</td>
				<td>${otherStaff.build_username }</td>
				<td><fmt:formatDate value="${otherStaff.build_datetime }" pattern="yyyy-MM-dd"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>

</div>
