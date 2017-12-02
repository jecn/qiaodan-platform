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
	<form id="viewForm" method="post" modelAttribute="dict">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">所属模块：</span></th>
				<td width="35%"><span id="module" name="module" >${dict.moduleValue}</span></td>
				<th width="15%"><span class="platform-title">类型：</span></th>
				<td width="35%"><span id="type" name="type">${dict.type}</span></td>
			  </tr>
			   <tr>
			   	<th width="15%"><span class="platform-title">标签：</span></th>
				<td width="35%"><span id="label" name="label">${dict.label}</span></td>
				<th width="15%"><span class="platform-title">值：</span></th>
				<td width="35%"><span id="value" name="value">${dict.value}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">排序：</span></th>
				<td width="35%"><span id="sort" name="sort">${dict.sort}</span></td>
				<th width="15%"><span class="platform-title">是否可用：</span></th>
				<td width="35%"><span id="useable" name="useable">${dict.useableValue}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">描述：</span></th>
				<td colspan="3" width="85%"><span name="inst" id="inst">${dict.inst}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${dict.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>