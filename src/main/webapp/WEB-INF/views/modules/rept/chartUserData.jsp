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
</head>
<style>
	.table2excel{
		border:1px;
		cellspacing:0px;
		cellpadding:0px;
		cellspacing:0;
		align:center;
		margin-top: 20px;
		margin-left: 10px;
		margin-bottom:30px;
		border-collapse:collapse
	}
	.table2excel tr{
		border: 1px solid #000;
		border-color: black;
	}
	.table2excel td{
		 border: 1px solid #000;
		 border-color: black;
		line-height: 30px;
		text-align: center;
	}
</style>
<body onload="">
	<div id="query-panel" class="easyui-panel" collapsible="false"
		title="用户打球数据" collapsed="false">
		<form method="post" id="searchForm"
			onkeydown="if(event.keyCode==13){return false;}">
			<table class="querytable" align="center">
				<tr>
					<td style="width: 10%"><span class="platform-title">开始时间：</span>
					</td>
					<td style="width: 20%" class="platform-input"><input
						id="beginTime" type="datetime" name="beginTime" tipMsg="格式：2017-01-01"
						class="easyui-validatebox input_bg" maxlength="80" />
					</td>
					<td style="width: 10%"><span class="platform-title">结束时间：</span>
					</td>
					<td style="width: 20%" class="platform-input"><input
						id="endTime" type="datetime" name="endTime" tipMsg="格式：2017-01-01"
						class="easyui-validatebox input_bg" maxlength="80" />
					</td>
					<td style="width: 70%" class="btn"><a href="#"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchQuery();"> <span id="platform.list.query"
							class="platform-title">查询</span> </a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-clear"
						onclick="javascript:method1('mainTable')"> <span
							id="platform.list.clear" class="platform-title">导出</span> </a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="load" style="width: 600px;height:400px;"></div>
	<div style=" height:500px; width:500xp; overflow:scroll;" >
	<table id="mainTable"  class="table2excel" data-tableName="Test Table 1">
		<thead><tr>
			<td>用户名</td>
			<td>手机号</td>
			<td>昵称</td>
			<td>性别</td>
			<td>年龄</td>
			<td>身高(cm)</td>
			<td>体重(kg)</td>
			<td>球场位置</td>
			<td>运动记录时间</td>
			<td>打球时长(秒)</td>
			<td>活跃度</td>
			<td>平均速度(米/秒)</td>
			
			<!-- add -->
			<td>最大移动速度(米/秒)</td>
			<td>冲刺次数（次）</td>
			<td>变向次数（次）</td>
			 
			<td>卡路里</td>
			<td>总步数</td>
			<td>纵向距离(米)</td>
			<td>横向距离(米)</td>
			<td>纵跳次数</td>
			<!-- add -->
			<td>平均纵跳高度(cm)</td>
			<td>纵跳最大高度</td>
			<td>平均滞空时间(秒)</td>
			<td>平均着地旋转角</td>
			<td>着地类型(-1内翻 0正常 1外翻)</td>
			<td>最大冲击力(体重倍数)</td>
			<td>平均冲击力(体重倍数)</td>
			<td>本场表现</td>
			<td>跑动等级</td>
			<td>突破等级</td>
			<td>弹跳等级</td>
			<td>类型 (1 全场 2 半场)</td>
		</tr>
		</thead>
		<tbody id="conten_body"></tbody>

	</table>
	</div>
	<div id="toolbar"></div>
