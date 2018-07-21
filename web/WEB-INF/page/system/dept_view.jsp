<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/DeptAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			
				<input name="dept_id"  type="hidden" size="30"    value="${dept1.dept_id }" />


			<p>
				<label>部门名称：</label>
				${dept1.dept_name }
			</p>
			

			<p>
				<label>部门编号：</label>
				${dept1.dept_no }
			</p>	
			
			<p>
				<label>部门领导：</label>
				${dept1.lead_name }
			</p>	
			
			<p>
				<label>是否统计：</label>
				<spring:message code="boolean.${dept1.statisticsed }" />
				
			</p>	
								
					
			<p>
				<label>描述：</label>
				${dept1.description }
			</p>	
			
			
			<div class="divider"></div>
		
			
				<h3 class="contentTitle">年度目标</h3>
				<table id="dept_target" class="list nowrap" width="100%">
						<thead>
							<tr>
								<th width="100" type="number" postField="years" defaultVal="${currYears }"  name="items[#index#].years" maxlength="4" mixlength="4"  size="12" readonly="readonly" fieldClass="required">年度</th>
								<th width="100" type="number" postField="profit_objective"  name="items[#index#].profit_objective" defaultVal="0"  maxlength="13" size="12" fieldClass="number required">利润目标</th>

							</tr>												
						</thead>
						<tbody>								
							<c:forEach var="departObjective"  varStatus="status1" items="${departObjectives}">
							<tr>
								<td>

									${departObjective.years }
								</td>
								
								<td>
							
										${departObjective.profit_objective }
								</td>
							</tr>
							</c:forEach>								
						</tbody>
					</table>

					
			
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
