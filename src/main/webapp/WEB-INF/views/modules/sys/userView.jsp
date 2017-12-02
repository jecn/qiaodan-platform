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
	<form id="viewForm" method="post" modelAttribute="user">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">用户名：</span></th>
				<td width="35%"><span id="username" name="username" >${user.username}</span></td>
				<th width="15%"><span class="platform-title">姓名：</span></th>
				<td width="35%"><span id="name" name="name">${user.name}</span></td>
			  </tr>
			   <tr>
				<th width="15%"><span class="platform-title">性别：</span></th>
				<td width="35%"><span id="gender" name="gender">${user.gender}</span></td>
				<th width="15%"><span class="platform-title">生日：</span></th>
				<td width="35%"><span id="birthday" name="birthday">${user.birthday}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">座机号：</span></th>
				<td width="35%"><span id="tel" name="tel">${user.tel}</span></td>
				<th width="15%"><span class="platform-title">手机号：</span></th>
				<td width="35%"><span id="mobile" name="mobile">${user.mobile}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">电子邮件：</span></th>
				<td width="35%"><span id="email" name="email">${user.email}</span></td>
				<th width="15%"><span class="platform-title">状态：</span></th>
				<td width="35%"><span id="stat" name="stat">${fns:getDictLabel(user.stat, 'sys_user_stat', '')}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${user.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>