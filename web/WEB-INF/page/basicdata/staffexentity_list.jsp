<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/StaffExEntityAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/StaffExEntityAction.do?method=list" method="post">
	<div class="searchBar">		
		<ul class="searchContent">
		
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${staffExEntity.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" value="${staffExEntity.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>			
			</li>
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name }"/>	
			</li>
		

			<li>				
				<label>工资模式：</label>
				<select name="salary_model" style="width:133px">
					<option value="">全部</option>
					<option value="0" <c:if test="${staffExEntity.salary_model == '0' }">selected</c:if>>原有模式</option>
					<option value="1" <c:if test="${staffExEntity.salary_model == '1' }">selected</c:if>>营销模式</option>					
				</select>		
			</li>	
		</ul>	
		
		<ul class="searchContent">		
			
			
			<li>
				<label>员工工号：</label>
				<input type="text" name="staff_no" value="${staffExEntity.staff_no }"/>
			</li>
			<li>
				<label>员工名称：</label>
				<input type="text" name="staff_name" value="${staffExEntity.staff_name }"/>
			</li>

			
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>			
			
		</ul>		
		
			
	</div>	
	
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/StaffExEntityAction.do?method=toEdit&id={sid}" mask="true" width="500"  height="500" rel="update_staffexentity" target="dialog" warn="请选择一条数据"><span>修改人员配置</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/StaffExEntityAction.do?method=toView&id={sid}" mustselect="false" mask="true" width="600"  height="480" rel="ztree" target="dialog" ><span>树状显示</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/StaffChartsAction.do?method=toStaffCharts&id={sid}" mask="true" width="850"  height="600" rel="ztree" target="dialog" ><span>薪资分析</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/StaffChartsAction.do?method=toPieComparison&id={sid}" mask="true" width="950"  height="600" rel="ztree" target="dialog" ><span>饼状比较</span></a></li>
				<li><a class="icon" href="${webroot}/StaffChartsAction.do?method=toBarComparison&id={sid}" mask="true" width="950"  height="600" rel="ztree" target="dialog" ><span>柱状比较</span></a></li>
			</c:if>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
				<th width="200">部门</th>	
				<th width="300">项目名称</th>	
				<th width="100">员工编号</th>	
				<th width="100">员工名称</th>	
				<th width="100">最新报价</th>
				<th width="100">基本工资</th>
				<th width="100">工资模式</th>
				<th width="100">垫资金额</th>
				<th width="100">上线名称</th>
				<th width="150">其它固定支出金额</th>
				<th width="120">其它支出系数</th>
				<th width="140">抵税发票金额</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="staffExEntity"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${staffExEntity.staff_id }">
			
				<td>${staffExEntity.dept_name }</td>
				<td>${staffExEntity.project_name }</td>
			
				<td>${staffExEntity.staff_no }</td>
				<td>${staffExEntity.staff_name }</td>
				
				<td align="right"><b><fmt:formatNumber value="${staffExEntity.qustomerquotes }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${staffExEntity.official_salary }" type="currency" pattern="###,###,##0.00"/></b></td>
								
				<td>${staffExEntity.salary_model_name }</td>
				<td align="right"><b><fmt:formatNumber value="${staffExEntity.guarantee_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				
				<td>${staffExEntity.parent_name }</td>
				<td align="right"><b><fmt:formatNumber value="${staffExEntity.other_expenditures }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${staffExEntity.other_ratio }" type="currency" pattern="###,###,##0.0000"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${staffExEntity.credit_tax_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>	
