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
	<input type="hidden" id="linsence" value="4028ce815bd7bf7b015bd7ced6b70004">
	<div id="query-panel" class="easyui-panel" collapsible="false" 
		title ="会员搜索" collapsed="false" >
		<form method="post" id="searchForm" onkeydown="if(event.keyCode==13){return false;}">
				<table class="querytable" align="center">
					<tr>
						<td style="width: 10%">
							<span class="platform-title">用户名：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input id="username" name="username" class="easyui-validatebox input_bg" maxlength="20" />
						</td>
						
						<td style="width: 10%">
							<span class="platform-title">手机号：</span>
						</td>
						<td style="width: 20%" class="platform-input">
							<input id="mobile" name="mobile" class="easyui-validatebox input_bg" maxlength="20" />
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
			title:"会员列表",
		    url:"${ctxAdmin}/user/vip/list",
            height: tableHeight-30,
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
			           {field:'username',title:'用户名',sortable:false},
			           {field:'mobile',title:'手机号',sortable:false},
			           {field:'name',title:'姓名',sortable:false},
			           {field:'nick',title:'昵称',sortable:false},
			           {field:'gender',title:'性别',sortable:false},
			           {field:'age',title:'年龄',sortable:false},
			           {field:'birthday',title:'出生年月',sortable:false},
			           {field:'email',title:'电子邮箱',sortable:false},
			           {field:'position',title:'擅长位置',sortable:false},
			           {field:'weight',title:'体重',sortable:false},
			           {field:'height',title:'身高',sortable:false},
			           {field:'footer',title:'左右脚',sortable:false},
			           {field:'stat',title:'状态',sortable:false},
			           {field:'action',title:'操作',
							formatter : function(value, row, index) {
								var opt =  '<a style="display:inline-block;margin-top:5px" href="javascript:resetpw(\''+row.id+'\')"><div class="icon-resetpwd" style="width:16px;height:16px;display:inline-block" title="重置密码"></div></a>';
								if(row.stat == '正常使用'){
									opt += '&nbsp;<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'2\')"><div class="icon-lock" style="width:16px;height:16px;display:inline-block" title="暂停使用"></div></a>';
								}else if(row.stat == '暂停使用'){
									opt += '&nbsp;<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'1\')"><div class="icon-lock-open" style="width:16px;height:16px;display:inline-block" title="正常使用"></div></a>';
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
	top.getDlg("${ctxAdmin}/user/vip/view/" + row.id ,"dlgb","查看",520,63);
}

//重置密码
function resetpw(id){
	$.messager.confirm("确认", "您确定要对选择的记录重置密码吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/user/vip/resetpw/"+id,
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

//禁用/启用
function opt(id,stat){
	var op = "";
	if(stat == '2'){
		op = "暂停使用";
	}else if(stat == '1'){
		op = "正常使用";
	}
	
	$.messager.confirm(op + "确认", "您确定要" + op +  "该记录吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/user/vip/"+id +"/opt?stat="+stat,
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