<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/query.css">
<style>

        table td{
            border:1px solid #000;
            line-height:30px;
            text-align:center;
            width:180px;
        }
    </style>
</head>
<body>
    <table border="1" style="border-collapse:collapse">
        <tr>
            <th>省份/地区</th>
            <th>注册数量</th>
            <th>活跃数量</th>
        </tr>
        <%-- <c:forEach itmes="${moveDatas}" var="move" varStatus="moveIndex"> --%>
            <tr>
                <td>${move.provice}</td>
                <td>${move.number}</td>
                <td>${move.user}</td>
            </tr>

        <%-- </c:forEach> --%>
    </table>
</body>
</html>