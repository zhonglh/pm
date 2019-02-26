<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/ProjectAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/ProjectAction.do?method=isExist', dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="project_id" value="${project1.project_id }" />
			<p>
				<label>项目名称：</label>
				<input name="project_name" class="text required" type="text" size="30" maxlength="100" value="${project1.project_name }" />
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project_no" class="text required" type="text" size="30"  maxlength="50" value="${project1.project_no }" />
			</p>
			<p>
				<label>客户名称：</label>				
				<input type="hidden" size="2" name="client.client_id" value="${project1.project_client_id }"/>
				<input name="client.client_name" class="text" type="text" size="28"  value="${project1.project_client_name }" readonly="readonly" />				
				<a class="btnLook" href="${webroot }/ItemsAction.do?method=lookupClient" lookupGroup="client" lookupPk="client_id" width="950">选择</a>
			</p>
			<p>
				<label>联系人：</label>
				<input name="project_contacts_name" class="text" type="text" size="30"  maxlength="60" value="${project1.project_contacts_name }"/>
			</p>
			<p>
				<label>联系人电话：</label>
				<input name="project_contacts_tel" class="text" type="text" size="30"  maxlength="30" value="${project1.project_contacts_tel }"/>
			</p>
			<p>
				<label>招聘专员：</label>				
				<input type="hidden" size="2" name="recruitment.user_id" value="${project1.recruitment_userid }"/>
				<input name="recruitment.user_name" class="text" type="text" size="28"  value="${project1.recruitment_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=1" lookupGroup="recruitment" lookupPk="user_id" width="950">选择</a>
			</p>
			<p>
				<label>销售负责人：</label>
				<input type="hidden" size="2" name="salesuser.user_id" value="${project1.sales_userid }"/>
				<input name="salesuser.user_name" class="text" type="text" size="28"  value="${project1.sales_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=2" lookupGroup="salesuser" lookupPk="user_id" width="950">选择</a>
			</p>
			<p>
				<label>信息来源：</label>
				<input type="hidden" size="2" name="infosource.user_id" value="${project1.info_sources_userid }"/>
				<input name="infosource.user_name" class="text" type="text" size="28"  value="${project1.info_sources_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=6" lookupGroup="infosource" lookupPk="user_id" width="950">选择</a>
			</p>
			<p>
				<label>项目管理人：</label>
				<input type="hidden" size="2" name="manage.user_id" value="${project1.manage_userid }"/>
				<input name="manage.user_name" class="text" type="text" size="28"  value="${project1.manage_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=5" lookupGroup="manage" lookupPk="user_id" width="950">选择</a>	
			</p>
			<p>
				<label>售前：</label>
				<input type="hidden" size="2" name="salesbefore.user_id" value="${project1.sales_before_userid }"/>
				<input name="salesbefore.user_name" class="text" type="text" size="28"  value="${project1.sales_before_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=3" lookupGroup="salesbefore" lookupPk="user_id" width="950">选择</a>
			</p>
			<p>
				<label>售后：</label>
				<input type="hidden" size="2" name="salesafter.user_id" value="${project1.sales_after_userid }"/>
				<input name="salesafter.user_name" class="text" type="text" size="28"  value="${project1.sales_after_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=4" lookupGroup="salesafter" lookupPk="user_id" width="950">选择</a>
			</p>
			<p>
				<label>销售额归属：</label>
				<input type="hidden" size="2"  name="sales.dept_id" value="${project1.sales_amount_deptid }"/>
				<input name="sales.dept_name" class="text" type="text" size="30"  value="${project1.sales_amount_deptname }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup" lookupGroup="sales"/>	
			</p>
			<p>
				<label>执行额归属：</label>			
				<input type="hidden" size="2"  name="exec.dept_id" value="${project1.exec_amount_deptid }"/>
				<input name="exec.dept_name" class="text" type="text" size="30"  value="${project1.exec_amount_deptname }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup" lookupGroup="exec"/>	
			</p>
			<p>
				<label>所属部门：</label>			
				<input type="hidden" size="2"  name="dept.dept_id" value="${project1.deptid }"/>
				<input name="dept.dept_name" class="text required" type="text" size="30"  value="${project1.deptname }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup" lookupGroup="dept"/>	
			</p>
			<p>
				<label>项目类型：</label>								
				<label class="radioButton"><input name="project_type" value="1" type="radio" <c:if test="${project1.project_type == '1' }">checked="true"</c:if> />项目外包</label>
				<label class="radioButton"><input name="project_type" value="2" type="radio" <c:if test="${project1.project_type == '2' }">checked="true"</c:if> />人员外派</label>
				<label class="radioButton"><input name="project_type" value="3" type="radio" <c:if test="${project1.project_type == '3' }">checked="true"</c:if>/>其他</label>
			</p>
			<p>
				<label>项目金额：</label> 
				<input name="project_amount" class="number" type="text" size="30"  maxlength="15" value="<fmt:formatNumber value="${project1.project_amount }" type="number" pattern="####0.00#"/>"/>
			</p>
			<p>
				<label>项目预估成本：</label>
				<input name="estimate_costs" class="number" type="text" size="30"  maxlength="15" value="<fmt:formatNumber value="${project1.estimate_costs }" type="number" pattern="####0.00#"/>"/>
			</p>
			
			<p>
				<label>项目开始时间：</label>
				<input name="start_date" class="date" type="text" size="30" value="<fmt:formatDate value="${project1.start_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>
			
			<p>
				<label>批复人：</label>
				<input type="hidden" size="2" name="approve.user_id" value="${project1.approve_user_id }"/>
				<input name="approve.user_name" class="text" type="text" size="28"  value="${project1.approve_user_name }" readonly="readonly" />
				<a class="btnLook" href="${webroot}/OtherStaffAction.do?method=lookup" lookupGroup="approve" lookupPk="user_id" width="950">选择</a>
			</p>			
			
			<p>
				<label>合同编号：</label>
				<input name="contract_no" class="text" type="text" size="30"  maxlength="60" value="${project1.contract_no }"/>
			</p>					
			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd><textarea name="description" cols="95" rows="4" maxlength="300">${project1.description }</textarea></dd>
				</dl>
			</p>			



		<div class="divider"></div>
			
			<h3 class="contentTitle">项目人员</h3>
						<table id="project_staff_cost_table" class="list nowrap itemDetail" addButton="新建人员及单价" width="100%">
							<thead>
								<tr>
									<!-- &delete_flag=0 -->
									<th width="150" type="lookup" fieldClass="required" name="items[#index#].staff.staff_name" lookupGroup="items[#index#].staff" lookupUrl="${webroot }/StaffCostAction.do?method=lookup&in_project=0&delete_flag=0"   lookupPk="staff_id" size="8">员工姓名</th>
									<th width="100" type="date" postField="join_project_datetime" defaultVal="${currDate }"  name="items[#index#].join_project_datetime"  size="10" fieldClass="date required">入场日期</th>
									<th width="100" type="label" nowrap>离场日期</th>
									<th width="60" type="number" postField="technical_cost"  name="items[#index#].staff.technical_cost" defaultVal="0"  maxlength="12" size="8" fieldClass="number">技术费用</th>
									<th width="100" type="text" postField="client_dept"  name="items[#index#].client_dept" maxlength="25" size="12" fieldClass="text">所属客户部门</th>
									<th width="250" type="label" >说明</th>
									<th type="del" width="60">操作</th>
								</tr>												
							</thead>
							<tbody>								
								<c:forEach var="projectstaff"  varStatus="status1" items="${projectStaffs}">
								<tr>
									<td height="22"><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if>${projectstaff.staff_name }<c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${projectstaff.join_project_datetime }" pattern="yyyy-MM-dd"/><c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if><fmt:formatDate value="${projectstaff.leave_project_datetime }" pattern="yyyy-MM-dd"/><c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td><c:if test="${projectstaff.delete_flag == '1' }"><s></c:if>${projectstaff.technical_cost }<c:if test="${projectstaff.delete_flag == '1' }"></s></c:if></td>
									<td>${projectstaff.client_dept }</td>
									<td>${projectstaff.description }</td>
									<td width="120" nowrap>
										<c:if test="${projectstaff.delete_flag == '0' }">
											<a href="void(0);" onclick="edit_project_staff(this,'${projectstaff.project_staff_id }');return false;" ><span>编辑</span></a>
											<a title="确实要<font color='red'><b>彻底</b></font>删除这条记录吗?" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem"  href="${webroot}/ProjectAction.do?method=removeProjectStaff&project_staff_id=${projectstaff.project_staff_id}&rownum=${status1.index}" class="delete"><span>删除</span></a>
											<a href="${webroot }/ProjectAction.do?method=toExchangeProjectStaff&project_staff_id=${projectstaff.project_staff_id}&rownum=${status1.index}" mask="true" width="400" height="380" rel="staff_costs_exchange" target="dialog"><span>离场</span></a>
										</c:if>
									</td>
								</tr>
								</c:forEach>								
							</tbody>
						</table>
			
		<div class="divider"></div>

			<br>
			<div class="divider"></div>

			<h3 class="contentTitle">收款合同附件</h3>
			<table id="project_contract_attach1_table" class="list nowrap itemDetail" addButton="新增收款合同附件" width="100%">
				<thead>
				<tr>
					<th width="300" fieldClass="required" type="attach" lookupPk="attachment_id" name="items[#index#].attachment1.attachment_name" lookupGroup="items[#index#].attachment1" lookupUrl="${webroot }/ProjectAction.do?method=toUplodad&attachment_type=1&project_id=${project1.project_id }" size="90" title="上传收款合同附件">附件名称</th>
					<th width="1" type="hidden" name="items[#index#].attachment1.attachment_path" ></th>
					<th type="del" width="60">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="contractAttach"  varStatus="status1" items="${contractAttachs}">
					<c:if test="${contractAttach.attachment_type=='1'}">
					<tr>
						<td><a href="${webroot }/ProjectAction.do?method=printAttach&project_id=${contractAttach.project_id }&attachment_id=${contractAttach.attachment_id}">${contractAttach.attachment_name }</a></td>
						<td width="1"></td>
						<td width="60"><a class="btnDel" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem" href="${webroot }/ProjectAction.do?method=deleteProjectAttach&attachment_id=${contractAttach.attachment_id}&rownum=${status1.index}" ><span>删除附件</span></a></td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>




			<br><br>
			<div class="divider"></div>

			<h3 class="contentTitle">付款合同附件</h3>
			<table id="project_contract_attach0_table" class="list nowrap itemDetail" addButton="新增付款合同附件" width="100%">
				<thead>
				<tr>
					<th width="300" fieldClass="required" type="attach" lookupPk="attachment_id" name="items[#index#].attachment0.attachment_name" lookupGroup="items[#index#].attachment0" lookupUrl="${webroot }/ProjectAction.do?method=toUplodad&attachment_type=0&project_id=${project1.project_id }" size="90" title="上传付款合同附件">附件名称</th>
					<th width="1" type="hidden" name="items[#index#].attachment0.attachment_path" ></th>
					<th type="del" width="60">操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="contractAttach"  varStatus="status1" items="${contractAttachs}">

					<c:if test="${contractAttach.attachment_type=='0'}">
					<tr>
						<td><a href="${webroot }/ProjectAction.do?method=printAttach&project_id=${contractAttach.project_id }&attachment_id=${contractAttach.attachment_id}">${contractAttach.attachment_name }</a></td>
						<td width="1"></td>
						<td width="60"><a class="btnDel" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem" href="${webroot }/ProjectAction.do?method=deleteProjectAttach&attachment_id=${contractAttach.attachment_id}&rownum=${status1.index}" ><span>删除附件</span></a></td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>

			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${project1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${project1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
