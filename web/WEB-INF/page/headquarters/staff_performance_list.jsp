<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/StaffPerformanceAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/StaffPerformanceAction.do?method=list" method="post">
	<div class="searchBar">
		
		<ul class="searchContent">
			
			<li>
				<label>员工工号：</label>
				<input type="text" name="staff_no" value="${param.staff_no }"/>
			</li>
			<li>
				<label>员工名称：</label>
				<input type="text" name="staff_name" value="${param.staff_name }"/>
			</li>
	
			

			<li>
				<label>月份：</label>
				<input type="text" class="digits date month" maxlength="6" minlength="6" format="yyyyMM" size="7" name="use_month1" value="${param.the_month1}"/>
				<input type="text" class="digits date month" maxlength="6" minlength="6" format="yyyyMM" size="7" name="use_month2" value="${param.the_month2}"/>
			</li>


			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			
		</ul>
			
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/StaffPerformanceAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_staff_performance"  target="dialog"><span>添加员工绩效</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/StaffPerformanceAction.do?method=deleteInsurance" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/StaffPerformanceAction.do?method=toEdit&id={sid}" mask="true" width="900"  height="400" rel="update_staff_performance" target="dialog" warn="请选择一条数据"><span>修改员工绩效</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/StaffPerformanceAction.do?method=toView&id={sid}" mask="true" width="900"  height="480" rel="view_staff_performance" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/StaffPerformanceAction.do?method=batchVerifyInsurance" class="delete"><span>批量核单</span></a></li> 
			</c:if>		
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/StaffPerformanceAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/StaffPerformanceAction.do?method=toExcel" mask="true" width="550" height="480" rel="add_staff_performance" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/StaffPerformanceAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	<table class="table" width="1400" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="60">月份</th>
				<th width="80">部门</th>
				<th width="80">工号</th>
				<th width="80">姓名</th>		
				<th width="70">绩效工资</th>
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="staffPerformance"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${staffPerformance.id }">
				<td>
					<c:if test="${staffPerformance.verify_userid==null || staffPerformance.verify_userid=='' }">
						<input name="ids" value="${staffPerformance.id }" type="checkbox" />
					</c:if>
				</td>
				<td>${staffPerformance.the_month }</td>
				<td>${staffPerformance.staff_no }</td>
				<td>${staffPerformance.staff_name }</td>
				<td align="right"><b><fmt:formatNumber value="${staff_performance.endowment_staff_performance_bypersonal }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${staff_performance.build_username }</td>
				<td>${staff_performance.verify_username }</td>
				<td>
					<c:if test="${staff_performance.need_approve!=null && staff_performance.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	

	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount_any.jsp"></c:import>
</div>	
