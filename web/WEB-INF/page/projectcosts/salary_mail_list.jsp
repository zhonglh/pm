<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<form id="pagerForm" method="post" action="${webroot }/SalaryMailAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/SalaryMailAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${salary1.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${salary1.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			</li>
		
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>


			
			
			
			<li>
				<label>发送状态：</label>			
				<select name="send_status" id="send_status">
					<option value="0" <c:if test="${'0' == param.send_status }">selected</c:if>>未发送</option>
					<option value="1" <c:if test="${'1' == param.send_status }">selected</c:if>>已发送</option>
					<option value="2" <c:if test="${'2' == param.send_status }">selected</c:if>>正在发送</option>
					<option value="3" <c:if test="${'3' == param.send_status }">selected</c:if>>已发送(成功)</option>
					<option value="4" <c:if test="${'4' == param.send_status }">selected</c:if>>已发送(失败)</option>
				</select>
			</li>

		</ul>
		
		
		<ul class="searchContent">
		
			<li>
				<label>工资月份：</label>
				<input type="text" class="digits date month"  autocomplete="off" size="7"   maxlength="6" minlength="6"  format="yyyyMM" name="salary_month1" value="${param.salary_month1}"/>
				<input type="text" class="digits date month"  autocomplete="off" size="7"   maxlength="6" minlength="6"  format="yyyyMM" name="salary_month2" value="${param.salary_month2}"/>
			</li>

			<li>
				<label>姓名：</label>
				<input type="text" class="text"name="staff_name" value="${param.staff_name}"/>
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
		
				<li><a title="确实要发送?" target="selectedTodo" rel="ids" href="${webroot}/SalaryMailAction.do?method=sendSalaryMail" class="edit"><span>发送邮件</span></a></li> 
		
				<li><a class="icon" href="${webroot}/SalaryMailAction.do?method=toView&salary_id={salary_id}" mask="true" width="850" height="320"  rel="view_salary_mail"  target="dialog" warn="请选择一条数据"><span>发送明细</span></a></li>

				
				<li><a title="确实要取消发送吗?" target="ajaxTodo"  href="${webroot}/SalaryMailAction.do?method=cancelSend" class="delete"><span>取消发送</span></a></li> 
				
				<li><a class="icon" href="${webroot}/MailSenderAction.do?method=list"   rel="rel_100"  target="navTab" ><span>发送箱管理</span></a></li>
		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">项目名称</th>
				<th width="150">项目编号</th>
				<th width="80">工资月份</th>
				<th width="140">姓名</th>
				<th width="200">邮箱</th>
				<th width="100">基本工资</th>
				<th width="100">岗位工资</th>
				<th width="100">绩效津贴</th>
				<th width="100">应发工资</th>
				<th width="100">实发工资</th>
				<th width="100">发送情况</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="salary"  varStatus="status1" items="${list}">
			<tr target="salary_id" rel="${salary.salary_id }">
				<td>						
					<input name="ids" value="${salary.salary_id }" type="checkbox" /> 
							
				</td>
				<td>${salary.project_name }</td>
				<td>${salary.project_no }</td>
				<td>${salary.salary_month }</td>
				<td>${salary.staff_name }</td>
				<td>${salary.email }</td>
				<td>${salary.basic_salary }</td>
				<td>${salary.post_salary }</td>
				<td>${salary.performance_allowances }</td>
				<td>${salary.should_salary }</td>
				<td>${salary.actual_salary }</td>
				<td>
					
						<c:if test="${ 1 == salary.send_status }">
							已发送(<c:if test="${ 0 == salary.detail_status }"><font color="red">失败</font></c:if><c:if test="${ 1 == salary.detail_status }">成功</c:if>)
						</c:if>
						
						<c:if test="${ 2 == salary.send_status }">
							正在发送
						</c:if>
					
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
	
</div>