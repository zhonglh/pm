<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="add_group_first" action="${webroot }/OtherSalaryGroupAction.do?method=toEditNext" class="pageForm required-validate" onsubmit="return dialogCheckJump(this,'${webroot }/OtherSalaryGroupAction.do?method=isExist', configMsgFunc);">
		<div class="pageFormContent" layoutH="56">	

			<p>
				<label>工资月份：</label>
				<input name="salary_month" class="digits date required month" readonly format="yyyyMM" minlength="6" maxlength="6" type="text" size="30" value="${salary_month }" />
			</p>




			<p>
				<label>项目名称：</label>


				<input name="dept.dept_id" class="text" type="hidden" size="2"   />
				<input name="dept.dept_name" class="text" type="text" size="30"   readonly="readonly" lookupPk="dept_id"
					   suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>

			</p>
			
			
			<div class="divider"></div>


		</div>
		<div class="formBar">
			<ul>	
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">下一步</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
