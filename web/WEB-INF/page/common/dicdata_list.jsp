<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/DicDataAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="dic_type_id" value="${param.dic_type_id}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
				<li><a class="add" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=${param.dic_type_id}" mask="true" width="400" height="200" rel="add_dicdata"  target="dialog"><span>添加数据</span></a></li>
			
	
				<li><a class="edit" href="${webroot}/DicDataAction.do?method=toEdit&id={sid}" mask="true" width="600"  height="200" rel="update_dicdata" target="dialog" warn="请选择一条数据"><span>修改数据</span></a></li>
	
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/DicDataAction.do?method=deleteDicData" class="delete"><span>删除</span></a></li> 
	
				<li><a title="确实要恢复这些记录吗?" target="selectedTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/DicDataAction.do?method=recoverDicData" class="add"><span>恢复</span></a></li> 
		
				<li class="line">line</li>
				
				<li><a target="ajaxTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/DicDataAction.do?method=initDicDataSort&dic_type_id=${param.dic_type_id}" ><span>初始化排序</span></a></li> 
				<li><a target="ajaxTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/DicDataAction.do?method=preDicData&id={sid}" ><span>上移</span></a></li> 
				<li><a target="ajaxTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/DicDataAction.do?method=nextDicData&id={sid}" ><span>下移</span></a></li> 

		</ul>
	</div>
	<table class="table" width="100%" layoutH="85">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="200">字典数据</th>
				<th width="30">状态</th>
				<th width="50">创建人</th>
				<th width="92">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dicdata"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${dicdata.id }">
				<td><input name="ids" value="${dicdata.id }" type="checkbox" /></td>
				<td>${dicdata.dic_data_name }</td>
				<td><spring:message code="delete.${dicdata.delete_flag }" /></td>
				<td>${dicdata.build_username }</td>
				<td><fmt:formatDate value="${dicdata.build_datetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>
</div>	
