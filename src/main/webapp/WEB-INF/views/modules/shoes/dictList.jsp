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
<body>
	<input type="hidden" id="linsence" value="4028ce815bd14e65015bd172777e000d">
	<div id="query-panel" class="easyui-panel" collapsible="false" 
		title ="字典搜索" collapsed="false" >
		<form method="post" id="searchForm" onkeydown="if(event.keyCode==13){return false;}">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 10%">
							<span class="platform-title">类型：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input id="type" name="type" class="easyui-validatebox input_bg" maxlength="80" />
						</td>
						
						<td style="width: 70%" class="btn">
							<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="searchQuery();">
								<span id="platform.list.query" class="platform-title">查询</span>
							</a>
							<a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearForm('searchForm',true,true,true,true)">
								<span id="platform.list.clear" class="platform-title">清空</span> </a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	
	<table id="mainTable" ></table>	
	<div id="toolbar" ></div>
<script type="text/javascript">
var IsCheckFlag = false;
var basePath = '${ctxAdmin}';
$(function(){
	toolbar("#toolbar");
	loadGrid();
});

/**
 * 加载表格数据
 */
function loadGrid(){
	var tableHeight = gridHeight();
	$("#mainTable").datagrid({    
			title:"字典列表",
		    url:"${ctxAdmin}/shoes/dict/list",
            height: tableHeight-5,
		    idField : "id",   //表明该列是一个唯一列
			width: "auto",
		    fitColumns:true,   //设置为true将自动使列适应表格宽度以防止出现水平滚动
	        border : false,  
	        rownumbers : true,  
	        animate: true,  
	        striped : true,  //设置为true将交替显示行背景
	        nowrap:true,  //设置为true当数据长度超出列宽时将会自动截取
			pagination:true,  //设置true将在数据表格底部显示分页工具栏
			pageNumber:1,  //当设置分页属性时，初始化分页码
			pageSize : 10, //当设置分页属性时，初始化每页记录数
			pageList : [ 10, 15, 20, 25, 30 ],
			singleSelect:true,
			columns: [[
			           {field:'ck',checkbox:true},
			           {field:'id',title:'id',hidden:true},
			           {field:'type',title:'类型',sortable:false},
			           {field:'value',title:'值',sortable:false},
			           {field:'label',title:'标签',sortable:false},
			           {field:'sort',title:'排序',sortable:false},
			           {field:'useableValue',title:'是否可用',sortable:false},
			           {field:'action',title:'操作',
							formatter : function(value, row, index) {
								var opt = "";
								if(row.useable == '1'){
									opt = '<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'0\')"><div class="icon-lock" style="width:16px;height:16px;display:inline-block" title="禁用"></div></a>';
								}else if(row.useable == '0'){
									opt = '<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'1\')"><div class="icon-lock-open" style="width:16px;height:16px;display:inline-block" title="启用"></div></a>';
								}
								return opt;
							}
				        }
			         ]],
			toolbar:"#toolbar",
			enableHeaderClickMenu: false,
		    enableHeaderContextMenu: false,
		    enableRowContextMenu: false,
		    onLoadSuccess: function (data) { 
		    	$("#mainTable").datagrid('clearSelections');  //清除已选项
		    },
		    onSelect: function (rowIndex, rowData) {  
		    	if(IsCheckFlag){
		    		IsCheckFlag = false;
		    		$("#mainTable").datagrid("unselectRow",rowIndex);  
		    	}else{
		    		IsCheckFlag = true;
		    	}
	        }
	});
}

//查询
function searchQuery(){
	var queryForm =$("#searchForm").serializeObject();
	$("#mainTable").datagrid("load",queryForm); 
}

//查看
function view(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/shoes/dict/view/" + row.id ,"dlgb","查看",520,143);
}

//新增
function add(){
	top.getDlg("${ctxAdmin}/shoes/dict/create/" ,"dlgb","新增",520,290);
}

//修改
function update(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/shoes/dict/update/" + row.id ,"dlgb","修改",520,290);
}

//删除
function del(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	$.messager.confirm("删除确认", "您确定要删除选择的记录吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/shoes/dict/delete/"+row.id,
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

//禁用/启用
function opt(id,useable){
	var op = "";
	if(useable == '0'){
		op = "禁用";
	}else if(useable == '1'){
		op = "启用";
	}
	
	$.messager.confirm(op + "确认", "您确定要" + op +  "该字典类型吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/shoes/dict/"+id +"/opt?useable="+useable,
				success: function(data){
					if(data == "success"){
						$("#mainTable").datagrid("reload");	// reload the user data
						$("#mainTable").datagrid("clearSelections"); //clear selected options
					}else{
						$.messager.alert("提示",op + "失败","error");
					}
				},
				error:function(data){
					$.messager.alert("提示",op + "失败","error");
				}
			});
		} 
	});
}
</script>
</body>
</html>