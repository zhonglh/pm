<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form  class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			



		<div class="divider"></div>
			
						<table id="project_staff_cost_table" class="list nowrap " width="100%">
							<thead>
								<tr>
									<th width="100" type="lookup" fieldClass="required" name="items[#index#].staff.staff_name" lookupGroup="items[#index#].staff" lookupUrl="${webroot }/StaffCostAction.do?method=lookup&in_project=0&delete_flag=0"   lookupPk="staff_id" size="25">员工姓名</th>
									<th width="180" type="label" >项目名称</th>
									<th width="120" type="date" postField="join_project_datetime" defaultVal="${currDate }"  name="items[#index#].join_project_datetime"  size="12" fieldClass="date required">入场日期</th>
									<th width="120" type="date" postField="leave_project_datetime" defaultVal="${currDate }"  name="items[#index#].leave_project_datetime"  size="12" fieldClass="date required">离场日期</th>
									<!-- <th type="date" postField="leave_project_datetime"  name="items[#index#].leave_project_datetime"  size="12" fieldClass="date">离场日期</th> -->
									<th width="120" type="number" postField="technical_cost"  name="items[#index#].staff.technical_cost" defaultVal="0"  maxlength="12" size="12" fieldClass="number">技术费用</th>
									<th width="300" type="label" >说明</th>
								</tr>												
							</thead>
							<tbody>								
								<c:forEach var="projectstaff"  varStatus="status1" items="${projectStaffs}">
								<tr>
									<td>${projectstaff.staff_name }</td>
									<td>${projectstaff.project_name }</td>
									<td><fmt:formatDate value="${projectstaff.join_project_datetime }" pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${projectstaff.leave_project_datetime }" pattern="yyyy-MM-dd"/></td>
									<td>${projectstaff.technical_cost }</td>
									<td>${projectstaff.description }</td>
								</tr>
								</c:forEach>								
							</tbody>
						</table>	
			
		<div class="divider"></div>
			

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
