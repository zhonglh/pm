<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/CommonCostAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="verify_flag" value="${param.verify_flag}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/CommonCostAction.do?method=list" method="post">
	<div class="searchBar">

		<ul class="searchContent">
			<li>
				<label>费用类别：</label>
				<input type="hidden" name="rai.id" value="${commoncost.pay_item_id }"/>
				<input name="rai.dic_data_name" class="text" type="text" value="${commoncost.pay_item_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="rai"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=COMMON_COST_ITEM" />


			</li>

			<li>
				<label>核单情况：</label>
				<select name="verify_flag" style="width:153px">
					<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
					<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
					<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
					<option value="3" <c:if test="${'3' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.3"/></option>
				</select>
			</li>


			<li>
				<label>月份：</label>
				<input type="text" class="digits date month"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" size="7" name="use_month1" value="${param.use_month1}"/>
				<input type="text" class="digits date month"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" size="7" name="use_month2" value="${param.use_month2}"/>
			</li>



		</ul>

		<ul class="searchContent">


			<li>
				<label>报销人：</label>
				<input type="text" class="text" name="staff_name" value="${param.staff_name}"/>
			</li>

			<li>
				<label>支付日期：</label>
				<input type="text" class="date"  autocomplete="off" maxlength="10" minlength="10" format="yyyy-MM-dd" size="7" name="pay_date1" value="${param.pay_date1}"/>
				<input type="text" class="date"  autocomplete="off" maxlength="10" minlength="10" format="yyyy-MM-dd" size="7" name="pay_date2" value="${param.pay_date2}"/>
			</li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		</ul>

	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/CommonCostAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_commoncost"  target="dialog"><span>添加公共费用</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/CommonCostAction.do?method=deleteCommonCost" class="delete"><span>删除</span></a></li> 
		</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/CommonCostAction.do?method=toEdit&id={sid}" mask="true" width="900"  height="400" rel="update_commoncost" target="dialog" warn="请选择一条数据"><span>修改公共费用</span></a></li>
			</c:if>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/CommonCostAction.do?method=toView&id={sid}" mask="true" width="900"  height="480" rel="view_commoncost" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/CommonCostAction.do?method=batchVerifyCommonCost" class="delete"><span>批量核单</span></a></li> 
			</c:if>		
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/CommonCostAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/CommonCostAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_commoncost" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/CommonCostAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	<table class="table" width="1100" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>

				<th width="80">月份</th>
				<th width="250">备注</th>
				<th width="80">金额</th>
				<th width="120">费用类别</th>
				<th width="100">报销人</th>
				<th width="120">支付日期</th>

				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="140" >申请状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="commoncost"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${commoncost.id }">
				<td>
					<c:if test="${commoncost.verify_userid==null || commoncost.verify_userid=='' }">
						<input name="ids" value="${commoncost.id }" type="checkbox" />
					</c:if>
				</td>


				<td align="center"><b>${commoncost.use_month }</b></td>
				<td>${commoncost.description }</td>
				<td align="right"><b><fmt:formatNumber value="${commoncost.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="center">${commoncost.pay_item_name }</td>
				<td align="center">${commoncost.staff_name }</td>
				<td align="center"><fmt:formatDate value="${commoncost.pay_date }" pattern="yyyy-MM-dd"/></td>

				<td>${commoncost.build_username }</td>
				<td>${commoncost.verify_username }</td>
				<td>
					<c:if test="${commoncost.need_approve!=null && commoncost.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>
</div>	
