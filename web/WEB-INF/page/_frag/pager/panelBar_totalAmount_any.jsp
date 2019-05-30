<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="panelBar">
	<div class="pages">

		<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">			
			<option value="30" ${pageSize eq 30 ? 'selected="selected"' : ''}>30</option>
			<option value="50" ${pageSize eq 50 ? 'selected="selected"' : ''}>50</option>
			<option value="100" ${pageSize eq 100 ? 'selected="selected"' : ''}>100</option>
		</select>
		
		<span>总记录数: ${totalRows}</span>
		
		<span>&nbsp;&nbsp;&nbsp;&nbsp;  总页数: ${totalPages}</span>		
		
		<span>&nbsp;&nbsp;&nbsp;&nbsp;  总金额: <b><font color="red"><fmt:formatNumber value="${total_amount }" type="currency" pattern="###,###,##0.00"/></font></b></span>
	
		<c:if test="${not empty resultObj && resultObj != '' }">
		<span id="more1" class="btn-outline-msg" title="${fn:replace( resultObj , '<br>', '&#10;')}" >&nbsp;&nbsp;&nbsp;&nbsp;  <b style="size: 16; color: green"> 更多信息 </b></span>
		</c:if>
	
	</div>
	
	
	
	<div class="pagination" targetType="navTab" totalCount="${totalRows}" numPerPage="${pageSize}" pageNumShown="5" currentPage="${param.pageNum}"></div>
</div>