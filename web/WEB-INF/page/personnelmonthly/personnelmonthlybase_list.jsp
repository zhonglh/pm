<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/PersonnelMonthlyBaseAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />	
	<input type="hidden" name="staff.staff_id" value="${personnelMonthlyBase.staff_id}" />
	<input type="hidden" name="staff.staff_name" value="${personnelMonthlyBase.staff_name}" />
	<input type="hidden" name="monthly_type" value="${param.monthly_type}" />
	<input type="hidden" name="the_month" value="${param.the_month}" />	
	<input type="hidden" name="verify_flag" value="${param.verify_flag}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return pmSearch(this);" action="${webroot }/PersonnelMonthlyBaseAction.do?method=list" method="post">
	<div class="searchBar">
			<ul class="searchContent">
			
		
			<li>
				<label>人员名称：</label>	
				<input type="hidden" size="2" name="staff.staff_id" value="${personnelMonthlyBase.staff_id }"/>
				<input name="staff.staff_name" id="staff_name" class="text" size="15" type="text" value="${personnelMonthlyBase.staff_name }" readonly />
				<a class="btnLook" href="${webroot }/StaffCostAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			
			</li>	
		
			<li>
				<label>类型：</label>						
				<select name="monthly_type" style="width:150px">
				<option value="" <c:if test="${'' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly."/></option>
				<option value="A" <c:if test="${'A' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.A"/></option>
				<option value="B" <c:if test="${'B' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.B"/></option>
				<option value="C" <c:if test="${'C' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.C"/></option>
				<option value="D" <c:if test="${'D' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.D"/></option>
				<option value="1" <c:if test="${'1' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.1"/></option>
				<option value="2" <c:if test="${'2' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.2"/></option>
				<option value="3" <c:if test="${'3' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.3"/></option>
				<option value="4" <c:if test="${'4' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.4"/></option>
				<option value="5" <c:if test="${'5' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.5"/></option>
				<option value="6" <c:if test="${'6' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.6"/></option>
				<option value="7" <c:if test="${'7' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.7"/></option>
				<option value="8" <c:if test="${'8' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.8"/></option>
				<option value="9" <c:if test="${'9' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.9"/></option>
				</select>
			</li>

			<li>
				<label>月份：</label>
				<input name="the_month" id="the_month" class="digits  month date" readonly format="yyyyMM" minlength="6" maxlength="6" type="text"  value="<c:if test="${ personnelMonthlyBase.the_month != 0}">${personnelMonthlyBase.the_month }</c:if>" />
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
				<li><a class="add" href="${webroot}/PersonnelMonthlyBaseAction.do?method=toFirst" mask="true" width="900" height="400" rel="add_personnelmonthlybase"  target="dialog"><span>添加人事月报</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/PersonnelMonthlyBaseAction.do?method=deletePersonnelMonthlyBase" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/PersonnelMonthlyBaseAction.do?method=toEditBase&id={sid}" mask="true" width="900"  height="400" rel="update_personnelmonthlybase" target="dialog" warn="请选择一条数据"><span>修改人事月报</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/PersonnelMonthlyBaseAction.do?method=toViewBase&id={sid}" mask="true" width="900"  height="480" rel="view_personnelmonthlybase" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/PersonnelMonthlyBaseAction.do?method=batchVerifyPersonnelMonthlyBase" class="delete"><span>批量核单</span></a></li> 
			</c:if>		
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/PersonnelMonthlyBaseAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/PersonnelMonthlyBaseAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_personnelmonthlybase" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/PersonnelMonthlyBaseAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	
	
	<table class="list" width="1800" layoutH="60">
	
	
	
	
	
	
		<c:if test="${ not empty joinStaffCosts}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职时间</th>
				<th width="100">转正时间</th>
				<th width="100">试用期工资</th>
				<th width="100">正式工资</th>
				<th width="80">餐补</th>
				<th width="80">电脑补助</th>
				<th width="180">身份证</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="joinStaffCost"  varStatus="status1" items="${joinStaffCosts}">
			<tr target="sid" rel="${joinStaffCost.staff_id }">				
				<td></td>
				<td>${joinStaffCost.the_month }</td>
				<td><spring:message code="personnel.monthly.${joinStaffCost.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${joinStaffCost.project_name }</td>
				<td>${joinStaffCost.staff_no }</td>
				<td>${joinStaffCost.staff_name }</td>
				<td><b><fmt:formatDate value="${joinStaffCost.join_datetime }" pattern="yyyy-MM-dd"/></b></td>				
				<td><fmt:formatDate value="${joinStaffCost.confirmation_date }" pattern="yyyy-MM-dd"/></td>				
				<td align="right"><b><fmt:formatNumber value="${joinStaffCost.tryout_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${joinStaffCost.official_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${joinStaffCost.meal_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${joinStaffCost.computer_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${joinStaffCost.identity_card_number }</td>	
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
	

		<c:if test="${ not empty leaveStaffCosts}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职时间</th>
				<th width="100">离职时间</th>
				<th width="100">试用期工资</th>
				<th width="100">正式工资</th>
				<th width="80">餐补</th>
				<th width="80">电脑补助</th>
				<th width="180">身份证</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="leaveStaffCost"  varStatus="status1" items="${leaveStaffCosts}">
			<tr target="sid" rel="${leaveStaffCost.staff_id }">
				<td></td>
				<td>${leaveStaffCost.the_month }</td>
				<td><spring:message code="personnel.monthly.${leaveStaffCost.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${leaveStaffCost.project_name }</td>
				<td>${leaveStaffCost.staff_no }</td>
				<td>${leaveStaffCost.staff_name }</td>
				<td><fmt:formatDate value="${leaveStaffCost.join_datetime }" pattern="yyyy-MM-dd"/></td>
				<td><b><fmt:formatDate value="${leaveStaffCost.leave_job_datetime }" pattern="yyyy-MM-dd"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${leaveStaffCost.tryout_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${leaveStaffCost.official_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${leaveStaffCost.meal_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${leaveStaffCost.computer_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${leaveStaffCost.identity_card_number }</td>
			</tr>
			</c:forEach>
		</tbody>
		</c:if>




		<c:if test="${ not empty contrctExpirationStaffCosts}">
			<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">合同开始时间</th>
				<th width="100">合同结束时间</th>
				<th width="100">试用期工资</th>
				<th width="100">正式工资</th>
				<th width="80">餐补</th>
				<th width="80">电脑补助</th>
				<th width="180">身份证</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="joinStaffCost"  varStatus="status1" items="${contrctExpirationStaffCosts}">
				<tr target="sid" rel="${joinStaffCost.staff_id }">
					<td></td>
					<td>${joinStaffCost.the_month }</td>
					<td><spring:message code="personnel.monthly.${joinStaffCost.monthly_type }"></spring:message></td>
					<td>${status1.index+1 }</td>
					<td>${joinStaffCost.project_name }</td>
					<td>${joinStaffCost.staff_no }</td>
					<td>${joinStaffCost.staff_name }</td>
					<td><b><fmt:formatDate value="${joinStaffCost.contract_start_date }" pattern="yyyy-MM-dd"/></b></td>
					<td><fmt:formatDate value="${joinStaffCost.contract_end_date }" pattern="yyyy-MM-dd"/></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.tryout_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.official_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.meal_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.computer_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td>${joinStaffCost.identity_card_number }</td>
				</tr>
			</c:forEach>
			</tbody>
		</c:if>


		<c:if test="${ not empty tryoutStaffCosts}">
			<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职时间</th>
				<th width="100">转正时间</th>
				<th width="100">试用期工资</th>
				<th width="100">正式工资</th>
				<th width="80">餐补</th>
				<th width="80">电脑补助</th>
				<th width="180">身份证</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="joinStaffCost"  varStatus="status1" items="${tryoutStaffCosts}">
				<tr target="sid" rel="${joinStaffCost.staff_id }">
					<td></td>
					<td>${joinStaffCost.the_month }</td>
					<td><spring:message code="personnel.monthly.${joinStaffCost.monthly_type }"></spring:message></td>
					<td>${status1.index+1 }</td>
					<td>${joinStaffCost.project_name }</td>
					<td>${joinStaffCost.staff_no }</td>
					<td>${joinStaffCost.staff_name }</td>
					<td><b><fmt:formatDate value="${joinStaffCost.join_datetime }" pattern="yyyy-MM-dd"/></b></td>
					<td><fmt:formatDate value="${joinStaffCost.confirmation_date }" pattern="yyyy-MM-dd"/></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.tryout_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.official_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.meal_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="right"><b><fmt:formatNumber value="${joinStaffCost.computer_allowance }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td>${joinStaffCost.identity_card_number }</td>
				</tr>
			</c:forEach>
			</tbody>
		</c:if>





		<c:if test="${ not empty officials}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职日期</th>
				<th width="100">转正日期</th>

				<th width="120">当月工作日天数</th>
				<th width="100">试用期工资</th>

				<th width="130">当月试用工作日天数</th>
				<th width="100">正式工资</th>

				<th width="130">当月转正工作日天数</th>
				<th width="100">当月转正工资</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="official"  varStatus="status1" items="${officials}">
			<tr target="sid" rel="${official.id }">
				
				<td>
					<c:if test="${official.verify_userid==null || official.verify_userid=='' }">
						<input name="ids" value="${official.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${official.the_month }</td>
				<td><spring:message code="personnel.monthly.${official.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${official.project_name }</td>
				<td>${official.staff_no }</td>
				<td>${official.staff_name }</td>
				<td><fmt:formatDate value="${official.join_datetime }" pattern="yyyy-MM-dd"/></td>				
				<td><fmt:formatDate value="${official.confirmation_date }" pattern="yyyy-MM-dd"/></td>
				<td>${official.work_days }</td>
				<td align="right"><b><fmt:formatNumber value="${official.tryout_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${official.tryout_days }</td>
				<td align="right"><b><fmt:formatNumber value="${official.official_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${official.official_days }</td>
				<td align="right"><b><fmt:formatNumber value="${official.curr_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${official.build_username }</td>
				<td>${official.verify_username }</td>
				<td>
					<c:if test="${official.need_approve!=null && official.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		<c:if test="${ not empty addsalarys}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职时间</th>
				<th width="100">调薪时间</th>
				<th width="120">当月工作日天数</th>
				<th width="100">调薪前薪资</th>
				<th width="130">当月调薪前工作日天数</th>
				<th width="100">调薪后薪资</th>
				<th width="130">当月调薪后工作日天数</th>
				<th width="100">当月工资</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="salary"  varStatus="status1" items="${addsalarys}">
			<tr target="sid" rel="${salary.id }">
				
				<td>
					<c:if test="${salary.verify_userid==null || salary.verify_userid=='' }">
						<input name="ids" value="${salary.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${salary.the_month }</td>
				<td><spring:message code="personnel.monthly.${salary.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${salary.project_name }</td>
				<td>${salary.staff_no }</td>
				<td>${salary.staff_name }</td>
				<td><fmt:formatDate value="${salary.join_datetime }" pattern="yyyy-MM-dd"/></td>				
				<td><fmt:formatDate value="${salary.change_time }" pattern="yyyy-MM-dd"/></td>
				<td>${salary.work_days }</td>
				<td align="right"><b><fmt:formatNumber value="${salary.old_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${official.old_days }</td>
				<td align="right"><b><fmt:formatNumber value="${salary.new_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${official.new_days }</td>
				<td align="right"><b><fmt:formatNumber value="${salary.curr_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${salary.description }</td>
				<td>${salary.build_username }</td>
				<td>${salary.verify_username }</td>
				<td>
					<c:if test="${salary.need_approve!=null && salary.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		<c:if test="${ not empty decrsalarys}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职时间</th>
				<th width="100">调薪时间</th>
				<th width="120">当月工作日天数</th>
				<th width="100">调薪前薪资</th>
				<th width="130">当月调薪前工作日天数</th>
				<th width="100">调薪后薪资</th>
				<th width="130">当月调薪后工作日天数</th>
				<th width="100">当月工资</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="salary"  varStatus="status1" items="${decrsalarys}">
			<tr target="sid" rel="${salary.id }">
				
				<td>
					<c:if test="${salary.verify_userid==null || salary.verify_userid=='' }">
						<input name="ids" value="${salary.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${salary.the_month }</td>
				<td><spring:message code="personnel.monthly.${salary.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${salary.project_name }</td>
				<td>${salary.staff_no }</td>
				<td>${salary.staff_name }</td>
				<td><fmt:formatDate value="${salary.join_datetime }" pattern="yyyy-MM-dd"/></td>				
				<td><fmt:formatDate value="${salary.change_time }" pattern="yyyy-MM-dd"/></td>
				<td>${salary.work_days }</td>
				<td align="right"><b><fmt:formatNumber value="${salary.old_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${salary.old_days }</td>
				<td align="right"><b><fmt:formatNumber value="${salary.new_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${salary.new_days }</td>
				<td align="right"><b><fmt:formatNumber value="${salary.curr_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${salary.description }</td>
				<td>${salary.build_username }</td>
				<td>${salary.verify_username }</td>
				<td>
					<c:if test="${salary.need_approve!=null && salary.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		
		<c:if test="${ not empty addinsurances}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="150">社保档次</th>
				<th width="100">养老个人</th>
				<th width="100">养老单位</th>
				<th width="100">医疗个人</th>
				<th width="100">医疗单位</th>
				<th width="100">失业个人</th>
				<th width="100">失业单位</th>
				<th width="100">工伤单位</th>
				<th width="100">生育单位</th>
				<th width="120">社保缴纳单位</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="insurance"  varStatus="status1" items="${addinsurances}">
			<tr target="sid" rel="${insurance.id }">
				
				<td>
					<c:if test="${insurance.verify_userid==null || insurance.verify_userid=='' }">
						<input name="ids" value="${insurance.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${insurance.the_month }</td>
				<td><spring:message code="personnel.monthly.${insurance.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${insurance.project_name }</td>
				<td>${insurance.staff_no }</td>
				<td>${insurance.staff_name }</td>	
				<td>${insurance.insurance_radix }</td>				
				<td align="right"><b><fmt:formatNumber value="${insurance.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${insurance.securty_unit }</td>
				<td>${insurance.description }</td>
				<td>${insurance.build_username }</td>
				<td>${insurance.verify_username }</td>
				<td>
					<c:if test="${insurance.need_approve!=null && insurance.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		<c:if test="${ not empty decrinsurances}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="150">社保档次</th>
				<th width="100">养老个人</th>
				<th width="100">养老单位</th>
				<th width="100">医疗个人</th>
				<th width="100">医疗单位</th>
				<th width="100">失业个人</th>
				<th width="100">失业单位</th>
				<th width="100">工伤单位</th>
				<th width="100">生育单位</th>
				<th width="120">社保缴纳单位</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="insurance"  varStatus="status1" items="${decrinsurances}">
			<tr target="sid" rel="${insurance.id }">
				
				<td>
					<c:if test="${insurance.verify_userid==null || insurance.verify_userid=='' }">
						<input name="ids" value="${insurance.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${insurance.the_month }</td>
				<td><spring:message code="personnel.monthly.${insurance.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${insurance.project_name }</td>
				<td>${insurance.staff_no }</td>
				<td>${insurance.staff_name }</td>	
				<td>${insurance.insurance_radix }</td>				
				<td align="right"><b><fmt:formatNumber value="${insurance.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${insurance.securty_unit }</td>
				<td>${insurance.description }</td>
				<td>${insurance.build_username }</td>
				<td>${insurance.verify_username }</td>
				<td>
					<c:if test="${insurance.need_approve!=null && insurance.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		<c:if test="${ not empty addreserveFunds}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="150">社保档次</th>
				<th width="100">个人</th>
				<th width="100">单位</th>
				<th width="100">基数</th>
				<th width="120">社保缴纳单位</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reserveFund"  varStatus="status1" items="${addreserveFunds}">
			<tr target="sid" rel="${reserveFund.id }">
				
				<td>
					<c:if test="${reserveFund.verify_userid==null || reserveFund.verify_userid=='' }">
						<input name="ids" value="${reserveFund.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${reserveFund.the_month }</td>
				<td><spring:message code="personnel.monthly.${reserveFund.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${reserveFund.project_name }</td>
				<td>${reserveFund.staff_no }</td>
				<td>${reserveFund.staff_name }</td>	
				<td>${reserveFund.insurance_radix }</td>				
				<td align="right"><b><fmt:formatNumber value="${reserveFund.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${reserveFund.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${reserveFund.base_cardinal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${reserveFund.securty_unit }</td>
				<td>${reserveFund.description }</td>
				<td>${reserveFund.build_username }</td>
				<td>${reserveFund.verify_username }</td>	
				<td>
					<c:if test="${reserveFund.need_approve!=null && reserveFund.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		<c:if test="${ not empty decrreserveFunds}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="150">社保档次</th>
				<th width="100">个人</th>
				<th width="100">单位</th>
				<th width="100">基数</th>
				<th width="120">社保缴纳单位</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="reserveFund"  varStatus="status1" items="${decrreserveFunds}">
			<tr target="sid" rel="${reserveFund.id }">
				
				<td>
					<c:if test="${reserveFund.verify_userid==null || reserveFund.verify_userid=='' }">
						<input name="ids" value="${reserveFund.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${reserveFund.the_month }</td>
				<td><spring:message code="personnel.monthly.${reserveFund.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${reserveFund.project_name }</td>
				<td>${reserveFund.staff_no }</td>
				<td>${reserveFund.staff_name }</td>	
				<td>${reserveFund.insurance_radix }</td>				
				<td align="right"><b><fmt:formatNumber value="${reserveFund.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${reserveFund.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${reserveFund.base_cardinal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${reserveFund.securty_unit }</td>
				<td>${reserveFund.description }</td>
				<td>${reserveFund.build_username }</td>
				<td>${reserveFund.verify_username }</td>	
				<td>
					<c:if test="${reserveFund.need_approve!=null && reserveFund.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		
		<c:if test="${ not empty bounss}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="100">入职时间</th>
				<th width="100">奖惩时间</th>
				<th width="100">奖惩金额</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bouns"  varStatus="status1" items="${bounss}">
			<tr target="sid" rel="${bouns.id }">
				
				<td>
					<c:if test="${bouns.verify_userid==null || bouns.verify_userid=='' }">
						<input name="ids" value="${bouns.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${bouns.the_month }</td>
				<td><spring:message code="personnel.monthly.${bouns.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${bouns.project_name }</td>
				<td>${bouns.staff_no }</td>
				<td>${bouns.staff_name }</td>
				<td><fmt:formatDate value="${bouns.join_datetime }" pattern="yyyy-MM-dd"/></td>				
				<td><fmt:formatDate value="${bouns.change_time }" pattern="yyyy-MM-dd"/></td>	
				<td align="right"><b><fmt:formatNumber value="${bouns.amount }" type="currency" pattern="###,###,##0.00"/></b></td>			
				<td>${bouns.description }</td>
				<td>${bouns.build_username }</td>
				<td>${bouns.verify_username }</td>	
				<td>
					<c:if test="${bouns.need_approve!=null && bouns.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
		<c:if test="${ not empty salarySupplys}">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="120">状态</th>
				<th width="50">序号</th>
				<th width="200">项目名称</th>
				<th width="60">工号</th>
				<th width="80">姓名</th>
				<th width="160">补充类型</th>	
				<th width="120">补贴/扣除金额</th>
				<th width="100">补充时间</th>
				<th width="180">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="salarySupply"  varStatus="status1" items="${salarySupplys}">
			<tr target="sid" rel="${salarySupply.id }">
				
				<td>
					<c:if test="${salarySupply.verify_userid==null || salarySupply.verify_userid=='' }">
						<input name="ids" value="${salarySupply.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${salarySupply.the_month }</td>
				<td><spring:message code="personnel.monthly.${salarySupply.monthly_type }"></spring:message></td>
				<td>${status1.index+1 }</td>
				<td>${salarySupply.project_name }</td>
				<td>${salarySupply.staff_no }</td>
				<td>${salarySupply.staff_name }</td>
				<td>${salarySupply.supply_type }</td>
				<td align="right"><b><fmt:formatNumber value="${salarySupply.amount }" type="currency" pattern="###,###,##0.00"/></b></td>			
				<td><fmt:formatDate value="${salarySupply.supply_time }" pattern="yyyy-MM-dd"/></td>	
				<td>${salarySupply.description }</td>
				<td>${salarySupply.build_username }</td>
				<td>${salarySupply.verify_username }</td>	
				<td>
					<c:if test="${salarySupply.need_approve!=null && salarySupply.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>			
			</tr>
			</c:forEach>			
		</tbody>
		</c:if>
		
		
	</table>	
</div>	
