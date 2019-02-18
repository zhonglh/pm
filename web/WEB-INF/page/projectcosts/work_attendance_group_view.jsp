<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/WorkAttendanceGroupAction.do?method=verifyWorkAttendance" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			



			<p>
				<label>考勤月份：</label>
				<label>${workAttendance1.attendance_month } <input type="hidden" name="attendance_month" value="${workAttendance1.attendance_month }" /></label>
			</p>	
			
			<p>
				<label>项目名称：</label>
				<input type="hidden"   name="project_id" value="${workAttendance1.project_id }"/>
				<input name="project_name" type="hidden" value="${workAttendance1.project_name }"  />
				<label>${workAttendance1.project_name }</label>
			</p>
			
						
			
			<div class="divider"></div>
			
			<table class="list nowrap" width=1800 >
				<thead>
					<tr>
					
						<th width="40" nowrap>序号</th>						
						<th width="140" >姓名</th>
						<th width="80" >工作日天数</th>						
						<th width="100" >实际工作天数</th>
						<th width="80" >法定假日</th>
						<th width="80" >倒休天数</th>
						<th width="80" >年假天数</th>
						<th width="80" >出差天数</th>
						<th width="80" >事假天数</th>
						<th width="80" >病假天数</th>

						<th width="80" >待岗天数</th>
						<th width="80" >产假天数</th>
						<%--<th width="90" >医疗假天数</th>--%>

						<th width="80" >旷工天数</th>
						<th width="80" >迟到天数</th>
						<th width="120" >周末加班天数</th>
						<th width="130" >日常加班(小时)</th>
						<th width="200" >备注</th>
						<th width="60" >核单人</th>
					</tr>
				</thead>
				<tbody>					
					<c:forEach var="workAttendance"  varStatus="status1" items="${list}">
					<tr>
										
						<td>							
							<c:if test="${operation_check != null && operation_check != '' }">							
								<c:if test="${workAttendance.verify_userid == null ||  workAttendance.verify_userid == ''}">
									<input type="hidden" name="attendance_id" value="${workAttendance.attendance_id }" />
								</c:if>					
							</c:if>	
							<input type="hidden" name="id" value="${status1.index + 1}" /><input name="attendance_id${status1.index + 1 }" value="${workAttendance.attendance_id}" type="hidden" />${status1.index + 1 }
						</td>
						
						<td title="${workAttendance.staff_no }"><input type="hidden"   name="staff_id${status1.index + 1 }" value="${workAttendance.staff_id }"/>${workAttendance.staff_name }</td>
						<td>${workAttendance.should_work_days }</td>
						<td>${workAttendance.work_days }</td>
						<td>${workAttendance.legal_holidays }</td>
						<td>${workAttendance.swopped_holidays }</td>
						<td>${workAttendance.annual_leave_days }</td>
						<td>${workAttendance.business_trip_days }</td>
						<td>${workAttendance.personal_leave_days }</td>
						<td>${workAttendance.sick_leave_days }</td>

						<td>${workAttendance.waiting_post_days }</td>
						<td>${workAttendance.maternity_leave_days }</td>
						<%--<td>${workAttendance.medical_days }</td>--%>

						<td>${workAttendance.neglect_work_days }</td>
						<td>${workAttendance.late_days }</td>
						<td>${workAttendance.weekend_overtime_days }</td>
						<td>${workAttendance.every_overtime }</td>
						<td>${workAttendance.remark }</td>
						<td>${workAttendance.verify_username }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
						
			
			<div class="divider"></div>
			<p>
				<label>制单人： 	</label> <label>${workAttendance1.build_username } </label>
			</p>
			<p>
				<label>制单日期： 	</label> <label><fmt:formatDate value="${workAttendance1.build_datetime }" pattern="yyyy-MM-dd" /> </label>
			</p>
			


		</div>
		<div class="formBar">
			<ul>
				<c:if test="${operation_check != null && operation_check != '' }">
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">核单</button></div></div></li>
				</c:if>			
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
