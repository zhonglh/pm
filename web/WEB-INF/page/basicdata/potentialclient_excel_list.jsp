<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="2100" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="100">客户编号</th>	
						<th width="100">客户名称</th>	
						<th width="100">行业</th>	
						<th width="160">客户地址</th>	
						<th width="100">联系人</th>	
						<th width="100">手机</th>	
						<th width="100">座机</th>	
						<th width="100">电子邮件</th>	
						<th width="100">QQ号码</th>	
						<th width="120">技术方向</th>	
						<th width="100">服务类型</th>	
						<th width="100">项目周期</th>	
						<th width="300">客户需求</th>	
						<th width="100">客户价值</th>	
						<th width="100">信息来源</th>	
						<th width="100">客户状态</th>	
						<th width="100">咨询时间</th>	
						<th width="100">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="potentialClient"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${potentialClient.errorInfo != null && potentialClient.errorInfo != '' }">
								<font color="red">${potentialClient.errorInfo }</font>
							</c:if>
							<c:if test="${potentialClient.errorInfo == null || potentialClient.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${potentialClient.client_no }</td>	
						<td>${potentialClient.client_name }</td>	
						<td>${potentialClient.trade }</td>	
						<td>${potentialClient.addr }</td>	
						<td>${potentialClient.linkman }</td>	
						<td>${potentialClient.mobile_phone }</td>	
						<td>${potentialClient.tel }</td>	
						<td>${potentialClient.email }</td>	
						<td>${potentialClient.qq }</td>	
						<td>${potentialClient.technology_direction }</td>	
						<td>${potentialClient.service_type }</td>	
						<td>${potentialClient.project_cycle_name }</td>	
						<td>${potentialClient.project_requirement }</td>	
						<td>${potentialClient.client_worth_name }</td>	
						<td>${potentialClient.info_source }</td>	
						<td>${potentialClient.status_name }</td>						
						<td><fmt:formatDate value="${potentialClient.consult_time }" pattern="yyyy-MM-dd"/></td>				
						<td>${potentialClient.description }</td>	
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
