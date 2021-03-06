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
	<input type="hidden" id="linsence" value="4028ce815bd14e65015bd18a37610027">
	<div id="query-panel" class="easyui-panel" collapsible="false" 
		title ="字典搜索" collapsed="false" >
		<form method="post" id="searchForm" onkeydown="if(event.keyCode==13){return false;}">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 10%">
							<span class="platform-title">名称：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input id="name" name="name" class="easyui-validatebox input_bg" maxlength="80" />
						</td>
						
						<td style="width: 10%">
							<span class="platform-title">类型：</span>
						</td>
						<td style="width: 20%" class="platform-input">
						<select id="type" name="type" class="easyui-validatebox select" >
								<option value="">--全部--</option>
								<option value="1">推荐训练</option>
								<option value="2">其他训练</option>
							</select>
						</td>
						
						<td style="width: 40%" class="btn">
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
		    url:"${ctxAdmin}/train/dict/list",
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
			           {field:'name',title:'名称',sortable:false},
			           {field:'type',title:'类型',sortable:false,
			        	   formatter:function(value,rec){
			        	   	 return rec.type==1?'推荐训练':'其他训练';
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
	top.getDlg("${ctxAdmin}/train/dict/view/" + row.id ,"dlgb","查看",520,63);
}

//新增
function add(){
	top.getDlg("${ctxAdmin}/train/dict/create/" ,"dlgb","新增",520,172);
}

//修改
function update(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/train/dict/update/" + row.id ,"dlgb","修改",520,172);
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
				url:"${ctxAdmin}/train/dict/delete/"+row.id,
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