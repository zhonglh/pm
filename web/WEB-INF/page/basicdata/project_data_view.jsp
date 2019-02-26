<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/ProjectAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="project_id" value="${project1.project_id }" />
			<p>
				<label>项目名称：</label>
				${project1.project_name }
			</p>
			<p>
				<label>项目编号：</label>
				${project1.project_no }
			</p>
			<p>
				<label>客户名称：</label>			
				${project1.project_client_name }
			</p>
			<p>
				<label>联系人：</label>
				${project1.project_contacts_name }
			</p>
			<p>
				<label>联系人电话：</label>
				${project1.project_contacts_tel }
			</p>
			<p>
				<label>招聘专员：</label>				
				${project1.recruitment_username }
			</p>
			<p>
				<label>销售负责人：</label>
				${project1.sales_username }
			</p>
			<p>
				<label>信息来源：</label>
				${project1.info_sources_username }
			</p>
			<p>
				<label>项目管理人：</label>
				${project1.manage_username }	
			</p>
			<p>
				<label>售前支撑：</label>
				${project1.sales_before_username }
			</p>
			<p>
				<label>售后支撑：</label>
				${project1.sales_after_username }
			</p>
			<p>
				<label>销售额归属：</label>
				${project1.sales_amount_deptname }
			</p>
			<p>
				<label>执行额归属：</label>			
				${project1.exec_amount_deptname }
			</p>
			<p>
				<label>所属部门：</label>			
				${project1.deptname }
			</p>
			<p>
				<label>项目类型：</label>
				<spring:message code="project.type.${project1.project_type}" />
			</p>
			<p>
				<label>项目金额：</label> 
				<fmt:formatNumber value="${project1.project_amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>项目预估成本：</label>
				<fmt:formatNumber value="${project1.estimate_costs }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>项目开始时间：</label>
				<fmt:formatDate value="${project1.start_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>批复人：</label>
				${project1.approve_user_name }
			</p>	
			<p>
				<label>合同编号：</label>
				${project1.contract_no }
			</p>					
			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd>${project1.description }</dd>
				</dl>
			</p>			



		<div class="divider"></div>
			
			<h3 class="contentTitle">项目人员</h3>
						<table id="project_staff_cost_table" class="list nowrap " addButton="新建人员及单价" width="100%">
							<thead>
								<tr>
									<th width="100">员工姓名</th>
									<th width="100" >入场日期</th>
									<th width="100" >离场日期</th>
									<th width="100">技术费用</th>
									<th width="120" >所属客户部门</th> 
									<th width="300">说明</th>
								</tr>												
							</thead>
							<tbody>								
								<c:forEach var="projectstaff"  varStatus="status1" items="${projectStaffs}">
								<tr>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if>${projectstaff.staff_name }<c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${projectstaff.join_project_datetime }" pattern="yyyy-MM-dd"/><c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${projectstaff.leave_project_datetime }" pattern="yyyy-MM-dd"/><c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if>${projectstaff.technical_cost }<c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td>${projectstaff.client_dept }</td>
									<td>${projectstaff.description }</td>
								</tr>
								</c:forEach>								
							</tbody>
						</table>	
			
		<div class="divider"></div>


			<br>
			<div class="divider"></div>
			<h3 class="contentTitle">收款合同附件</h3>
				<table id="project_contract_attach_table" class="list nowrap "  width="100%">
					<thead>
						<tr>
							<th width="300" type="attach" lookupPk="attachment_id" name="items[#index#].attachment.attachment_name" lookupGroup="items[#index#].attachment" lookupUrl="${webroot }/ProjectAction.do?method=toUplodad&project_id=${project.project_id }" size="90" title="上传合同附件">附件名称</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="contractAttach"  varStatus="status1" items="${contractAttachs}">
						<c:if test="${contractAttach.attachment_type=='1'}">
						<tr>
							<td><a target="dwzExport" targetType="navTab" href="${webroot }/ProjectAction.do?method=printAttach&project_id=${contractAttach.project_id }&attachment_id=${contractAttach.attachment_id}">${contractAttach.attachment_name }</a></td>

						</tr>
						</c:if>
						</c:forEach>
					</tbody>
				</table>

			<div class="divider"></div>


			<br><br>
			<div class="divider"></div>
			<h3 class="contentTitle">付款合同附件</h3>
			<table id="project_contract_attach_table" class="list nowrap "  width="100%">
				<thead>
				<tr>
					<th width="300" type="attach" lookupPk="attachment_id" name="items[#index#].attachment.attachment_name" lookupGroup="items[#index#].attachment" lookupUrl="${webroot }/ProjectAction.do?method=toUplodad&project_id=${project.project_id }" size="90" title="上传合同附件">附件名称</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="contractAttach"  varStatus="status1" items="${contractAttachs}">
					<c:if test="${contractAttach.attachment_type=='0'}">
					<tr>
						<td><a target="dwzExport" targetType="navTab" href="${webroot }/ProjectAction.do?method=printAttach&project_id=${contractAttach.project_id }&attachment_id=${contractAttach.attachment_id}">${contractAttach.attachment_name }</a></td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>

			<div class="divider"></div>

			<br>
			<div class="divider"></div>
			
		
			<c:if test="${operation_read != null && operation_read != '' }">
				<h3 class="contentTitle">收款合同</h3>
							<table id="project_constract1_table" class="list nowrap " width="100%">
								<thead>
									<tr>
										<th width="100">合同编号</th>	
										<th width="280">执行合同</th>	
										<th width="120">人月费用</th>	
										<th width="80">销售负责人</th>	
										<th width="80">客户联系人</th>	
										<th width="160">存放地</th>	
									</tr>												
								</thead>
								<tbody>								
									<c:forEach var="contract"  varStatus="status1" items="${contracts}">
									<tr>
										<td>
											<a class="add" href="${webroot}/ContractAction.do?method=toView&id=${contract.id }"  
													mask="true" width="800" height="390" rel="contractdetail" title="合同明细"  target="dialog">
												<u>${contract.contract_no }</u>
											</a>
										</td>
										<td>${contract.exec_contract }</td>
										<td><b>${contract.monthly_expenses_str }</b></td>
										<td>${contract.sales_username }</td>
										<td>${contract.client_linkman }</td>
										<td>${contract.storage_addr_name }</td>
									</tr>
									</c:forEach>								
								</tbody>
							</table>
	
				<div class="divider"></div>
			</c:if>







			<c:if test="${paycontract_read != null && paycontract_read != '' }">
				<h3 class="contentTitle">付款合同</h3>
				<table id="project_constract0_table" class="list nowrap " width="100%">
					<thead>
					<tr>
						<th width="100">合同编号</th>
						<th width="280">执行合同</th>
						<th width="120">金额</th>
						<th width="80">负责人</th>
						<th width="80">客户联系人</th>
						<th width="260">公司联系人</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="contract"  varStatus="status1" items="${paycontracts}">
						<tr>
							<td>
								<a class="add" href="${webroot}/PayContractAction.do?method=toView&id=${contract.id }"
								   mask="true" width="800" height="390" rel="contractdetail" title="合同明细"  target="dialog">
									<u>${contract.contract_no }</u>
								</a>
							</td>
							<td>${contract.exec_contract }</td>
							<td><b>${contract.amount }</b></td>
							<td>${contract.manager_username }</td>
							<td>${contract.client_linkman }</td>
							<td>${contract.company_name }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<div class="divider"></div>
			</c:if>





			<h3 class="contentTitle">项目付款信息</h3>
			<table id="project_expend_table" class="list nowrap " width="100%">
				<thead>
				<tr>
					<th width="80">应付金额</th>
					<th width="150">分包商名称</th>
					<th width="100">分包合同编号</th>
					<th width="100">收到的发票号</th>
					<th width="80">应付月份</th>
					<th width="80">核单人</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="projectExpend"  varStatus="status1" items="${projectExpends}">
					<tr>
						<td><fmt:formatNumber value="${projectExpend.amount }" type="currency" pattern="###,###,##0.00"/></td>
						<td>${projectExpend.sub_contractor_name }</td>
						<td>${projectExpend.sub_contractor_no }</td>
						<td>${projectExpend.invoiceno }</td>
						<td>${projectExpend.use_month }</td>
						<td>${projectExpend.build_username }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			
			
			
			<p>
				<label>制单人：</label>  ${project1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${project1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
