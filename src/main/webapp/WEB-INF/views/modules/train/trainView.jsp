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
<body>
<div>
	<form id="viewForm" method="post" modelAttribute="train">
	<input type="hidden" id="thumb" name="thumb" value="${train.thumb}">
		  <table class="detailtable">
		   <tr>
				<th width="15%"><span class="platform-title">名称：</span></th>
				<td width="35%"><span id="name" name="name">${train.trainDict.name}</span></td>
				<th width="15%"><span class="platform-title">类型：</span></th>
				<td width="35%"><span id="type" name="type">
				<c:choose> 
					<c:when test="${train.trainDict.type eq '1'}"> 
						推荐训练
					</c:when>
					<c:when test="${train.trainDict.type eq '2'}"> 
						其他训练
					</c:when> 
					<c:otherwise> 
						
					</c:otherwise>
				</c:choose>
				</span></td>
				</tr>
				<tr>
				<th width="15%"><span class="platform-title">标题：</span></th>
				<td width="35%"><span id="title" name="title">${train.title}</span></td>
				<th width="15%"><span class="platform-title">阅读次数：</span></th>
				<td width="35%"><span id="link" name="link">${train.count}</span></td>
				</tr>
				<tr>
				<th width="15%"><span class="platform-title">链接：</span></th>
				<td colspan="3" width="85%"><span name="intro" id="intro">${train.link}</span></td>
			  </tr>
				<tr>
				<th width="15%"><span class="platform-title">简介：</span></th>
				<td colspan="3" width="85%"><span name="intro" id="intro">${train.intro}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${train.remarks}</span></td>
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
							<div id="scduploadifydiv" style="width: 100%; height: auto; overflow: auto;"></div>
						</fieldset>
					</div>
				</td>
			  </tr>
		</table>
	</form>
</div>
<script type="text/javascript">
var thumb = '${recm.thumb}';
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