<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/InsuranceAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/InsuranceAction.do?method=list" method="post">
	<div class="searchBar">
		
		<ul class="searchContent">
			
				<li>
					<label>员工工号：</label>
					<input type="text" name="staff_no" value="${param.staff_no }"/>
				</li>
				<li>
					<label>员工名称：</label>
					<input type="text" name="staff_name" value="${param.staff_name }"/>
				</li>
	
				<li>
				<label>时间段：</label>
				<input name="salary_month1" class="date" format="yyyyMM" type="text" size="7" value="${param.salary_month1 }" readonly="readonly" />
				<input name="salary_month2" class="date" format="yyyyMM" type="text" size="7" value="${param.salary_month2 }" readonly="readonly" />
				</li>
			
			
		</ul>
			
		<ul class="searchContent">
			<li>
				<label>缴纳单位：</label>
				<input type="text" name="securty_unit" value="${param.securty_unit }"/>
			</li>
				

			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" style="width:153px">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
				</select>
			</li>	
			
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/InsuranceAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_insurance"  target="dialog"><span>添加员工保险</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/InsuranceAction.do?method=deleteInsurance" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/InsuranceAction.do?method=toEdit&id={sid}" mask="true" width="900"  height="400" rel="update_insurance" target="dialog" warn="请选择一条数据"><span>修改员工保险</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/InsuranceAction.do?method=toView&id={sid}" mask="true" width="900"  height="480" rel="view_insurance" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/InsuranceAction.do?method=batchVerifyInsurance" class="delete"><span>批量核单</span></a></li> 
			</c:if>		
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/InsuranceAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/InsuranceAction.do?method=toExcel" mask="true" width="550" height="480" rel="add_insurance" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/InsuranceAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	<table class="table" width="1400" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="80">工号</th>
				<th width="80">姓名</th>		
				<th width="70">养老个人</th>
				<th width="70">养老单位</th>	
				<th width="70">医疗个人</th>			
				<th width="70">医疗单位</th>
				<th width="70">失业个人</th>	
				<th width="70">失业单位</th>
				<th width="70">生育单位</th>	
				<th width="70">工伤单位</th>	
				<th width="70">公积金个人</th>	
				<th width="70">公积金单位</th>	
				<th width="100">社保缴纳单位</th>	
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="insurance"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${insurance.id }">
				<td>
					<c:if test="${insurance.verify_userid==null || insurance.verify_userid=='' }">
						<input name="ids" value="${insurance.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${insurance.salary_month }</td>
				<td>${insurance.staff_no }</td>
				<td>${insurance.staff_name }</td>
				<td align="right"><b><fmt:formatNumber value="${insurance.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${insurance.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${insurance.securty_unit }</td>
				<td>${insurance.build_username }</td>
				<td>${insurance.verify_username }</td>
				<td>
					<c:if test="${insurance.need_approve!=null && insurance.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount_any.jsp"></c:import>
</div>	
