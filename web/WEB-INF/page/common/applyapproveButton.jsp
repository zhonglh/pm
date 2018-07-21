<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
		<div class="formBar">
		
		
			<ul>
				<c:if test="${operation_check != null && operation_check != '' }">
					<c:if test="${verify_userid==null || verify_userid =='' }">
					<li> 
						<div class="buttonActive"><div class="buttonContent"><button type="submit">核单</button></div></div>
					</li>
					</c:if>
				</c:if>
				
				<c:if test="${verify_userid!=null && verify_userid !='' }">
					<c:if test="${applyApprove == null }">
						
						<c:if test="${operation_uncheck != null && operation_uncheck != '' }">
						<div class="button"><div class="buttonContent">
						<a mask="true" width="420" height="180" href="${webroot }/ApplyApproveAction.do?method=toApplyApprove&operation_type=1&data_type=${data_type }&data_id=${data_id }" target="dialog" rel="apply" title="申请取消核单-输入说明信息"><span>申请取消核单</span></a><br /><br />
						</div></div>
						</c:if>
						
					</c:if>
					
					<c:if test="${applyApprove != null }">
							<c:if test="${ applyApprove.user_id == sessionUser.user_id}">								
								<div class="button"><div class="buttonContent">
								<a  target="ajaxToDo" callback="dialogAjaxDone" href="${webroot }/ApplyApproveAction.do?method=cancelApplyApprove&data_type=${data_type }&data_id=${data_id }" ><span>撤销申请</span></a><br /><br />
								</div></div>
							</c:if>

							<c:if test="${ ((project.approve_user_id == sessionUser.staff_id || dept.lead_id == sessionUser.staff_id) && sessionUser.staff_id != null ) || ( empty project  && verify_userid == sessionUser.user_id) }">
							
								<div class="buttonActive"><div class="buttonContent">							
								<a mask="true" width="450" height="180" href="${webroot }/ApplyApproveAction.do?method=toApplyApprove&operation_type=2&approve_result=1&data_type=${data_type }&data_id=${data_id }" target="dialog" rel="approve"  title="批准取消核单申请-输入说明信息"><span>批准申请</span></a><br /><br />
								</div></div>
								
								<div class="buttonActive"><div class="buttonContent">
								<a mask="true" width="450" height="180" href="${webroot }/ApplyApproveAction.do?method=toApplyApprove&operation_type=2&approve_result=0&data_type=${data_type }&data_id=${data_id }" target="dialog" rel="back"  title="驳回取消核单申请-输入说明信息"><span>驳回申请</span></a><br /><br />
								</div></div>
							</c:if>							
					</c:if>					
				
				</c:if>
				
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li> 
			</ul>
		</div>
