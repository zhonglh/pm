<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="add_group_first" action="${webroot }/WorkAttendanceGroupAction.do?method=toEditNext" class="pageForm required-validate" onsubmit="return dialogCheckJump(this,'${webroot }/WorkAttendanceGroupAction.do?method=isExist', configMsgFunc);">
		<div class="pageFormContent" layoutH="56">	

			<p>
				<label>考勤月份：</label>
				<input name="attendance_month" class="digits date required month" autocomplete="off"  readonly format="yyyyMM" minlength="6" maxlength="6" type="text" size="30" value="${attendance_month}" />
			</p>
			
			<p>
				<label>项目名称：</label>
					<input type="hidden"   name="project.project_id" value=""/>
					<input name="project.project_name" class="text required" type="text" size="28"  value="" readonly="readonly" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			
			<p>
				<label>工作日天数：</label>
				<input name="should_work_days" class="number required" type="text" size="30" value="<fmt:formatNumber value="${should_work_days }" type="currency" pattern="####0.0#"/>" />
			</p>
			
			<p>
				<label> 法定假天数：</label>
				<input name="legal_holidays" class="number required" type="text" size="30" value="<fmt:formatNumber value="${legal_holidays }" type="currency" pattern="####0.0#"/>" />
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
