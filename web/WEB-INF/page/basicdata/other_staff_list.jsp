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
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${staff.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${staff.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			</li>		
			
			
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
	<div class="panelBar">
		<ul class="toolBar">
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/OtherStaffAction.do?method=toEdit" mask="true" width="820" height="260" rel="other_staff_add" target="dialog"><span>添加行政人员</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">						
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/OtherStaffAction.do?method=deleteOtherStaff" class="delete"><span>删除</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/OtherStaffAction.do?method=toEdit&staff_id={staff_id}" rel="other_staff_edit" width="820" height="260" mask="true" target="dialog" warn="请选择一条数据"><span>修改行政人员</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/OtherStaffAction.do?method=toView&staff_id={staff_id}" mask="true" width="800" height="260" rel="view_project_data"  target="dialog" warn="请选择一条数据"><span>显示明细</span></a></li>
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
	<table class="table" width="1000" layoutH="112">
		<thead>
			<tr>
				<th width="25"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="40">序号</th>
				<th width="100">工号</th>
				<th width="100">名称</th>
				<th width="120">所在部门</th>
				<th  width="120">职位类型</th>
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
				<td>${otherStaff.staff_name }</td>
				<td>${otherStaff.dept_name }</td>
				<td><spring:message code="position.type.${otherStaff.position_type }" /></td>
				<td>${otherStaff.build_username }</td>
				<td><fmt:formatDate value="${otherStaff.build_datetime }" pattern="yyyy-MM-dd"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>

</div>
