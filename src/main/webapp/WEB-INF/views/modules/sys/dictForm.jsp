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
	<form id="mainForm"  method="post">
		<input type="hidden" name="id" value="${dict.id}">
		<table align="center" class="edittable">
			<tr>
				<td width="15%">所属模块：</td>
				<td width="35%">
					<select id="module" name="module"  class="easyui-validatebox select" >
						<c:forEach var="item" items="${modules}">  
                              <option value="${item.id}" <c:if test="${item.id eq dict.module}">selected</c:if> > ${item.name}</option>  
                         </c:forEach>  
					</select>
				</td>
				<td width="15%">类型：<span class="safari-required">*</span></td>
				<td width="35%">
					<c:choose> 
						<c:when test="${empty dict.id}"> 
							<input type="text" id="type" name="type" class="easyui-validatebox input_bg" maxlength="80" data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/dict/checkType?id=${dict.id}\',\'type\',\'1\']']" />
						</c:when> 
						<c:otherwise>
							<c:if test="${isAdmin eq '1'}"> 
								<input type="text" id="type" name="type" value="${dict.type}" class="easyui-validatebox input_bg" maxlength="80" data-options="required:true" />
							</c:if>
							<c:if test="${isAdmin ne '1'}"> 
								<input type="text" id="type" name="type" value="${dict.type}" class="easyui-validatebox input_bg" maxlength="80" readonly="readonly"/>
							</c:if>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="15%">值：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text"  id="value" name="value" value="${dict.value}" class="easyui-validatebox input_bg" maxlength="80" data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/dict/checkValue?id=${dict.id}\',\'value\',\'1\',\'该类型的值已存在\',\'type\']']" /></td>
				<td width="15%">标签：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="label" name="label"  value="${dict.label}" class="easyui-validatebox input_bg" maxlength="80" data-options="required:true"/></td>
			</tr>
			<tr>
				<td width="15%">排序：</td>
				<td width="35%"><input type="text" id="sort" name="sort"  value="${dict.sort}" class="easyui-validatebox input_bg" /></td>
				<td width="15%">是否可用：</td>
				<td  width="35%">
					<c:choose> 
						<c:when test="${empty dict.id}"> 
							<select type="text" id="useable" name="useable"  class="easyui-validatebox select">
								<option value="1">是</option>
								<option value="0">否</option>
							</select> 
						</c:when> 
						<c:otherwise> 
							<input type="hidden"  id="useable" name="useable" value="${dict.useable}" class="easyui-validatebox input_bg" readonly="readonly"/>
							<input type="text"  id="useableValue" name="useableValue" value="${dict.useableValue}" class="easyui-validatebox input_bg" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="15%">描述：</td>
				<td colspan="3" width="85%">
					<textarea rows="3" cols="40" name="inst"  maxlength="128">${dict.inst}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%">备注：</td>
				<td colspan="3" width="85%">
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${dict.remarks}</textarea>
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
		url:"${ctxAdmin}/sys/dict/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("sys/dict", "#mainTable");
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