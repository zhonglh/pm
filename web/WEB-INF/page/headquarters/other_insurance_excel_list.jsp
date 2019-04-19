<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1200" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="60">月份</th>
						<th width="80">工号</th>
						<th width="80">姓名</th>	
						<th width="70">养老单位</th>	
						<th width="70">养老个人</th>	
						<th width="70">失业单位</th>	
						<th width="70">失业个人</th>	
						<th width="70">工伤单位</th>	
						<th width="70">生育单位</th>	
						<th width="70">医疗单位</th>	
						<th width="70">医疗个人</th>	
						<th width="70">公积金单位</th>	
						<th width="70">公积金个人</th>	
						<th width="100">社保缴纳单位</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="insurance"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${insurance.errorInfo != null && insurance.errorInfo != '' }">
								<font color="red">${insurance.errorInfo }</font>
							</c:if>
							<c:if test="${insurance.errorInfo == null || insurance.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${insurance.salary_month }</td>
						<td>${insurance.staff_no }</td>
						<td>${insurance.staff_name }</td>
						<td>${insurance.endowment_insurance_bycompany }</td>	
						<td>${insurance.endowment_insurance_bypersonal }</td>	
						<td>${insurance.losejob_insurance_bycompany }</td>	
						<td>${insurance.losejob_insurance_bypersonal }</td>	
						<td>${insurance.jobharm_insurance_bycompany }</td>	
						<td>${insurance.maternity_insurance_bycompany }</td>	
						<td>${insurance.medical_insurance_bycompany }</td>	
						<td>${insurance.medical_insurance_bypersonal }</td>	
						<td>${insurance.reservefund_bypcompany }</td>	
						<td>${insurance.reservefund_bypersonal }</td>	
						<td>${insurance.securty_unit }</td>	
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
