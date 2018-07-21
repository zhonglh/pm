<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/RecruitInfoAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />	
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this,'rel17');" class="pageForm required-validate" action="${webroot }/RecruitInfoAction.do?method=addRecruitInfo" method="post">
	<div class="searchBar">
		<ul class="searchContent"> 		
				<input type="hidden" name="recrcz" value="1"  />
			<li>
				<label>简历编号：</label>
				<input type="text" name="resume_no" class="text required" value="${recruitInfo1.resume_no }" onChange="javaScript:changeresume_no(this)" /> 
			</li>
			
			<li>
				<label>简历来源：</label>
				<input type="hidden" name="rs.id" value="${recruitInfo1.resume_source }"  />
				<input name="rs.dic_data_name" class="text required" type="text" value="${recruitInfo1.resume_source_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="rs"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=JOB_SOURCE" />				
			</li>
			<li>			
				<c:if test="${operation_insert != null && operation_insert != '' }">
				<div class="buttonActive"><div class="buttonContent"><button type="submit" onClick="javaScript:reviewRecruit(this.form,'${webroot }')">查重</button></div></div>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="buttonActive"><div class="buttonContent"><button type="submit" onClick="javaScript:return saveRecruit(this.form,'${webroot }');">保存</button></div></div>
				</c:if>
			</li>
		</ul>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="100%" layoutH="86">
		<thead>
			<tr>				
				<th width="150">简历编号</th>	
				<th width="150">简历来源</th>	
				<th width="80">创建人</th>
				<th width="80">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="recruitInfo"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${recruitInfo.id }">				
				<td>${recruitInfo.resume_no }</td>
				<td>${recruitInfo.resume_source_name }</td>
				<td>${recruitInfo.build_username }</td>
				<td><fmt:formatDate value="${recruitInfo.build_datetime }" pattern="yyyy-MM-dd"/></td>
		
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>	
