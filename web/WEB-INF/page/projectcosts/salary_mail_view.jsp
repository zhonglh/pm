<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="form1" name="form1"  class="pageForm">
		<div class="pageFormContent" layoutH="56">	
			
			<div class="divider"></div>
			<table class="list nowrap" width="1500" >
				<thead>
					<tr>
						<th width="30" nowrap>序号</th>						
						<th width="120" nowrap>发送时间</th>					
						<th width="120" nowrap>邮箱服务器</th>					
						<th width="120" nowrap>邮箱服务器端口</th>					
						<th width="200" nowrap>发件箱用户名</th>					
						<th width="120" nowrap>发件箱密码</th>					
						<th width="200" nowrap>收件箱地址</th>
						<th width="80" >发送状态</th>	
						<th width="450" >说明</th>	
						<th width="80" >发送人</th>	
					</tr>
				</thead>	
				
				<tbody>					
					<c:forEach var="detail"  varStatus="status1" items="${list}">
					<tr>
						<td nowrap>${status1.index + 1 }</td>
						<td nowrap>
							<fmt:formatDate value="${detail.send_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>	
						<td nowrap>${detail.mail_server_host }</td>	
						<td nowrap>${detail.mail_server_port }</td>	
						<td nowrap>${detail.from_username }</td>	
						<td nowrap>${detail.from_password }</td>	
						<td nowrap>${detail.to_Address }</td>	
						<td nowrap>${detail.send_status }</td>	
						<td title="${detail.send_remark }">${detail.send_remark }</td>
						<td nowrap>${detail.send_username }</td>	
					</tr>
					</c:forEach>
				</tbody>		
				
			</table>			
			

		</div>
		<div class="formBar">
			<ul>
				
				
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li> 
			</ul>
		</div>
	</form>
</div>


