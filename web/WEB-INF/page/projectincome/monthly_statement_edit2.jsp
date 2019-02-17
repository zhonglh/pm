<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="monthly_statement_next" action="${webroot }/MonthlyStatementAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		

			<input type="hidden" name="monthly_statement_id" value="${monthlyStatement1.monthly_statement_id }" />
			<input type="hidden" name="statement_type" value="${monthlyStatement1.statement_type }" />


			<p>
				<label>结算月份：</label>
				
				<label>${monthlyStatement1.statement_month } <input type="hidden" name="statement_month" value="${monthlyStatement1.statement_month }" /></label>
			</p>	
			
			<p>
				<label>项目名称：</label>
				<input type="hidden"   name="project_id" value="${monthlyStatement1.project_id }"/>
				<input name="project_name" type="hidden" value="${monthlyStatement1.project_name }"  />
				<input name="project_no" type="hidden" value="${monthlyStatement1.project_no }"  />
				<label>${monthlyStatement1.project_name }</label>
			</p>
			
						
			
			<div class="divider"></div>


			<%--<div class="buttonActive"><div class="buttonContent">
				<button type="button" class="buttonActive" curr="${fn:length(list)}" onclick="addMSD(this , '${webroot }')">新建</button>
			</div></div>--%>


			
			
			
			<table addButton="增加" webroot="${webroot}" class="list nowrap  <c:if test="${monthlyStatement1.monthly_statement_id == null || monthlyStatement1.monthly_statement_id == ''}">monthlyStatementComputer</c:if><c:if test="${monthlyStatement1.monthly_statement_id != null && monthlyStatement1.monthly_statement_id != ''}">monthlyStatementUpdateComputer</c:if>" width="1500" >
				<thead>
					<tr>
						<th width="40" nowrap>序号</th>						
						<th width="120" nowrap>人员名称</th>
						<th width="160" >服务费用(人月)</th>	
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
						<th width="120" >客户所在部门</th>
						<th width="200" >说明</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="monthlyStatementDetail"  varStatus="status1" items="${list}">
					<tr>
						<td nowrap>${status1.index + 1 }</td>
						<td nowrap>
							<c:if test="${monthlyStatement1.monthly_statement_id == null || monthlyStatement1.monthly_statement_id =='' }">
							<input type="hidden" size="2" id="id" name="id" value="${status1.index + 1 }"/>
							</c:if>							
							<c:if test="${monthlyStatement1.monthly_statement_id != null && monthlyStatement1.monthly_statement_id !='' }">
							<input type="hidden" size="2" id="id" name="id" value="${status1.index + 1 }"/>
							</c:if>
							<input type="hidden"   name="staff_id${status1.index + 1 }" value="${monthlyStatementDetail.staff_id }"/>
							<input type="hidden"   name="staff_name${status1.index + 1 }" value="${monthlyStatementDetail.staff_name }"/>						
							${monthlyStatementDetail.staff_name }
							<input type="hidden"   name="monthly_statement_detail_id${status1.index + 1 }" value="${monthlyStatementDetail.monthly_statement_detail_id }"/>
						</td>
						
						<td><input type="text" size="7" maxlength="10" class="number required"  id="technical_cost" 	name="technical_cost${status1.index + 1 }" 		value="${monthlyStatementDetail.technical_cost }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="should_work_days" 	name="should_work_days${status1.index + 1 }" 	value="${monthlyStatementDetail.should_work_days }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="work_days" 			name="work_days${status1.index + 1 }" 			value="${monthlyStatementDetail.work_days }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="daily_overtime" 	name="daily_overtime${status1.index + 1 }" 		value="${monthlyStatementDetail.daily_overtime }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="weekend_overtime" 	name="weekend_overtime${status1.index + 1 }" 	value="${monthlyStatementDetail.weekend_overtime }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="overtime_cost" 		name="overtime_cost${status1.index + 1 }" 		value="${monthlyStatementDetail.overtime_cost }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="business_trip_days" name="business_trip_days${status1.index + 1 }" 	value="${monthlyStatementDetail.business_trip_days }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="business_trip_cost" name="business_trip_cost${status1.index + 1 }" 	value="${monthlyStatementDetail.business_trip_cost }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="other_cost" 		name="other_cost${status1.index + 1 }" 			value="${monthlyStatementDetail.other_cost }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="man_month" 			name="man_month${status1.index + 1 }" 			value="${monthlyStatementDetail.man_month }" /></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="total_cost" 		name="total_cost${status1.index + 1 }" 			value="${monthlyStatementDetail.total_cost }" /></td>
						<td><input type="text" size="16" maxlength="50" class="text"  id="client_dept" 		name="client_dept${status1.index + 1 }" 			value="${monthlyStatementDetail.client_dept }" /></td>
						<td><input type="text" size="38" maxlength="150" class="text"  id="description" 		name="description${status1.index + 1 }" 			value="${monthlyStatementDetail.description }" /></td>
					</tr>
					</c:forEach>
				</tbody>

				<tfoot>
					<tr id="monthly_statement_sumhr">
						<td><b>合计</b></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td><input type="text" size="7" maxlength="10" class="number required"  id="amount" 		name="amount" 			value="${monthlyStatement1.amount }" /></td>
					</tr>
				</tfoot>
			</table>
		
						
			<br/>
			<div class="divider"></div>
			<p>
				<label>制单人： 	</label> <label>${monthlyStatement1.build_username } </label>
			</p>
			<p>
				<label>制单日期： 	</label> <label><fmt:formatDate value="${monthlyStatement1.build_datetime }" pattern="yyyy-MM-dd" /> </label>
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
