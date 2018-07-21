<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post"  class="pageForm required-validate">
		<div class="pageFormContent" layoutH="56">	
			
			<p>
				<label>
					<b>分享给：</b>
					<c:if test="${shareInfo1.is_share_all=='1' }"> 所有人 </c:if>
				</label>
				
			</p>
			
			<p>
				
			<c:if test="${shareInfo1.is_share_all=='0' }"> 
				
					<c:forEach items="${toUsers}" var="toUser">
						<label>${toUser.to_user_name }    </label>
					</c:forEach>
				
				</c:if>
			
			</p>
			


		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" id="closeSalary" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>				
	
