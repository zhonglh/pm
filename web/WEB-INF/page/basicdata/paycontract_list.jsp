<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/PayContractAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/PayContractAction.do?method=list" method="post">
		<div class="searchBar">
			<ul class="searchContent">

				<li>
					<label>项目名称：</label>
					<input name="project_name" class="text" type="text" value="${param.project_name }" />
				</li>

				<li>
					<label>合同编号：</label>
					<input name="contract_no" class="text" type="text"  value="${param.contract_no }" />
				</li>


				<li>
					<label>提交日期：</label>
					<input type="text" class="date" name="date1" size="7" value="${param.date1 }"/>
					<input type="text" class="date" name="date2" size="7" value="${param.date2 }"/>
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
				<li><a class="add" href="${webroot}/PayContractAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_paycontract"  target="dialog"><span>添加付款合同</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/PayContractAction.do?method=deletePayContract" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/PayContractAction.do?method=toEdit&id={sid}" mask="true" width="900"  height="400" rel="update_paycontract" target="dialog" warn="请选择一条数据"><span>修改付款合同</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/PayContractAction.do?method=toView&id={sid}" mask="true" width="900"  height="480" rel="view_paycontract" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	


			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/PayContractAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/PayContractAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_paycontract" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/PayContractAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	<table class="table" width="1600" layoutH="113">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">项目名称</th>
				<th width="100">项目编号</th>	
				<th width="100">合同编号</th>	
				<th width="150">公司名称</th>
				<th width="150">执行合同</th>
				<th width="80">金额</th>
				<th width="100">合同签订日期</th>	
				<th width="200">合同有效日期</th>
				<th width="80">负责人</th>
				<th width="100">提交日期</th>	
				<th width="80">客户联系人</th>
				<th width="100">邮箱/电话</th>	
				<th width="80">合同份数</th>
				<th width="80">付款方式</th>
				<th width="250">备注</th>
				<th width="80">制表人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="payContract"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${payContract.id }">
				<td>
						<input name="ids" value="${payContract.id }" type="checkbox" />
				</td>
				<td>${payContract.project_name }</td>
				<td>${payContract.project_no }</td>
				<td>${payContract.contract_no }</td>
				<td>${payContract.company_name }</td>
				<td>${payContract.exec_contract }</td>
				<td align="right"><b><fmt:formatNumber value="${payContract.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><fmt:formatDate value="${payContract.signing_date }" pattern="yyyy-MM-dd"/></td>
				<td>
					<fmt:formatDate value="${payContract.validity_date1 }" pattern="yyyy-MM-dd"/>
					---
					<fmt:formatDate value="${payContract.validity_date2 }" pattern="yyyy-MM-dd"/>
				</td>
				<td>${payContract.manager_username }</td>
				<td><fmt:formatDate value="${payContract.submit_date }" pattern="yyyy-MM-dd"/></td>
				<td>${payContract.client_linkman }</td>
				<td>${payContract.email_phone }</td>
				<td align="right"><b><fmt:formatNumber value="${payContract.contract_number }" type="number" pattern="###,###,##0"/></b></td>
				<td>${payContract.paymen_mode }</td>
				<td>${payContract.description }</td>
				<td>${payContract.build_username }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>
</div>	
