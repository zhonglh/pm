<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/${actionName}.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/${actionName}.do?method=list" method="post">
	<div class="searchBar">
		
		<ul class="searchContent">		

			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${statistics1.deptId }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${statistics1.deptName }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search&statisticsed=1" lookupGroup="dept"/>				
			
			</li>
			
			<li>
				<label>时间段：</label>
				<input type="text" class="digits date month" maxlength="6" minlength="6" format="yyyyMM" size="7" name="month1" value="${param.month1}"/>
				<input type="text" class="digits date month" maxlength="6" minlength="6" format="yyyyMM" size="7" name="month2" value="${param.month2}"/>
			
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
		
			<li><a class="icon" href="${webroot}/${actionName}.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
					
		</ul>
	</div>
	<table class="table" width="${200+deptsize*120+ (deptsize>1?130:0) }" layoutH="100">
		<thead>
			<tr>
				<th  width="250"></th>				
				<c:forEach var="dept"  varStatus="status1" items="${depts}">					
					<th  width="120">${dept.dept_name }</th>
				</c:forEach>
				<c:if test="${deptsize > 1 }">
					<th  width="120">总计</th>
				</c:if>
			</tr>
		</thead>
		<tbody>		
			<c:forEach var="datas"  varStatus="status1" items="${datass}">
			<tr>				
				<c:forEach var="data"  varStatus="status2" items="${datas}">				
					<c:if test="${status2.index == 0}">											
						<td>						
							<c:if test="${data.itemFormatter == 'B' }"><b></c:if>	
							${data.itemName }
							<c:if test="${data.itemFormatter == 'B' }"></b></c:if>		
						</td>									
					</c:if>					
					<c:if test="${status2.index > 0}">										
						<td align="right">
							
							<c:if test="${data.url != null && data.url != '' }">
								<a class="add" href="${webroot}/${data.url}" mask="true" width="850" height="520" rel="detail" title="明细"  target="dialog"><u>
							</c:if>	
						
							<c:if test="${data.itemFormatter == 'B' }"><b></c:if>
							<fmt:formatNumber value="${data.val }" type="currency" pattern="###,###,##0.00"/>${data.formatter }
							<c:if test="${data.itemFormatter == 'B' }"></b></c:if>	
							
							<c:if test="${data.url != null && data.url != '' }">
								</u></a>
							</c:if>	
						</td>				
					</c:if>					
				</c:forEach>				
			</tr>
			</c:forEach>
		</tbody>
	</table>	
</div>
