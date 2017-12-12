<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
<style>
body{
	width:80%;
	height:80%;
}
table {
	margin-top: 30px;
	margin-left: 50px;
	margin-bottom: 50px;
}

table td {
	border: 1px solid #000;
	line-height: 30px;
	text-align: center;
	width: 180px;
}
</style>
<script type="text/javascript">
	function getLocation() {
		var myChart = echarts.init(document.getElementById('load'));
		$("#content").hide();
		myChart.showLoading();
		var url = "${ctxAdmin}/rept/userLocation/getUserLoction.do";
		$.get(url,

		function(data, status) {
			myChart.hideLoading();
			if (status == "success") {
				myChart.hideLoading();
				$("#load").remove();
				$("#content").show();
				for ( var k in data) {
					//var aobj = data[i];
					var leg = k;
					var ser = data[k];
					$("#table tbody").append('<tr><td>'+leg+'</td><td>'+ser+'</td></tr>');
				}
				//解析数据
			}
		});
	}
</script>
</head>
<body onload="getLocation();">
	<div id="load" style="width: 600px;height:400px;"></div>
	<div id="content" style="height:500px; overflow:scroll;" >
		<table id="table" border="1" style="border-collapse:collapse">
			<thead>
			<tr>
				<td>省份/地区</td>
				<td>活跃数量</td>
			</tr>
			</thead>
			<tbody></tbody>
			<%-- <c:forEach items="${locationMap}" var="move">
				<tr>
					<td>${move.key}</td>
					<td>${move.value}</td>
				</tr>
			</c:forEach> --%>
		</table>
	</div>
</body>
</html>