<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/detail.css">
</head>
<body>
<div>
	<form id="viewForm" method="post" modelAttribute="user">
	  <table class="detailtable">
	    <tr>
			<th width="15%">父名称：</th>
			<td width="35%"><span id="pid" name="pid" >${privilege.parent.name}</span></td>
			<th width="15%">名称：</th>
			<td width="35%"><span id="name" name="name" >${privilege.name}</span></td>
		</tr>
		<tr>
			<th width="15%">编号：</th>
			<td width="35%"><span id="code" name="code" >${privilege.code}</span></td>
			<th width="15%">类型：</th>
			<td width="35%"><span id="type" name="type" >${privilege.type}</span></td>
		</tr>
		<tr>
			<th width="15%">排序：</th>
			<td width="35%"><span id="sort" name="sort" >${privilege.sort}</span></td>
			<th width="15%">图标：</th>
			<td width="35%"><span id="icon" name="icon" >${privilege.icon}</span></td>
		</tr>
		<tr>
			<th width="15%">链接：</th>
			<td width="35%"><span id="url" name="url" > ${privilege.url}</span></td>
			<th width="15%">是否可用：</th>
			<td width="35%"><span id="useableValue" name="useableValue" >${privilege.useableValue}</span></td>
		</tr>
		<tr>
			<th width="15%">描述：</th>
			<td width="85%" colspan="3"><span id="useableValue" name="useableValue" >${privilege.inst}</span></td>
		</tr>
		<tr>
			<th width="15%">备注：</th>
			<td width="85%" colspan="3"><span id="remarks" name="remarks" >${privilege.remarks}</span></td>
		</tr>
	</table>
	</form>
</div> 
<script type="text/javascript">
//父级权限
$('#pid').val(parentPermId);

//菜单类型
$('#type').combobox({  
	width:180,
	panelHeight:50
});  

//上级权限
$('#pid').combotree({
	width:180,
	method:'GET',
	url: '${ctxAdmin}/sys/permission/menu/json',
    idField : 'id',
    textFiled : 'name',
	parentField : 'pid',
	iconCls: 'icon',
    animate:true
});  

$('#mainform').form({    
    onSubmit: function(){    
    	var isValid = $(this).form('validate');
		return isValid;	// 返回false终止表单提交
    },    
    success:function(data){   
    	successTip(data,dg,d);
    }    
});   


</script>
</body>
</html>