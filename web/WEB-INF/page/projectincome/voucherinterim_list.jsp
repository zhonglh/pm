<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/${action }.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/${action }.do?method=list" method="post">
	<div class="searchBar">
		
		<ul class="searchContent">		
		
					
			<li>
				<label>导出状态：</label>
				<select name="exported" style="width:153px">
				<option value="" <c:if test="${'' == param.exported }">selected</c:if>>全部</option>
				<option value="1" <c:if test="${'1' == param.exported }">selected</c:if>>已导出</option>
				<option value="0" <c:if test="${'0' == param.exported }">selected</c:if>>未导出</option>
				</select>
			</li>
					
					
			<li>
				<label>业务类型：</label>
				<select name="data_type" style="width:153px">
				<option value="" <c:if test="${'' == param.data_type }">selected</c:if>>全部</option>
				<option value="1" <c:if test="${'1' == param.data_type }">selected</c:if>>月度结算单</option>
				<option value="2" <c:if test="${'2' == param.data_type }">selected</c:if>>发票</option>
				<option value="3" <c:if test="${'3' == param.data_type }">selected</c:if>>到款</option>
				</select>
			</li>
					
			<li>
				<label>凭证月份：</label>
				<input  type="text" class="digits date month" size="7"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" name="months1" value="${param.months1}"/>
				<input  type="text" class="digits date month" size="7"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" name="months2" value="${param.months2}"/>
			</li>
			

		</ul>
		
		
		<ul class="searchContent">		
		
					
			<li>
				<label>客户名称：</label>
				<input type="text" name="client_name" value="${param.client_name}" size="17"/>
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
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/${action }.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
				<li><a class="edit" href="${webroot}/${action }.do?method=toView&id={sid}" mask="true" width="950"  height="550" rel="view" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
		
			</c:if>	
			
		</ul>
	</div>
	<table class="table" width="3600" layoutH="136">
		<thead>
			<tr>
				<th width="100">导出人</th>
				<th width="180">导出时间</th>	
				<th width="50">会计年</th>	
				<th width="70">会计期间</th>	
				<th width="100">制单日期</th>	
				<th width="80">凭证类别</th>	
				<th width="100">制单人</th>	
				<th width="100">所附单据数</th>	
				<th width="200">备注1</th>	
				<th width="300">备注2</th>	
				<th width="100">科目编码</th>	
				<th width="450">摘要</th>	
				<th width="100">结算方式编码</th>	
				<th width="100">票据号</th>	
				<th width="100">票据日期</th>	
				<th width="70">币种名称</th>	
				<th width="100">汇率</th>	
				<th width="100">单价</th>	
				<th width="100">借方数量</th>	
				<th width="100">贷方数量</th>	
				<th width="100">原币借方</th>	
				<th width="100">原币贷方</th>	
				<th width="100">借方金额</th>	
				<th width="100">贷方金额</th>	
				<th width="100">部门编码</th>	
				<th width="100">职员编码</th>	
				<th width="100">客户编码</th>	
				<th width="100">供应商编码</th>	
				<th width="100">项目大类编码</th>	
				<th width="100">项目编码</th>	
				<th width="100">业务员</th>	
				<th width="100">自定义项1</th>	
				<th width="100">自定义项2</th>	
				<th width="100">自定义项3</th>	
				<th width="100">自定义项4</th>	
				<th width="100">自定义项5</th>	
				<th width="100">自定义项6</th>	
				<th width="100">自定义项7</th>	
				<th width="100">自定义项8</th>	
				<th width="100">自定义项9</th>	
				<th width="100">自定义项10</th>	
				<th width="100">自定义项11</th>	
				<th width="100">自定义项12</th>	
				<th width="100">自定义项13</th>	
				<th width="100">自定义项14</th>	
				<th width="100">自定义项15</th>	
				<th width="100">自定义项16</th>	
				<th width="100">现金流量项目</th>	
				<th width="180">现金流量借方金额</th>	
				<th width="180">现金流量贷方金额</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="voucherInterim"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${voucherInterim.id }">
				<td>${voucherInterim.exported_username }</td>
				<td><fmt:formatDate value="${voucherInterim.exported_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${voucherInterim.years }</td>
				<td>${voucherInterim.months }</td>
				<td><fmt:formatDate value="${voucherInterim.build_time }" pattern="yyyy-MM-dd"/></td>
				<td>${voucherInterim.voucher_type }</td>
				<td>${voucherInterim.build_username }</td>
				<td>${voucherInterim.attached_number }</td>
				<td>${voucherInterim.description1 }</td>
				<td>${voucherInterim.description2 }</td>
				<td>${voucherInterim.course_code }</td>
				<td>${voucherInterim.summary }</td>
				<td>${voucherInterim.settlement_code }</td>
				<td>${voucherInterim.bill_number }</td>
				<td><fmt:formatDate value="${voucherInterim.bill_date }" pattern="yyyy-MM-dd"/></td>
				<td>${voucherInterim.currency_name }</td>
				<td>${voucherInterim.exchange_rate }</td>
				<td>${voucherInterim.price }</td>
				<td>${voucherInterim.debit_number }</td>
				<td>${voucherInterim.loan_number }</td>
				<td>${voucherInterim.original_currency_debit }</td>
				<td>${voucherInterim.original_currency_loan }</td>
				<td align="right"><c:if test="${ voucherInterim.debit_amount!=0}"><b>${voucherInterim.debit_amount }</b></c:if></td>
				<td align="right"><c:if test="${ voucherInterim.loan_amount!=0}"><b>${voucherInterim.loan_amount }</b></c:if></td>
				<td>${voucherInterim.depart_code }</td>
				<td>${voucherInterim.staff_code }</td>
				<td>${voucherInterim.client_code }</td>
				<td>${voucherInterim.vendor_code }</td>
				<td>${voucherInterim.project_class_code }</td>
				<td>${voucherInterim.project_code }</td>
				<td>${voucherInterim.salesman }</td>
				<td>${voucherInterim.custom_item1 }</td>
				<td>${voucherInterim.custom_item2 }</td>
				<td>${voucherInterim.custom_item3 }</td>
				<td>${voucherInterim.custom_item4 }</td>
				<td>${voucherInterim.custom_item5 }</td>
				<td>${voucherInterim.custom_item6 }</td>
				<td>${voucherInterim.custom_item7 }</td>
				<td>${voucherInterim.custom_item8 }</td>
				<td>${voucherInterim.custom_item9 }</td>
				<td>${voucherInterim.custom_item10 }</td>
				<td>${voucherInterim.custom_item11 }</td>
				<td>${voucherInterim.custom_item12 }</td>
				<td>${voucherInterim.custom_item13 }</td>
				<td>${voucherInterim.custom_item14 }</td>
				<td>${voucherInterim.custom_item15 }</td>
				<td>${voucherInterim.custom_item16 }</td>
				<td>${voucherInterim.cash_flow_item }</td>
				
				<td align="right"><c:if test="${ voucherInterim.cash_flow_debit_amount!=0}"><b>${voucherInterim.cash_flow_debit_amount }</b></c:if></td>
				
				<td align="right"><c:if test="${ voucherInterim.cash_flow_loan_amount!=0}"><b>${voucherInterim.cash_flow_loan_amount }</b></c:if></td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>	
