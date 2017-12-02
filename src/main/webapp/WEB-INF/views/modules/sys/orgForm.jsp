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
<div class="form-org" >
	<form id="mainForm"  method="post">
		<input type="hidden" name="id" value="${org.id}">
		<table align="center" class="edittable">
			<tr>
				<td width="15%">上级机构：</td>
				<td colspan="3" width="85%">
					<input type="hidden" id="pid" name="pid"  value="${org.pid}" />
					<input type="text" id="pname" name="pname"  value="${org.parent.cname}" class="easyui-validatebox input_bg" onClick="choose.openWindow('pname','pid','${ctxAdmin}/sys/org/treeSelect/-1?iframeId=iframeb','机构');"  />
				</td>
			</tr>
			<tr>
				<td width="15%">中文名称：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="cname" name="cname" value="${org.cname}" class="easyui-validatebox input_bg" maxlength="32" data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/org/checkCname?id=${org.id}\',\'cname\',\'1\']']" /></td>
				<td width="15%">英文名称：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="ename" name="ename"  value="${org.ename}" class="easyui-validatebox input_bg" maxlength="48" data-options="required:true,validType:['unique[\'${ctxAdmin}/sys/org/checkEname?id=${org.id}\',\'ename\',\'1\']']" /></td>
			</tr>
			<tr>
				<td width="15%">类型：</td>
				<td width="35%">
					<select id="type" name="type" class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getDictList('sys_org_type')}">  
                            <option value="${item.value}" <c:if test="${item.label eq org.type}">selected</c:if>> ${item.label}</option>  
                       	</c:forEach>
                 	</select>
				</td>
				<td width="15%">等级：</td>
				<td width="35%">
					<select id="grade" name="grade" class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getDictList('sys_org_grade')}">  
                            <option value="${item.value}" <c:if test="${item.label eq org.grade}">selected</c:if>> ${item.label}</option>  
                       	</c:forEach>
                 	</select>
				</td>
			</tr>
			<tr>
				<td width="15%">联系电话：</td>
				<td width="35%"><input type="text" id="tel" name="tel" value="${org.tel}" class="easyui-validatebox input_bg" maxlength="20" data-options="validType:['tel']" /></td>
				<td width="15%">传真：</td>
				<td width="35%"><input type="text" id="fax" name="fax" value="${org.fax}" class="easyui-validatebox input_bg" maxlength="20" data-options="validType:['fax']" /></td>
			</tr>
			<tr>
				<td width="15%">电子邮件：</td>
				<td width="35%"><input type="text" id="email" name="email" value="${org.email}" class="easyui-validatebox input_bg" maxlength="80" data-options="validType:['email']" /></td>
				<td width="15%">是否可用：</td>
				<td  width="35%">
					<c:choose> 
						<c:when test="${empty org.id}"> 
							<select type="text" id="useable" name="useable"  class="easyui-validatebox input_bg select">
								<option value="1">是</option>
								<option value="0">否</option>
							</select> 
						</c:when> 
						<c:otherwise> 
							<input type="hidden"  id="useable" name="useable" value="${org.useable}" class="easyui-validatebox input_bg " readonly="readonly" />
							<input type="text"  id="useableValue" name="useableValue" value="${org.useableValue}" class="easyui-validatebox input_bg " readonly="readonly" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="15%">地址：</td>
				<td colspan="3" width="85%">
					<textarea rows="2" cols="40" name="address"  maxlength="160">${org.address}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%">描述：</td>
				<td colspan="3" width="85%">
					<textarea rows="3" cols="40" name="inst"  maxlength="120">${org.inst}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%">备注：</td>
				<td colspan="3" width="85%">
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${org.remarks}</textarea>
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

function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/sys/org/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadTreegrid("sys/org", "#mainTable");
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