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
<link rel="stylesheet" type="text/css" href="${ctxStatic}/plugins/jquery-uploadify/scduploadify.css">
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-uploadify/scduploadify.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/validate_class.js"></script>
</head>
<body>
<div class="form-area" >
	<form id="mainForm"  method="post">
		<input type="hidden" name="id" value="${train.id}">
		<input type="hidden" id="thumb" name="thumb" value="${train.thumb}">
		<table align="center" class="edittable">
			<tr>
				<td width="15%">类型：<span class="safari-required">*</span></td>
				<td width="35%">
					<select id="tdId" name="tdId" class="easyui-validatebox select" >
						<c:forEach var="item" items="${fns:getNames()}">  
                             <option value="${item.id}" <c:if test="${item.id eq train.tdId}">selected</c:if>> ${item.name}</option>  
                        </c:forEach>
                    </select>
				</td>
				<td width="15%">标题：<span class="safari-required">*</span></td>
				<td width="35%"><input type="text"  id="title" name="title" value="${train.title}" class="easyui-validatebox input_bg" maxlength="80" data-options="required:true,validType:['unique[\'${ctxAdmin}/train/main/checkTitle?id=${train.id}\',\'title\',\'1\',\'该标题已存在\',\'title\']']" /></td>
			</tr>
			<tr>
				<td width="15%">链接：<span class="safari-required">*</span></td>
				<td colspan="3"  width="85%"><input type="text"  id="link" name="link" value="${train.link}" class="easyui-validatebox input_bg" maxlength="512" data-options="required:true"/></td>
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
					<textarea rows="5" cols="40" name="remarks"  maxlength="255">${train.remarks}</textarea>
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
var thumb = '${train.thumb}';
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
		url:"${ctxAdmin}/train/main/save",
        onSubmit: function(){
		    $("#btn_save").removeAttr("disabled");
			return $(this).form("validate");
   		},
   		success:function(result){
			if (result == "success"){
			    $.messager.alert("提示","保存成功","info",function(){
					top.closeDlg("dlgb");
				    $("#btn_save").removeAttr("disabled");
				    reloadDatagrid("train/main", "#mainTable");
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