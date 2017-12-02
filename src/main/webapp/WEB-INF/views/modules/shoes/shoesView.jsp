<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/detail.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/plugins/jquery-uploadify/scduploadify.css">
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-uploadify/scduploadify.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/validate_class.js"></script>
</head>
</head>
<body>
<div>
	<form id="viewForm" method="post" modelAttribute="dict">
	<input type="hidden" id="thumb" name="thumb" value="${shoes.thumb}">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">名称：</span></th>
				<td width="35%"><span id="name" name="name" >${shoes.name}</span></td>
				<th width="15%"><span class="platform-title">编号：</span></th>
				<td width="35%"><span id="code" name="code">${shoes.code}</span></td>
			  </tr>
			   <tr>
			   	<th width="15%"><span class="platform-title">款号：</span></th>
				<td width="35%"><span id="styleNumber" name="styleNumber">${shoes.styleNumber}</span></td>
				<th width="15%"><span class="platform-title">价格：</span></th>
				<td width="35%"><span id="price" name="price">${shoes.price}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">上市时间：</span></th>
				<td width="35%"><span id="marketTime" name="marketTime">${shoes.marketTime}</span></td>
				<th width="15%"><span class="platform-title">颜色：</span></th>
				<td width="35%"><span id="color" name="color">${shoes.color}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">尺寸：</span></th>
				<td width="35%"><span id="size" name="size">${shoes.size}</span></td>
				<th width="15%"><span class="platform-title">适用人群：</span></th>
				<td width="35%"><span id="forPeople" name="forPeople">${shoes.forPeople}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">适用场地：</span></th>
				<td width="35%"><span id="forSpace" name="forSpace">${shoes.forSpace}</span></td>
				<th width="15%"><span class="platform-title">适用位置：</span></th>
				<td width="35%"><span id="forPosition" name="forPosition">${shoes.forPosition}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">款式：</span></th>
				<td width="35%"><span id="style" name="style">${shoes.style}</span></td>
				<th width="15%"><span class="platform-title">功能：</span></th>
				<td width="35%"><span id="function" name="function">${shoes.function}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">状态：</span></th>
				<td width="35%"><span id="stat" name="stat">${shoes.stat}</span></td>
				<th width="15%"><span class="platform-title">是否可用：</span></th>
				<td width="35%"><span id="useable" name="useable">${shoes.useableValue}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">简介：</span></th>
				<td colspan="3" width="85%"><span name="intro" id="intro">${shoes.intro}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${shoes.remarks}</span></td>
			  </tr>
			  <tr>
			 	<td colspan="4" width="100%">
			 		<div>
						<fieldset>
							<legend>
								<label>
									<font size="14px"><span class="platform-title">缩略图：</span></font>
								</label>
							</legend>
							<div id="scduploadifydiv" style="width: 100%; height: auto; overflow: none;"></div>
						</fieldset>
					</div>
				</td>
			  </tr>
		</table>
	</form>
</div>

<script type="text/javascript">
var thumb = '${shoes.thumb}';
var basePath = '${ctxAdmin}';
$(function(){
	chooseNotype(thumb);
});

function chooseNotype(id) {
	$("#scduploadifydiv").scduploadify( {
		//一定要指定的参数
		'filehandlerurl' : basePath + "/sys/media/filehandler/", //附件操作的的url
		'uploader' : "${ctxStatic}/plugins/jquery-uploadify/uploadify.swf", //以下几个参数要修改路径
		'script' : "${ctxStatic}/plugins/jquery-uploadify/scduploadify.jsp",
		'buttonImg' : "${ctxStatic}/plugins/jquery-uploadify/selectfile.jpg",
		//可选参数
		'mode' : "view",
		'ids' : "" + id + "",
		'reftno' :"thumb",
		'filtertype' : '2',  //过滤显示类型
		'fields' : [ 
		             {"name" : "文件名称","value" : "name"}, 
		             {"name" : "文件类型","value" : "type"}, 
		             {"name" : "文件大小","value" : "size"},
		    		 {"name" : "操作","value" : "func"} 
		]
	});
}
</script>
</body>
</html>