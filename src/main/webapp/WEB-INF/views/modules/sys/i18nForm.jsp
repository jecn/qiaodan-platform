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
		<input type="hidden" name="id" value="${i18n.id}">
		<table align="center" class="edittable">
			<tr>
				<td width="15%">语言编码：</td>
				<td width="35%">
					<select id="langCode" name="langCode" class="easyui-validatebox select" >
						<option value="zh_cn">zh_cn</option>
						<option value="en">en</option>
					</select>
				</td>
				<td width="15%">标签：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="langKey" name="langKey" value="${i18n.langKey}" class="easyui-validatebox input_bg" maxlength="80" 
					data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/i18n/checkKey?id=${i18n.id}\',\'langKey\',\'1\',\'该编码下的标签已存在\',\'langCode\']']" /></td>
			</tr>
			<tr>
				<td width="15%">值：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="langValue" name="langValue"  value="${i18n.langValue}" class="easyui-validatebox input_bg" maxlength="160" data-options="required:true"/></td>
			</tr>
			<tr>
				<td width="15%">备注：</td>
				<td colspan="3" width="85%">
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${i18n.remarks}</textarea>
				</td>
			</tr>
		</table>
		
		<div class="btn_area">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">
				<span class="paltform-title">保存</span>
			</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="top.closeDlg('dlgb')">
				<span class="paltform-title">取消</span> 
			</a>
		</div>
	</form>
</div>
<script type="text/javascript">
$(function(){
	var id="${i18n.id}";
	if(!Utils.isEmpty(id)){
		 $("#langCode").val("${i18n.langCode}");
	}
});

//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/i18n/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("sys/i18n", "#mainTable");
				});
			} else {
				$.messager.alert("提示","保存失败","error",function(){
					$("#btn_save").removeAttr("disabled");
				});
			}  
   		},
		error:function(data){
			$.messager.alert("提示","保存失败","error",function(){
				$("#btn_save").removeAttr("disabled");
			});
		}
	});
}
</script>
</body>
</html>