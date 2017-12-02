<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<!-- ztree样式 -->
<link href="${ctxStatic}/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
<!-- ztree扩展 -->
<script src="${ctxStatic}/plugins/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctxStatic}/plugins/jquery-ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
<script src="${ctxStatic}/plugins/jquery-safari/list.js" type="text/javascript"></script>
</head>
<body>   
<div class="tree_area" style="padding:8px 15px;max-height:284px;overflow: auto;">
	<table id="org_tree" class="ztree" style="height:100%;"></table>
</div>
<div class="btn_area">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">
		<span class="paltform-title">保存</span>
	</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="top.closeDlg('dlge')">
		<span class="paltform-title">取消</span> 
	</a>
</div>
<script type="text/javascript">
	var tree;
	var setting = {
	    	check: { 
	    			enable: true,
					chkboxType: { "Y": "", "N": "" }
				},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pid"
				},
				key: {
					name: "cname"
				}
			}
		};
$(function (){
	//获取树
	$.ajax({
		async : false,
		url : "${ctxAdmin}/sys/org/tree/1",
		type : "get",
		dataType : "json",
		success : function(datas) {
			tree = $.fn.zTree.init($("#org_tree"), setting, datas);
			
			//勾选默认值
			$.ajax({
				async:false,
				type:"get",
				url:"${ctxAdmin}/sys/user/${userId}/org",
				success: function(data){
					if(data){
						for(var i=0,j=data.length;i<j;i++){
							var nodes = tree.getNodesByParam("id", data[i], null);//根据org的ID值获取符合的节点
							for (var d=0, b=nodes.length; d < b; d++) {//对符合的节点进行遍历勾选
								tree.checkNode(nodes[d], true  , false);
							}
						}
					} 
				},error : function(event, errors) {
					$.messager.alert("提示","加载失败","info");
				}
			});
		},
		error : function(event, errors) {
			$.messager.alert("提示","加载失败","info");
		}
	});
});
	
//保存用户机构
function save() {
	var newOrgList = [];
	var data = tree.getCheckedNodes(true);
	//所选的机构列表
	for (var i = 0, j = data.length; i < j; i++) {
		newOrgList.push(data[i].id);
	}
	
	$.ajax({
		async : false,
		type : "POST",
		data : JSON.stringify(newOrgList),
		contentType : "application/json;charset=utf-8", //必须
		url : "${ctxAdmin}/sys/user/${userId}/updateOrg",
		success : function(data) {
			if(data=='success'){
				$.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlge");
				});
			}else{
				$.messager.alert("提示","保存失败","error");
			}
		},
		error:function(data){
			$.messager.alert("提示","保存失败","error");
		}
	});
}
</script>
</body>
</html>