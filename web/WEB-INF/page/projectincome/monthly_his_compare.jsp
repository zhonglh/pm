<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent" id="CompareVersion">

	<input type="hidden" id="items" value="<c:forEach var="monthlyStatementDiffs"  varStatus="status1" items="${monthlyStatementDiffs}">${monthlyStatementDiffs},</c:forEach>" />
	<input type="hidden" id="subitems" value="<c:forEach var="detail"  varStatus="status1" items="${monthlyStatementDetailsDiffs}">${detail},</c:forEach>" />




	<div id="jbsxBox1" class="unitBox " style="float:left; display:block; border:solid 1px #111CCC; overflow:auto; width:50%;">
		<div class="pageFormContent listenerScroll" layoutH="46">	
			<p>
				<label>版本时间：</label>
				<fmt:formatDate value="${monthlyStatement1.his_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>

			<div class="divider"></div>			
		
			<p id="statement_month" <c:if test="${fn:contains(diffs, 'statement_month')}"> class="diff" </c:if>>
				<label>结算月份：</label>
				${monthlyStatement1.statement_month }
			</p>
			
			<p  id="project_name" <c:if test="${fn:contains(diffs, 'project_name')}"> class="diff" </c:if>>
				<label>项目名称：</label>
					${monthlyStatement1.project_name }	
			</p>

			<p  id="statement_type" <c:if test="${fn:contains(diffs, 'statement_type')}"> class="diff" </c:if>>
				<label>结算类型：</label>
				<spring:message code="statement.type.${monthlyStatement1.statement_type }"/>						
					
			</p>
			
			<p id="jsje" <c:if test="${fn:contains(diffs, 'jsje')}"> class="diff" </c:if> <c:if test="${monthlyStatement1.statement_type!=null && monthlyStatement1.statement_type =='2' }">style="display:none"</c:if>>
				<label>结算金额：</label>
				<fmt:formatNumber value="${monthlyStatement1.amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			
			<c:if test="${monthlyStatement1.statement_type!=null && monthlyStatement1.statement_type =='2' }">
			<div class="divider"></div>
			<table class="list nowrap" width="800" >
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
					<c:forEach var="monthlyStatementDetail"  varStatus="status1" items="${list1}">		
					
						<c:set var="technical_cost" value=",${numbers[status1.index]}___technical_cost," />
						<c:set var="should_work_days" value=",${numbers[status1.index]}___should_work_days," />
						<c:set var="work_days" value=",${numbers[status1.index]}___work_days," />
						<c:set var="daily_overtime" value=",${numbers[status1.index]}___daily_overtime," />
						<c:set var="weekend_overtime" value=",${numbers[status1.index]}___weekend_overtime," />
						<c:set var="overtime_cost" value=",${numbers[status1.index]}___overtime_cost," />
						<c:set var="business_trip_days" value=",${numbers[status1.index]}___business_trip_days," />
						<c:set var="business_trip_cost" value=",${numbers[status1.index]}___business_trip_cost," />
						<c:set var="other_cost" value=",${numbers[status1.index]}___other_cost," />
						<c:set var="man_month" value=",${numbers[status1.index]}___man_month," />
						<c:set var="total_cost" value=",${numbers[status1.index]}___total_cost," />
						<c:set var="client_dept" value=",${numbers[status1.index]}___client_dept," />
						<c:set var="description" value=",${numbers[status1.index]}___description," />
								
					<tr>						
						<td nowrap>${status1.index + 1 }</td>
						<td nowrap>${monthlyStatementDetail.staff_name }</td>						
						<td id="technical_cost" <c:if test="${fn:contains(detaildiffs, technical_cost)}"> class="diff" </c:if>  nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.technical_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="should_work_days" <c:if test="${fn:contains(detaildiffs, should_work_days)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.should_work_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="work_days" <c:if test="${fn:contains(detaildiffs, work_days)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.work_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="daily_overtime" <c:if test="${fn:contains(detaildiffs, daily_overtime)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.daily_overtime }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="weekend_overtime" <c:if test="${fn:contains(detaildiffs, weekend_overtime)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.weekend_overtime }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="overtime_cost" <c:if test="${fn:contains(detaildiffs, overtime_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.overtime_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="business_trip_days" <c:if test="${fn:contains(detaildiffs, business_trip_days)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.business_trip_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="business_trip_cost" <c:if test="${fn:contains(detaildiffs, business_trip_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.business_trip_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="other_cost" <c:if test="${fn:contains(detaildiffs, other_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.other_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="man_month" <c:if test="${fn:contains(detaildiffs, man_month)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.man_month }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="total_cost" <c:if test="${fn:contains(detaildiffs, total_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.total_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="client_dept" <c:if test="${fn:contains(detaildiffs, client_dept)}"> class="diff" </c:if>>${monthlyStatementDetail.client_dept }</td>
						<td id="description" <c:if test="${fn:contains(detaildiffs, description)}"> class="diff" </c:if>>${monthlyStatementDetail.description }</td>
					</tr>
					</c:forEach>
				
				
					<c:if test="${fn:length(list1)>0 }">
					
						<c:set var="list1size" value="${fn:length(list1)}" />
						<c:set var="technical_cost" value="${list1size}___technical_cost" />
						<c:set var="should_work_days" value="${list1size}___should_work_days" />
						<c:set var="work_days" value="${list1size}___work_days" />
						<c:set var="daily_overtime" value="${list1size}___daily_overtime" />
						<c:set var="weekend_overtime" value="${list1size}___weekend_overtime" />
						<c:set var="overtime_cost" value="${list1size}___overtime_cost" />
						<c:set var="business_trip_days" value="${list1size}___business_trip_days" />
						<c:set var="business_trip_cost" value="${list1size}___business_trip_cost" />
						<c:set var="other_cost" value="${list1size}___other_cost" />
						<c:set var="man_month" value="${list1size}___man_month" />
					
					<tr>
						<td><b>合计</b></td>				
						<td></td>				
						<td id="technical_cost" <c:if test="${fn:contains(detaildiffs, technical_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.technical_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="should_work_days" <c:if test="${fn:contains(detaildiffs, should_work_days)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.should_work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="work_days" <c:if test="${fn:contains(detaildiffs, work_days)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="daily_overtime" <c:if test="${fn:contains(detaildiffs, daily_overtime)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.daily_overtime }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="weekend_overtime" <c:if test="${fn:contains(detaildiffs, weekend_overtime)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.weekend_overtime }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="overtime_cost" <c:if test="${fn:contains(detaildiffs, overtime_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.overtime_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="business_trip_days" <c:if test="${fn:contains(detaildiffs, business_trip_days)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.business_trip_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="business_trip_cost" <c:if test="${fn:contains(detaildiffs, business_trip_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.business_trip_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="other_cost" <c:if test="${fn:contains(detaildiffs, other_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.other_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="man_month" <c:if test="${fn:contains(detaildiffs, man_month)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail1.man_month }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="amount" <c:if test="${fn:contains(diffs, 'amount')}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${monthlyStatement1.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
					</tr>
					</c:if>
					
					</tbody>		
			</table>			
			</c:if>				
			<br />		
		</div>			
	</div>
	
	
			
			
			
			
	
	<div id="jbsxBox2" class="unitBox " style="margin-left:50%;border:solid 1px #111CCC;">
		<div class="pageFormContent listenerScroll" layoutH="46">	
			<p>
				<label>版本时间：</label>
				<fmt:formatDate value="${monthlyStatement2.his_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>

			<div class="divider"></div>			
		
			<p id="statement_month" <c:if test="${fn:contains(diffs, 'statement_month')}"> class="diff" </c:if>>
				<label>结算月份：</label>
				${monthlyStatement2.statement_month }
			</p>
			
			<p  id="project_name" <c:if test="${fn:contains(diffs, 'project_name')}"> class="diff" </c:if>>
				<label>项目名称：</label>
					${monthlyStatement2.project_name }	
			</p>

			<p  id="statement_type" <c:if test="${fn:contains(diffs, 'statement_type')}"> class="diff" </c:if>>
				<label>结算类型：</label>
				<spring:message code="statement.type.${monthlyStatement2.statement_type }"/>						
					
			</p>
			
			<p id="jsje" <c:if test="${fn:contains(diffs, 'jsje')}"> class="diff" </c:if> <c:if test="${monthlyStatement2.statement_type!=null && monthlyStatement2.statement_type =='2' }">style="display:none"</c:if>>
				<label>结算金额：</label>
				<fmt:formatNumber value="${monthlyStatement2.amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			
			<c:if test="${monthlyStatement2.statement_type!=null && monthlyStatement2.statement_type =='2' }">
			<div class="divider"></div>
			<table class="list nowrap" width="800" >
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
					<c:forEach var="monthlyStatementDetail"  varStatus="status1" items="${list2}">	
					
					<c:set var="technical_cost" value=",${numbers[status1.index]}___technical_cost," />
						<c:set var="should_work_days" value=",${numbers[status1.index]}___should_work_days," />
						<c:set var="work_days" value=",${numbers[status1.index]}___work_days," />
						<c:set var="daily_overtime" value=",${numbers[status1.index]}___daily_overtime," />
						<c:set var="weekend_overtime" value=",${numbers[status1.index]}___weekend_overtime," />
						<c:set var="overtime_cost" value=",${numbers[status1.index]}___overtime_cost," />
						<c:set var="business_trip_days" value=",${numbers[status1.index]}___business_trip_days," />
						<c:set var="business_trip_cost" value=",${numbers[status1.index]}___business_trip_cost," />
						<c:set var="other_cost" value=",${numbers[status1.index]}___other_cost," />
						<c:set var="man_month" value=",${numbers[status1.index]}___man_month," />
						<c:set var="total_cost" value=",${numbers[status1.index]}___total_cost," />
						<c:set var="client_dept" value=",${numbers[status1.index]}___client_dept," />
						<c:set var="description" value=",${numbers[status1.index]}___description," />
									
					<tr>
												
						<td nowrap>${status1.index + 1 }</td>
						<td nowrap>${monthlyStatementDetail.staff_name }</td>						
						<td id="technical_cost" <c:if test="${fn:contains(detaildiffs, technical_cost)}"> class="diff" </c:if>  nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.technical_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="should_work_days" <c:if test="${fn:contains(detaildiffs, should_work_days)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.should_work_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="work_days" <c:if test="${fn:contains(detaildiffs, work_days)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.work_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="daily_overtime" <c:if test="${fn:contains(detaildiffs, daily_overtime)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.daily_overtime }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="weekend_overtime" <c:if test="${fn:contains(detaildiffs, weekend_overtime)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.weekend_overtime }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="overtime_cost" <c:if test="${fn:contains(detaildiffs, overtime_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.overtime_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="business_trip_days" <c:if test="${fn:contains(detaildiffs, business_trip_days)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.business_trip_days }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="business_trip_cost" <c:if test="${fn:contains(detaildiffs, business_trip_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.business_trip_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="other_cost" <c:if test="${fn:contains(detaildiffs, other_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.other_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="man_month" <c:if test="${fn:contains(detaildiffs, man_month)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.man_month }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="total_cost" <c:if test="${fn:contains(detaildiffs, total_cost)}"> class="diff" </c:if> nowrap align="right"><fmt:formatNumber value="${monthlyStatementDetail.total_cost }" type="currency" pattern="###,###,##0.00"/></td>
						<td id="client_dept" <c:if test="${fn:contains(detaildiffs, client_dept)}"> class="diff" </c:if>>${monthlyStatementDetail.client_dept }</td>
						<td id="description" <c:if test="${fn:contains(detaildiffs, description)}"> class="diff" </c:if>>${monthlyStatementDetail.description }</td>
					</tr>
					</c:forEach>
				
				
					<c:if test="${fn:length(list2)>0 }">
					
						<c:set var="list2size" value="${fn:length(list2)}" />
						<c:set var="technical_cost" value="${list2size}___technical_cost" />
						<c:set var="should_work_days" value="${list2size}___should_work_days" />
						<c:set var="work_days" value="${list2size}___work_days" />
						<c:set var="daily_overtime" value="${list2size}___daily_overtime" />
						<c:set var="weekend_overtime" value="${list2size}___weekend_overtime" />
						<c:set var="overtime_cost" value="${list2size}___overtime_cost" />
						<c:set var="business_trip_days" value="${list2size}___business_trip_days" />
						<c:set var="business_trip_cost" value="${list2size}___business_trip_cost" />
						<c:set var="other_cost" value="${list2size}___other_cost" />
						<c:set var="man_month" value="${list2size}___man_month" />
					
					<tr>
						<td><b>合计</b></td>				
						<td></td>				
						<td id="technical_cost" <c:if test="${fn:contains(detaildiffs, technical_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.technical_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="should_work_days" <c:if test="${fn:contains(detaildiffs, should_work_days)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.should_work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="work_days" <c:if test="${fn:contains(detaildiffs, work_days)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.work_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="daily_overtime" <c:if test="${fn:contains(detaildiffs, daily_overtime)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.daily_overtime }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="weekend_overtime" <c:if test="${fn:contains(detaildiffs, weekend_overtime)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.weekend_overtime }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="overtime_cost" <c:if test="${fn:contains(detaildiffs, overtime_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.overtime_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="business_trip_days" <c:if test="${fn:contains(detaildiffs, business_trip_days)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.business_trip_days }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="business_trip_cost" <c:if test="${fn:contains(detaildiffs, business_trip_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.business_trip_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="other_cost" <c:if test="${fn:contains(detaildiffs, other_cost)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.other_cost }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="man_month" <c:if test="${fn:contains(detaildiffs, man_month)}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${totalMonthlyStatementDetail2.man_month }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td id="amount" <c:if test="${fn:contains(diffs, 'amount')}"> class="diff" </c:if> align="right"><b><fmt:formatNumber value="${monthlyStatement2.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
					</tr>
					</c:if>
					
					</tbody>		
			</table>			
			</c:if>				
			<br />		
		</div>			
	</div>
		
	
</div>


