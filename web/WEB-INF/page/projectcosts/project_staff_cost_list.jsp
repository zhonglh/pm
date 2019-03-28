<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ProjectStaffCostAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ProjectStaffCostAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
		 	<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${projectStaffCost.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${projectStaffCost.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				

			</li>
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>

			<li>
				<label>月份：</label>
				<input type="text" class="digits date month"  autocomplete="off" size="5"   maxlength="6" minlength="6"  format="yyyyMM" name="work_month1" value="${param.work_month1}"/>
				<input type="text" class="digits date month"  autocomplete="off" size="5"   maxlength="6" minlength="6"  format="yyyyMM" name="work_month2" value="${param.work_month2}"/>
			</li>
			
		</ul>
		
		
		<ul class="searchContent">
		
		
			<li>
				<label>人员名称：</label>
				<input type="text"  name="staff_name" value="${param.staff_name}"/>
			</li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			
		</ul>
			
	
	
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/ProjectStaffCostAction.do?method=toView&project_staffcost_id={sid_project_staffcost_id}" mask="true" width="900"  height="480" rel="view_workAttendanceGroupAction" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
				
				<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/ProjectStaffCostAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				</c:if>
			</c:if>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr>
				<th width="40">序号</th>			
				<th width="200">项目名称</th>	
				<th width="100">项目编号</th>
				<th width="80">月份</th>
				<th width="80">人员名称</th>
				<th width="80">人员工号</th>
				<th width="80">工作天数</th>
				<th width="100">实发金额</th>
				<th width="100">社保</th>
				<th width="100">公积金</th>
				<th width="100">个税</th>
				<th width="100">总成本</th>
				<th width="120">实际支付日期</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="projectStaffCost"  varStatus="status1" items="${list}">
			<tr target="sid_project_staffcost_id" rel="${projectStaffCost.project_staffcost_id }">
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${projectStaffCost.project_name }</td>
				<td>${projectStaffCost.project_no }</td>
				<td>${projectStaffCost.work_month }</td>
				<td>${projectStaffCost.staff_name }</td>
				<td>${projectStaffCost.staff_no }</td>
				<td>${projectStaffCost.work_days }</td>
				<td><b><fmt:formatNumber value="${projectStaffCost.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><b><fmt:formatNumber value="${projectStaffCost.insurance_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><b><fmt:formatNumber value="${projectStaffCost.pub_funds_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><b><fmt:formatNumber value="${projectStaffCost.personal_income_tax }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><b><fmt:formatNumber value="${projectStaffCost.all_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><fmt:formatDate value="${projectStaffCost.pay_date }" pattern="yyyy-MM-dd"/></td>
			</p>
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount_any.jsp"></c:import>
	
</div>
