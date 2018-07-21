<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="webroot" value="${pageContext.request.contextPath}"/>
<c:set var="locale"><spring:message code="common.language"/></c:set>
<c:set var="htmllang" value="${fn:replace(locale,'_','-')}" />
<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Expires", "0");
%> 