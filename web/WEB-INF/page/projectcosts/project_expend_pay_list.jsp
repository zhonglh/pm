<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/ProjectExpendPayAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="project_expend_id" value="${projectExpend.project_expend_id}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ProjectExpendPayAction.do?method=list" method="post">
		<div class="searchBar">

			<ul class="searchContent">

				<li>
					<label>项目名称：</label>
					${projectExpend.project_name}

				</li>
				<li>
					<label>分包商名称：</label>
					${projectExpend.sub_contractor_name}


				</li>
			</ul>


			<ul class="searchContent">

				<li>
					<label>应付金额：</label>
					<b><fmt:formatNumber value="${projectExpend.amount }" type="currency" pattern="###,###,##0.00"/></b>

				</li>
				<li>
					<label>累计已付金额：</label>
					<b><fmt:formatNumber value="${projectExpend.pay_amount }" type="currency" pattern="###,###,##0.00"/></b>

				</li>
			</ul>

		</div>

	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/ProjectExpendPayAction.do?method=toEdit&project_expend_id=${projectExpend.project_expend_id}" mask="true" width="500" height="400" rel="add_projectexpendpay"  target="dialog"><span>添加项目实际支付信息</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/ProjectExpendPayAction.do?method=deleteProjectExpendPay" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/ProjectExpendPayAction.do?method=toEdit&id={sid}" mask="true" width="500"  height="400" rel="update_projectexpendpay" target="dialog" warn="请选择一条数据"><span>修改项目实际支付信息</span></a></li>
			</c:if>


			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/ProjectExpendPayAction.do?method=toView&id={sid}" mask="true" width="500"  height="400" rel="view_projectexpendpay" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>

			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/ProjectExpendPayAction.do?method=batchVerifyProjectExpendPay" class="delete"><span>批量核单</span></a></li> 
			</c:if>		
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/ProjectExpendPayAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	

		</ul>
	</div>
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="100">收到的发票号</th>	
				<th width="100">实际支付日期</th>	
				<th width="100">实付金额</th>	
				<th width="200">备注</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="projectExpendPay"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${projectExpendPay.id }">
				<td>
					<c:if test="${projectExpendPay.verify_userid==null || projectExpendPay.verify_userid=='' }">
						<input name="ids" value="${projectExpendPay.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${projectExpendPay.invoiceno }</td>
				<td><fmt:formatDate value="${projectExpendPay.pay_date }" pattern="yyyy-MM-dd"/></td>
				<td align="right"><b><fmt:formatNumber value="${projectExpendPay.pay_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${projectExpendPay.description }</td>
				<td>${projectExpendPay.build_username }</td>
				<td>${projectExpendPay.verify_username }</td>
				<td>
					<c:if test="${projectExpendPay.need_approve!=null && projectExpendPay.need_approve=='1' }">
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
