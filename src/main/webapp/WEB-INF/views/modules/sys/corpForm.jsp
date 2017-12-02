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
<div class="form-corp" >
	<form id="mainForm" method="post">
		<input type="hidden" name="id" value="${corp.id}">
		<table align="center" class="edittable">
			<tr>
				<td width="15%">名称：<span class="safari-required">*</span></td>
				<td colspan="3" width="85%"><input type="text" id="name" name="name" value="${corp.name}" class="easyui-validatebox input_bg" maxlength="128" 
					data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/corp/checkName?id=${corp.id}\',\'name\',\'1\',\'名称已存在\']']" /></td>
			</tr>
			<tr>
				<td width="15%">负责人：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="leader" name="leader" value="${corp.leader}" class="easyui-validatebox input_bg" maxlength="48"  data-options="required:true"/></td>
				<td width="15%">机构编码：</td>
				<td width="35%"><input type="text" id="orgCode" name="orgCode" value="${corp.orgCode}" class="easyui-validatebox input_bg" maxlength="48"/></td>
			</tr>
			<tr>
				<td width="15%">单位类型：</td>
				<td width="35%">
					<select id="type" name="type"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getDictList('sys_corp_type')}">  
                            <option value="${item.value}" <c:if test="${item.label eq corp.type}">selected</c:if>> ${item.label}</option>  
                       	</c:forEach>
					</select>
				</td>
				<td width="15%">单位性质：</td>
				<td width="35%">
					<select id="lnc" name="lnc" class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getDictList('sys_corp_lnc')}">  
                            <option value="${item.value}" <c:if test="${item.label eq corp.lnc}">selected</c:if>> ${item.label}</option>  
                       	</c:forEach>
                 	</select>
				</td>
			</tr>
				<td width="15%">电话：</td>
				<td width="35%"><input type="text" id="tel" name="tel" value="${corp.tel}" class="easyui-validatebox input_bg" maxlength="20"  data-options="validType:['tel']"/></td>
				<td width="15%">传真：</td>
				<td width="35%"><input type="text" id="fax" name="fax" value="${corp.fax}" class="easyui-validatebox input_bg" maxlength="20" data-options="validType:['fax']"/></td>
			</tr>
			<tr>
				<td width="15%">电子邮箱：</td>
				<td width="35%"><input type="text" id="email" name="email" value="${corp.email}" class="easyui-validatebox input_bg" maxlength="80" data-options="validType:['email']"/></td>
				<td width="15%">邮编：</td>
				<td width="35%" ><input type="text" id="zipCode" name="zipCode" value="${corp.zipCode}" class="easyui-validatebox input_bg" maxlength="10" data-options="validType:['zip']"/></td>
			</tr>
			<tr>
				<td width="15%">规模：</td>
				<td width="35%">
					<select id="scale" name="scale" class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getDictList('sys_corp_scale')}">  
                            <option value="${item.value}" <c:if test="${item.label eq corp.scale}">selected</c:if>> ${item.label}</option>  
                       	</c:forEach>
					</select>
				</td>
				<td width="15%">区域：</td>
				<td width="35%"><select type="text" id="area.id" name="area.id" value="${corp.area.name}" class="easyui-validatebox input_bg" maxlength="64"/></td>
			</tr>
			<tr>
				<td width="15%">地址：</td>
				<td colspan="3" width="85%">
					<textarea rows="3" cols="40" name="address"  maxlength="128">${corp.address}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%">备注：</td>
				<td colspan="3" width="85%">
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${corp.remarks}</textarea>
				</td>
			</tr>
		</table>
		
		<div class="btn_area">
			<a href="#" id="btn_save" class="easyui-linkbutton" iconCls="icon-ok" onclick="save();">
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
});

//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/corp/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("sys/corp", "#mainTable");
				});
			} else {
				$.messager.alert("提示","保存失败","error",function(){
					$("#btn_save").removeAttr("disabled");
				});
			}  
		}
	});
}
</script>
</body>
</html>