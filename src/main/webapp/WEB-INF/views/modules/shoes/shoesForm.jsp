<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/save.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/plugins/jquery-uploadify/scduploadify.css">
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-uploadify/scduploadify.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/validate_class.js"></script>
</head>
<body>
<div class="form-area">
	<form id="mainForm"  method="post" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${shoes.id}">
		<input type="hidden" id="thumb" name="thumb" value="${shoes.thumb}">
		<table align="center" class="edittable">
			<tr>
				<td width="15%">名称：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="name" name="name" value="${shoes.name}" class="easyui-validatebox input_bg" maxlength="128" data-options="required:true" /></td>
				<td width="15%">编号：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text" id="code" name="code" value="${shoes.code}" class="easyui-validatebox input_bg" maxlength="20" data-options="required:true,validType:['unique[\'${ctxAdmin}/shoes/main/checkCode?id=${shoes.id}\',\'code\',\'1\',\'该编号已存在\']']" /></td>
			</tr>
			 <tr>
				<td width="15%">款号：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text"  id="styleNumber" name="styleNumber" value="${shoes.styleNumber}" class="easyui-validatebox input_bg" maxlength="20" data-options="required:true" /></td>
				<td width="15%">价格：</td>
				<td width="35%"><input type="text" id="price" name="price"  value="${shoes.price}" class="easyui-validatebox input_bg" maxlength="20"  data-options="validType:['price']"/></td>
			</tr>
			<tr>
				<td width="15%">上市时间：</td>
				<td width="35%"><input type="text"  id="marketTime" name="marketTime" value="${shoes.marketTime}" class="easyui-validatebox input_bg" maxlength="48"  /></td>
				<td width="15%">颜色：</td>
				<td width="35%">
					<select id="color" name="color"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getShoesDictList('shoes_color')}">  
                            <option value="${item.label}" <c:if test="${item.value eq shoes.color}">selected</c:if>> ${item.value}</option>  
                       	</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%">尺寸：</td>
				<td width="35%"><input type="text"  id="size" name="size" value="${shoes.size}" class="easyui-validatebox input_bg" maxlength="512"  /></td>
				<td width="15%">适用人群：</td>
				<td width="35%">
					<select id="forPeople" name="forPeople"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getShoesDictList('shoes_people')}">  
                            <option value="${item.label}" <c:if test="${item.value eq shoes.forPeople}">selected</c:if>> ${item.value}</option>  
                       	</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%">适用场地：</td>
				<td width="35%">
					<select id="forSpace" name="forSpace"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getShoesDictList('shoes_space')}">  
                            <option value="${item.label}" <c:if test="${item.value eq shoes.forSpace}">selected</c:if>> ${item.value}</option>  
                       	</c:forEach>
					</select>
				</td>
				<td width="15%">适用位置：</td>
				<td width="35%">
					<select id="forPosition" name="forPosition"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getDictList('sys_shoes_position')}">  
                            <option value="${item.value}" <c:if test="${item.label eq shoes.forPosition}">selected</c:if>> ${item.label}</option>  
                       	</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%">款式：</td>
				<td width="35%">
					<select id="style" name="style"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getShoesDictList('shoes_style')}">  
                            <option value="${item.label}" <c:if test="${item.value eq shoes.style}">selected</c:if>> ${item.value}</option>  
                       	</c:forEach>
					</select>
				</td>
				<td width="15%">功能：</td>
				<td width="35%">
					<select id="function" name="function"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getShoesDictList('shoes_function')}">  
                            <option value="${item.label}" <c:if test="${item.value eq shoes.function}">selected</c:if>> ${item.value}</option>  
                       	</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%">简介：</td>
				<td colspan="3" width="85%">
					<textarea rows="3" cols="40" name="intro"  maxlength="128">${shoes.intro}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%">状态：</td>
				<td width="35%">
					<select id="stat" name="stat"  class="easyui-validatebox select" >
						<option value="" >--请选择--</option>
						<c:forEach var="item" items="${fns:getShoesDictList('shoes_stat')}">  
                            <option value="${item.label}" <c:if test="${item.value eq shoes.stat}">selected</c:if>> ${item.value}</option>  
                       	</c:forEach>
					</select>
				</td>
				<td width="15%">是否可用：</td>
				<td  width="35%">
					<c:choose> 
						<c:when test="${empty shoes.id}"> 
							<select type="text" id="useable" name="useable"  class="easyui-validatebox select">
								<option value="1">是</option>
								<option value="0">否</option>
							</select> 
						</c:when> 
						<c:otherwise> 
							<input type="hidden"  id="useable" name="useable" value="${shoes.useable}" class="easyui-validatebox input_bg" readonly="readonly"/>
							<input type="text"  id="useableValue" name="useableValue" value="${shoes.useableValue}" class="easyui-validatebox input_bg" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="15%">缩略图：</td>
				<td colspan="3"  width="85%">
					<table id="scduploadifytable" class="uploadifytable">
						<tr>
							<td><div id="scduploadifydiv"></div></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="15%">备注：</td>
				<td colspan="3" width="85%">
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${shoes.remarks}</textarea>
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
var thumb = '${shoes.thumb}';
var basePath = '${ctxAdmin}';
$(function(){
	chooseNotype(thumb);
});

function chooseNotype(id) {
	$("#scduploadifydiv").scduploadify({
		//一定要指定的参数
		'filehandlerurl' : basePath + "/sys/media/filehandler/", //附件操作的的url
		'uploader' : "${ctxStatic}/plugins/jquery-uploadify/uploadify.swf", //以下几个参数要修改路径
		'script' : "${ctxStatic}/plugins/jquery-uploadify/scduploadify.jsp",
		'buttonImg' : "${ctxStatic}/plugins/jquery-uploadify/selectfile.jpg",
		//可选参数
		'mode' : "add",
		'ids' : "" + id + "",
		'reftno' :"thumb",
		'multi': false,
		'sizeLimit' : 2 * 1024 * 1024 ,
		'queueSizeLimit':1,
		'filetype':[{"name":"图片","value":"2","fileext":"*.png;*.jpg;*.jpeg;*.bmp","accept":"image/png,image/jpeg,image/bmp"}],
		'fields' : [ 
		             {"name" : "文件名称","value" : "name"}, 
		             {"name" : "文件类型","value" : "type"}, 
		             {"name" : "文件大小","value" : "size"},
		    		 {"name" : "操作","value" : "func"} 
		]
	});
}
//提交表单
function save(){ 
	$("#btn_save").attr("disabled","disabled");
	$("#mainForm").form("submit",{
		url:"${ctxAdmin}/shoes/main/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("shoes/main", "#mainTable");
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