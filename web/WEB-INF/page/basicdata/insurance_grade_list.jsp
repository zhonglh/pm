<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/InsuranceGradeAction.do?method=list">

</form>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/InsuranceGradeAction.do?method=toEdit" mask="true" width="850" height="400" rel="insurance_grade_add" target="dialog"><span>添加保险档次</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">						
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/InsuranceGradeAction.do?method=deleteInsuranceGrade" class="delete"><span>删除</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/InsuranceGradeAction.do?method=toEdit&insurance_grade_id={insurance_grade_id}" rel="insurance_grade_edit" width="850" height="400" mask="true" target="dialog" warn="请选择一条数据"><span>修改保险档次</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/InsuranceGradeAction.do?method=toView&insurance_grade_id={insurance_grade_id}" mask="true" width="850" height="400" rel="insurance_grade_view"  target="dialog" warn="请选择一条数据"><span>显示明细</span></a></li>
			</c:if>			
			<li class="line">line</li>		
			
			<c:if test="${operation_synchrodata != null && operation_synchrodata != '' }">						
				<li><a title="确实要同步到人员成本记录中吗?" target="selectedTodo" rel="ids" href="${webroot }/StaffCostAction.do?method=synchroInsuranceGrade" class="edit"><span>同步到人员成本</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="1500" layoutH="48">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="100">社保种类</th>
				<th width="100">社保基数</th>
				<th width="130">个人缴纳养老保险</th>
				<th width="130">个人缴纳医疗保险</th>
				<th width="130">个人缴纳失业保险</th>
				<th width="110">个人缴纳公积金</th>
				<th width="80">个人所的税</th>
				<th width="130">公司缴纳养老保险</th>
				<th width="130">公司缴纳医疗保险</th>
				<th width="130">公司缴纳失业保险</th>					
				<th width="130">公司缴纳生育保险</th>
				<th width="130">公司缴纳工伤保险</th>
				<th width="130">公司缴纳公积金</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="insuranceGrade"  varStatus="status1" items="${list}">
			<tr target="insurance_grade_id" rel="${insuranceGrade.insurance_grade_id }">
				<td><input name="ids" value="${insuranceGrade.insurance_grade_id }" type="checkbox" /></td>
				<td align="center">${insuranceGrade.insurance_radix }</td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.base_cardinal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.incometax_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${insuranceGrade.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	

</div>
