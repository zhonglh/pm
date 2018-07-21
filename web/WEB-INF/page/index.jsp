<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title><spring:message code="system.name"/></title>

		<style>		
			.diff {border: 1px solid rgba(255,255, 255, 0);background:#d6c212}
		</style>


<%@ include file="/head.inc.jsp"%>

<script type="text/javascript">



var webroot = "${webroot}";

$(function(){	
	DWZ.init("${webroot}/dwz/dwz.frag.xml", {
		loginUrl:"${webroot}/LoginAction.do?method=toAjaxLogin", loginTitle:"<spring:message code="login.login"/>",	// 弹出登录对话框
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${webroot}/dwz/themes"});
		}
	});
});


//清理浏览器内存,只对IE起效,FF不需要
if ($.browser.msie) {
	window.setInterval("CollectGarbage();", 10000);
}

</script>
</head>


<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="">   </a>
				<ul class="nav">

					<li><label>${user.user_name }  欢迎您 </label></li>
					
					<c:if test="${user.user_id == '1' }">
					
						<li id="switchEnvBox"><a href="javascript:void();"><span>工资设置</span></a>
							<ul>										
								<c:forEach var="paramExtend"  varStatus="status1" items="${paramExtends}">
									<li>
										<a href="${webroot}/ParamExtendAction.do?method=paramExtendSets&group1=${paramExtend.group1 }&group2=${paramExtend.group2 }" target="dialog" width="600">
											<spring:message code="${paramExtend.group2 }" />
										</a>
									</li>
								</c:forEach>
							</ul>
						</li>
						
						<li id="switchEnvBox1"><a href="javascript:void();"><span>数据字典</span></a>
							<ul>										
								<c:forEach var="dicType"  varStatus="status1" items="${dicTypes}">
									<li>
										<a href="${webroot}/DicDataAction.do?method=list&dic_type_id=${dicType.id}" target="dialog"  rel="rel100001" mask="true" width="600" height="400">
											<spring:message code="${dicType.dic_type_name }" />
										</a>
									</li>
								</c:forEach>
							</ul>
						</li>
					
					
						<li><a class="edit" href="${webroot }/ParamsAction.do?method=list" mask="true" width="420" height="300" rel="client_add" target="dialog"><span>参数设置</span></a></li>
												
						<!-- 
						<li id="switchEnvBox2"><a href="javascript:void();"><span>营销模式设置</span></a>
							<ul>								
								<li>
									<a href="${webroot }/MarketSetsAction.do?method=toEdit" mask="true" width="420" height="300" rel="rel120001" target="dialog">营销模式全局设置</a>
								</li>
								
								<li>
									<a href="${webroot}/ItemDefineAction.do?method=list" target="dialog"  rel="rel120002" mask="true" width="600" height="400">营销模式费用项定义</a>
								</li>
							</ul>
						</li>
						 -->
						
						
						<li><a title="确定要导出数据库文件吗?"  target="dwzExport" href="${webroot}/SystemAction.do?method=backupMysql" ><span>导出数据库</span></a></li> 
				
						
					</c:if>
					
					
					
					<li><a class="edit" href="${webroot}/UserAction.do?method=toUpdatePassword" mask="true" width="450"  height="200" rel="update_password" target="dialog"><span>修改密码</span></a></li>
					<li><a href="" ><spring:message code="index.home"/></a></li>
					<li><a href="${webroot}/LoginAction.do?method=logout"><spring:message code="login.logout"/></a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div  class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2></h2><div><spring:message code="index.data"/></div></div>


				<div class="accordion" fillSpace="sidebar">
					
					<c:forEach var="menu"  varStatus="status1" items="${menus}">
					<div class="accordionHeader">
						<h2><span>Folder</span><spring:message code="${menu.menu_i18n_name}"/></h2>
					</div>
					

					<div class="accordionContent">
						<ul class="tree treeFolder">							
							<c:forEach var="childmenu"  varStatus="status1" items="${menu.childs}">
								<li>
									<c:if test="${childmenu.external == true }">
									<a href="${childmenu.menu_url}" rel="rel${childmenu.menu_id}"  target="_blank"><spring:message code="${childmenu.menu_i18n_name}"/></a>
									</c:if>									
									
									<c:if test="${childmenu.external == false }">
										<a href="${webroot}${childmenu.menu_url}" rel="rel${childmenu.menu_id}" firstCloseAll="true" target="navTab"><spring:message code="${childmenu.menu_i18n_name}"/></a>	
									</c:if>
								</li>
							</c:forEach>
						</ul>
					</div>
					</c:forEach>


																	
				
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon"><spring:message code="index.myhome"/></span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore" >more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;"><spring:message code="index.myhome"/></a></li>
				</ul>
		
		

				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<h2><a href="" target="_blank"><spring:message code="use.documents"/></a></h2>
								<a href="" target="_blank"><spring:message code="setup.documents"/></a>
								
							</div>
							<div class="right">
								<p><a href="" target="_blank" style="line-height:19px"><spring:message code="design.documents"/></a></p>
								
							</div>
							<p><span><spring:message code="company.name"/></span></p>
							<p><spring:message code="official.website"/>:<a href="http://www.souvi.com/" target="_blank">http://www.souvi.com/</a></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							


						</div>
						
					</div>
					
				</div>

		
		
			</div>
		</div>

	</div>
	
	<div id="footer"> Copyright &copy; 2015 http://www.souvi.com/     &nbsp;&nbsp;  Inc. All Rights Reserved.</div>

</body>

</html>