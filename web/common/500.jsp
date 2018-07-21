<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ include file="/include.inc.jsp"%>

<%

	response.setStatus(HttpServletResponse.SC_OK);
	if( exception != null) exception.printStackTrace();


%>