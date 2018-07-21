<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<%@ include file="/ztree.jsp"%>



</head>



<body>
		过滤器：<input type="text" id="key"  class="empty" />
		<ul id="userInfo" class="ztree"></ul>
</body>


<script type="text/javascript">
var setting = {
	view: {
		fontCss: getFontCss
	},	
	check: {  
        enable: true
    }, 
	data: {
		simpleData: {
			enable: true,
			pIdKey: "pid"
		},
		key:{
			title : "no"
		}
	}
};
	
	
	


function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}
var lastValue = "", nodeList = [], fontCss = {};
var nodeMap = new Map();

function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A6CF00", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}

function searchNode(e) {
	var zTree = zTreeObj;

	var value = $.trim(key.get(0).value);

	/* if (key.hasClass("empty")) {
		value = "";
	} */
	
	if (lastValue === value) return;
	lastValue = value;
	if (value === "") return;
	updateNodes(false);

	nodeList = zTree.getNodesByParamFuzzy("name", value);
	var nList = zTree.getNodesByParamFuzzy("no", value);
	if(nList != null && nList != undefined && nList.length >0){
		for (var i=0,l=nList.length; i<l; i++) {
			nodeList.push(nList[i]);
		}
	}	
	
	updateNodes(true);

}

function updateNodes(highlight) {

	var zTree = zTreeObj;
	
	if(highlight){
		for( var i=0, l=nodeList.length; i<l; i++) {
			var pNode = nodeList[i].getParentNode();
			while(pNode != null &&  pNode != undefined && pNode.id != null && pNode.id != undefined && pNode.id != ''){
				nodeMap.put(pNode.id,pNode);
				pNode = pNode.getParentNode();
			}
		}
	}
	
	$.each(nodeMap.values(),function (i,val) {
		zTreeObj.expandNode(val, highlight, false, true);
	});
	
	if(!highlight){
		nodeMap = new Map();
	}
	
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTreeObj.updateNode(nodeList[i]);
	}
	
	if(highlight){
		if(nodeList.length >0){
			zTreeObj.selectNode(nodeList[0],false,false);
		}
	}
	
}




var zTreeObj  ;
var key;

$(document).ready(function(){
	var url = "${webroot}/FileInfoAction.do?method=ztree&file_id=${shareInfo1.file_id}";
	jQuery.ajax({
		type:'GET',
		url:url,
		dataType:'json',
		timeout: 50000,
		cache: false,
		error: function(xhr){
			alert('Error loading XML document: ' + pageFrag + "\nHttp status: " + xhr.status + " " + xhr.statusText);
		}, 
		success: function(json){
			zTreeObj  = $.fn.zTree.init($("#userInfo"), setting, json);
			
			key = $("#key");
			key.bind("focus", focusKey)
			.bind("blur", blurKey)
			.bind("propertychange", searchNode)
			.bind("input", searchNode);
			
			searchNode(null);
		}
	});
	
});



function accept(){
	var objs = [];
	var nodes = zTreeObj.getCheckedNodes(true);
	for(var i=0;i<nodes.length;i++){
		 		  
	      var obj = {};
	      obj.id = nodes[i].id;
	      obj.pid = nodes[i].pid;
	      obj.name = nodes[i].name;
	      objs.push(obj);
	}
	
	
	var json1 = JSON.stringify(objs);
	return json1;
	
	
	
}

</script>

</html>