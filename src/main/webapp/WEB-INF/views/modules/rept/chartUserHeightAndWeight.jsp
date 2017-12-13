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
    function load(){
	    var myChart = echarts.init(document.getElementById('load'));
		$("#mainTable").hide();
		myChart.showLoading();
    	var url = "${ctxAdmin}/rept/user/getHeightWeight.do";
    	//$(function(){
			$.ajax({
				type:"get",
				url:url,
				success: function(str){
				myChart.hideLoading();
				$("#load").remove();
				$("#mainTable").show();
					console.log(str);
					var data = $.parseJSON(str);
					$("#allUserCount").html(data.allUserCount);
		            $("#allManWomen").html(data.allManWomen);
		            $("#allManAvgHeight").html(data.allManAvgHeight);
		            $("#allWomenAvgHeight").html(data.allWomenAvgHeight);
		            $("#allManAvgWeight").html(data.allManAvgWeight);
		            $("#allWomenAvgWeight").html(data.allWomenAvgWeight);
		            $("#activeUserCount").html(data.activeUserCount);
		            $("#activeManWomen").html(data.activeManWomen);
		            $("#activeManAvgHeight").html(data.activeManAvgHeight);
		            $("#activeWomenAvgHeight").html(data.activeWomenAvgHeight);
		            $("#activeManAvgWeight").html(data.activeManAvgWeight);
		            $("#activeWomenAvgWeight").html(data.activeWomenAvgWeight);
				},
				error:function(data){
					console.log(data);
					myChart.hideLoading();
					$("#load").remove();
					//$("#mainTable").show();
					$.messager.alert("提示","数据加载失败","error");
				}
			});
		//});
    }
    		function exportExecl() {
				var table=document.getElementById("mainTable");
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
<body onload="load();">
<div id="load" style="width: 600px;height:400px;"></div>
<table id="mainTable"  border="1" style="border-collapse: collapse">
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
       
            <td id="allUserCount">${allUserCount}</td>
            <td id="allManWomen">${allManWomen}</td>
            <td id="allManAvgHeight">${allManWomen}</td>
            <td id="allWomenAvgHeight">${allWomenAvgHeight}</td>
            <td id="allManAvgWeight">${allManAvgWeight}</td>
            <td id="allWomenAvgWeight">${allWomenAvgWeight}</td>
    </tr>
    <tr>
        <td>活跃</td>
            <td id="activeUserCount">${activeUserCount}</td>
            <td id="activeManWomen">${activeManWomen}</td>
            <td id="activeManAvgHeight">${activeManAvgHeight}</td>
            <td id="activeWomenAvgHeight">${activeWomenAvgHeight}</td>
            <td id="activeManAvgWeight">${activeManAvgWeight}</td>
            <td id="activeWomenAvgWeight">${activeWomenAvgWeight}</td>
    </tr>
</table>
</body>
</html>