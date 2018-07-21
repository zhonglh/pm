<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/PotentialClientAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/PotentialClientAction.do?method=isExist', dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${potentialClient1.id }"/>
			<p>
				<label>客户编号：</label>
				<input name="client_no" class="text required" type="text" size="30" maxlength="25" value="${potentialClient1.client_no }" />
			</p>
			<p>
				<label>客户名称：</label>
				<input name="client_name" class="text required" type="text" size="30" maxlength="100" value="${potentialClient1.client_name }" />
			</p>
			<p>
				<label>行业：</label>
				<input name="trade" class="text" type="text" size="30" maxlength="50" value="${potentialClient1.trade }" />
			</p>
			<p>
				<label>客户地址：</label>
				<input name="addr" class="text" type="text" size="30" maxlength="250" value="${potentialClient1.addr }" />
			</p>
			<p>
				<label>联系人：</label>
				<input name="linkman" class="text" type="text" size="30" maxlength="50" value="${potentialClient1.linkman }" />
			</p>
			<p>
				<label>手机：</label>
				<input name="mobile_phone" class="phone" type="text" size="30" maxlength="20" value="${potentialClient1.mobile_phone }" />
			</p>
			<p>
				<label>座机：</label>
				<input name="tel" class="phone" type="text" size="30" maxlength="20" value="${potentialClient1.tel }" />
			</p>
			<p>
				<label>电子邮件：</label>
				<input name="email" class="email" type="text" size="30" maxlength="60" value="${potentialClient1.email }" />
			</p>
			<p>
				<label>QQ号码：</label>
				<input name="qq" class="digits" type="text" size="30" maxlength="20" value="${potentialClient1.qq }" />
			</p>
			<p>
				<label>技术方向：</label>
				<input name="technology_direction" class="text" type="text" size="30" maxlength="25" value="${potentialClient1.technology_direction }" />
			</p>
			<p>
				<label>服务类型：</label>
				<input name="service_type" class="text" type="text" size="30" maxlength="25" value="${potentialClient1.service_type }" />
			</p>
			
			<p>
				<label>项目周期：</label>
				<input type="hidden" name="pc.id" value="${potentialClient1.project_cycle }"/>
				<input name="pc.dic_data_name" class="text" type="text" size="28"  value="${potentialClient1.project_cycle_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="pc"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=PROJ_PERIOD" />	
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=PROJ_PERIOD" mask="true" width="500" height="260" rel="add_PROJ_PERIOD"  target="dialog"><span>添加</span></a>					
			</p>
			
			<div class="divider"></div>
				<dl class="nowrap">
					<dt>客户需求：</dt>
					<dd><textarea name="project_requirement" cols="90" rows="5" maxlength="500">${potentialClient1.project_requirement }</textarea></dd>
				</dl>
			<div class="divider"></div>
				
			<p>
				<label>客户价值：</label>
				<input type="hidden" name="cw.id" value="${potentialClient1.client_worth }"/>
				<input name="cw.dic_data_name" class="text" type="text" size="30" value="${potentialClient1.client_worth_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="cw"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CLIENT_VAL" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=CLIENT_VAL" mask="true" width="500" height="260" rel="add_CLIENT_VAL"  target="dialog"><span>添加</span></a>		
			</p>
			<p>
				<label>信息来源：</label>
				<input name="info_source" class="text" type="text" size="30" maxlength="50" value="${potentialClient1.info_source }" />
			</p>
			<p>
				<label>客户状态：</label>				
				<input type="hidden" name="sn.id" value="${potentialClient1.status }"/>
				<input name="sn.dic_data_name" class="text" type="text" size="30" value="${potentialClient1.status_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="sn"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=CLIENT_STATUS" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=CLIENT_STATUS" mask="true" width="500" height="260" rel="add_CLIENT_STATUS"  target="dialog"><span>添加</span></a>
			</p>
			
			<p>
				<label>咨询时间：</label>
				<input id="consult_time" name="consult_time" class="text date" type="text" size="30" value="<fmt:formatDate value="${potentialClient1.consult_time }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>
			
			
			<div class="divider"></div>
			
				<dl class="nowrap">
					<dt>备注：</dt>
					<dd><textarea name="description" cols="90" rows="3" maxlength="150">${potentialClient1.description }</textarea></dd>
				</dl>

			<div class="divider"></div>
			
			
			

			<div class="divider"></div>

			<h3 class="contentTitle">跟进记录</h3>
			
			<table id="followups_table" class="list nowrap itemDetail" <c:if test="${operation_insert != null && operation_insert != '' }">addButton="新建跟进记录"</c:if> width="100%">
				<thead>
					<tr>
						<th width="120" type="date" postField="link_time"  name="items[#index#].link_time" format="yyyy-MM-dd HH:mm"  size="14" fieldClass="date required">联系时间</th>
						<th width="60" type="text" postField="link_type"  name="items[#index#].link_type" maxlength="5" size="4" fieldClass="text required">联系方式</th>
						<th width="450" type="text" postField="link_content"  name="items[#index#].link_content" maxlength="500" size="60" fieldClass="text required">联系内容</th>
						<th width="120" type="text" postField="description"  name="items[#index#].description" maxlength="150" size="12" fieldClass="text">备注</th>
						<th type="del" >操作</th>
					</tr>												
				</thead>
				<tbody>								
					<c:forEach var="followup"  varStatus="status1" items="${followups}">
					<tr>
						<td><fmt:formatDate value="${followup.link_time }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${followup.link_type }</td>
						<td title="${followup.link_content }">${followup.link_content }</td>
						<td title="${followup.description }">${followup.description }</td>
						<td width="80" nowrap>						
							<c:if test="${operation_update != null && operation_update != '' }">
								<a href="void(0);" onclick="edit_followups(this,'${followup.id }');return false;" ><span>编辑</span></a>
							</c:if>							
							
							<c:if test="${operation_delete != null && operation_delete != '' }">
							<a title="确实要<font color='red'><b>彻底</b></font>删除这条记录吗?" target="ajaxTodo" callback="dialogAjaxDoneDeleteItem"  href="${webroot}/PotentialClientAction.do?method=deleteFollowup&id=${followup.id}&rownum=${status1.index}" class="delete"><span>删除</span></a>
							</c:if>
						</td>
					</tr>
					</c:forEach>								
				</tbody>
			</table>
			
			<div class="divider"></div>
			
			
			
			<p>
				<label>制单人：</label>  ${potentialClient1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${potentialClient1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
