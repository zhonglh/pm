<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ProjectAction.do?method=lookup">

	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/ProjectAction.do?method=lookup" method="post">
	<div class="searchBar">
		<ul class="searchContent">


			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name }"/>				
				<input type="hidden" name="origin_type" value="${param.origin_type}" />
			</li>

			<li>
				<label>项目编号：</label>
				<input type="text" name="project_no" value="${param.project_no }"/>
			</li>	

	
			 
			<li>
				<label>项目类型：</label>
				<select name="project_type" id="projecct_type" style="width:153px">
					<option value=""><spring:message code="project.type." /></option>
					<option value="1" <c:if test="${project.project_type == '1' }">selected</c:if> ><spring:message code="project.type.1" /></option>
					<option value="2" <c:if test="${project.project_type == '2' }">selected</c:if> ><spring:message code="project.type.2" /></option>
					<option value="3" <c:if test="${project.project_type == '3' }">selected</c:if> ><spring:message code="project.type.3" /></option>
					
				</select>
			</li>

		</ul>
		
		<ul class="searchContent">
		
		
 			
			<li>
				<label>客户名称：</label>
				<input name="client.client_name" class="text" type="text" size="20"  value="${project.project_client_name }"  />
			</li>		
 			


				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		
		</ul>

		
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="1500" layoutH="118">
		<thead>
			<tr>
				<th width="40">选择</th>
				<th width="30">序号</th>
				<th width="150">项目名称</th>
				<th width="120">项目编号</th>
				<th width="80">项目金额</th>
				<th width="150">客户名称</th>
				<th width="80">销售负责人</th>
				<th width="80">项目管理人</th>
				<th width="60">项目类型</th>
				<th width="80">信息来源</th>
				<th width="80">所属部门</th>
				
				<th width="80">预估成本</th>
				<th width="120">合同编号</th>
				<th width="80">项目联系人</th>
				<th width="60">售前支撑</th>
				<th width="60">售后支撑</th>
				<th width="80">销售额归属</th>
				<th width="80">执行额归属</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="project"  varStatus="status1" items="${list}">
				<tr target="project_id" rel="${project.project_id}"  <c:if test="${project.exceed_budget}">style="background-color:red;"</c:if> >
					<td><a class="btnSelect" href="javascript:$.bringBack({project_id:'${project.project_id }',project_name:'${project.project_name }',project_no:'${project.project_no }',deptid:'${project.deptid }',deptname:'${project.deptname }',project_type:'${project.project_type }',project_client_name:'${project.project_client_name }'});callBackIndividuation('${param.origin_type}','${project.project_type }')" title="">选择</a></td>
					<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
					
					<td>${project.project_name }</td>
					<td>${project.project_no }</td>
					<td nowarp align="right"><b><fmt:formatNumber value="${project.project_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td>${project.project_client_name }</td>
					<td>${project.sales_username }</td>
					<td>${project.manage_username }</td>
					<td><spring:message code="project.type.${project.project_type }"/></td>		
					<td>${project.info_sources_username }</td>		
					<td>${project.deptname }</td>		
					
					<td nowarp align="right"><b><fmt:formatNumber value="${project.estimate_costs }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td>${project.contract_no }</td>
					<td>${project.project_contacts_name }</td>
					<td>${project.sales_before_username }</td>
					<td>${project.sales_after_username }</td>
					<td>${project.sales_amount_deptname }</td>			
					<td>${project.exec_amount_deptname }</td>	
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>
	
</div>
