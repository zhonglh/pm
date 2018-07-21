<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/OtherStaffAction.do?method=lookup">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/OtherStaffAction.do?method=lookup" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			
			<li>
				<label>姓名：</label>
				<input type="text" name="staff_name" value="${param.staff_name}"/>

			</li>
			
			<li>
				<label>职位类型：</label>
				<select name="position_type">
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
	<table class="table" width="1000" layoutH="92">
		<thead>
			<tr>
				<th width="25">选择</th>
				<th width="40">序号</th>
				<th width="120">工号</th>
				<th width="120">名称</th>
				<th width="120">所在部门</th>
				<th width="100">职位</th>
				<th width="100">创建人</th>
				<th width="120">创建时间</th>
						
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="otherStaff"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${otherStaff.staff_id }">				
				<td>
					<c:if test="${otherStaff.delete_flag == '0' }">
					<a class="btnSelect" href="javascript:$.bringBack({dept_id:'${otherStaff.dept_id }',dept_name:'${otherStaff.dept_name }',user_id:'${otherStaff.staff_id }',user_name:'${otherStaff.staff_name }',staff_id:'${otherStaff.staff_id }',staff_name:'${otherStaff.staff_name }',staff_type:'${staff_type }'})" title="">选择</a>
					</c:if>
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${otherStaff.staff_no }</td>
				<td>${otherStaff.staff_name }</td>
				<td>${otherStaff.dept_name }</td>
				<td><spring:message code="position.type.${otherStaff.position_type }"/></td>
				<td>${otherStaff.build_username }</td>
				<td><fmt:formatDate value="${otherStaff.build_datetime }" pattern="yyyy-MM-dd"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>

</div>
