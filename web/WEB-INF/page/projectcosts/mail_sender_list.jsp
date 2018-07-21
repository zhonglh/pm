<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<form id="pagerForm" method="post" action="${webroot }/MailSenderAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">			
				<li><a class="add" href="${webroot}/MailSenderAction.do?method=toEdit" mask="true" width="400" height="280" rel="add_mail_sender"  target="dialog"><span>添加发送箱</span></a></li>
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/MailSenderAction.do?method=deleteMailSender" class="delete"><span>删除</span></a></li> 
				<li><a class="edit" href="${webroot}/MailSenderAction.do?method=toEdit&sender_id={sender_id}" mask="true" width="400"  height="280" rel="update_mail_sender" target="dialog" warn="请选择一条数据"><span>修改发送箱</span></a></li>
				<li><a  target="selectedTodo" rel="ids" href="${webroot}/MailSenderAction.do?method=updateStatus&status=1" class="edit"><span>启用</span></a></li>
				<li><a  target="selectedTodo" rel="ids" href="${webroot}/MailSenderAction.do?method=updateStatus&status=0" class="edit"><span>关闭</span></a></li> 					
		</ul>
	</div>
	<table class="table" width="100%" layoutH="72">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="100">邮箱服务器</th>
				<th width="120">邮箱服务器端口</th>
				<th width="200">发件箱用户名</th>
				<th width="200">发件箱密码</th>
				<th width="80">状态</th>
				<th width="80">创建人</th>
				<th width="120">创建日期</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="item"  varStatus="status1" items="${list}">
			<tr target="sender_id" rel="${item.sender_id }">
				<td><input name="ids" value="${item.sender_id }" type="checkbox" /></td>
				<td>${item.mail_server_host }</td>
				<td>${item.mail_server_port }</td>
				<td>${item.from_username }</td>
				<td>${item.from_password }</td>
				<td>
					<c:if test="${ 0 == item.status }">
						<font color="red"><b>关闭</b></font>
					</c:if>
					<c:if test="${ 1 == item.status }">
						启用
					</c:if>
				</td>
				<td>${item.build_username }</td>
				<td><fmt:formatDate value="${item.build_datetime }" pattern="yyyy-MM-dd" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
	
</div>