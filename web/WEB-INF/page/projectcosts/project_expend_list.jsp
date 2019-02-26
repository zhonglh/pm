<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ProjectExpendAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ProjectExpendAction.do?method=list" method="post">
	<div class="searchBar">
	
		<ul class="searchContent">	

			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${projectExpend.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${projectExpend.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				

			</li>
				
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>
			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" style="width:133px">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
				<option value="3" <c:if test="${'3' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.3"/></option>
				</select>
			</li>
			

		</ul>
		
		<ul class="searchContent">	
			<li>
				<label>应付款月份：</label>							
				<input type="text" class="digits date month" maxlength="6" minlength="6" format="yyyyMM" size="7" name="use_month1" value="${param.use_month1}"/>
				<input type="text" class="digits date month" maxlength="6" minlength="6" format="yyyyMM" size="7" name="use_month2" value="${param.use_month2}"/>
			</li>	
			
			<li>
				<label>支付日期：</label>							
				<input type="text" class="date" maxlength="10" minlength="10" format="yyyy-MM-dd" size="7" name="pay_date1" value="${param.pay_date1}"/>
				<input type="text" class="date" maxlength="10" minlength="10" format="yyyy-MM-dd" size="7" name="pay_date2" value="${param.pay_date2}"/>
			</li>
			
			
			<li>
				<label>分包商：</label>
				<input type="text" name="sub_contractor_name" value="${param.sub_contractor_name}"/>
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
				<li><a class="add" href="${webroot}/ProjectExpendAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_project_expend"  target="dialog"><span>添加</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/ProjectExpendAction.do?method=deleteProjectExpend" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/ProjectExpendAction.do?method=toEdit&project_expend_id={sid}" mask="true" width="900"  height="400" rel="update_project_expend" target="dialog" warn="请选择一条数据"><span>修改</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/ProjectExpendPayAction.do?method=list&project_expend_id={sid}" mask="true" width="950"  height="500" rel="update_project_expend" target="dialog" warn="请选择一条数据"><span>付款信息</span></a></li>
			</c:if>			
			
			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/ProjectExpendAction.do?method=toView&project_expend_id={sid}" mask="true" width="900"  height="480" rel="view_project_expend" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>		
			
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/ProjectExpendAction.do?method=batchVerifyProjectExpend" class="delete"><span>批量核单</span></a></li> 
			</c:if>			

			<li class="line">line</li>
			
				
			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/ProjectExpendAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>		
			</c:if>
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/ProjectExpendAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_project_expend" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			 
		
			
			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/ProjectExpendAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>				
				
		</ul>
	</div>
	<table class="table" width="1600" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">项目名称</th>
				<th width="100">项目编号</th>				
				<th width="80">应付金额</th>
				<th width="150">分包商名称</th>
				<th width="100">分包合同编号</th>
				<th width="100">收到的发票号</th>
				<th width="80">应付月份</th>
				<th width="120">实际支付日期</th>
				<th width="80">实付金额</th>
				<th width="200">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="projectExpend"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${projectExpend.project_expend_id }">
				<td>
					<c:if test="${projectExpend.verify_userid==null || projectExpend.verify_userid=='' }">
						<input name="ids" value="${projectExpend.project_expend_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${projectExpend.project_name }</td>
				<td>${projectExpend.project_no }</td>
				<td><fmt:formatNumber value="${projectExpend.amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td>${projectExpend.sub_contractor_name }</td>
				<td>${projectExpend.sub_contractor_no }</td>
				<td>${projectExpend.invoiceno }</td>
				<td>${projectExpend.use_month }</td>
				<td><fmt:formatDate value="${projectExpend.pay_date }" pattern="yyyy-MM-dd"/></td>				
				<td align="right"><b>
				<c:if  test="${projectExpend.pay_amount != 0 }">
				<fmt:formatNumber value="${projectExpend.pay_amount }" type="currency" pattern="###,###,##0.00"/>
				</c:if>
				</b></td>
				<td title="${projectExpend.description }">${projectExpend.description }</td>
				<td>${projectExpend.build_username }</td>
				<td>${projectExpend.verify_username }</td>
				<td>
					<c:if test="${projectExpend.need_approve!=null && projectExpend.need_approve=='1' }">
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