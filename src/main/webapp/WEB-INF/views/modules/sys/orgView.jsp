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
	<form id="viewForm" method="post" modelAttribute="org">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">中文名称：</span></th>
				<td width="35%"><span id="cname" name="cname" >${org.cname}</span></td>
				<th width="15%"><span class="platform-title">英文名称：</span></th>
				<td width="35%"><span id="ename" name="ename">${org.ename}</span></td>
			  </tr>
			   <tr>
			   	<th width="15%"><span class="platform-title">类型：</span></th>
				<td width="35%"><span id="type" name="type">${fns:getDictLabel(org.type, 'sys_org_type', '')}</span></td>
				<th width="15%"><span class="platform-title">等级：</span></th>
				<td width="35%"><span id="grade" name="grade">${fns:getDictLabel(org.grade, 'sys_org_grade', '')}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">电话号码：</span></th>
				<td width="35%"><span id="tel" name="tel">${org.tel}</span></td>
				<th width="15%"><span class="platform-title">传真：</span></th>
				<td width="35%"><span id="fax" name="fax">${org.fax}</span></td>
			  </tr>
			  <tr>
			  	<th width="15%"><span class="platform-title">电子邮箱：</span></th>
				<td width="35%"><span id="email" name="email">${org.email}</span></td>
				<th width="15%"><span class="platform-title">是否可用：</span></th>
				<td width="35%"><span id="useable" name="useable">${org.useableValue}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">地址：</span></th>
				<td colspan="3" width="85%"><span name="address" id="address">${org.address}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${org.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>