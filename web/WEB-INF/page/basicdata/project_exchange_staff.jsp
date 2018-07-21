<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	
	<form method="post" action="${webroot }/ProjectAction.do?method=deleteProjectStaff" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneDeleteItem)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="project_staff_id" value="${projectStaff.project_staff_id }"/>
			<input type="hidden" name="rownum" value="${param.rownum }"/>

			<div class="unit">
				<label>离场日期：</label>
				<input name="leave_project_datetime" class="date required" type="text" size="30" value="<fmt:formatDate value="${projectStaff.leave_project_datetime }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</div>
				

			<div class="unit">
				<label>说明：</label>
				<input name="description" class="text" type="text" size="30" value="${projectStaff.description }" />
			</div>
				
								
			<div class="divider"></div>
			<br></br>
				
			
				
			<h3 class="contentTitle"> <a onclick="javascript:toggle('newproject','selectProject','project_name');return false;" href="void();" >调到新项目</a></h3>
			<div id="newproject" style="display:none">	
				<div style="display:none">
					<input type="checkbox" id="selectProject" name="selectProject" value="1" />
				</div>	
				<div class="unit">
					<label>项目：</label>
					<input type="hidden" size="2"  name="project.project_id" value=""/>
					<input id="project_name" name="project.project_name" class="text" type="text" size="30"  value="" readonly="readonly" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>				
				</div>
				<div class="unit">
					<label>入场日期：</label>
					<input name="join_project_datetime" class="date" type="text" size="30" value="<fmt:formatDate value="${projectStaff.leave_project_datetime }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
				</div>
				<div class="unit">
					<label>技术费用：</label>
					<input name="technical_cost" class="number" type="text" size="30"  maxlength="15" value="<fmt:formatNumber value="${projectStaff.technical_cost }"  pattern="####0.00#"/>"/>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
