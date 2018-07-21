<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/FileInfoAction.do?method=list">	
	<input type="hidden" name="parent_id" value="${fileInfo1.parent_id}" />
</form>

<div class="pageHeader">
	<c:if test="${empty fileInfo1.parent_id }">我的文件</c:if>
	<c:if test="${not empty fileInfo1.parent_id }"><a style="cursor:pointer;" class="file" id="">我的文件</a><c:forEach var="item" items="${parents}">  /  <a  style="cursor:pointer;"  class="file" id="${item.id }">${item.show_name }</a></c:forEach></c:if>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		
			<li><a class="add" href="${webroot}/FileInfoAction.do?method=toAdd&parent_id=${fileInfo1.parent_id}" mask="true" width="900" height="400" rel="add_fileinfo"  target="dialog"><span>上传</span></a></li>
		
			<li><a title="确实要删除这些文件吗?" target="selectedTodo" rel="ids" href="${webroot}/FileInfoAction.do?method=deleteFileInfo" class="delete"><span>删除</span></a></li> 

			<li><a class="icon" href="${webroot}/FileInfoAction.do?method=toShareSelect&file_id={sid}" mask="true" width="500"  height="500" rel="update_fileinfo" target="dialog" warn="请先选择一个文件"><span>分享</span></a></li>
		
			<li><a class="add" href="${webroot}/FileInfoAction.do?method=toAddFolder&parent_id=${fileInfo1.parent_id}" mask="true" width="450" height="200" rel="add_fileinfo"  target="dialog"><span>创建文件夹</span></a></li>
			
			<li class="line">line</li>
			
			
			<li><a class="edit" href="${webroot}/FileInfoAction.do?method=toReName&id={sid}" mask="true" width="400"  height="200" rel="rename_fileinfo" target="dialog" warn="请先选择一个文件"><span>重命名</span></a></li>
		
			
			<li><a class="icon" href="${webroot}/FileInfoAction.do?method=toMove&id={sid}" mask="true" width="500"  height="500" rel="move_fileinfo" target="dialog" warn="请先选择一个文件"><span>移动到..</span></a></li>
			
		</ul>
	</div>
	<table class="table" width="900" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="600">文件名</th>	
				<th width="60">文件类型</th>	
				<th width="100" >文件大小</th>
				<th width="150" >上传时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fileInfo"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${fileInfo.id }">
				<td>
						<input name="ids" value="${fileInfo.id }" type="checkbox" />
				</td>
				<td>				
		    		<i class="icon-16 icon-16-${fileInfo.ext}"></i>
					<c:if test="${fileInfo.is_folder=='1' }">
					<a href="javaScript:void();retur false;" class="file" id="${fileInfo.id }">${fileInfo.show_name }</a>
					</c:if>
					<c:if test="${fileInfo.is_folder=='0' }">
					<a href="${webroot}/FileInfoAction.do?method=download&id=${fileInfo.id}">
					${fileInfo.show_name }
					</a>
					</c:if>
				</td>
				<td><c:if test="${fileInfo.is_folder=='0' }">${fileInfo.ext }</c:if></td>
				<td align="right"><c:if test="${fileInfo.is_folder=='0' }">${fileInfo.show_size }</c:if></td>
				<td align="center"><fmt:formatDate value="${fileInfo.build_datetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	
</div>	
