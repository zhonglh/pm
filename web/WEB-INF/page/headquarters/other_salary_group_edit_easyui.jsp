<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/OtherSalaryGroupAction.do?method=updateSalaryGroup4Datagrid" class="pageForm required-validate" onsubmit="return submitSalaryInfo(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		

			<p>
				<label>工资月份：</label>
				
				<label>${salary1.salary_month } <input type="hidden" name="salary_month" value="${salary1.salary_month }" /></label>
			</p>	
			
			<p>
				<label>部门名称：</label>


				<input type="hidden"   name="dept_id" value="${salary1.dept_id }"/>
				<input name="dept_name" type="hidden" value="${salary1.dept_name }"  />
				<label>${salary1.dept_name }</label>

			</p>


			
			<div class="divider"></div>
		
			<table class="list" id="salaryChangeComputer" height="100%" width="100%" layoutH="165">
				<tr><td>
				<input type="hidden" name="info" id="info" />
				<iframe src="${webroot}/OtherSalaryGroupAction.do?method=toEdit4DG&id=${id}" id="salaryDetails" name="salaryDetails" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
				</td></tr>
			</table>
			
						
			
			<div class="divider"></div>
			<p>
				<label>制单人： 	</label> <label>${salary1.build_username } </label>
			</p>
			<p>
				<label>制单日期： 	</label> <label><fmt:formatDate value="${salary1.build_datetime }" pattern="yyyy-MM-dd" /> </label>
			</p>
			


		</div>
		<div class="formBar">
			<ul>
				<li><a class="buttonActive" target="_blank" href="javascript:void(0);" onClick="return fullScreen(this);"><span>全屏编辑</span></a></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" id="closeSalary" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>				
	
<script type="text/javascript">

function clickClose(){
	$('#closeSalary').click();
}

function fullScreen(fullscreenButton){

	try{
		$(fullscreenButton).attr("disabled","true");
		var url = "${webroot}/OtherSalaryGroupAction.do?method=toEdit4DG&id=${id}&full";
		window.open(url);	
		window.setTimeout(clickClose,2000);
	}catch(e){}
	return false;	
}


</script>
