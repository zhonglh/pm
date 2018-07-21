<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/DicDataAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneEx);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${dicdata1.id }"/>
			
			<c:if test="${empty dicTypeList}">
				<input type="hidden" id="dic_type_id" name="dic_type_id" value="${dicdata1.dic_type_id }"/>
			</c:if>
			<input type="hidden" id="dic_type_name" name="dic_type_name" value="${dicdata1.dic_type_name }"/>


			<c:if test="${ not empty dicTypeList}">	
			<p>			
				<label>字典类型：</label>
				<select id="dic_type_id" name="dic_type_id" onchange="onchangeDicType(this)">
					<c:forEach var="type"  varStatus="status1" items="${dicTypeList}">
						<option value="${type.id}">${type.dic_type_name }</option>
					</c:forEach>				
				</select>
			
			</p>
			</c:if>
			
			
			<p>
				<label>字典数据：</label>
				<input name="dic_data_name" class="text" type="text" size="30" maxlength="200" value="${dicdata1.dic_data_name}" />
			</p>
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${dicdata1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${dicdata1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
