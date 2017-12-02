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

<script src="${ctxStatic}/plugins/jquery/json2.js"></script>
<!-- ztree扩展 -->
<script src="${ctxStatic}/plugins/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctxStatic}/plugins/jquery-ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
<script src="${ctxStatic}/plugins/jquery-safari/list.js" type="text/javascript"></script>
</head>
<body> 
	<div id="roleLayout" class="easyui-layout hidden" data-options="fit: true">
	<input type="hidden" id="linsence" value="4028ce815ad54045015ad54045a40001">  
	<select id="role" name="role" style="display: none">
		<c:forEach var="item" items="${fns:getRoleList()}">  
			<option value="" ></option>
            <option value="${item.id}"> ${item.cname}</option>  
       	</c:forEach>
	</select>
    <div id="roleList" data-options="region:'center',split:true,border:false,title:'角色列表'">
		<div id="role_toolbar" ></div>
		<table id="role_table" ></table>	
    </div> 
    
      
    <div id="privilegeList" data-options="region:'east',split:true,collapsed: true,border:false,title:'权限列表'" style="width: 425px">
    	<div id="pri_toolbar" style="border-bottom:1px solid #95B8E7 ;margin:3px 5px">
		    <div>
		    	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">
		    		<span id="platform.list.save" class="platform-title">保存授权</span>
		    	</a>
		    	<span class="toolbar-item dialog-tool-separator"></span>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="back()">
		        	<span id="platform.list.cancel" class="platform-title">恢复</span>
		        </a>
		    </div>
		</div>
		
    	<table id="pri_table" class="ztree" style="height:auto;"></table>
    </div>   
 </div>
<script type="text/javascript">
var tree;
var roleId;
var rolePerData = null;
var IsCheckFlag = false;
var basePath = '${ctxAdmin}';
var setting = {
    	check: { 
    		enable: true,
			nocheckInherit: true
			},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid"
			},
			key: {
				name: "name"
			}
		}
	};
$(function(){
	 toolbar("#role_toolbar");
	 loadGrid();
	 loadPrivilege();
	 
});

/**
 * 加载表格数据
 */