</body>
<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('load'));
	$("#load").hide();
	$("#mainTable").hide();
	$("#beginTime").focus();
	//查询
	function searchQuery() {
		
		//var queryForm =$("#searchForm").serializeObject();
		//$("#mainTable").datagrid("load",queryForm); 
		var beginTime = document.getElementById("beginTime").value;//"2017/10/10";//document.getElementById('begin_time').value;
		var endTime = document.getElementById("endTime").value;//"2017/11/10";//document.getElementById('end_time').value;
		var DATE_FORMAT = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
		if (beginTime == null || "" == beginTime) {
			$.messager.alert("提示", "开始时间不能为空", "error");
			return;
		}
		if (endTime == null || "" == endTime) {
			$.messager.alert("提示", "结束时间不能为空", "error");
			return;
		}
		 if(!DATE_FORMAT.test(beginTime)){
		   $.messager.alert("提示", "抱歉，您输入的开始时间日期格式有误，", "error");
		   return;
		  }
		 if(!DATE_FORMAT.test(endTime)){
		   $.messager.alert("提示", "抱歉，您输入的结束时间日期格式有误，", "error");
		   return;
		  }
		$("#load").show();
		myChart.showLoading();
		var url = "${ctxAdmin}/rept/userData/getUserMoveData.do?beginTime="
				+ beginTime + "&endTime=" + endTime;
				
				$.ajax({
				type:"get",
				url:url,
				success: function(data){
				//console.log(data);
					myChart.hideLoading();
				$("#load").remove();
				$("#mainTable").show();
				//alert(data);
				var username;
			    var name;
			    var nick;
			    var gender;
			    var age;
			    var mobile;
			    var weight;
			    var height;
			    var position;//打球位置
			    var beginTime;
			    var endTime;
			    var spend;//运动时长
			    var totalStep;//总步数
			    var totalDist;
			    var totalHorDist;//横向距离
			    var totalVerDist;//纵向距离
			    var activeRate;//活跃占比
			    var avgSpeed;//平均移动速度
			    var maxSpeed;//最大移动速度
			    var spurtCount;//冲刺次数
			    var breakinCount;//变向次数
			    var breakinAvgTime;//变向平均触地时间
			    var avgHoverTime;//平均滞空时间
			    var avgTouchAngle;//平均着地旋转角
			    var touchType;//着地类型(-1内翻 0空的 1外翻)
			    var maxWallup;//最大冲击力
			    var avgWallup;//平均冲击力
			    var type;//类型 (1 全场 2 半场)
			    var fieldType;//场地类型(1:水泥 2:塑胶 3:木地板)
			    var calorie;
			    var verJumpCount;
			    var verJumpAvgHigh;
			    var verJumpMaxHigh;
			    var perfRank;
			    var runRank;
			    var breakRank;
			    var bounceRank;
				//var dataObj = JSON.parse(data);
				
				for ( var i = 0; i < data.length; i++) {
					var aobj = data[i];
					console.log(aobj);
					username = aobj.username;
				    name = aobj.name;
				    nick = aobj.nick;
				    gender = aobj.gender == null||aobj.gender == '2' ? "女":"男";
				    age = aobj.age == null ? 20 : aobj.age;
				    mobile = aobj.mobile;
				    weight = aobj.weight;
				    height = aobj.height;
				    position = aobj.position;//打球位置
				    //得分后卫:SG， 控球后卫:PG， 小前锋:SF， 大前锋:PF，中锋:C
				    if(position == 'SG'){
				    	position = "得分后卫";
				    }else if(position == 'PG'){
				    	position = "控球后卫";
				    }else if(position == 'SF'){
				    	position = "小前锋";
				    }else if(position == 'PF'){
				    	position = "大前锋";
				    }else if(position == 'C'){
				    	position = "中锋";
				    }
				    beginTime = aobj.beginTime;
				    endTime = aobj.endTime;
				    spend = aobj.spend;//运动时长
				    totalStep = aobj.totalStep;//总步数
				    totalDist = aobj.totalDist;
				    totalHorDist = aobj.totalHorDist;//横向距离
				    totalVerDist = aobj.totalVerDist;//纵向距离
				    activeRate = aobj.activeRate;//活跃占比
				    avgSpeed = aobj.avgSpeed;//平均移动速度
				    maxSpeed = aobj.maxSpeed;//最大移动速度
				    spurtCount = aobj.spurtCount;//冲刺次数
				    avgHoverTime = aobj.avgHoverTime;
				    breakinCount = aobj.breakinCount;
				    verJumpCount = aobj.verJumpCount;
				    verJumpAvgHigh = aobj.verJumpAvgHigh;
				    verJumpMaxHigh = aobj.verJumpMaxHigh;
				    perfRank = aobj.perfRank;
				    runRank = aobj.runRank;
				    breakRank = aobj.breakRank;
				    bounceRank = aobj.bounceRank;
				    calorie = aobj.calorie;
				    avgTouchAngle = aobj.avgTouchAngle;
				    touchType= aobj.touchType;
				    maxWallup=aobj.maxWallup;
				    avgWallup = aobj.avgWallup;
				    type = aobj.type;
					$("#conten_body").append(
						'<tr>'
						+'<td>'+username+'</td>'
						+'<td>'+mobile+'</td>'
						+'<td>'+nick+'</td>'
						+'<td>'+gender+'</td>'
						+'<td>'+age+'</td>'
						+'<td>'+height+'</td>'
						+'<td>'+weight+'</td>'
						+'<td>'+position+'</td>'
						+'<td>'+beginTime+'</td>'
						+'<td>'+spend+'</td>'
						+'<td>'+activeRate+'</td>'
						+'<td>'+avgSpeed+'</td>'
						
						+'<td>'+maxSpeed+'</td>'
						+'<td>'+spurtCount+'</td>'
						+'<td>'+breakinCount+'</td>'
						
						+'<td>'+calorie+'</td>'
						+'<td>'+totalStep+'</td>'
						+'<td>'+totalVerDist+'</td>'
						+'<td>'+totalHorDist+'</td>'
						+'<td>'+verJumpCount+'</td>'
						
						+'<td>'+verJumpAvgHigh+'</td>'
						+'<td>'+verJumpMaxHigh+'</td>'
						+'<td>'+avgHoverTime+'</td>'
						+'<td>'+avgTouchAngle+'</td>'
						+'<td>'+touchType+'</td>'
						+'<td>'+maxWallup+'</td>'
						+'<td>'+avgWallup+'</td>'
						+'<td>'+perfRank+'</td>'
						+'<td>'+runRank+'</td>'
						+'<td>'+breakRank+'</td>'
						+'<td>'+bounceRank+'</td>'
						+'<td>'+type+'</td>'
					+'</tr>');
				}
				},
				error:function(data){
					console.log(data);
					$.messager.alert("提示","获取数据失败","error");
				}
			});
			
			
			
		/* $.get(url, function(data, status) {
			if (status == "success") {
				myChart.hideLoading();
				$("#load").remove();
				$("#mainTable").show();
				//alert(data);
				var legs = [], sers = [];
				//var dataObj = JSON.parse(data);
				
				for ( var i = 0; i < data.length; i++) {
					var aobj = data[i];
					console.log(aobj);
					legs[i] = aobj.ages;
					sers[i] = aobj.userCount;
					$("#table tbody").append(
						'<tr>'
						+'<td>用户名</td>'
						+'<td>昵称</td>'
						+'<td>性别</td>'
						+'<td>年龄</td>'
						+'<td>身高体重</td>'
						+'<td>球场位置</td>'
						+'<td>运动记录时间</td>'
						+'<td>打球时长</td>'
						+'<td>活跃度</td>'
						+'<td>平均速度</td>'
						+'<td>卡路里</td>'
						+'<td>总步数</td>'
						+'<td>纵向距离</td>'
						+'<td>横向距离</td>'
						+'<td>纵跳次数</td>'
					+'</tr>');
				}
			} else {
				alert("查询数据失败！");
			}
		}); */
	}
	
	//function exportData(){
				
    		//导出
    		
    			 var idTmr;
