<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="monthly_statement_first" name="monthly_statement_first" action="${webroot }/MonthlyStatementAction.do?method=to" class="pageForm required-validate" onsubmit="return dialogCheckJump(this,'${webroot }/MonthlyStatementAction.do?method=isExist', configMsgFunc);">
		<div class="pageFormContent" layoutH="56">	
			<input type="hidden" name="monthly_statement_id" value="${monthlyStatement1.monthly_statement_id }" />
			<p>
				<label>结算月份：</label>
				<input name="statement_month" class="digits required month" minlength="6" maxlength="6" type="text" size="30" value="${monthlyStatement1.statement_month }" />
			</p>
			
			<p>
				<label>项目名称：</label>
					<input type="hidden"   name="project.project_id" value="${monthlyStatement1.project_id }"/>
					<input type="hidden"   name="project.project_no" value="${monthlyStatement1.project_no }"/>
					<input type="hidden"   name="project.project_type" value="${monthlyStatement1.project_type }"/>
					<input name="project.project_name" class="text required" type="text" size="28"  value	="${monthlyStatement1.project_name }" readonly="readonly" />
					<c:if test="${empty monthlyStatement1.monthly_statement_id }">				
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup&origin_type=MonthlyStatement" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
					</c:if>
			</p>

			<p>
				<label>结算类型：</label>
				<select id="statement_type" name="statement_type" id ="statement_type" class="required" onchange="changeMonthlyStatementType(this)">
					<c:if test="${monthlyStatement1.monthly_statement_id !=null }">					
						<option value="${monthlyStatement1.project_type }" <c:if test="${monthlyStatement1.statement_type == monthlyStatement1.monthly_statement_id }">selected</c:if> ><spring:message code="statement.type.${monthlyStatement1.project_type }"/></option>
						<c:if test="${monthlyStatement1.project_type != '3' }">
							<option value="3" <c:if test="${monthlyStatement1.statement_type == '3' }">selected</c:if> ><spring:message code="statement.type.3"/></option>
						</c:if> 
						<option value="4" <c:if test="${monthlyStatement1.statement_type == '4' }">selected</c:if> ><spring:message code="statement.type.4"/></option> 
					</c:if>
				</select>								
					
			</p>
			
			<p id="jsje" <c:if test="${monthlyStatement1.statement_type!=null && monthlyStatement1.statement_type =='2' }">style="display:none"</c:if>>
				<label>结算金额：</label>
				<input id="amount" name="amount" class="number required" type="text" size="28"  maxlength="15" value="<fmt:formatNumber value="${monthlyStatement1.amount }" type="number" pattern="####0.00#"/>"/>
				
				<!-- 用于保存临时的结算金额 -->
				<input id="amount_old" name="amount_old" type="hidden" size="30"   value="<fmt:formatNumber value="${monthlyStatement1.amount }" type="number" pattern="####0.00#"/>"/>
			</p>
			
			
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
				<li id="thenext"  <c:if test="${monthlyStatement1.statement_type == null || monthlyStatement1.statement_type != '2' }">style="display:none"</c:if>>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">下一步</button></div></div>
				</li>
				<li id="thesave" <c:if test="${monthlyStatement1.statement_type!=null && monthlyStatement1.statement_type =='2' }">style="display:none"</c:if>> 
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li> 
			</ul>
		</div>
	</form>
</div>


