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
	<input type="hidden" id="linsence" value="4028ce815ad54045015ad54045a40000">
	<div id="query-panel" class="easyui-panel" collapsible="false" 
		title ="用户搜索" collapsed="false" >
		<form method="post" id="searchForm" onkeydown="if(event.keyCode==13){return false;}">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 10%">
							<span class="platform-title">用户名：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input type="text" id="username" name="username" class="easyui-validatebox input_bg" maxlength="20" " />
						</td>
						<td style="width: 10%">
							<span class="platform-title">手机号：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input type="text" id="mobile" name="mobile" class="easyui-validatebox input_bg" maxlength="20" />
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
	</div>
	
	<table id="mainTable"></table>	
	<div id="toolbar" ></div>
<script type="text/javascript">
var IsCheckFlag = false;
var basePath = '${ctxAdmin}';
$(function(){
	 toolbar("#toolbar");
	 loadCol();
});

function loadCol(){
	$.ajax({
 		async : false,
 		cache : false,
 		type : "GET",
 		url :  "${ctxAdmin}/sys/privilege/cols/"+$("#linsence").val(),
 		success : function(data) {
 			data = eval(data);
 			data.push(  
 				{field:'action',title:'操作',
 					formatter : function(value, row, index) {
 	                return '<a style="display:inline-block;margin-top:5px" href="javascript:resetpw(\''+row.id+'\')"><div class="icon-resetpwd" style="width:16px;height:16px;display:inline-block" title="重置密码"></div></a>';  
 	            }  
 	        });
 			loadGrid(data);
 		}
 	});
}

/**
 * 加载表格数据
 * @param data 数据
 */
function loadGrid(data){
	var tableHeight = gridHeight();
	$("#mainTable").datagrid({    
		    url:"${ctxAdmin}/sys/user/list/",
		    title:"用户列表",
            height: tableHeight-4,
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
			columns: [data],
			toolbar:"#toolbar",
			enableHeaderClickMenu: false,
		    enableHeaderContextMenu: false,
		    enableRowContextMenu: false,
		    onLoadSuccess: function (data) { 
		    	$("#mainTable").datagrid("clearSelections");  //清除已选项
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
	var queryForm = $("#searchForm").serializeObject();
	$("#mainTable").datagrid("load",queryForm); 
}

//查看
function view(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/user/view/" + row.id ,"dlga","查看",520,143);
}

//新增
function add(){
	top.getDlg("${ctxAdmin}/sys/user/create/" ,"dlgb","新增",520,268);
}

//修改 
function update(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	
	top.getDlg("${ctxAdmin}/sys/user/update/"+row.id ,"dlgb","修改",520,268);
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
				url:"${ctxAdmin}/sys/user/delete/"+row.id,
				success: function(data){
					if(data == "success"){
						$("#mainTable").datagrid("reload");	// reload the user data
						$("#mainTable").datagrid("clearSelections"); //clear selected options
					}else{
						$.messager.alert("提示", "删除失败","error");
					}
				},
				error:function(data){
					$.messager.alert("提示","删除失败","error");
				}
			});
		} 
	});
}

//重置密码
function resetpw(id){
	$.messager.confirm("确认", "您确定要对选择的记录重置密码吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/sys/user/resetpw/"+id,
				success: function(data){
					if(data == "success"){
						$("#mainTable").datagrid("reload");	// reload the user data
						$("#mainTable").datagrid("clearSelections"); //clear selected options
					}else{
						$.messager.alert("提示", "重置密码失败","error");
					}
				},
				error:function(data){
					$.messager.alert("提示","重置密码失败","error");
				}
			});
		} 
	});
}

function userForRole(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	
	top.getDlg("${ctxAdmin}/sys/user/" + row.id +"/userRole","dlgc","用户角色",520,350);
}

//用户单位弹窗
function userForCorp(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	
	top.getDlg("${ctxAdmin}/sys/user/" + row.id +"/userCorp","dlgd","用户单位",520,350);
}

//用户机构弹窗
function userForOrg(){
	var row = $("#mainTable").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	
	top.getDlg("${ctxAdmin}/sys/user/" + row.id +"/userOrg","dlge","用户机构",520,350);
}

</script>
</body>
</html>