<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
<script src="${ctxStatic}/plugins/jquery-safari/list.js" type="text/javascript"></script>
</head>
<body class="easyui-layout">
	<input type="hidden" id="linsence" value="4028ce815ad54045015ad54045a40006">
	<div data-options="region:'west',split:true,border:false,title:'区域列表'"  style="width: 300px">
		<table id="area_table"></table>
    </div> 
	
	<div data-options="region:'center',split:true,border:false,title:'单位列表'">
		<form method="post" id="searchForm" onkeydown="if(event.keyCode==13){return false;}">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 10%">
							<span class="platform-title">名称：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input type="text" id="name" name="name" class="easyui-validatebox input_bg" maxlength="128"  />
						</td>
						<td style="width: 20%"class="btn">
							<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="searchQuery();">
								<span id="platform.list.query" class="platform-title">查询</span>
							</a>
							<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearForm('searchForm',true,true,true,true)">
								<span id="platform.list.clear" class="platform-title">清空</span> </a>
						</td>
					</tr>
				</table>
			</form>
		<div id="toolbar"></div>
		<table id="mainTable"></table>
    </div>
 
<script type="text/javascript">
var IsCheckFlag = false;
var basePath = '${ctxAdmin}';
$(function(){   
	$("#area_table").treegrid({  
		url:"${ctxAdmin}/sys/area/json/1", 
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
	    columns:[[    
	        {field:'id',title:'id',hidden:true},    
	        {field:'name',title:'名称',width:"100"}
	    ]],
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    enableRowContextMenu: false,
	    dataPlain: true,
	    onClickRow:function(row){
	    	$("#mainTable").datagrid("load",{areaId:row.id}); 
	    },
	    onSelect: function (rowIndex, rowData) {  
	    	if(IsCheckFlag){
	    		IsCheckFlag = false;
	    		$("#area_table").treegrid("unselectRow",rowIndex);  
	    	}else{
	    		IsCheckFlag = true;
	    	}
        }
	});
	
	toolbar("#toolbar");
	clos("#mainTable","","${ctxAdmin}/sys/corp/list","#toolbar",42);
});


//查询
function searchQuery(){
	var queryForm = $("#searchForm").serializeObject();
	$("#mainTable").datagrid("load",queryForm); 
}


//查看
function view(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/corp/view/" + row.id ,"dlgb","查看",580,420);
}

//新增
function add(){
	var pid = "";
	var row = $("#mainTable").datagrid("getSelected");
	if(undefined != row && null != row){
		pid = row.id;
	}
	top.getDlg("${ctxAdmin}/sys/corp/create?pid="+pid  ,"dlgb","新增",580,386);
}

//修改
function update(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/corp/update/" + row.id ,"dlgb","修改",580,386);
}

//删除
function del(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	$.messager.confirm("删除确认", "您确定要删除该单位吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/sys/corp/delete/"+row.id,
				success: function(data){
					if(data == "success"){
						$("#mainTable").datagrid("reload");	// reload the user data
						$("#mainTable").datagrid("clearSelections"); //clear selected options
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


</script>
</body>
</html>