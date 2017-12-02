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
	<form id="viewForm" method="post" modelAttribute="area">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">名称：</span></th>
				<td width="35%"><span id="name" name="name" >${area.name}</span></td>
				<th width="15%"><span class="platform-title">编码：</span></th>
				<td width="35%"><span id="code" name="code">${area.code}</span></td>
			  </tr>
			   <tr>
				<th width="15%"><span class="platform-title">类型：</span></th>
				<td width="35%"><span id="type" name="type">${area.type}</span></td>
				<th width="15%"><span class="platform-title">排序：</span></th>
				<td width="35%"><span id="sort" name="sort">${area.sort}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">是否可用：</span></th>
				<td width="35%"><span id="useable" name="useable">${area.useableValue}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${area.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>