<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/ItemDefineAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
				
			<li><a class="add" href="${webroot}/ItemDefineAction.do?method=toEdit" mask="true" width="500" height="300" rel="add_itemdefine"  target="dialog"><span>添加</span></a></li>
			<li><a class="edit" href="${webroot}/ItemDefineAction.do?method=toEdit&id={sid}" mask="true" width="400"  height="200" rel="update_itemdefine" target="dialog" warn="请选择一条数据"><span>修改</span></a></li>	
			<li><a class="edit" href="${webroot}/ItemDefineAction.do?method=toEditFormula&id={sid}" mask="true" width="600"  height="500" rel="update_itemdefine" target="dialog" warn="请选择一条数据"><span>编辑公式</span></a></li>	
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/ItemDefineAction.do?method=deleteItemDefine" class="delete"><span>删除</span></a></li>

			<li class="line">line</li>		
			
			<li><a target="ajaxTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/ItemDefineAction.do?method=updateEnableState&enable_state=1&id={sid}" ><span>启用</span></a></li> 
			<li><a target="ajaxTodo" callback="dialogAjaxDoneByToDo" targetType="dialog" rel="ids" href="${webroot}/ItemDefineAction.do?method=updateEnableState&enable_state=0&id={sid}" ><span>停用</span></a></li> 
		
		</ul>
	</div>
	<table class="table" width="600" layoutH="75">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>				
				<th width="120">项目名称</th>	
				<th width="80">使用方向</th>	
				<th width="80">启用状态</th>	
				<th width="300">计算公式</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="itemDefine"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${itemDefine.id }">
				<td><input name="ids" value="${itemDefine.id }" type="checkbox" /></td>
				
				<td>${itemDefine.item_name }</td>
				<td>${itemDefine.item_direction_name }</td>
				<td>${itemDefine.enable_state_name }</td>
				<td>${itemDefine.computational_formula_text }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>
</div>	
