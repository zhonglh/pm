<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>员工名称：</label>
				${projectStaffCost1.staff_name}
			</p>
			<p>
				<label>工号：</label>
				${projectStaffCost1.staff_no}
			</p>

			<p>
				<label>项目名称：</label>
				${projectStaffCost1.project_name}
			</p>
			<p>
				<label>项目编号：</label>
				${projectStaffCost1.project_no}
			</p>			

			<p>
				<label>月份：</label>
				${projectStaffCost1.work_month}
			</p>
			
			<p>
				<label>实际支付日期：</label>
				<fmt:formatDate value="${projectStaffCost1.pay_date }" pattern="yyyy-MM-dd"/>
			</p>
			
				

			<p>
				<label>工作天数：</label>
				${projectStaffCost1.work_days}
			</p>
			<p>
				<label>实发金额：</label>
				${projectStaffCost1.amount}
			</p>	
			
			<p>
				<label>社保：</label>
				${projectStaffCost1.insurance_amount}
			</p>	
			
			<p>
				<label>公积金：</label>
				${projectStaffCost1.pub_funds_amount}
			</p>	
			
				
			
			<p>
				<label>个税：</label>
				${projectStaffCost1.personal_income_tax}
			</p>	
			
			<p>
				<label>总成本：</label>
				${projectStaffCost1.all_amount}
			</p>		
				
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${projectStaffCost1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${projectStaffCost1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>
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
