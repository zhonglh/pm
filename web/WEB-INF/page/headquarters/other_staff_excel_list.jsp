<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="5100" >
				<thead>
					<tr>
						<th width="180">导入说明</th>	
						<th width="100">工号</th>
						<th width="100">姓名</th>
						<th width="100">所在部门</th>
						<th  width="100">职位</th>

						<th width="100">岗位名称</th>
						<th width="50">性别</th>
						<th width="120">生日</th>
						<th width="100">民族</th>
						<th width="120">户籍性质</th>
						<th width="160">身份证号</th>
						<th width="80">联系电话</th>
						<th width="120">紧急联系人电话</th>
						<th width="100">QQ号码</th>
						<th width="120">合同开始时间</th>
						<th width="120">合同结束时间</th>
						<th width="80">签约次数</th>
						<th width="200">毕业学校</th>
						<th width="40">学历</th>
						<th width="180">户口所在地</th>
						<th width="180">通讯地址</th>
						<th width="120">入职时间</th>
						<th width="120">转正日期</th>
						<th width="100">试用期工资</th>
						<th width="100">正式工资</th>
						<th width="100">电脑补助</th>
						<th width="120">餐补(每天)</th>
						<th width="80">奖金(税前)</th>
						<th width="80">出差补助</th>
						<th width="80">社保种类</th>
						<th width="180">社保缴纳单位</th>
						<th width="60">额外</th>
						<th width="200">邮箱地址</th>
						<th width="200">开户行</th>
						<th width="120">卡号</th>
						<th width="120">额外支出</th>
						<th width="160">是否允许发送信息</th>
						<th width="100">合同种类</th>
						<th width="100">合同归属</th>
						<th width="100">社保说明</th>
						<th width="120">成本中心</th>
						<th width="300">备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="staffcost"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${staffcost.errorInfo != null && staffcost.errorInfo != '' }">
								<font color="red">${staffcost.errorInfo }</font>
							</c:if>
							<c:if test="${staffcost.errorInfo == null || staffcost.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${staffcost.staff_no }</td>
						<td>${staffcost.staff_name }</td>
						<td>${staffcost.dept_name }</td>
						<td>${staffcost.position_type_temp }</td>


						<td>${staffcost.job_title }</td>
						<td>${staffcost.sex }</td>
						<td><fmt:formatDate value="${staffcost.birthday }" pattern="yyyy-MM-dd"/></td>
						<td>${staffcost.nationality_name }</td>
						<td>${staffcost.census_property_name }</td>
						<td>${staffcost.identity_card_number }</td>
						<td>${staffcost.tel }</td>
						<td>${staffcost.pressing_tel }</td>
						<td>${staffcost.qq }</td>
						<td><fmt:formatDate value="${staffcost.contract_start_date }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${staffcost.contract_end_date }" pattern="yyyy-MM-dd"/></td>
						<td>${staffcost.contract_number }</td>
						<td>${staffcost.graduate_school }</td>
						<td>${staffcost.educational_name }</td>
						<td>${staffcost.registered_residence }</td>
						<td>${staffcost.postal_address }</td>
						<td><fmt:formatDate value="${staffcost.join_datetime }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${staffcost.confirmation_date }" pattern="yyyy-MM-dd"/></td>
						<td>${staffcost.tryout_salary }</td>
						<td>${staffcost.official_salary }</td>
						<td>${staffcost.computer_allowance }</td>
						<td>${staffcost.meal_allowance }</td>
						<td>${staffcost.project_allowance }</td>
						<td>${staffcost.travel_allowance }</td>
						<td>${staffcost.insurance_radix }</td>
						<td>${staffcost.securty_unit }</td>
						<td>${staffcost.extra }</td>
						<td>${staffcost.email }</td>
						<td>${staffcost.open_account }</td>
						<td>${staffcost.bank_card_number }</td>
						<td>${staffcost.extra_expend }</td>
						<td>${staffcost.can_send_info }</td>

						<td>${staffcost.contract_type_name }</td>
						<td>${staffcost.contract_attach_name }</td>
						<td>${staffcost.social_security_name }</td>
						<td>${staffcost.cost_center_name }</td>
						<td>${staffcost.description }</td>
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
</div>
