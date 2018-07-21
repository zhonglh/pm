<%@page contentType="text/html;charset=UTF-8"%>
<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%pageContext.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">   
    <%@include file="_s.jsp"%>
	<link rel="stylesheet" href="${ctx}/s/disk/sprite_list_icon.css">
  </head>
  <body>
    <div id="wrap">&nbsp;


      <div class="container-fluid" style="padding: 0px 15px 0;" id="top">
        <div class="row">
          <div class="col-md-12">
            <div class="alert-fixed-top" data-alerts="alerts" data-titles="{}" data-ids="myid" data-fade="1000"></div>

            <%pageContext.setAttribute("listType", "list");%> 
            <%@include file="_upload.jsp"%>

   

  </body>
</html>
