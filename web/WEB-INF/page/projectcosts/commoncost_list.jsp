<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/CommonCostAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="project_name" value="${param.project_name}" />
	<input type="hidden" name="verify_flag" value="${param.verify_flag}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/CommonCostAction.do?method=list" method="post">
	<div class="searchBar">
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
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
	<table class="table" width="1600" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">项目名称</th>
				<th width="100">项目编号</th>	
				<th width="100">支付月份</th>	
				<th width="100">月份</th>	
				<th width="100">金额</th>	
				<th width="100">备注</th>	
				<th width="80">制表人</th>
				<th width="80">核单人</th>
				<th width="120" >申请状态</th>
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
				<td>${commoncost.project_name }</td>
				<td>${commoncost.project_no }</td>
				<td><fmt:formatDate value="${commoncost.pay_date }" pattern="yyyy-MM-dd"/></td>
				<td align="right"><b><fmt:formatNumber value="${commoncost.use_month }" type="number" pattern="###,###,##0"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${commoncost.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td>${commoncost.description }</td>
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
