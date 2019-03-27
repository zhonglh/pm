<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/FileShareMyAction.do?method=list">	

</form>

<div class="pageHeader">
	<c:if test="${empty top_id }">共享文件</c:if>
	<c:if test="${not empty top_id }"><a style="cursor:pointer;" class="sharefile" id="">共享文件</a><c:forEach var="item" items="${parents}">  /  <a  style="cursor:pointer;"  class="sharefile" id="${item.id }" top_id="${top_id}">${item.show_name }</a></c:forEach></c:if>
</div>


<div class="pageContent">
	
	<table class="table" width="1000" layoutH="105">
		<thead>
			<tr>
				<th width="10"></th>
				<th width="600">文件名</th>	
				<th width="60">文件类型</th>	
				<th width="100" >文件大小</th>
				<th width="150" >分享时间</th>
				<th width="100" >分享人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fileInfo"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${fileInfo.id }">
				<td>
						
				</td>
				<td>

					<a href="${webroot}/FileInfoAction.do?method=download&id=${fileInfo.id}">
		    		<i class="icon-16 icon-16-${fileInfo.ext}"></i>
					</a>

					<c:if test="${fileInfo.is_folder=='1' }">
						<a href="javaScript:void();retur false;" class="sharefile" id="${fileInfo.id }" top_id="${top_id }">${fileInfo.show_name }</a>
					</c:if>
					<c:if test="${fileInfo.is_folder=='0' }">
					<a href="${webroot}/FileInfoAction.do?method=download&id=${fileInfo.id}">
							${fileInfo.show_name }
					</a>
					</c:if>
					
				</td>
				<td><c:if test="${fileInfo.is_folder=='0' }">${fileInfo.ext}</c:if></td>
				<td align="right"><c:if test="${fileInfo.is_folder=='0' }">${fileInfo.show_size }</c:if></td>
				<td align="center"><fmt:formatDate value="${fileInfo.share_datetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				<td>${fileInfo.build_username }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>	
</div>	
