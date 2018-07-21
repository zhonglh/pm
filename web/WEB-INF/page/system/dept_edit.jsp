<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/DeptAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/DeptAction.do?method=isExist', dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			
				<input name="dept_id"  type="hidden" size="30"    value="${dept1.dept_id }" />


			<p>
				<label>部门名称：</label>
				<input name="dept_name" class="text required" type="text" size="25" minlength="2" maxlength="30"   value="${dept1.dept_name }" />
			</p>
			

			<p>
				<label>部门编号：</label>
				<input name="dept_no" class="text required" type="text" size="25" minlength="2" maxlength="30"   value="${dept1.dept_no }" />
			</p>	
			
			<p>
				<label>部门领导：</label>
				<input type="hidden" size="2" name="staff.user_id" value="${dept1.lead_id }"/>
				<input name="staff.user_name" class="text" type="text" size="25"  value="${dept1.lead_name }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="staff" lookupPk="user_id" width="950">选择</a>
			</p>		
			
			
			<p>
				<label>是否统计：</label>
				<select name="statisticsed" id="statisticsed" class="required">
					<option value="1" <c:if test="${'1' == dept1.statisticsed }">selected</c:if>>是</option>
					<option value="0" <c:if test="${'0' == dept1.statisticsed }">selected</c:if>>否</option>
				</select>
				
			</p>				
					
			<p>
				<label>描述：</label>
				<input name="description" type="text" class="text" size="25" maxlength="150"  value="${dept1.description }" />
			</p>			
			

	
			<div class="divider"></div>
				
				<h3 class="contentTitle">年度目标</h3>
					<table id="dept_target" class="list nowrap itemDetail" addCount="<c:out value="${haveCurrYears?0:1 }" />" addButton="新建本年度目标" width="100%">
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
									<c:if test="${currYears == departObjective.years}">
										<input type="hidden"  id="index_dept_target" name="index_dept_target" value="${status1.index  }" />
										<input type="hidden"  id="items[${status1.index  }].id" name="items[${status1.index  }].id" value="${departObjective.id }" />
										<input type="hidden"  id="items[${status1.index  }].years" name="items[${status1.index}].years" value="${departObjective.years }" />
									</c:if>
									${departObjective.years }
								</td>
								
								<td>
									<c:if test="${currYears == departObjective.years}">
										<input tpye="text" class="number required" maxlength="13" id="items[${status1.index  }].profit_objective" name="items[${status1.index  }].profit_objective" value="${departObjective.profit_objective }"   />
									</c:if>
									
									<c:if test="${currYears != departObjective.years}">									
										${departObjective.profit_objective }
									</c:if>
								</td>
							</tr>
							</c:forEach>								
						</tbody>
					</table>
				
			<div class="divider"></div>
					
			
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
