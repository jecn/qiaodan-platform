<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/save.css">
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/validate_class.js"></script>
</head>
<body>
<div class="form-area" >
	<form id="mainForm" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
		<table align="center" class="edittable">
			<tr>
				<td width="30%">用户名：<span class="safari-required">*</span></td>
				<td width="70%"><input type="password" id="oldPassword" name="oldPassword" class="easyui-validatebox input_bg" maxlength="20" required="required" /></td>
			</tr>
			<tr>
				<td width="30%">用户名：<span class="safari-required">*</span></td>
				<td width="70%"><input type="password" id="plainPassword" name="plainPassword" class="easyui-validatebox input_bg" maxlength="20" required="required" /></td>
			</tr>
			<tr>
				<td width="30%">用户名：<span class="safari-required">*</span></td>
				<td width="70%"><input type="password" id="confirmPassword" name="confirmPassword" class="easyui-validatebox input_bg" maxlength="20" data-options="required:true,validType:['same[\'plainPassword\']']"/></td>
			</tr>
		</table>
		
		<div class="btn_area">
			<a id="btn_save" href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">
				<span class="paltform-title">保存</span>
			</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="top.closeDlg('dlgb')">
				<span class="paltform-title">取消</span> 
			</a>
		</div>
	</form>
</div>

<script type="text/javascript">
//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/user/updatePwd",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","修改成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				});
			} else {
				$.messager.alert("提示","修改失败","error",function(){
					$("#btn_save").removeAttr("disabled");
				});
			}  
   		},
		error:function(data){
			$.messager.alert("提示","修改失败","error",function(){
				$("#btn_save").removeAttr("disabled");
			});
		}
	});
}
</script>
</body>
</html>