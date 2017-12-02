<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/detail.css">
</head>
<body>
<div>
	<form id="viewForm" method="post" modelAttribute="role">
		  <table class="detailtable">
			   <tr>
			   	<th width="15%"><span class="platform-title">中文名称：</span></th>
				<td width="35%"><span id="cname" name="cname">${role.cname}</span></td>
				<th width="15%"><span class="platform-title">英文名称：</span></th>
				<td width="35%"><span id="ename" name="ename">${role.ename}</span></td>
			  </tr>
			  <tr>
<!-- 			  	<th width="15%"><span class="platform-title">编号：</span></th> -->
<!-- 				<td width="35%"><span id="code" name="code">${role.code}</span></td> -->
				<th width="15%"><span class="platform-title">是否可用：</span></th>
				<td width="35%"><span id="useable" name="useable">${role.useableValue}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">描述：</span></th>
				<td colspan="3" width="85%"><span name="inst" id="inst">${role.inst}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${role.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>