<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
</head>
<body>
<div>
	<form id="mainform" action="${ctx }/sys/scheduleJob/add" method="post">
		<table class="formTable">
			<tr>
				<td>任务名：</td>
				<td>
					<input type="hidden" name="id" value="${id }"/>
					<input id="name" name="name" class="easyui-validatebox" data-options="width: 150,required:'required'"> 
				</td>
			</tr>
			<tr>
				<td>任务组：</td>
				<td><input id="group" name="group" type="text" class="easyui-validatebox" data-options="width: 150,required:'required'"/></td>
			</tr>
			<tr>
				<td>表达式：</td>
				<td><input id="cronExpression" name="cronExpression" type="text" class="easyui-validatebox" data-options="width: 150,required:'required'"/></td>
			</tr>
			<tr>
				<td>任务类：</td>
				<td><input name="className" type="text" class="easyui-validatebox" data-options="width: 150,required:'required'"/></td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
//提交表单
$('#mainform').form({    
    onSubmit: function(){    
    	var isValid = $(this).form('validate');
		return isValid;	// 返回false终止表单提交
    },    
    success:function(data){   
    	if(data=='success'){
	    	dg.datagrid('reload');
			d.panel('close');
			parent.$.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
    	}else{
    		alert(data)
    	}
    }    
});    
</script>
</body>
</html>