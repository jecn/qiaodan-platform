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
	<form id="viewForm" method="post" modelAttribute="i18n">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">语言：</span></th>
				<td width="35%"><span id="langCode" name="langCode" >${i18n.langCode}</span></td>
			   	<th width="15%"><span class="platform-title">标签：</span></th>
				<td width="35%"><span id="langKey" name="langKey">${i18n.langKey}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">值：</span></th>
				<td width="35%"><span id="langValue" name="langValue">${i18n.langValue}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${i18n.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>