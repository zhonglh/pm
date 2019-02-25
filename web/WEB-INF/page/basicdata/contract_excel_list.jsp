<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<table class="list nowrap" width="1600" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="100">存放地</th>	
						<th width="100">立项名称</th>	
						<th width="100">合同编号</th>	
						<th width="100">执行合同</th>	
						<th width="100">人月费用</th>	
						<th width="130">合同签订日期</th>	
						<th width="200">合同有效期</th>	
						<th width="120">销售负责人</th>							
						<th width="100">客户联系人</th>	
						<th width="120">法定代表人</th>	
						<th width="100">提交日期</th>	
						<th width="100">合同份数</th>
						<th width="100">付款方式</th>
						<th width="160">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="contract"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${contract.errorInfo != null && contract.errorInfo != '' }">
								<font color="red">${contract.errorInfo }</font>
							</c:if>
							<c:if test="${contract.errorInfo == null || contract.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${contract.storage_addr_name }</td>	
						<td>${contract.project_name }</td>	
						<td>${contract.contract_no }</td>	
						<td>${contract.exec_contract }</td>	
						<td>${contract.monthly_expenses }</td>	
						<td>
							<c:if test="${contract.signing_date != null}">
							<fmt:formatDate value="${contract.signing_date }" pattern="yyyy-MM-dd"/>
							</c:if>
						</td>	
						<td>
								${contract.validity_date1 }---${contract.validity_date2 }
						</td>
						<td>${contract.sales_username }</td>
						<td>${contract.client_linkman }</td>	
						<td>${contract.corporation }</td>
						<td>
							<c:if test="${contract.submit_date != null}">
							<fmt:formatDate value="${contract.submit_date }" pattern="yyyy-MM-dd"/>
							</c:if>
						</td>
						<td>${contract.contract_number }</td>
						<td>${contract.paymen_mode }</td>
						<td>${contract.description }</td>	
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
