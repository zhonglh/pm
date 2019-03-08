<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/ContractAction.do?method=verifyContract" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>合同存放地：</label>
				${contract1.storage_addr_name }
			</p>
			<p>
				<label>立项名称：</label>
				${contract1.project_name }
			</p>
			<p>
				<label>立项编号：</label>
				${contract1.project_no }
			</p>
			
			<p>
				<label>客户名称：</label>
				${contract1.project_client_name }
			</p>
			
			<p>
				<label>合同编号：</label>
				${contract1.contract_no }
			</p>
			<p>
				<label>执行合同：</label>
				${contract1.exec_contract }
			</p>
			<p>
				<label>人月费用：</label>
				${contract1.monthly_expenses_str }
			</p>
			<p>
				<label>合同签订日期：</label>
				<fmt:formatDate value="${contract1.signing_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>合同有效期：</label>
				${contract1.effectivedate }
			</p>
			<p>
				<label>销售负责人：</label>
				${contract1.sales_username }
			</p>
			<p>
				<label>客户联系人：</label>
				${contract1.client_linkman }
			</p>			
			<p>
				<label>法定代表人：</label>
				${contract1.corporation }
			</p>
			<p>
				<label>提交日期：</label>
				<fmt:formatDate value="${contract1.submit_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>合同份数：</label>
				<fmt:formatNumber value="${contract1.contract_number }" type="number" pattern="###,###,##0"/>
			</p>


			<p>
				<label>付款方式：</label>
				${contract1.paymen_mode }
			</p>

			<p>
				<label>备注：</label>
				${contract1.description }
			</p>

			<br>

			<div class="divider"></div>



			<h3 class="contentTitle">合同附件</h3>
			<table id="contract_attachment_table" class="list nowrap " width="100%">
				<thead>
				<tr>
					<th >附件名称</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="contractAttachment"  varStatus="status1" items="${contractAttachments}">
					<tr>
						<td><a href="${webroot }/ContractAttachmentAction.do?method=printAttach&contract_id=${contract1.id }&attachment_id=${contractAttachment.attachment_id}">${contractAttachment.attachment_name }</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>



			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${contract1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${contract1.build_datetime }" pattern="yyyy-MM-dd"/>
			</p>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
