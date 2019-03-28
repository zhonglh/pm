<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<form id="pagerForm" method="post" action="${webroot }/OtherSalaryGroupAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/OtherSalaryGroupAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">

			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${salary1.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${salary1.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			</li>




			<li>
				<label>工号：</label>
				<input type="text" name="staff_no" value="${param.staff_no}"/>
			</li>



			<li>
				<label>姓名：</label>
				<input type="text" name="staff_name" value="${param.staff_name}"/>
			</li>
			


		</ul>
		

		<ul class="searchContent">



			<li>
				<label>工资月份：</label>

				<input type="text" class="digits date month"  autocomplete="off" size="7"   maxlength="6" minlength="6"  format="yyyyMM" name="salary_month1" value="${param.salary_month1}"/>
				<input type="text" class="digits date month"  autocomplete="off" size="7"   maxlength="6" minlength="6"  format="yyyyMM" name="salary_month2" value="${param.salary_month2}"/>
			</li>
			
			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" style="width:153px">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
					<option value="3" <c:if test="${'3' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.3"/></option>
				</select>
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
				<li><a class="add" href="${webroot}/OtherSalaryGroupAction.do?method=toEditFirst" mask="true" width="900" height="480" rel="add_salaryGroupAction"  target="dialog"><span>添加工资</span></a></li>
				<li><a id="autoadd" title="一键制作月工资" msg="可以一次性的制作所有总部人员单个月份工资，前提是该部门在该月的考勤和人员绩效都已经审核通过了，并且该月份的工资还没有制作过。" width="450" height="220" target="dialog" href="${webroot}/OtherSalaryGroupAction.do?method=toAutoAddSalaryGroup" class="add btn-outline-msg"><span>一键添加</span></a></li>
			</c:if>
			
			
			<li class="line">line</li>
			 
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/OtherSalaryGroupAction.do?method=deleteSalaryGroup" class="delete"><span>删除</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/OtherSalaryGroupAction.do?method=toEdit&id={sid_salary}" mask="true" width="920"  height="480" rel="update_salaryGroupAction" target="dialog" warn="请选择一条数据"><span>修改工资</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/OtherSalaryGroupAction.do?method=toEdit4Easyui&id={sid_salary}" mask="true" width="920"  height="480" rel="update_salaryGroupAction" target="dialog" warn="请选择一条数据"><span>修改工资</span></a></li>
			</c:if>
			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/OtherSalaryGroupAction.do?method=toView&id={sid_salary}" mask="true" width="900"  height="480" rel="view_salaryGroupAction" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>				
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/OtherSalaryGroupAction.do?method=batchVerifySalary" class="delete"><span>批量核单</span></a></li>
			</c:if>	
			
			<li class="line">line</li>

			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/OtherSalaryGroupAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>
			</c:if>
						
		</ul>
	</div>
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">部门名称</th>
				<th width="80">工资月份</th>
				<th width="40">人数</th>
				<th width="80">制表人</th>
				<th width="120">制表日期</th>
				<th width="80">核单情况</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="salary"  varStatus="status1" items="${list}">
			<tr target="sid_salary" rel="${salary.salary_month }___${salary.dept_id }">
				<td>
					<c:if test="${salary.dept_salary_number > salary.verify_number }">
						<c:if test="${ 0 == salary.verify_number }">							
							<input name="ids" value="${salary.salary_month }___${salary.dept_id }" type="checkbox" />
						</c:if>
					</c:if>				
				</td>
				<td>${salary.dept_name }</td>
				<td>${salary.salary_month }</td>
				<td>${salary.dept_salary_number }</td>
				<td>${salary.build_username }</td>
				<td><fmt:formatDate value="${salary.build_datetime }" pattern="yyyy-MM-dd" /></td>
				<td>
					<c:if test="${salary.dept_salary_number == salary.verify_number }"><font color="red">已核单</font></c:if>
					<c:if test="${salary.dept_salary_number > salary.verify_number }">
						<c:if test="${ 0 == salary.verify_number }">
							未核单
						</c:if>
						<c:if test="${ 0 != salary.verify_number }">
							<font color="red">部分核单</font>	
						</c:if>
					</c:if>
				</td>
				
				<td>
					<c:if test="${salary.need_approve!=null && salary.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>					
				
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>
	
</div>