<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ReimburseCostsAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="project_name" value="${param.project_name}" />
	<input type="hidden" name="staff_name" value="${param.staff_name}" />
	<input type="hidden" name="verify_flag" value="${param.verify_flag}" />
	
	<input type="hidden" name="dept.dept_id" value="${reimburseCosts.dept_id}" />
	<input type="hidden" name="dept.dept_name" value="${reimburseCosts.dept_name}" />
	<input type="hidden" name="use_month1" value="${param.use_month1}" />
	<input type="hidden" name="use_month2" value="${param.use_month2}" />
	<input type="hidden" name="pay_date1" value="${param.pay_date1}" />
	<input type="hidden" name="pay_date2" value="${param.pay_date2}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ReimburseCostsAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>部门：</label>				
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${reimburseCosts.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${reimburseCosts.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>	
			</li>
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>

			<li>
				<label>报销人：</label>
				<input type="text" class="text" name="staff_name" value="${param.staff_name}"/>
			</li>
			
		</ul>
		<ul class="searchContent">
			<li>
				<label>报销类别：</label>
				<input type="hidden" name="rai.id" value="${reimburseCosts.pay_item_id }"/>
				<input name="rai.dic_data_name" class="text" type="text" value="${reimburseCosts.pay_item_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="rai"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=REIMBURSE_ITEM" />	


			</li>

			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" style="width:153px">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
					<option value="3" <c:if test="${'3' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.3"/></option>
				</select>
			</li>	
			

			<li>
				<label>报销月份：</label>							
				<input type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  format="yyyyMM" size="7" name="use_month1" value="${param.use_month1}"/>
				<input type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  format="yyyyMM" size="7" name="use_month2" value="${param.use_month2}"/>
			</li>	
			
			

		</ul>
		
		<ul class="searchContent">
		
			<li>
				<label>支付日期：</label>							
				<input type="text"  class="date" autocomplete="off"    maxlength="10" minlength="10" format="yyyy-MM-dd" size="7" name="pay_date1" value="${param.pay_date1}"/>
				<input type="text"  class="date" autocomplete="off"    maxlength="10" minlength="10" format="yyyy-MM-dd" size="7" name="pay_date2" value="${param.pay_date2}"/>
			</li>	
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		</ul>
					
	
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/ReimburseCostsAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_riemburse"  target="dialog"><span>添加报销单</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/ReimburseCostsAction.do?method=deleteReimburseCosts" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/ReimburseCostsAction.do?method=toEdit&reimburse_id={sid_reimburse_costs}" mask="true" width="900"  height="400" rel="update_riemburse" target="dialog" warn="请选择一条数据"><span>修改报销单</span></a></li>
			</c:if>
			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/ReimburseCostsAction.do?method=toView&reimburse_id={sid_reimburse_costs}" mask="true" width="900"  height="480" rel="view_riemburse" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/ReimburseCostsAction.do?method=batchVerifyReimburseCosts" class="delete"><span>批量核单</span></a></li> 
			</c:if>			
			
			<li class="line">line</li>
			
				
			<c:if test="${operation_read != null && operation_read != '' }">
				<c:if test="${ totalRows <= 15000}">
					<li><a  class="icon" href="${webroot}/ReimburseCostsAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				</c:if>
			</c:if>		
			
			
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/ReimburseCostsAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_riemburse" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			 
		
			
			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/ReimburseCostsAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
				
		</ul>
	</div>
	<table class="table" width="100%" layoutH="160">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="200">项目名称</th>
				<th width="80">项目编号</th>
				<th width="150">备注</th>
				<th width="40">报销人</th>
				<th width="80">所属部门</th>
				<th width="80">报销类别</th>
				<th width="80">报销金额</th>
				<th width="80">报销月份</th>
				<th width="120">实际支付日期</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="reimburseCosts"  varStatus="status1" items="${list}">
			<tr target="sid_reimburse_costs" rel="${reimburseCosts.reimburse_id }">
				<td>
					<c:if test="${reimburseCosts.verify_userid==null || reimburseCosts.verify_userid=='' }">
						<input name="ids" value="${reimburseCosts.reimburse_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${reimburseCosts.project_name }</td>
				<td>${reimburseCosts.project_no }</td>
				<td title="${reimburseCosts.description }">${reimburseCosts.description }</td>
				<td>${reimburseCosts.staff_name }</td>
				<td>${reimburseCosts.dept_name }</td>
				<td>${reimburseCosts.pay_item_name }</td>
				<td align="right"><b><fmt:formatNumber value="${reimburseCosts.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${reimburseCosts.use_month }</td>
				<td><fmt:formatDate value="${reimburseCosts.pay_date }" pattern="yyyy-MM-dd"/></td>
				<td>${reimburseCosts.build_username }</td>
				<td>${reimburseCosts.verify_username }</td>
				<td>
					<c:if test="${reimburseCosts.need_approve!=null && reimburseCosts.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>

</div>	