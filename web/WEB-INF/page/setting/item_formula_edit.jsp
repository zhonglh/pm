<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/ItemDefineAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneEx);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${itemDefine1.id }"/>
			
			<div class="panel collapse" defH="180">

			<h1>可用公式变量</h1>			
			      <div>
					<table class="table" width="98%">
						<thead>
						<tr>
							<th width="40">序号</th>
							<th width="100">名称</th>
							<th>说明</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="variable1"  varStatus="status1" items="${list}">
						<tr ondblclick="appendFormula('${variable1[1] }')">
							<td>${status1.index + 1 }</td>
							<td>${variable1[1] }</td>
							<td>${variable1[2] }</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
			      </div>			
			</div>

			
			<div class="divider"></div>
				<dl class="nowrap">
					<dd><textarea name="computational_formula_text" id="computational_formula_text"  rows="8" style="width:100%" maxlength="500">${itemDefine1.computational_formula_text }</textarea></dd>
				</dl>
			
			
		
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
