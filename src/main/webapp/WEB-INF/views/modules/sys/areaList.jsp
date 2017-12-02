<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
<script src="${ctxStatic}/plugins/jquery-safari/list.js" type="text/javascript"></script>
</head>
<body>
	<input type="hidden" id="linsence" value="4028ce815ad54045015ad54045a40005">
	<table id="mainTable"></table>
	<div id="toolbar"></div> 
<script type="text/javascript">
var IsCheckFlag = false;
var basePath = '${ctxAdmin}';
$(function(){   
	toolbar("#toolbar");
	$("#mainTable").treegrid({  
	    url:"${ctxAdmin}/sys/area/tree/-1", 
	    method:"get",
	    fit : true,
		fitColumns : true,
		border : false,
		idField : "id",
		treeField:"name",
		parentField : "pid",
		iconCls: "icon",
		animate:true, 
		rownumbers:true,
		singleSelect:true,
		striped:true,
		frozenColumns:[[ 
			{field:'ck',checkbox:true,width:"5%",resizable:true},
			{field:'id',title:'id',hidden:true,width:"5%",resizable:true},    
			{field:'name',title:'名称',width:"35%",resizable:true}
		]], 
	    columns:[[    
	        {field:'code',title:'编码',width:"10%",resizable:true},
	        {field:'type',title:'类型',width:"20%",resizable:true},
	        {field:'sort',title:'排序',width:"5%",resizable:true},
	        {field:'useableValue',title:'是否可用',width:"10%",resizable:true},
	        {field : 'action',title : '操作',width:"10%",resizable:true,
				formatter : function(value, row, index) {
					var opt = "";
					if(row.useable == '1'){
						opt += '<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'0\')"><div class="icon-lock" style="width:16px;height:16px;display:inline-block" title="禁用"></div></a>';
					}else if(row.useable == '0'){
						opt += '<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'1\')"><div class="icon-lock-open" style="width:16px;height:16px;display:inline-block" title="启用"></div></a>';
					}
					return opt;
				}
	        }
	    ]],
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    enableRowContextMenu: false,
	    toolbar:"#toolbar",
	    dataPlain: true,
	    onSelect: function (rowIndex, rowData) {  
	    	if(IsCheckFlag){
	    		IsCheckFlag = false;
	    		$("#mainTable").treegrid("unselectRow",rowIndex);  
	    	}else{
	    		IsCheckFlag = true;
	    	}
        }
	});
});

//查看
function view(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/area/view/" + row.id ,"dlga","查看",520,116);
}

//新增
function add(){
	var pid = "";
	var row = $("#mainTable").datagrid("getSelected");
	if(undefined != row && null != row){
		pid = row.id;
	}
	top.getDlg("${ctxAdmin}/sys/area/create?pid="+pid,"dlgb","新增",520,236);
}

//修改
function update(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/area/update/" + row.id ,"dlgb","修改",520,236);
}

//删除
function del(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	$.messager.confirm("删除确认", "您确定要删除该区域及其子区域项吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/sys/area/delete/"+row.id,
				success: function(data){
					if(data == "success"){
						$("#mainTable").treegrid("reload");	// reload the user data
						$("#mainTable").treegrid("clearSelections"); //clear selected options
					}else{
						$.messager.alert("提示","删除失败","error");
					}
				},
				error:function(data){
					$.messager.alert("提示","删除失败","error");
				}
			});
		} 
	});
}

//禁用/启用
function opt(id,useable){
	var op = "";
	if(useable == '0'){
		op = "禁用";
	}else if(useable == '1'){
		op = "启用";
	}
	
	$.messager.confirm(op + "确认", "您确定要" + op +  "该区域及其子区域项吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/sys/area/"+id +"/opt?useable="+useable,
				success: function(data){
					if(data == "success"){
						$("#mainTable").treegrid("reload");	// reload the user data
						$("#mainTable").treegrid("clearSelections"); //clear selected options
					}else{
						$.messager.alert("提示",op + "失败","error");
					}
				},
				error:function(data){
					$.messager.alert("提示", op + "失败","error");
				}
			});
		} 
	});
}
</script>
</body>
</html>