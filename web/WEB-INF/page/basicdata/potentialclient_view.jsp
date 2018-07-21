<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/potentialClient1Action.do?method=verifypotentialClient1" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>客户编号：</label>
				${potentialClient1.client_no }
			</p>
			<p>
				<label>客户名称：</label>
				${potentialClient1.client_name }
			</p>
			<p>
				<label>行业：</label>
				${potentialClient1.trade }
			</p>
			<p>
				<label>客户地址：</label>
				${potentialClient1.addr }
			</p>
			<p>
				<label>联系人：</label>
				${potentialClient1.linkman }
			</p>
			<p>
				<label>手机：</label>
				${potentialClient1.mobile_phone }
			</p>
			<p>
				<label>座机：</label>
				${potentialClient1.tel }
			</p>
			<p>
				<label>电子邮件：</label>
				${potentialClient1.email }
			</p>
			<p>
				<label>QQ号码：</label>
				${potentialClient1.qq }
			</p>
			<p>
				<label>技术方向：</label>
				${potentialClient1.technology_direction }
			</p>
			<p>
				<label>服务类型：</label>
				${potentialClient1.service_type }
			</p>
			<p>
				<label>项目周期：</label>
				${potentialClient1.project_cycle_name }
			</p>

			<dl class="nowrap">
				<dt>客户需求：</dt>
				<dd><textarea name="description" cols="90" rows="4" maxlength="150">${potentialClient1.project_requirement }</textarea></dd>
			</dl>
			
			<p>
				<label>客户价值：</label>
				${potentialClient1.client_worth_name }
			</p>
			<p>
				<label>信息来源：</label>
				${potentialClient1.info_source }
			</p>
			<p>
				<label>客户状态：</label>
				${potentialClient1.status_name }
			</p>
			<p>
				<label>咨询时间：</label>
				<fmt:formatDate value="${potentialClient1.consult_time }" pattern="yyyy-MM-dd"/>
			</p>

			
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd><textarea name="description" cols="90" rows="3" maxlength="150">${potentialClient1.description }</textarea></dd>
			</dl>
			
		
			<div class="divider"></div>
			<div class="divider"></div>

			<h3 class="contentTitle">跟进记录</h3>
			
			<table id="followups_table" class="list nowrap"  width="100%">
				<thead>
					<tr>
						<th width="120" type="date" postField="link_time"  name="items[#index#].link_time" format="yyyy-MM-dd HH:mm"  size="13" fieldClass="date required">联系时间</th>
						<th width="60" type="text" postField="link_type"  name="items[#index#].link_type" maxlength="5" size="4" fieldClass="text required">联系方式</th>
						<th width="450" type="text" postField="link_content"  name="items[#index#].link_content" maxlength="500" size="60" fieldClass="text required">联系内容</th>
						<th width="120" type="text" postField="description"  name="items[#index#].description" maxlength="150" size="12" fieldClass="text">备注</th>
						
					</tr>												
				</thead>
				<tbody>
					<c:forEach var="followup"  varStatus="status1" items="${followups}">
					<tr>
						<td><fmt:formatDate value="${followup.link_time }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${followup.link_type }</td>
						<td title="${followup.link_content }">${followup.link_content }</td>
						<td title="${followup.description }">${followup.description }</td>
					</tr>
					</c:forEach>								
				</tbody>
			</table>
			
			<div class="divider"></div>

		</div>
	</form>
</div>
