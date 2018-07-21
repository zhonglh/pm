<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<p>
				<label>结算月份：</label>
				${monthlyStatement1.statement_month }
			</p>
			
			<p>
				<label>项目名称：</label>
					${monthlyStatement1.project_name }	
			</p>

			<p>
				<label>结算类型：</label>
				<spring:message code="statement.type.${monthlyStatement1.statement_type }"/>						
					
			</p>
			
			<p id="jsje" <c:if test="${monthlyStatement1.statement_type!=null && monthlyStatement1.statement_type =='2' }">style="display:none"</c:if>>
				<label>结算金额：</label>
				<fmt:formatNumber value="${monthlyStatement1.amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			
			<c:if test="${monthlyStatement1.statement_type!=null && monthlyStatement1.statement_type =='2' }">
			<div class="divider"></div>
			<table class="list nowrap" width="100%"> 
				<thead>
					<tr>
						<th width="40" nowrap>序号</th>						
						<th width="80" nowrap>人员名称</th>
						<th width="140" >服务费用(人月)</th>	
						<th width="100" >应出勤天数</th>	
						<th width="100" >实出勤天数</th>	
						<th width="80" >日常加班</th>	
						<th width="80" >周末加班</th>	
						<th width="80" >加班费用</th>
						<th width="80" >出差天数</th>	
						<th width="80" >出差费用</th>	
						<th width="80" >其它费用</th>
						<th width="40" >人月</th>
						<th width="80" >小计费用</th>
						<th width="130" >客户所在部门</th>
						<th width="200" >说明</th>
					</tr>
				</thead>	
				
				<tbody>					
					<c:forEach var="monthlyStatementDetail"  varStatus="status1" items="${list}">
					<tr>
						<td nowrap>${status1.index + 1 }</td>
						<td nowrap>${monthlyStatementDetail.staff_name }</td>						
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.technical_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.should_work_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.work_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.daily_overtime }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.weekend_overtime }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.overtime_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.business_trip_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.business_trip_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.other_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.man_month }" type="currency" pattern="###,###,##0.00"/></td>
						<td nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.total_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td >${monthlyStatementDetail.client_dept }</td>
						<td >${monthlyStatementDetail.description }</td>
					</tr>
					</c:forEach>
				</tbody>		
				
					<c:if test="${fn:length(list)>0 }">
					<tr id="monthly_statement_sumhr">
						<td><b>合计</b></td>				
						<td></td>				
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.technical_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.should_work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.daily_overtime }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.weekend_overtime }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.overtime_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.business_trip_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.business_trip_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.other_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail.man_month }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td align="right"><b><fmt:formatNumber value="${monthlyStatement1.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
					</tr>
					</c:if>
			</table>			
			</c:if>					
			
			<br />
			
			
			
	
		
		</div>			

	
	
</div>


