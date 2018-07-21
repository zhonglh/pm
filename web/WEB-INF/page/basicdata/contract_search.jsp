<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ContractAction.do?method=lookup">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="contract_no" value="${param.contract_no}" />
	<input type="hidden" name="project_name" value="${param.project_name}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/ContractAction.do?method=lookup" method="post">
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
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>
		</ul>	
	</div>
	</form>
</div>

<div class="pageContent">	
	
	<table class="table" width="1600" layoutH="85">
		<thead>
			<tr>
				<th width="25">选择</th>
				<th width="40">序号</th>	
				<th width="130">立项名称</th>
				<th width="100">立项编号</th>	
				<th width="150">存放地</th>	
				<th width="100">合同编号</th>	
				<th width="300">执行合同</th>	
				<th width="150">人月费用</th>	
				<th width="100">合同签订日期</th>	
				<th width="180">合同有效期</th>	
				<th width="100">销售负责人</th>	
				<th width="100">客户联系人</th>	
				<th width="100">法定代表人</th>	
				<th width="100">提交日期</th>	
				<th width="80">合同份数</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="contract"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${contract.id }">
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'${contract.id }',contract_no:'${contract.contract_no }'})" title="">选择</a>
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${contract.project_name }</td>
				<td>${contract.project_no }</td>
				<td>${contract.storage_addr_name }</td>
				<td>${contract.contract_no }</td>
				<td>${contract.exec_contract }</td>
				<td><b>${contract.monthly_expenses_str }</b></td>
				<td><fmt:formatDate value="${contract.signing_date }" pattern="yyyy-MM-dd"/></td>
				<td>${contract.effectivedate }</td>
				<td>${contract.sales_username }</td>
				<td>${contract.client_linkman }</td>
				<td>${contract.corporation }</td>
				<td><fmt:formatDate value="${contract.submit_date }" pattern="yyyy-MM-dd"/></td>
				<td align="center"><b><fmt:formatNumber value="${contract.contract_number }" type="number" pattern="###,###,##0"/></b></td>
				</td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>
	
</div>	
