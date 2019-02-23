<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1800" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="80">考勤月份</th>
						<th  width="150">项目名称</th>
						<th width="80">人员名称</th>
						<th width="80">人员工号</th>
						<th width="100">工作日天数</th>
						<th width="100">实际工作天数</th>
						<th width="100">法定假日天数</th>
						
						<th width="80">倒休天数</th>
						<th width="80">年假天数</th>
						<th width="80">出差天数</th>
						<th width="80">事假天数</th>
						
						
						<th width="80">病假天数</th>

						<th width="80" >待岗天数</th>
						<th width="80" >产假天数</th>
						<%--<th width="90" >医疗假天数</th>--%>

						<th width="80">旷工天数</th>
						<th width="80">迟到天数</th>
						<th width="80">周末加班</th>
						
						<th width="80">日常加班(小时)</th>
						<th width="200">备注</th>
					</tr>
				</thead>
				<tbody>					
					<c:forEach var="otherWorkAttendance"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${otherWorkAttendance.errorInfo != null && otherWorkAttendance.errorInfo != '' }">
								<font color="red">${otherWorkAttendance.errorInfo }</font>
							</c:if>
							<c:if test="${otherWorkAttendance.errorInfo == null || otherWorkAttendance.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${otherWorkAttendance.str_month }</td>
						<td>${otherWorkAttendance.project_name }</td>
						<td>${otherWorkAttendance.staff_name }</td>
						<td>${otherWorkAttendance.staff_no }</td>
						<td>${otherWorkAttendance.should_work_days }</td>
						<td>${otherWorkAttendance.work_days }</td>
						<td>${otherWorkAttendance.legal_holidays }</td>
						
						<td>${otherWorkAttendance.swopped_holidays }</td>
						<td>${otherWorkAttendance.annual_leave_days }</td>
						<td>${otherWorkAttendance.business_trip_days }</td>
						<td>${otherWorkAttendance.personal_leave_days }</td>
						<td>${otherWorkAttendance.sick_leave_days }</td>

						<td>${otherWorkAttendance.waiting_post_days }</td>
						<td>${otherWorkAttendance.maternity_leave_days }</td>
						<%--<td>${otherWorkAttendance.medical_days }</td>--%>

						<td>${otherWorkAttendance.neglect_work_days }</td>
						<td>${otherWorkAttendance.late_days }</td>
						<td>${otherWorkAttendance.weekend_overtime_days }</td>
						<td>${otherWorkAttendance.every_overtime }</td>
						<td>${otherWorkAttendance.remark }</td>
				
					</tr>
					</c:forEach>
				</tbody>
			</table>			
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>
