<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
</head>
<style>

    table td{
        border:1px solid #000;
        line-height:30px;
        text-align:center;
        width:50px;
    }
</style>
<body onload="getFile();">
	<h1 id="h"></h1>
<table border="1" style="border-collapse:collapse">
    <tr>
        <td>用户名</td>
        <td>昵称</td>
        <td>性别</td>
        <td>年龄</td>
        <td>身高体重</td>
        <td>球场位置</td>
        <td>运动记录时间</td>
        <td>打球时长</td>
        <td>活跃度</td>
        <td>平均速度</td>
        <td>卡路里</td>
        <td>总步数</td>
        <td>纵向距离</td>
        <td>横向距离</td>
        <td>纵跳次数</td>
    </tr>
    <%-- <c:forEach itmes="${moveDates}" var="m"> --%>
       <%--  <tr>
            <td>${m.userNmae}</td>
            <td>${m.Nickname}</td>
            <td>${m.gender}</td>
            <td>${m.age}</td>
            <td>${m.weight}</td>
            <td>${m.location}</td>
            <td>${m.lastTime}</td>
            <td>${m.bsTime}</td>
            <td>${m.live}</td>
            <td>${m.speed}</td>
            <td>${m.calorie}</td>
            <td>${m.step}</td>
            <td>${m.long}</td>
            <td>${m.cross}</td>
            <td>${m.degree}</td>
        </tr> --%>
   <%--  </c:forEach> --%>
</table>
</body>
<script type="text/javascript">
function getFile() {
	var hostname = window.location.host;//获取域名端口
	 	var begin = "2017/10/10";//document.getElementById('begin_time').value;
		var end = "2017/11/10";//document.getElementById('end_time').value;
	var url = "${ctxAdmin}/repte/userData/getUserMoveData.do?beginTime="
			+begin+"&endTime="+end;
	$.get(url,
	function(data, status) {
		if (status == "success") {
			alert(data);
		} else {
			alert("查询数据失败！");
		}
	});
}
</script>
</html>