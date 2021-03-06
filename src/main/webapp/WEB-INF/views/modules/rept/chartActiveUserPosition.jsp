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
<body onload="getFiles();">
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <!-- 开始时间：<input id="begin_time" type="date"/>结束时间：<input id="end_time" type="date"/> -->
    <!-- <input type="button" value="查询" onclick="getFiles();"> -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
    function getFiles() {
    	var hostname = window.location.host;//获取域名端口
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
    /* 	var begin = document.getElementById('begin_time').value;
    	var end = document.getElementById('end_time').value; */
        myChart.showLoading();
        var url = "${ctxAdmin}/rept/userPosition/getUserPosition.do?isActive=1";
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
                		  
                		  function(data,status){
                		  
        			   		myChart.hideLoading();
        			   		if(status == "success"){
        			   			
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
        			            var legs = [],sers = [];
        			            //var dataObj = JSON.parse(data);
        			   			for(var i=0;i<data.length;i++){
        			   		        var aobj = data[i];
        			   		        legs[i] = aobj.ages;
        			   		        sers[i] = aobj.userCount;
        			   		    }

        			   		// 指定图表的配置项和数据
        			   	        var option = {
        			   	            title: {
        			   	                text: '活跃用户球场位置'
        			   	            },
        			   	            tooltip: {},
        			   	            legend: {
        			   	                data:['百分比']
        			   	            },
        			   	            xAxis: {
        			   	                data: legs
        			   	            },
        			   	            yAxis: {},
        			   	            series: [{
        			   	                name: '用户',
        			   	                type: 'bar',
        			   	                data: sers,
        								itemStyle : {
        									normal : {
        										label : {
        											show : true,
        											position : 'top',
        											textStyle : {
        												color : '#615a5a'
        											},
        											formatter : function(sers) {
        												if (sers.value == 0) {
        													return '';
        												} else {
        													return sers.value+"%";
        												}
        											}
        										}
        									}
        								}
        			   	            }]
        			   	        };
        			   	        // 使用刚指定的配置项和数据显示图表。
        			   	        myChart.setOption(option);
        			   		}else{
        			   			alert("查询数据失败！data="+data);
        			   		}
                		  }); 
    }
    </script>
</body>
</html>