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
	<input type="hidden" id="linsence" value="4028ce815ad54045015ad54045a40004">
	<div id="query-panel" class="easyui-panel" collapsible="false" 
		title ="字典搜索" collapsed="false" >
		<form method="post" id="searchForm" onkeydown="if(event.keyCode==13){return false;}">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 10%">
							<span class="platform-title">所属模块：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<select id="module" name="module" class="easyui-validatebox select" >
								<option value="" > --全部--</option>
								<c:forEach var="item" items="${fns:getModules()}">  
	                              	<option value="${item.id}"> ${item.name}</option>  
	                         	</c:forEach>
                         	</select>
						</td>
						
						<td style="width: 10%">
							<span class="platform-title">类型：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input id="type" name="type" class="easyui-validatebox input_bg" maxlength="80" />
						</td>
						
						<td style="width: 20%" class="btn">
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
var basePath = '${ctxAdmin}';
$(function(){
	toolbar("#toolbar");
	clos("#mainTable","字典列表","${ctxAdmin}/sys/dict/list","#toolbar",4);
});

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
	top.getDlg("${ctxAdmin}/sys/dict/view/" + row.id ,"dlgb","查看",520,143);
}

//新增
function add(){
	top.getDlg("${ctxAdmin}/sys/dict/create/" ,"dlgb","新增",520,290);
}

//修改
function update(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/dict/update/" + row.id ,"dlgb","修改",520,290);
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
				url:"${ctxAdmin}/sys/dict/delete/"+row.id,
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