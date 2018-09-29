<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />


<script type="text/javascript">

var contextpath = "${webroot}";
var ie=("ActiveXObject" in window);
</script>


<link href="${webroot}/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${webroot}/easyui/themes/icon.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${webroot}/easyui/demo.css" rel="stylesheet" type="text/css" media="screen"/>



<!--[if IE]>
<link href="${webroot}/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->


<script src="${webroot}/easyui/json2.js" type="text/javascript"></script>
<script src="${webroot}/easyui/jquery.min.js" type="text/javascript"></script>
<script src="${webroot}/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${webroot}/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>


<script src="${webroot}/pmjs/myMath.js" type="text/javascript"></script>
<script src="${webroot}/pmjs/compute4Datagrid.js?t="+new Date() type="text/javascript"></script>

<script type="text/javascript">
<c:forEach var="paramExtend"  varStatus="status1" items="${paramExtends}">
var exp_${paramExtend.group2 } = "${paramExtend.expression}";	
</c:forEach>
</script>