function loadGrid(){
	var tableHeight = gridHeight();
	$("#role_table").datagrid({    
		    url:"${ctxAdmin}/sys/role/list/-1",
            height: tableHeight-28,
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
			           {field:'cname',title:'中文名称',sortable:false},
			           {field:'ename',title:'英文名称',sortable:false},
// 			           {field:'code',title:'编码',sortable:false},
			           {field:'useableValue',title:'是否可用',sortable:false},
			           {field:'inst',title:'描述',sortable:false},
			           {field:'action',title:'操作',
							formatter : function(value, row, index) {
								var opt = '<a style="display:inline-block;margin-top:5px" href="javascript:look(\''+row.id+'\')"><div class="icon-lock-go" style="width:16px;height:16px;display:inline-block" title="查看权限"></div></a>';
								if(row.useable == '1'){
									opt += '&nbsp;';
									opt += '<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'0\')"><div class="icon-lock" style="width:16px;height:16px;display:inline-block" title="禁用"></div></a>';
								}else if(row.useable == '0'){
									opt += '&nbsp;';
									opt += '<a style="display:inline-block;margin-top:5px" href="javascript:opt(\''+row.id+'\',\'1\')"><div class="icon-lock-open" style="width:16px;height:16px;display:inline-block" title="启用"></div></a>';
								}
								return opt;
							}
				        }
			         ]],
			toolbar:"#role_toolbar",
			enableHeaderClickMenu: false,
		    enableHeaderContextMenu: false,
		    enableRowContextMenu: false,
		    onLoadSuccess: function (data) { 
		    	$("#role_table").datagrid('clearSelections');  //清除已选项
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
}

function loadPrivilege(){
	$.ajax({
		async : false,
		url : "${ctxAdmin}/sys/privilege/tree",
		type : "GET",
		dataType : "json",
		success : function(data) {
			tree = $.fn.zTree.init($("#pri_table"), setting, data);
		}
	});
}

//查看
function view(){
	var row = $("#role_table").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/role/view/" + row.id ,"dlgb","查看",520,116);
}

//新增
function add(){
	top.getDlg("${ctxAdmin}/sys/role/create/" ,"dlgb","新增",520,258);
}

//修改
function update(){
	var row = $("#role_table").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	top.getDlg("${ctxAdmin}/sys/role/update/" + row.id ,"dlgb","修改",520,258);
}

//删除
function del(){
	var row = $("#role_table").datagrid("getSelected");
	if(rowIsNull(row)) {
		return;
	}
	$.messager.confirm("删除确认", "您确定要删除选择的记录吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/sys/role/delete/"+row.id,
				success: function(data){
					if(data == "success"){
						$("#role_table").datagrid("reload");	// reload the user data
						$("#role_table").datagrid("clearSelections"); //clear selected options
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

//查看权限
function look(id){
	$("#role").val(id);
	roleId = id;
	var panel = $("#roleLayout").layout("panel", "east")[0].clientWidth; 
	if (panel <= 0) {
		$("#roleLayout").layout("expand", "east");
	} 
    loadTree();
}

function loadTree(){
    //清除已选
	var treeObj = $.fn.zTree.getZTreeObj("pri_table");
	treeObj.checkAllNodes(false);
	
	//获取角色拥有权限
	$.ajax({
		async:false,
		type:"get",
		url:"${ctxAdmin}/sys/role/"+roleId+"/json",
		success: function(data){
			for(var i=0,j=data.length;i<j;i++){
				var nodes = tree.getNodesByParam("id", data[i], null);//根据org的ID值获取符合的节点
				for (var d=0, b=nodes.length; d < b; d++) {//对符合的节点进行遍历勾选
					tree.checkNode(nodes[d], true  , false);
				}
			}
		}
	});
}

function opt(id,useable){
	var op = "";
	if(useable == '0'){
		op = "禁用";
	}else if(useable == '1'){
		op = "启用";
	}
	
	$.messager.confirm(op + "确认", "您确定要" + op +  "选择的记录吗？", function(data){
		if (data){
			$.ajax({
				type:"get",
				url:"${ctxAdmin}/sys/role/"+id +"/opt?useable="+useable,
				success: function(data){
					if(data == "success"){
						$("#role_table").datagrid("reload");	// reload the user data
						$("#role_table").datagrid("clearSelections"); //clear selected options
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

//保存授权
function save(){
	if(Utils.isEmpty(roleId)) {
		$.messager.alert("提示","请选择角色","error");
		return;
	}
	
	var userRole = $("#role").val();
	if(userRole == roleId && roleId != '1'){
		$.messager.alert("提示","不能给自己授权","error");
		return;
	}
	
	
	var nodes = tree.getCheckedNodes(true);  //选中
	
	$.messager.confirm("提示", "确认要保存修改吗？", function(data){
		if (data){
			var newPrivileges=[];
			for(var i=0,j=nodes.length;i<j;i++){
				newPrivileges.push(nodes[i].id);
			}
			
			$.ajax({
				async:false,
				type:'POST',
				data:JSON.stringify(newPrivileges),
				contentType:"application/json;charset=utf-8",
				url:"${ctxAdmin}/sys/role/"+roleId+"/updatePrivilege",
				success: function(data){
					if(data == "success"){
						$.messager.alert("提示","保存成功","info",function(){
							$("#roleLayout").layout("collapse", "east"); 
						});
					}else{
						$.messager.alert("提示","保存失败","error");
					}
				},
				error:function(data){
					$.messager.alert("提示",op + "失败","error");
				}
			});
		} 
	});
}

//撤销
function back(){
	loadTree();
}
</script>
</body>
</html>