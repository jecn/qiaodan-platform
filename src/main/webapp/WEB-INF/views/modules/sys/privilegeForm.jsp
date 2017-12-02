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
<div class="form-privilege">
	<form id="mainForm" method="post">
		<input type="hidden" name="id" value="${privilege.id}">
		<table align="center" class="edittable">
		<tr>
			<td width="15%">上级权限：</td>
			<td width="35%">
				<input type="hidden" id="pid" name="pid"  value="${privilege.pid}"/>
				<c:choose> 
					<c:when test="${empty privilege.pid}"> 
						<input type="text" id="pname" name="pname"  class="easyui-validatebox input_bg" onClick="choose.openWindow('pname','pid','${ctxAdmin}/sys/privilege/treeSelect/-1?type=12&iframeId=iframeb','权限');"  />
					</c:when>
					<c:when test="${privilege.pid eq '0'}"> 
						<input type="text" id="pname" name="pname"  class="easyui-validatebox input_bg" onClick="choose.openWindow('pname','pid','${ctxAdmin}/sys/privilege/treeSelect/-1?type=12&iframeId=iframeb','权限');"  />
					</c:when> 
					<c:otherwise> 
						<input type="text" id="pname" name="pname"  value="${privilege.parent.name}" class="easyui-validatebox input_bg" onClick="choose.openWindow('pname','pid','${ctxAdmin}/sys/privilege/treeSelect/-1?type=12&iframeId=iframeb','权限');"  />
					</c:otherwise>
				</c:choose>
			</td>
			<td width="15%">名称：<span class="safari-required">*</span></td>
			<td width="35%"><input type="text"  id="name" name="name" value="${privilege.name}" class="easyui-validatebox input_bg" maxlength="50" 
				data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/privilege/checkName?id=${privilege.id}\',\'name\',\'1\',\'该名称已存在\',\'pid\']']" /></td>
		</tr>
		<tr>
			<td width="15%">编号：</td>
			<td width="35%"><input type="text" id="code" name="code" value="${privilege.code}" class="easyui-validatebox input_bg" maxlength="50" 
				data-options="validType:['unique[\'${ctxAdmin}/sys/privilege/checkCode?id=${privilege.id}\',\'code\',\'1\',\'该编号已存在\']']" /></td>
			<td width="15%">类型：</td>
			<td width="35%">
				<select id="type" name="type"  class="easyui-validatebox select" >
					<option value="" >--请选择--</option>
					<c:forEach var="item" items="${fns:getDictList('sys_privilege_type')}">  
                        <option value="${item.value}" <c:if test="${item.label eq privilege.type}">selected</c:if>>${item.label}</option>  
                   	</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="15%">排序：</td>
			<td width="35%"><input type="text" id="sort" name="sort"  value="${privilege.sort}" class="easyui-validatebox input_bg" maxlength="6" /></td>
			<td width="15%">图标：</td>
			<td width="35%"><input type="text" id="icon" name="icon" value="${privilege.icon}" class="easyui-validatebox input_bg" maxlength="128" /></td>
		</tr>
		<tr>
			<td width="15%">链接：</td>
			<td width="35%"><input  type="text" id="url" name="url" value="${privilege.url}" class="easyui-validatebox input_bg" maxlength="128" /></td>
			<td width="15%">是否可用：</td>
			<td  width="35%">
				<c:choose> 
					<c:when test="${empty privilege.id}"> 
						<select type="text" id="useable" name="useable"  class="easyui-validatebox select">
							<option value="1">是</option>
							<option value="0">否</option>
						</select> 
					</c:when> 
					<c:otherwise> 
						<input type="hidden"  id="useable" name="useable" value="${privilege.useable}" class="easyui-validatebox input_bg" readonly="readonly"/>
						<input type="text"  id="useableValue" name="useableValue" value="${privilege.useableValue}" class="easyui-validatebox input_bg" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td width="15%">描述：</td>
			<td colspan="3" width="85%">
				<textarea rows="3" cols="40" name="inst"  maxlength="128">${privilege.inst}</textarea>
			</td>
		</tr>
		<tr>
			<td width="15%">备注：</td>
			<td colspan="3" width="85%">
				<textarea rows="5" cols="40" name="remarks"  maxlength="255">${privilege.remarks}</textarea>
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

//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/privilege/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadTreegrid("sys/privilege", "#mainTable");
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