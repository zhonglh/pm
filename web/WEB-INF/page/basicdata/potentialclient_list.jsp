<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/PotentialClientAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="client_name" value="${potentialClient1.client_name}" />
	<input type="hidden" name="salesuser.user_id" value="${potentialClient1.sales_userid}" />
	<input type="hidden" name="salesuser.user_name" value="${potentialClient1.sales_username}" />
	
	<input type="hidden" name="sn.id" value="${potentialClient1.status}" />
	<input type="hidden" name="sn.dic_data_name" value="${potentialClient1.status_name}" />
	
	<input type="hidden" name="addr" value="${param.addr}" />
	<input type="hidden" name="date1" value="${param.date1}" />
	<input type="hidden" name="date2" value="${param.date2}" />
		
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/PotentialClientAction.do?method=list" method="post">
	<div class="searchBar">
			<ul class="searchContent">
			<li>
				<label>客户名称：</label>
				<input type="text" class="text" name="client_name"  value="${potentialClient1.client_name }"/>
			</li>

			<li>
				<label>销售负责人：</label>
				<input type="hidden"  name="salesuser.user_id" value="${potentialClient1.sales_userid }"/>
				<input name="salesuser.user_name" class="text" type="text" size="18"  value="${potentialClient1.sales_username }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup&position_type=2" lookupGroup="salesuser" lookupPk="user_id" width="950">选择</a>
				
			</li>		
			<li>
				<label>状态：</label>
				<input type="hidden" name="sn.id" value="${potentialClient1.status }"/>
				<input name="sn.dic_data_name" class="text" type="text" value="${potentialClient1.status_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="sn"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CLIENT_STATUS" />			
			</li>
		</ul>
		
		
		<ul class="searchContent">
			<li>
				<label>客户地址：</label>
				<input type="text" class="text" name="addr" value="${potentialClient1.addr }"/>
			</li>
			
			<li>
				<label>咨询时间：</label>
				<input type="text" class="date" name="date1" size="7" value="${param.date1 }"/>
				<input type="text" class="date" name="date2" size="7" value="${param.date2 }"/>
			</li>
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>
		</ul>
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/PotentialClientAction.do?method=toEdit" mask="true" width="900" height="400" rel="add_potentialclient"  target="dialog"><span>添加潜在客户</span></a></li>
			</c:if>
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/PotentialClientAction.do?method=deletePotentialClient" class="delete"><span>删除</span></a></li> 
			</c:if>
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/PotentialClientAction.do?method=toEdit&id={sid}" mask="true" width="900"  height="400" rel="update_potentialclient" target="dialog" warn="请选择一条数据"><span>修改潜在客户</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/PotentialClientAction.do?method=toView&id={sid}" mask="true" width="900"  height="480" rel="view_potentialclient" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>	
			
			<c:if test="${operation_dallot != null && operation_dallot != '' }">
				<li><a class="edit" href="${webroot}/OtherStaffAction.do?method=lookup1&id={sid}&position_type=2&toDoUrl=PotentialClientAction.do?method=dallotSales" mask="true" width="950"  height="480" rel="dallot_potentialclient" target="dialog" warn="请选择一条数据" title="分配销售负责人"><span>分配</span></a></li>
			</c:if>	
						
			
			<li class="line">line</li>
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/PotentialClientAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			<li class="line">line</li>
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/PotentialClientAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_potentialclient" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			<c:if test="${operation_insert != null && operation_insert != '' }">	
				<li><a class="icon" href="${webroot }/PotentialClientAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
		</ul>
	</div>
	
	<table class="table" width="1700" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="100">客户编号</th>	
				<th width="100">客户名称</th>	
				<th width="100">行业</th>	
				<th width="100">客户地址</th>	
				<th width="100">联系人</th>	
				<th width="100">手机</th>	
				<th width="100">座机</th>	
				<th width="100">销售负责人</th>	
				<th width="100">电子邮件</th>	
				<th width="100">QQ号码</th>	
				<th width="100">技术方向</th>	
				<th width="100">服务类型</th>	
				<th width="100">项目周期</th>	
				<th width="100">客户价值</th>	
				<th width="100">信息来源</th>	
				<th width="100">客户状态</th>	
				<th width="100">咨询时间</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="potentialClient"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${potentialClient.id }">
				<td><input name="ids" value="${potentialClient.id }" type="checkbox" /></td>
				<td>${potentialClient.client_no }</td>
				<td>${potentialClient.client_name }</td>
				<td>${potentialClient.trade }</td>
				<td>${potentialClient.addr }</td>
				<td>${potentialClient.linkman }</td>
				<td>${potentialClient.mobile_phone }</td>
				<td>${potentialClient.tel }</td>
				<td>${potentialClient.sales_username }</td>
				<td>${potentialClient.email }</td>
				<td>${potentialClient.qq }</td>
				<td>${potentialClient.technology_direction }</td>
				<td>${potentialClient.service_type }</td>
				<td>${potentialClient.project_cycle_name }</td>
				<td>${potentialClient.client_worth_name }</td>
				<td>${potentialClient.info_source }</td>
				<td>${potentialClient.status_name }</td>				
				<td><fmt:formatDate value="${potentialClient.consult_time }" pattern="yyyy-MM-dd"/></td>	
					
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>	
