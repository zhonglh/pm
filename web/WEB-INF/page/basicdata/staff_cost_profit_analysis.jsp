<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/StaffCostAction.do?method=profitAnalysis">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/StaffCostAction.do?method=profitAnalysis" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		

			
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name }"/>	
			</li>
			
			<li>
				<label>招聘专员名称：</label>
				<input type="text" name="recruiter_name" value="${param.recruiter_name }"/>
			</li>
			
			<li>				
				<label>外协人员：</label>
				<select name="outsource_staff" style="width:153px">
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
				<select name="delete_flag" style="width:153px">
					<option value="">全部</option>
					<option value="0" <c:if test="${staffCost.delete_flag == '0' }">selected</c:if>>在职</option>
					<option value="1" <c:if test="${staffCost.delete_flag == '1' }">selected</c:if>>离职</option>					
				</select>		
			</li>	
		</ul>
		<ul class="searchContent">		

			<li>
				<label>时间段：</label>
				<input name="month1"  class="date" autocomplete="off"  type="text" size="5"   maxlength="6" minlength="6"  format="yyyyMM" value="${param.month1 }" readonly="readonly" />
				<input name="month2"  class="date" autocomplete="off"  type="text" size="5"   maxlength="6" minlength="6"  format="yyyyMM" value="${param.month2 }" readonly="readonly" />
			</li>
			
			<li>
				<label>排序：</label>
				<select name="orderby" style="width:153px">
					<option value="build_datetime" <c:if test="${param.orderby == 'build_datetime' }">selected</c:if>>入职时间</option>	
					<option value="technical_income desc" <c:if test="${param.orderby == 'technical_income desc' }">selected</c:if>>总收入</option>	
					<option value="technical_cost desc" <c:if test="${param.orderby == 'technical_cost desc' }">selected</c:if>>总成本</option>	
					<option value="technical_profit desc" <c:if test="${param.orderby == 'technical_profit desc' }">selected</c:if>>总利润</option>	
					<option value="profit desc" <c:if test="${param.orderby == 'profit desc' }">selected</c:if>>平均利润</option>			
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
			<li><a class="icon" href="${webroot}/StaffCostAction.do?method=exportByAnalysis" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="1300px" layoutH="162">
		<thead>
			<tr>
				<th width="80">员工工号</th>
				<th width="80">员工名称</th>		
				<th width="160">身份证号码</th>								
				<th width="140">联系电话</th>
				<th width="100">客户最新报价</th>	
				<th width="120">入职时间</th>
				<th width="120">离职时间</th>					
				<th width="120">招聘专员名称</th>	
				<th width="120">是否外协人员</th>
				
				
				<th width="100">总收入</th>
				<th width="100">总成本</th>
				<th width="100">总利润</th>
				<th width="100">平均月利润</th>


				<th width="100">工作天数</th>
				<th width="100">请假天数</th>
                <th width="100">待岗天数</th>
				<th width="100">周末加班天数</th>
				<th width="100">平时加班小时</th>
				
				
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="staffcost"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${staffcost.staff_id }"  >
				
				<td>
					<a class="add" href="${webroot}/StaffCostAction.do?method=toView&staff_id=${staffcost.staff_id }"  
						mask="true" width="850" height="520" rel="detail" title="明细"  target="dialog">
							<u>${staffcost.staff_no }</u>
					</a>					
				</td>
				<td>${staffcost.staff_name }</td>						
				<td>${staffcost.identity_card_number }</td>
				<td>${staffcost.tel }</td>	
				<td align="right"><fmt:formatNumber value="${staffcost.qustomerquotes }" type="currency" pattern="###,###,##0.00"/></td>
				
				<td><fmt:formatDate value="${staffcost.join_datetime }" pattern="yyyy-MM-dd"/></td>
				<td><font color="red"><fmt:formatDate value="${staffcost.leave_job_datetime }" pattern="yyyy-MM-dd"/></font></td>
				
				<td>${staffcost.recruiter_name }</td>
				<td><spring:message code="boolean.${staffcost.outsource_staff }"/></td>		
				
				
				
				<td align="right">
					<a class="add" href="${webroot}/StaffCostAction.do?method=queryAnalysisDetails&x=income&month1=${param.month1 }&month2=${param.month2 }&staff_id=${staffcost.staff_id }"  
							mask="true" width="850" height="520" rel="detail" title="明细"  target="dialog">
						<u><fmt:formatNumber value="${staffcost.technical_income }" type="currency" pattern="###,###,##0.00"/></u>
					</a>
				</td>
				<td align="right">
					<a class="add" href="${webroot}/StaffCostAction.do?method=queryAnalysisDetails&x=cost&month1=${param.month1 }&month2=${param.month2 }&staff_id=${staffcost.staff_id }"  
							mask="true" width="850" height="520" rel="detail" title="明细"  target="dialog">
						<u><fmt:formatNumber value="${staffcost.technical_cost }" type="currency" pattern="###,###,##0.00"/></u>
					</a>
				</td>
				<td align="right"><b><fmt:formatNumber value="${staffcost.technical_profit }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><fmt:formatNumber value="${staffcost.profit }" type="currency" pattern="###,###,##0.00"/></td>


				<td align="right"><b><fmt:formatNumber value="${staffcost.work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><fmt:formatNumber value="${staffcost.holidays }" type="currency" pattern="###,###,##0.00"/></td>
                <td align="right"><fmt:formatNumber value="${staffcost.waiting_post_days }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><b><fmt:formatNumber value="${staffcost.weekend_overtime_days }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><fmt:formatNumber value="${staffcost.every_overtime }" type="currency" pattern="###,###,##0.00"/></td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>

</div>
