<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link href="${ctxStatic}/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">

<script src="${ctxStatic}/plugins/jquery/json2.js"></script>
<!-- ztree扩展 -->
<script src="${ctxStatic}/plugins/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctxStatic}/plugins/jquery-ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
<script src="${ctxStatic}/plugins/jquery-safari/list.js" type="text/javascript"></script>
</head>
<body>
<div class="tree_area" style="padding:8px 15px;max-height:294px;overflow: auto;">
	<table id="privilege_tree" class="ztree" style="height:100%;"></table>
</div>
<div class="btn_area" style="border-top:1px solid #cecece">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save(true);"> 
		<span class="paltform-title">确定</span>
	</a> 
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="top.closeDlg('dlge')"> 
		<span class="paltform-title">取消</span>
	</a>
</div>

<script type="text/javascript">
//获得el表达式中的值，进行遍历json串
var datas = jQuery.parseJSON( '${data}');
var iframeId = '${iframeId}';
var fnTree = $.fn.zTree;
var tree = null;
var id = null;
var name = null;
var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid"
		},
		key: {
			name: "name"
		}
	},
	callback: {
		onClick: selected
	}
};

$(function (){
	tree = fnTree.init($("#privilege_tree"), setting, datas);
});

function selected(event, treeId, treeNode){
    id = treeNode.id;  //节点ID
    name = treeNode.name;   //节点名称
    save(false);
}

function save(flag){
	if(!Utils.isEmpty(top.document.getElementById(iframeId))){
	    var iframe = parent.document.getElementById(iframeId);
	    iframe.contentWindow.choose.setId(id);
		iframe.contentWindow.choose.setName(name);
    }
	
	if(flag){
		top.closeDlg('dlge');
	}
}


</script>
</body>
</html>