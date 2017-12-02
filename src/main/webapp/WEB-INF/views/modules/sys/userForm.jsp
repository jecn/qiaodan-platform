<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/save.css">
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/validate_class.js"></script>
</head>
<body>
<div class="form-area" >
	<form id="mainForm" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
		<table align="center" class="edittable">
			<tr>
				<td width="15%">用户名：<span class="safari-required">*</span></td>
				<td width="35%">
					<c:choose> 
						<c:when test="${empty user.id}"> 
							<input type="text" id="username" name="username" value="${user.username}" class="easyui-validatebox input_bg" maxlength="20" 
								data-options="required:true,validType:['username','unique[\'${ctxAdmin}/sys/user/checkUsername?id=${user.id}\',\'username\',\'6\',\'用户名已存在\']']" />
						</c:when> 
						<c:otherwise> 
							<input type="text"  id="username" name="username" value="${user.username}" class="easyui-validatebox input_bg" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td width="15%">姓名：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text"  id="name" name="name" value="${user.name}" class="easyui-validatebox input_bg" maxlength="20" data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="15%">出生年月：</td>
				<td width="35%"><input type="text" id="birthday" name="birthday"  value="${user.birthday}"  class="easyui-validatebox input_bg" 
					class="Wdate"  onclick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/></td>
				<td width="15%">性别：</td>
				<td width="35%" style="text-align:left">
					<input type="radio" id="man" name="gender" value="1"/>男 &nbsp;
					<input type="radio" id="woman" name="gender" value="0"/>女
				</td>
			</tr>
			<tr>
				<td width="15%">座机：</td>
				<td width="35%"><input type="text" name="tel" value="${user.tel}" class="easyui-validatebox input_bg" maxlength="20" data-options="validType:['tel']" /></td>
				<td width="15%">手机号：</td>
				<td width="35%"><input type="text" name="mobile" value="${user.mobile}" class="easyui-validatebox input_bg" maxlength="20" data-options="validType:['mobile']" /></td>
			</tr>
			<tr>
				<td width="15%">电子邮件：</td>
				<td width="35%"><input type="text" name="email" value="${user.email }" class="easyui-validatebox input_bg" maxlength="80" data-options="validType:['email']" /></td>
			</tr>
			<tr>
				<td width="15%">备注：</td>
				<td colspan="3" width="85%">
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${user.remarks}</textarea>
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
var id="${user.id}";
//用户 添加修改区分
if(Utils.isEmpty(id)){
	$("input[name='gender'][value=1]").attr("checked",true); 
}else{
	$("input[name='username']").attr("readonly","readonly");
	$("input[name='gender'][value=${user.sex}]").attr("checked",true);
}

//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/user/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("sys/user", "#mainTable");
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