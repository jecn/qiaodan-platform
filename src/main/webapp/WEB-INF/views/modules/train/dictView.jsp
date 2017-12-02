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
				<th width="15%"><span class="platform-title">名称：</span></th>
				<td width="35%"><span id="name" name="name">${dict.name}</span></td>
				<th width="15%"><span class="platform-title">类型：</span></th>
				<td width="35%"><span id="type" name="type">
				<c:choose> 
					<c:when test="${dict.type eq '1'}"> 
						推荐训练
					</c:when>
					<c:when test="${dict.type eq '2'}"> 
						其他训练
					</c:when> 
					<c:otherwise> 
						
					</c:otherwise>
				</c:choose>
				</span></td>
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