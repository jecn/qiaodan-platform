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
<input type="hidden" id="userId" value="${userId}">
<div id="query-panel" class="easyui-panel" collapsible="false"  title ="角色搜索" collapsed="false" >
		<form method="post" id="searchForm">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 25%">
							<span class="platform-title">名称：</span>
						</td>
						<td style="width: 45%" class="platform-input">
							<input type="text" id="name" name="name" class="easyui-validatebox" maxlength="48" />
						</td>
						<td style="width: 30%"class="btn">
							<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="searchQuery();">
								<span id="platform.list.query" class="platform-title">查询</span>
							</a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	<table id="role_table"></table>
	<div class="btn_area">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">
			<span class="paltform-title">保存</span>
		</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="top.closeDlg('dlgc')">
			<span class="paltform-title">取消</span> 
		</a>
    </div>
<script type="text/javascript">
var IsCheckFlag = false;
$(function(){
	$("#role_table").datagrid({    
		url:"${ctxAdmin}/sys/role/list/1",
	    idField : "id",   //表明该列是一个唯一列
		width: "auto",
		height:"232",
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
		singleSelect:false,
	    columns:[[    
			{field:'ck',checkbox:true},
			{field:'id',title:'id',hidden:true},
			{field:'code',title:'编码'},
			{field:'cname',title:'中文名称'},
			{field:'ename',title:'英文名称'},
			{field:'inst',title:'描述'}
	    ]],
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    enableRowContextMenu: false,
	    onLoadSuccess:function(){
	    	//获取用户拥有角色,选中
	    	$.ajax({
	    		async:false,
				type:"get",
				url:"${ctxAdmin}/sys/user/${userId}/role",
				success: function(data){
					if(data){
						for(var i=0,j=data.length;i<j;i++){
							$("#role_table").datagrid("selectRecord",data[i]);
						}
					} 
				}
			});
	    },
	    onSelect: function (rowIndex, rowData) {  
	    	if(IsCheckFlag){
	    		IsCheckFlag = false;
	    		$("#role_table").datagrid("unselectRow",rowIndex);  
	    	}else{
	    		IsCheckFlag = true;
	    	}
        }
	});
});

//查询
function searchQuery(){
	var queryForm = $("#searchForm").serializeObject();
	$("#role_table").datagrid("load",queryForm); 
}

//保存用户角色
function save(){
	var newRoleList=[];
	var data = $("#role_table").datagrid("getSelections");
	//所选的角色列表
	for(var i=0,j=data.length;i<j;i++){
		newRoleList.push(data[i].id);
	}
	
	$.ajax({
		async:false,
		type:"POST",
		data:JSON.stringify(newRoleList),
		contentType:"application/json;charset=utf-8",	//必须
		url:"${ctxAdmin}/sys/user/${userId}/updateRole",
		success: function(data){
			if(data=='success'){
				$.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgc");
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