function  getExplorer() {
    var explorer = window.navigator.userAgent ;
    //ie 
    if (explorer.indexOf("MSIE") >= 0) {
        return 'ie';
    }
    //firefox 
    else if (explorer.indexOf("Firefox") >= 0) {
        return 'Firefox';
    }
    //Chrome
    else if(explorer.indexOf("Chrome") >= 0){
        return 'Chrome';
    }
    //Opera
    else if(explorer.indexOf("Opera") >= 0){
        return 'Opera';
    }
    //Safari
    else if(explorer.indexOf("Safari") >= 0){
        return 'Safari';
    }
}
function method1(tableid) {//整个表格拷贝到EXCEL中
    if(getExplorer()=='ie')
    {
        var curTbl = document.getElementById(tableid);
        var oXL = new ActiveXObject("Excel.Application");

        //创建AX对象excel 
        var oWB = oXL.Workbooks.Add();
        //获取workbook对象 
        var xlsheet = oWB.Worksheets(1);
        //激活当前sheet 
        var sel = document.body.createTextRange();
        sel.moveToElementText(curTbl);
        //把表格中的内容移到TextRange中 
        sel.select();
        //全选TextRange中内容 
        sel.execCommand("Copy");
        //复制TextRange中内容  
        xlsheet.Paste();
        //粘贴到活动的EXCEL中       
        oXL.Visible = true;
        //设置excel可见属性

        try {
            var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
        } catch (e) {
            print("Nested catch caught " + e);
        } finally {
            oWB.SaveAs(fname);

            oWB.Close(savechanges = false);
            //xls.visible = false;
            oXL.Quit();
            oXL = null;
            //结束excel进程，退出完成
            //window.setInterval("Cleanup();",1);
            idTmr = window.setInterval("Cleanup();", 1);

        }

    }
    else
    {
        tableToExcel(tableid);
    }
}
function Cleanup() {
    window.clearInterval(idTmr);
    CollectGarbage();
}
var tableToExcel = (function() {
    var uri = 'data:application/vnd.ms-excel;base64,',
    template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))); },
    format = function(s, c) {
        return s.replace(/{(\w+)}/g,
        function(m, p) { return c[p]; }) }
            return function(table, name) {
                if (!table.nodeType) table = document.getElementById(table);
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
                window.location.href = uri + base64(format(template, ctx));
              }
})();
	//}
	
	
	
	
	$(function(){ 
	    inputTipText();  //初始化Input的灰色提示信息  
    });  
	
	
	/**
 * Input框里的灰色提示，使用前先引入jquery
 * <br>使用方法：<input type="text" tipMsg="您的用户名"&nbsp;&nbsp; />
 * 
 * @return
 */
function inputTipText(){ 
	$("input[tipMsg]").each(function(){
		if($(this).val() == ""){
			var oldVal=$(this).attr("tipMsg");
		if($(this).val()==""){$(this).attr("value",oldVal).css({"color":"#888"});}
		$(this)
		   .css({"color":"#888"})     //灰色
		   .focus(function(){
		    if($(this).val()!=oldVal){$(this).css({"color":"#000"});}else{$(this).val("").css({"color":"#888"});}
		   })
		   .blur(function(){
		    if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"});}
		   })
		   .keydown(function(){$(this).css({"color":"#000"});});
		}
	});
}
</script>
</html>