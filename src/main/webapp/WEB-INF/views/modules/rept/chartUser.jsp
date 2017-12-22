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
<style type="text/css">
.content {
	height: 30px;
	width: 280px;
	float: left;
	
}
</style>
</head>
<body onload="getFiles();">
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<!--   开始时间：<input id="begin_time" type="date"/>结束时间：<input id="end_time" type="date"/>
    <input type="button" value="查询" onclick="getFiles();"> -->
	<div style="margin-top: 30px;">
		<!-- <div onclick="oneCount();" class="content" style="cursor:hand;">总注册用户每月增长统计</div>
		<div onclick="twoCount();" class="content" style="cursor:hand;">注册用户每月总额统计</div> -->
		<a href="#"	class="easyui-linkbutton" iconCls="icon-search"
						onclick="oneCount();"> <span id="platform.list.query"
							class="platform-title">总注册用户每月增长统计</span> </a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-clear"
						onclick="twoCount();"> <span
							id="platform.list.clear" class="platform-title">注册用户每月总额统计</span> </a>
	</div>
	<div id="main" style="width: 600px;height:400px;"></div>
	<script type="text/javascript">
		var resultData = {};
		var myChart = echarts.init(document.getElementById('main'));
		/* var begin = document.getElementById('begin_time').value;
		var end = document.getElementById('end_time').value; */
		myChart.showLoading();
		function getFiles() {
			// 基于准备好的dom，初始化echarts实例
			var url = "${ctxAdmin}/rept/chartUser/selectTableOfUser.do";
			/* $.post(url,
					  {
						"beginTime":begin,
					    "endTime":end
					  },
					  function(data,status){
						myChart.hideLoading();
					  	alert("Data: " + data + "\nStatus: " + status);
					  }); */
			$.get(url,

			function(data, status) {
				console.log(data);
				myChart.hideLoading();
				if (status == "success") {
					//resultData = data;
					$.data(resultData, 'name', data);  
					//alert("Data: " + data + "\nStatus: " + status);
					//[{"month":7,"count":55},{"month":8,"count":14},{"month":9,"count":74},{"month":10,"count":2647},{"month":11,"count":3653}]
					/* var legs,sers;	
					$.each(data, function (n, value) {
					 //alert(n + ' ' + value);
					 //var trs = "";
					 //trs += "<tr><td>" + value.name + "</td> <td>" + value.password + "</td></tr>";
					 //tbody += trs;
					 legs[n] = value.month;
					 sers[n] = value.count;
					}); */
					oneCount();
				} else {
					alert("查询数据失败！");
				}
			});
		}
		function oneCount() {
			var data = $.data(resultData, 'name');
			var legs = [], sers = [];
			for ( var i = 0; i < data.length; i++) {
				var aobj = data[i];
				legs[i] = aobj.month;
				sers[i] = aobj.count;

			}

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '总注册用户每月增长统计'
				},
				tooltip : {},
				legend : {
					data : [ '用户' ]
				},
				xAxis : {
					data : legs
				},
				yAxis : {},
				series : [ {
					name : '用户',
					type : 'line',
					data : sers
				} ]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}

		function twoCount() {
			var data2 = $.data(resultData, 'name');
			if(data2 == null){
				$.messager.alert("提示","数据加载失败","error");
				return;
			}
			var legs = [], sers = [];
			var sum = 0;
			for ( var i = 0; i < data2.length; i++) {
				var aobj = data2[i];
				legs[i] = aobj.month;
				sum = sum + aobj.count;
				sers[i] = sum;

			}
			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '注册用户每月总额统计'
				},
				tooltip : {},
				legend : {
					data : [ '用户' ]
				},
				xAxis : {
					data : legs
				},
				yAxis : {},
				series : [ {
					name : '用户',
					type : 'line',
					data : sers
				} ]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
	</script>
</body>
</html>