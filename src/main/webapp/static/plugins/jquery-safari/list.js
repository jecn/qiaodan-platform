/**
 * 动态获取
 */
function toolbar(barId){
	console.log("url="+ basePath + "/sys/privilege/toolbar/"+$("#linsence").val());
	$(barId).empty();
	$.ajax({
		async : false,
		cache : false,
		type : 'GET',
		url : basePath + "/sys/privilege/toolbar/"+$("#linsence").val(),// 请求的action路径
		success : function(data) {
			console.log("data="+data);
			$(barId).append(data);
		}
	});
}

/**
 * 加载表格数据
 * @param tableId 表格ID
 * @param title 标题
 * @param url 请求URL
 * @param barId 工具栏div
 * @param height 裁剪高度
 */
function clos(tableId,title,url,barId,height){
	$.ajax({
		async : false,
		cache : false,
		type : 'GET',
		url : basePath + "/sys/privilege/cols/"+$("#linsence").val(),
		success : function(data) {
			data = eval(data);
			loadGrid(tableId,title,url,data,barId,height);
		}
	});
}

/**
 * 加载表格数据
 * @param tableId 表格ID
 * @param title 标题
 * @param url 请求URL
 * @param data 数据
 * @param barId 工具栏div
 * @param height 裁剪高度
 */
function loadGrid(tableId,title,url,data,barId,height){
	var IsCheckFlag = false;
	var tableHeight = gridHeight();
	$(tableId).datagrid({    
		    url:url, 
		    title:title,
		    idField : "id",   //表明该列是一个唯一列
			width: "auto",
			height: tableHeight-height,
		    fitColumns:true,   //设置为true将自动使列适应表格宽度以防止出现水平滚动
	        border : false,  
	        rownumbers : true,  
	        animate: true,  
	        striped : true,  //设置为true将交替显示行背景
	        nowrap:true,  //设置为true当数据长度超出列宽时将会自动截取
			pagination:true,  //设置true将在数据表格底部显示分页工具栏
			pageNumber:1,  //当设置分页属性时，初始化分页码
			pageSize : 10, //当设置分页属性时，初始化每页记录数
			pageList : [ 10, 15, 20, 25, 30 ],
			singleSelect:true,  
			checkOnSelect:true,
			columns: [data],
			toolbar:barId,
			enableHeaderClickMenu: false,
		    enableHeaderContextMenu: false,
		    enableRowContextMenu: false,
		    onLoadSuccess: function (data) { 
		    	$(tableId).datagrid('clearSelections');  //清除已选项
		    },
		    onSelect: function (rowIndex, rowData) {  
		    	if(IsCheckFlag){
		    		IsCheckFlag = false;
		    		$(tableId).datagrid("unselectRow",rowIndex);  
		    	}else{
		    		IsCheckFlag = true;
		    	}
	        }
	});
}

function del(url,ids){
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : basePath + url,// 请求的action路径
		data : {"ids":ids.toString()},
		success : function(data) {
			jQuery("#grid-table").trigger("reloadGrid");
		}
	});
}

/**
 * 计算表格的高度
 */
function gridHeight(){
	var iframeHeight = document.documentElement.clientHeight;
	var searchHeight = $("#query-panel").height();
	var toolbarHeight = $("#toolbar").height();
	return iframeHeight - (searchHeight + toolbarHeight);
}

function resizeGrid(selector){
	$(window).on('resize.jqGrid', function () {
		$(selector).jqGrid( 'setGridWidth', $(".widget-body").width());
    });
}
