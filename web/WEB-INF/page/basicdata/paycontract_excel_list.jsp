<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1200" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="100">合同编号</th>	
						<th width="100">公司名称</th>	
						<th width="100">执行合同</th>	
						<th width="100">金额</th>	
						<th width="100">合同签订日期</th>	
						<th width="100">合同有效日期1</th>	
						<th width="100">合同有效日期2</th>	
						<th width="100">提交日期</th>	
						<th width="100">客户联系人</th>	
						<th width="100">邮箱/电话</th>	
						<th width="100">合同份数</th>	
						<th width="100">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="payContract"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${payContract.errorInfo != null && payContract.errorInfo != '' }">
								<font color="red">${payContract.errorInfo }</font>
							</c:if>
							<c:if test="${payContract.errorInfo == null || payContract.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${payContract.contract_no }</td>	
						<td>${payContract.company_name }</td>	
						<td>${payContract.exec_contract }</td>	
						<td>${payContract.amount }</td>	
						<td>${payContract.signing_date }</td>	
						<td>${payContract.validity_date1 }</td>	
						<td>${payContract.validity_date2 }</td>	
						<td>${payContract.submit_date }</td>	
						<td>${payContract.client_linkman }</td>	
						<td>${payContract.email_phone }</td>	
						<td>${payContract.contract_number }</td>	
						<td>${payContract.description }</td>	
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
