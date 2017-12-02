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
		<input type="hidden" name="id" value="${role.id}">
		<table align="center" class="edittable">
			<td width="15%">中文名称：</td>
			<td  width="35%"><input type="text" id="cname" name="cname"  value="${role.cname}" class="easyui-validatebox input_bg" maxlength="32"
			 data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/role/checkCname?id=${role.id}\',\'cname\',\'1\',\'中文名称已存在\']']" /></td>
			<td width="15%">英文名称：</td>
			<td  width="35%"><input type="text" id="ename" name="ename"  value="${role.ename}" class="easyui-validatebox input_bg" maxlength="48" 
			 data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/role/checkEname?id=${role.id}\',\'ename\',\'1\',\'英文名称已存在\']']" /></td>
		</tr>
		<tr>
<!-- 			<td width="15%">编码：</td> -->
<!-- 			<td  width="35%"><input type="text"  id="code" name="code" value="${role.code}" class="easyui-validatebox input_bg"  maxlength="32"/></td> -->
			<td width="15%">是否可用：</td>
			<td  width="35%">
				<c:choose> 
					<c:when test="${empty role.id}"> 
						<select id="useable" name="useable"  class="easyui-validatebox select">
							<option value="1">是</option>
							<option value="0">否</option>
						</select> 
					</c:when> 
					<c:otherwise> 
						<input type="hidden"  id="useable" name="useable" value="${role.useable}" class="easyui-validatebox input_bg" readonly="readonly"/>
						<input type="text"  id="useableValue" name="useableValue" value="${role.useableValue}" class="easyui-validatebox input_bg" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td width="15%">描述：</td>
			<td colspan="3" width="85%">
				<textarea rows="3" cols="40" name="inst"  maxlength="128">${role.inst}</textarea>
			</td>
		</tr>
		<tr>
			<td width="15%">备注：</td>
			<td colspan="3" width="85%">
				<textarea rows="5" cols="40" name="remarks"  maxlength="255">${role.remarks}</textarea>
			</td>
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
//用户 添加修改区分
if(!Utils.isEmpty($("#id").val())){
	$("#code").attr("readonly",true);
}

//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/role/save",
      	onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
 		},
 		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("sys/role", "#role_table");
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