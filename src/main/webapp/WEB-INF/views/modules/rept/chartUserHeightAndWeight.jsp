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
		table{
			margin-top: 50px;
		}
        table td{
            border:1px solid #000;
            line-height:30px;
            text-align:center;
            width:150px;
        }
    </style>
    <script type="text/javascript">
    		function exportExecl() {
				var table=document.getElementById("height");
 				var oXL = new ActiveXObject("Excel.Application");
				var oWB = oXL.Workbooks.Add();
				var oSheet = oWB.ActiveSheet; 
				var sel=document.body.createTextRange();
				sel.moveToElementText(table);
				sel.select();
				sel.execCommand("Copy");
				oSheet.Paste();
				oXL.Visible = true;
			}
    </script>    
</head>
<body>
<button onclick="exportExecl();">导出</button>
<table id="height"  border="1" style="border-collapse: collapse">
    <tr>
        <td></td>
    <td>总人数</td>
    <td>男女比例</td>
    <td>男身高均值</td>
    <td>女身高均指</td>
    <td>男体重均值</td>
    <td>女体重均值</td>
    </tr>
    <tr>
        <td>注册</td>
       
            <td>${s.Number}</td>
            <td>${s.scale}</td>
            <td>${s.Wheight}</td>
            <td>${s.Mheight}</td>
            <td>${s.Weight}</td>
            <td>${s.Brggr}</td>
    </tr>
    <tr>
        <td>活跃</td>
            <td>${D.Number}</td>
            <td>${D.scale}</td>
            <td>${D.Wheight}</td>
            <td>${D.Mheight}</td>
            <td>${D.Weight}</td>
            <td>${D.Brggr}</td>
    </tr>
</table>
</body>
</html>