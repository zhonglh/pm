<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<%@ include file="/echarts4.jsp"%>

</head>


<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="border:1px solid red;width:900px;height:600px;margin-left:18%;"></div>
</body>


<script type="text/javascript">
	
	var url = "${webroot}/${data_url}";

	var myChart = echarts.init(document.getElementById('main'));

    var waterMarkText = 'ECHARTS';
	var canvas = document.createElement('canvas');
	var ctx = canvas.getContext('2d');
	canvas.width = canvas.height = 100;
	ctx.textAlign = 'center';
	ctx.textBaseline = 'middle';
	ctx.globalAlpha = 0.08;
	ctx.font = '20px Microsoft Yahei';
	ctx.translate(50, 50);
	ctx.rotate(-Math.PI / 4);
	ctx.fillText(waterMarkText, 0, 0);



	var objs = ${o} ;
	objs.backgroundColor.image = canvas;


	//将对象转为字符串
	//var option = JSON.stringify(objs);
	//将字符串转为对象
	//var option = JSON.parse(objs);
	
	myChart.setOption(objs);

</script>

</html>