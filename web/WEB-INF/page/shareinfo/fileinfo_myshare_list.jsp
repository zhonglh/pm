<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/FileMyShareAction.do?method=list">	

</form>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		
			
			<li><a title="确实要将选择的文件取消分享吗?" target="selectedTodo" rel="ids" href="${webroot}/FileMyShareAction.do?method=cancelShare" class="delete"><span>取消分享</span></a></li> 
	
			<li><a class="edit" href="${webroot}/FileMyShareAction.do?method=toShareInfo&file_id={sid}" mask="true" width="430"  height="400" rel="viewshare" target="dialog" warn="请选择一个文件"><span>查看分享情况</span></a></li>

		</ul>
	</div>
	<table class="table" width="900" layoutH="135">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="600">文件名</th>	
				<th width="60">文件类型</th>	
				<th width="100" >文件大小</th>
				<th width="150" >分享时间</th>
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
					
					<a href="${webroot}/FileInfoAction.do?method=download&id=${fileInfo.id}">
					${fileInfo.show_name }
					</a>
					
				</td>
				<td><c:if test="${fileInfo.is_folder=='0' }">${fileInfo.ext}</c:if></td>
				<td align="right"><c:if test="${fileInfo.is_folder=='0' }">${fileInfo.show_size }</c:if></td>
				<td align="center"><fmt:formatDate value="${fileInfo.share_datetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
			</tr>
			</c:forEach>
		</tbody>
	</table>	
</div>	
