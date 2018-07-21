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
	
	var myChart = echarts.init(document.getElementById('main'));

	var objs = ${option} ;
	//将对象转为字符串
	//var option = JSON.stringify(objs);
	//将字符串转为对象
	//var option = JSON.parse(objs);
	
	myChart.setOption(objs);
	
	var option1 = {
			tooltip: {
		        padding: 10,
		        backgroundColor: '#222',
		        borderColor: '#777',
		        borderWidth: 1,
		        formatter: function (obj) {
		            var value = obj.value;
		            return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
		                +' ' + value[4]
		                + '</div>'
		                + '总下线人数:' + '：' + value[2] + '<br>'
		                + '核心下线人数:' + '：' + value[3] + '<br>' ;
		        }
		    }
			
	};

    myChart.setOption(option1);

</script>

</html>