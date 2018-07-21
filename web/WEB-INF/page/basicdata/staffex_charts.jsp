<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<%@ include file="/echarts.jsp"%>

</head>


<body>
	<div id="main" style="width: 100%;height:100%;"></div>
</body>


<script type="text/javascript">
	
	var url = "${webroot}/${data_url}";
	console.debug(url);
	debugger;
	var myChart = echarts.init(document.getElementById('main'));

	var objs = ${option} ;
	//将对象转为字符串
	//var option = JSON.stringify(objs);
	//将字符串转为对象
	//var option = JSON.parse(objs);
	
	myChart.setOption(objs);
	
	myChart.showLoading();
	$.get(url).done(function (data) {
	    try{
			var option1 = JSON.parse(data);
		    myChart.hideLoading();
		    myChart.setOption(option1);
	    }catch(e){
		    myChart.hideLoading();
		    var json = {
		    	"statusCode":"300", 
		    	"message":"出现内部错误" 
		    }
	    	top.DWZ.ajaxDone(json);
	    }
	});
	

</script>

</html>