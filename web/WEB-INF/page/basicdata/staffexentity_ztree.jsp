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
		过滤器：<input type="text" id="key" value="${staffExEntity1.staff_name}" class="empty" />
		<ul id="staffexInfo" class="ztree"></ul>
</body>


<script type="text/javascript">
var setting = {
		view: {
			selectedMulti: false,
			dblClickExpand: dblClickExpand,
			fontCss: getFontCss
		},
		callback: {
			onClick: onClick,
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop,
			beforeDragOpen: beforeDragOpen,
			onDrag: onDrag,
			onDrop: onDrop,
			onExpand: onExpand
		},
		edit: {
			drag: {
				autoExpandTrigger: true,
				prev: false,
				inner: dropInner,
				next: false,
				isCopy : false,
				isMove : true
			},
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
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
	
function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}

function onClick(e,treeId, treeNode) {
	if(treeNode.level > 0)
	zTreeObj.expandNode(treeNode);
}

function dropInner(treeId, nodes, targetNode) {
	if (targetNode == false) {
		return false;
	} else {
		for (var i=0,l=nodes.length; i<l; i++) {
			if (!targetNode && nodes[i].dropRoot === false) {
				return false;
			} 
		}
	}
	return true;
}

function beforeDrag(treeId, treeNodes) {
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
			return false;
		}
	}
	return true;
}
function beforeDragOpen(treeId, treeNode) {
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
	return true;
}
function onDrag(event, treeId, treeNodes) {
}
function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
}
function onExpand(event, treeId, treeNode) {
}


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
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
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
	var url = "${webroot}/StaffExEntityAction.do?method=ztree";
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
			zTreeObj  = $.fn.zTree.init($("#staffexInfo"), setting, json);
			
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
	var MAX_LEVEL = (${MAX_LEVEL} - 1);
	var nodes = zTreeObj.getNodes();
	nodes = zTreeObj.transformToArray(nodes);
	for(var i=0;i<nodes.length;i++){
		  if(nodes[i].id == undefined || nodes[i].id == "" || nodes[i].id == null)
			  continue;
		  
		  if(nodes[i].level > MAX_LEVEL){
			  return nodes[i].name+"的层级太大！";
		  }
		  
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