<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/ContractAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/ContractAction.do?method=isExist', dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${contract1.id }"/>
			
			<p>
				<label>合同存放地：</label>				
				<input type="hidden" name="sa.id" value="${contract1.storage_addr }"/>
				<input name="sa.dic_data_name" class="text" type="text" size="28"  value="${contract1.storage_addr_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="sa"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CONTRACT_STORAGE_ADDR" />	
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=CONTRACT_STORAGE_ADDR" mask="true" width="500" height="260" rel="add_CONTRACT_STORAGE_ADDR"  target="dialog"><span>添加</span></a>
			</p>
			<p>
				<label>立项名称：</label>
				<input name="project.project_id"	type="hidden" value="${contract1.project_id}"	 />		
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${contract1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>立项编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${contract1.project_no}" readonly="true"/>
			</p>

			<p>
				<label>客户名称：</label>
				<input name="project.project_client_name" class="text" type="text" size="30" maxlength="50" value="${contract1.project_client_name}" readonly="true"/>
			</p>
			
			<p>
				<label>合同编号：</label>
				<input name="contract_no" class="text required" type="text" size="30" maxlength="30" value="${contract1.contract_no }" />
			</p>
			<p>
				<label>执行合同：</label>
				<input name="exec_contract" class="text" type="text" size="30" maxlength="300" value="${contract1.exec_contract }" />
			</p>
			<p>
				<label>人月费用：</label>
				<input name="monthly_expenses" class="number required" type="text" size="12" value="<fmt:formatNumber value="${contract1.monthly_expenses }" type="number" pattern="####0.00#"/>" />
				<input name="monthly_expenses2" class="number" type="text" size="12" value="<fmt:formatNumber value="${contract1.monthly_expenses2 }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>合同签订日期：</label>
				<input name="signing_date" class="date" type="text" size="30" value="<fmt:formatDate value="${contract1.signing_date }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>合同有效期：</label>
				<input name="validity_date1" class="date" type="text" size="12" value="<fmt:formatDate value="${contract1.validity_date1 }" pattern="yyyy-MM-dd"/>" />
				<input name="validity_date2" class="date" type="text" size="12" value="<fmt:formatDate value="${contract1.validity_date2 }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>销售负责人：</label>
				<input type="hidden" size="2" name="salesuser.user_id" value="${contract1.sales_userid }"/>
				<input name="salesuser.user_name" class="text" type="text" size="28"  value="${contract1.sales_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=2" lookupGroup="salesuser" lookupPk="user_id" width="950">选择</a>
				
			</p>			
			<p>
				<label>客户联系人：</label>
				<input name="client_linkman" class="text" type="text" size="30" maxlength="30" value="${contract1.client_linkman }" />
			</p>
			<p>
				<label>法定代表人：</label>
				<input name="corporation" class="text" type="text" size="30" maxlength="30" value="${contract1.corporation }" />
			</p>
			<p>
				<label>提交日期：</label>
				<input name="submit_date" class="date" type="text" size="30" value="<fmt:formatDate value="${contract1.submit_date }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>合同份数：</label>
				<input name="contract_number" class="digits required" type="text" size="30" value="<fmt:formatNumber value="${contract1.contract_number }" type="number" pattern="####0"/>" />
			</p>


			<p>
				<label>付款方式：</label>
				<input name="paymen_mode" class="text" type="text" size="30" maxlength="30" value="${contract1.paymen_mode }" />
			</p>

			<p>
				<label>备注：</label>
				<input name="description" class="text" type="text" size="30" maxlength="30" value="${contract1.description }" />
			</p>


			<br>



			<div class="divider"></div>


			<h3 class="contentTitle">合同附件</h3>
			<table id="contract_attachment_table" class="list nowrap itemDetail" addButton="新增合同附件" width="100%">
				<thead>
				<tr>
					<th width="300" fieldClass="required" type="attach" lookupPk="attachment_id" name="items[#index#].attachment.attachment_name" lookupGroup="items[#index#].attachment" lookupUrl="${webroot }/ContractAttachmentAction.do?method=toUplodad&contract_id=${contract1.id }" size="90" title="上传合同附件">附件名称</th>
					<th width="1" type="hidden" name="items[#index#].attachment.attachment_path" ></th>
					<th type="del" width="60">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="contractAttachment"  varStatus="status1" items="${contractAttachments}">
						<tr>
							<td><a href="${webroot }/ContractAttachmentAction.do?method=printAttach&contract_id=${contract1.id }&attachment_id=${contractAttachment.attachment_id}">${contractAttachment.attachment_name }</a></td>
							<td width="1"></td>
							<td width="60"><a class="btnDel" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem" href="${webroot }/ContractAttachmentAction.do?method=deleteContractAttachment&attachment_id=${contractAttachment.attachment_id}&rownum=${status1.index}" ><span>删除附件</span></a></td>
						</tr>
				</c:forEach>
				</tbody>
			</table>



			<br>
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
