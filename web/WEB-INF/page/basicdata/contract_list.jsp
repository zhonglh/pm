<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ContractAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="contract_no" value="${param.contract_no}" />
	<input type="hidden" name="project_name" value="${param.project_name}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ContractAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			
			<li>
				<label>立项名称：</label>
				<input name="project_name" class="text" type="text" value="${param.project_name }" />
			</li>
			
			<li>
				<label>合同编号：</label>
				<input name="contract_no" class="text" type="text"  value="${param.contract_no }" />
			</li>


			<li>
				<label>提交日期：</label>
				<input type="text"  class="date" autocomplete="off"  name="date1" size="7" value="${param.date1 }"/>
				<input type="text"  class="date" autocomplete="off"  name="date2" size="7" value="${param.date2 }"/>
			</li>
			
			

		</ul>

		<ul class="searchContent">

			<li>
				<label>客户名称：</label>
				<input name="project_client_name" class="text" type="text" value="${param.project_client_name }" />
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
				<li><a class="add" href="${webroot}/ContractAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_contract" title="添加收款合同" target="dialog"><span>添加合同</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/ContractAction.do?method=deleteContract" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/ContractAction.do?method=toEdit&id={sid}" mask="true" width="900"  height="400" rel="update_contract" target="dialog" title="修改收款合同" warn="请选择一条数据"><span>修改合同</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/ContractAction.do?method=toView&id={sid}" mask="true" width="900"  height="480" rel="view_contract" target="dialog" title="查看收款合同明细" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/ContractAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/ContractAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_contract" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/ContractAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	
	<table class="table" width="1700" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
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
				<th width="200">备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="contract"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${contract.id }"  <c:if test="${contract.remind == 1}">style="background-color:red;"</c:if> >
				<td><input name="ids" value="${contract.id }" type="checkbox" /></td>
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
				<td>${contract.description }</td>
				</td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>
	
</div>	
