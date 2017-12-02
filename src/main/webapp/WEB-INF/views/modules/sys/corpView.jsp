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
	<form id="viewForm" method="post" modelAttribute="corp">
		  <table class="detailtable">
			  <tr>
				<th width="15%"><span class="platform-title">名称：</span></th>
				<td colspan="3" width="85%"><span id="name" name="name" >${corp.name}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">负责人：</span></th>
				<td width="35%"><span id="leader" name="leader">${corp.leader}</span></td>
				<th width="15%"><span class="platform-title">机构编码：</span></th>
				<td width="35%"><span id="orgCode" name="orgCode">${corp.orgCode}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">单位类型：</span></th>
				<td width="35%"><span id="type" name="type">${corp.type}</span></td>
				<th width="15%"><span class="platform-title">单位性质：</span></th>
				<td width="35%"><span id="lnc" name="lnc">${corp.lnc}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">电话：</span></th>
				<td width="35%"><span id="tel" name="tel">${corp.tel}</span></td>
				<th width="15%"><span class="platform-title">传真：</span></th>
				<td width="35%"><span id="fax" name="fax">${corp.fax}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">电子邮箱：</span></th>
				<td width="35%"><span id="email" name="email">${corp.email}</span></td>
				<th width="15%"><span class="platform-title">邮编：</span></th>
				<td width="35%"><span id="zipCode" name="zipCode">${corp.zipCode}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">规模：</span></th>
				<td width="35%"><span id="scale" name="scale">${corp.scale}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">地址：</span></th>
				<td colspan="3" width="85%"><span name="address" id="address">${corp.remarks}</span></td>
			  </tr>
			  <tr>
				<th width="15%"><span class="platform-title">备注：</span></th>
				<td colspan="3" width="85%"><span name="remarks" id="remarks">${corp.remarks}</span></td>
			  </tr>
		</table>
	</form>
</div>
</body>
</html>