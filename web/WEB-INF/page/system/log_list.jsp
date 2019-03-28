<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/LogAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="project_name" value="${param.project_name}" />
	<input type="hidden" name="project_id" value="${param.project_id}" />
	<input type="hidden" name="operation_username" value="${param.operation_username}" />
	<input type="hidden" name="operation_userid" value="${param.operation_userid}" />
	<input type="hidden" name="entity_type" value="${param.entity_type}" />
	
	<input type="hidden" name="operation_time1" value="${param.operation_time1}" />
	<input type="hidden" name="operation_time2" value="${param.operation_time2}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${webroot }/LogAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}" />
			</li>
			<li>
				<label>操作人：</label>
				<input type="text" name="operation_username" value="${param.operation_username}"/>
			</li>
			<li>
				<label>操作对象：</label>
				<select	id="entity_type" name="entity_type">					
						<option value="">全部</option>
						<c:forEach var="entityType"  varStatus="status1" items="${enumEntityTypes}">
							<option value="${entityType.i18ncode }" <c:if test="${entityType.i18ncode == param.entity_type }">selected</c:if> ><spring:message code="${entityType.i18ncode }" /></option>
						</c:forEach>
				</select>
			</li>
		</ul>

		<ul class="searchContent">
			<li>
				<label>开始时间：</label>
				<input type="text" name="operation_time1" value="${param.operation_time1}"  class="date" autocomplete="off"  readonly="true"/>
			</li>
			<li>
				<label>结束时间：</label>
				<input type="text" name="operation_time2" value="${param.operation_time2}"  class="date" autocomplete="off"  readonly="true"/>
			</li>
			<li>
				<div class="buttonActive">
					<div class="buttonContent"><button type="submit">检索</button></div>						
				</div>
			</li>
		</ul>		
		
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除历史记录吗?" target="ajaxTodo" href="${webroot}/LogAction.do?method=deleteLog" class="delete"><span>清除历史记录(一年前)</span></a></li> 
			</c:if>			
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/LogAction.do?method=toDetail&log_id={sid_log}" mask="true" width="850"  height="350" rel="view_log" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="25" >序号</th>
				<th width="120" >操作类型</th>	
				<th width="120" >实体类型</th>
				<th width="150" >实体名称</th>
				<th width="120" >操作人</th>
				<th width="120" >操作时间</th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach var="log"  varStatus="status1" items="${list}">
			<tr target="sid_log" rel="${log.log_id }">
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td><spring:message code="${log.operation_type }" /></td>
				<td><spring:message code="${log.entity_type }" /></td>
				<td>${log.entity_name }</td>
				<td>${log.operation_username }</td>
				<td><fmt:formatDate value="${log.operation_time}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>

</div>
