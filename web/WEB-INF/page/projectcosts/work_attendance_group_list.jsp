<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/WorkAttendanceGroupAction.do?method=list">
	
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/WorkAttendanceGroupAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${workAttendance1.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${workAttendance1.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			
			</li>
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>

			<li>
				<label>考勤月份：</label>
				<input type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  size="7" format="yyyyMM" name="attendance_month1" value="${param.attendance_month1}"/>
				<input type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  size="7" format="yyyyMM" name="attendance_month2" value="${param.attendance_month2}"/>
			
			
			</li>
			
		</ul>
			
		<ul class="searchContent">
		
				
			
			<li>
				<label>姓名：</label>
				<input type="text" name="staff_name" value="${param.staff_name}"/>
			</li>
			
			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" style="width:153px">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
				</select>
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
		
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/WorkAttendanceGroupAction.do?method=toEditFirst" mask="true" width="1050" height="480" rel="add_workAttendanceGroupAction"  target="dialog"><span>添加考勤</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/WorkAttendanceGroupAction.do?method=deleteWorkAttendanceGroup" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/WorkAttendanceGroupAction.do?method=toEdit&id={sid_work_attendance}" mask="true" width="1050"  height="480" rel="update_workAttendanceGroupAction" target="dialog" warn="请选择一条数据"><span>修改考勤</span></a></li>
			</c:if>
			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/WorkAttendanceGroupAction.do?method=toView&id={sid_work_attendance}" mask="true" width="1050"  height="480" rel="view_workAttendanceGroupAction" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>		
			
			
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/WorkAttendanceGroupAction.do?method=batchVerifyWorkAttendance" class="delete"><span>批量核单</span></a></li> 
			</c:if>	
			

			
			<li class="line">line</li>
			
				
			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/WorkAttendanceGroupAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>		
			</c:if>
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/WorkAttendanceGroupAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_riemburse" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			 
		
			
			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/WorkAttendanceGroupAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>					
			
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">项目名称</th>
				<th width="150">项目编号</th>
				<th width="80">考勤月份</th>
				<th width="40">人数</th>
				<th width="80">制表人</th>
				<th width="120">制表日期</th>
				<th width="120">核单情况</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="workAttendance"  varStatus="status1" items="${list}">
			<tr target="sid_work_attendance" rel="${workAttendance.attendance_month }___${workAttendance.project_id }">
				<td>
				
				
					<c:if test="${workAttendance.project_attendance_number > workAttendance.verify_number }">
						<c:if test="${ 0 == workAttendance.verify_number }">
							<input name="ids" value="${workAttendance.attendance_month }___${workAttendance.project_id }" type="checkbox" />
						</c:if>
					</c:if>
				</td>
				<td>${workAttendance.project_name }</td>
				<td>${workAttendance.project_no }</td>
				<td>${workAttendance.attendance_month }</td>
				<td>${workAttendance.project_attendance_number }</td>
				<td>${workAttendance.build_username }</td>
				<td><fmt:formatDate value="${workAttendance.build_datetime }" pattern="yyyy-MM-dd" /></td>
				<td>
					<c:if test="${workAttendance.project_attendance_number == workAttendance.verify_number }"><font color="red">已核单</font></c:if>									
					<c:if test="${workAttendance.project_attendance_number > workAttendance.verify_number }">
						<c:if test="${ 0 == workAttendance.verify_number }">
							未核单
						</c:if>
						<c:if test="${ 0 != workAttendance.verify_number }">
							<font color="red">部分核单</font>	
						</c:if>
					</c:if>
				</td>				
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
	
</div>
