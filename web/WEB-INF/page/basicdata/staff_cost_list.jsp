<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/StaffCostAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/StaffCostAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${staffCost.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" value="${staffCost.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>			
			</li>
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name }"/>	
			</li>
			
			<li>				
				<label>外协人员：</label>
				<select name="outsource_staff" style="width:133px">
					<option value="">全部</option>
					<option value="0" <c:if test="${staffCost.outsource_staff == '0' }">selected</c:if>>否</option>
					<option value="1" <c:if test="${staffCost.outsource_staff == '1' }">selected</c:if>>是</option>					
				</select>		
			</li>	
			
		</ul>
		
		<ul class="searchContent">
		
			<li>
				<label>员工工号：</label>
				<input type="text" name="staff_no" value="${staffCost.staff_no }"/>
			</li>
			<li>
				<label>员工名称：</label>
				<input type="text" name="staff_name" value="${staffCost.staff_name }"/>
			</li>

			<li>				
				<label>在职状态：</label>
				<select name="delete_flag" style="width:133px">
					<option value="">全部</option>
					<option value="0" <c:if test="${staffCost.delete_flag == '0' }">selected</c:if>>在职</option>
					<option value="1" <c:if test="${staffCost.delete_flag == '1' }">selected</c:if>>离职</option>					
				</select>		
			</li>	
		</ul>
		<ul class="searchContent">		
			<li>
				<label>招聘专员名称：</label>
				<input type="text" name="recruiter_name" value="${param.recruiter_name }"/>
			</li>
			<li>
				<label>时间段：</label>
				<input name="date1" class="date" type="text" size="7" value="${param.date1 }" readonly="readonly" />
				<input name="date2" class="date" type="text" size="7" value="${param.date2 }" readonly="readonly" />
			</li>

			
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>
			
			
		</ul>		
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/StaffCostAction.do?method=toEdit" mask="true" width="850" height="480" rel="staff_costs_add" target="dialog"><span>添加人员成本</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">						
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/StaffCostAction.do?method=deleteStaffCost" class="delete"><span>删除</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/StaffCostAction.do?method=toEdit&staff_id={staff_id}" rel="staff_costs_edit" width="850" height="480" mask="true" target="dialog" warn="请选择一条数据"><span>修改人员成本</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/StaffCostAction.do?method=toView&staff_id={staff_id}" mask="true" width="850" height="480" rel="view_project_data"  target="dialog" warn="请选择一条数据"><span>显示明细</span></a></li>
			</c:if>			
			<li class="line">line</li>
			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/StaffCostAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/StaffCostAction.do?method=exportByRecruiter" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>按招聘导出EXCEL</span></a></li>
			</c:if>
			
			<li class="line">line</li>
			
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/StaffCostAction.do?method=toExcel" mask="true" width="850" height="480" rel="staff_costs_add" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			
			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/StaffCostAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>				
			
		</ul>
	</div>
	<table class="table" width="6200" layoutH="162">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="80">员工工号</th>
				<th width="80">员工名称</th>
				<th width="180">项目名称</th>
				<th width="100">部门</th>
				<th width="100">岗位名称</th>
				<th width="120">招聘专员名称</th>				
				<th width="40">性别</th>
				<th width="100">生日</th>
				<th width="100">民族</th>
				<th width="120">户籍性质</th>				
				<th width="160">身份证号码</th>
				
							
				<th width="140">联系电话</th>
				<th width="140">紧急联系电话</th>
				<th width="100">QQ号码</th>
				<th width="120">合同开始时间</th>
				<th width="120">合同结束时间</th>
				<th width="80">续签次数</th>
				<th width="160">毕业学校</th>
				<th width="60">学历</th>
				<th width="100">专业</th>
				<th width="100">毕业时间</th>
				<th width="80">工作年限</th>
				<th width="200">户口所在地</th>
				<th width="200">通讯地址</th>
				
				<th width="120">入职时间</th>
				<th width="120">离职时间</th>	
				<th width="100">人员总成本</th>	
				<th width="100">客户最初报价</th>
				<th width="100">客户最新报价</th>								
				<th width="100">转正日期</th>
				<th width="100">试用期工资</th>
				<th width="100">正式工资</th>				
				<th width="70">基本工资</th>
				<th width="70">岗位工资</th>
				<th width="70">绩效津贴</th>
				
				
				
				
				<th width="80">电脑补助</th>	
				<th width="50">餐补</th>	
				<th width="100">奖金(税前)</th>	
				<th width="80">出差补助</th>	
				<th width="140">社保种类</th>	
				<th width="200">社保缴纳单位</th>	
				<th width="160">个人缴纳养老保险</th>	
				<th width="160">个人缴纳医疗保险</th>	
				<th width="160">个人缴纳失业保险</th>	
				<th width="140">个人缴纳公积金</th>	
				<th width="140">缴纳个人所得税</th>	
				<th width="160">公司缴纳养老保险</th>	
				<th width="160">公司缴纳医疗保险</th>	
				<th width="160">公司缴纳失业保险</th>	
				<th width="160">公司缴纳生育保险</th>	
				<th width="160">公司缴纳工伤保险</th>	
				<th width="140">公司缴纳公积金</th>	
				
				
				<th width="60">额外</th>	
				<th width="120">是否外协人员</th>
				<th width="200">邮箱</th>
				<th width="200">开户行</th>	
				<th width="150">银行卡号</th>
				<th width="80">主管工号</th>
				<th width="80">主管名称</th>				
				<th width="80">额外支出</th>
				<th width="140">是否允许发送信息</th>	
				
				
				<th width="60">差价</th>	
				
				<th width="100">合同种类</th>
				<th width="100">合同归属</th>
				<th width="100">社保说明</th>
				<th width="100">社保城市</th>
				<th width="240">工作地点</th>
				<th width="80">证书</th>
				<th width="600">备注</th>
				
				
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="staffcost"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${staffcost.staff_id }"  >
				<td>
					<c:if test="${staffcost.delete_flag == '0' }">
					<input name="ids" value="${staffcost.staff_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${staffcost.staff_no }</td>
				<td>
					<c:if test="${curr_ym == staffcost.confirmation_month }">
						<font color="red">					
					</c:if>					
					<c:if test="${curr_ym == staffcost.contract_end_month }">
						<font color="blue">
					</c:if>
					${staffcost.staff_name }
					<c:if test="${curr_ym == staffcost.confirmation_month }">
						</font>					
					</c:if>
					<c:if test="${curr_ym == staffcost.contract_end_month }">
						</font>					
					</c:if>
				</td>
				<td>${staffcost.project_name }</td>
				<td>${staffcost.dept_name }</td>
				<td>${staffcost.job_title }</td>
				<td>${staffcost.recruiter_name }</td>				
				<td>${staffcost.sex }</td>
				<td><fmt:formatDate value="${staffcost.birthday }" pattern="yyyy-MM-dd"/></td>
				<td>${staffcost.nationality_name }</td>
				<td>${staffcost.census_property_name }</td>								
				<td>${staffcost.identity_card_number }</td>
				
				
										
				<td>${staffcost.tel }</td>						
				<td>${staffcost.pressing_tel }</td>				
				<td>${staffcost.qq }</td>			
				<td><fmt:formatDate value="${staffcost.contract_start_date }" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${curr_ym == staffcost.contract_end_month }">
						<font color="blue">
					</c:if>
					<fmt:formatDate value="${staffcost.contract_end_date }" pattern="yyyy-MM-dd"/>
					<c:if test="${curr_ym == staffcost.contract_end_month }">
						</font>
					</c:if>
				</td>					
				<td>${staffcost.contract_number }</td>						
				<td>${staffcost.graduate_school }</td>						
				<td>${staffcost.educational_name }</td>		
									
				<td>${staffcost.specialty_name }</td>		
				<td><fmt:formatDate value="${staffcost.graduation_date }" pattern="yyyy-MM-dd"/></td>									
				<td><fmt:formatNumber value="${staffcost.working_life }" type="currency" pattern="#0.00"/></td>	

				
								
				<td>${staffcost.registered_residence }</td>						
				<td>${staffcost.postal_address }</td>	
					
				<td><fmt:formatDate value="${staffcost.join_datetime }" pattern="yyyy-MM-dd"/></td>
				<td><font color="red"><fmt:formatDate value="${staffcost.leave_job_datetime }" pattern="yyyy-MM-dd"/></font></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.totalcost }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.firstquotes }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right">
					<u>
					<a class="edit" href="${webroot}/StaffCostAction.do?method=toProjectStaffView&staff_id=${staffcost.staff_id }" title="人员报价信息" mask="true" width="760"  height="350" rel="toProjectStaffView" target="dialog" >
					<fmt:formatNumber value="${staffcost.qustomerquotes }" type="currency" pattern="###,###,##0.00"/>
					</a>
					</u>
				</td>				
				<td>
					<c:if test="${curr_ym == staffcost.confirmation_month }">
						<font color="red">					
					</c:if>		
					<fmt:formatDate value="${staffcost.confirmation_date }" pattern="yyyy-MM-dd"/>
					<c:if test="${curr_ym == staffcost.confirmation_month }">
						</font>				
					</c:if>		
				</td>				
				<td align="right"><fmt:formatNumber value="${staffcost.tryout_salary }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.official_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.basic_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.post_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.performance_allowances }" type="currency" pattern="###,###,##0.00"/></td>
							
							
							
							

				<td align="right"><fmt:formatNumber value="${staffcost.computer_allowance }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.meal_allowance }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.project_allowance }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.travel_allowance }" type="currency" pattern="###,###,##0.00"/></td>				
				<td>${staffcost.insurance_radix }</td>				
				<td>${staffcost.securty_unit }</td>				
					
				<td align="right"><fmt:formatNumber value="${staffcost.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.incometax_bypersonal }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/></td>				
				<td align="right"><fmt:formatNumber value="${staffcost.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/></td>				
				
				
				<td align="right"><fmt:formatNumber value="${staffcost.extra }" type="currency" pattern="###,###,##0.00"/></td>				
				
				<td><spring:message code="boolean.${staffcost.outsource_staff }"/></td>			
				<td>${staffcost.email }</td>				
				<td>${staffcost.open_account }</td>				
				<td>${staffcost.bank_card_number }</td>				
				<td>${staffcost.lead_no }</td>					
				<td>${staffcost.lead_name }</td>	
				<td align="right"><fmt:formatNumber value="${staffcost.extra_expend }" type="currency" pattern="###,###,##0.00"/></td>		
				
				<td>
					<c:if test="${staffcost.can_send_info == 0}"><font color="red"><b></c:if>
					<spring:message code="boolean.${staffcost.can_send_info }"/>
					<c:if test="${staffcost.can_send_info == 0}"></b></font></c:if>
				</td>
				
				
							
				<td align="right" <c:if test="${staffcost.difference < staff_costs_threshold }">style="background:red"</c:if>>
					<fmt:formatNumber value="${staffcost.difference }" type="currency" pattern="###,###,##0.00"/>
				</td>
							
							
				<td>${staffcost.contract_type_name }</td>
				<td>${staffcost.contract_attach_name }</td>
				<td>${staffcost.social_security_name }</td>
				<td>${staffcost.insured_city_name }</td>
				<td title="${staffcost.working_address_name }">${staffcost.working_address_name }</td>
				<td>${staffcost.certificate_name }</td>
				<td title="${staffcost.description }">${staffcost.description }</td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>

</div>
