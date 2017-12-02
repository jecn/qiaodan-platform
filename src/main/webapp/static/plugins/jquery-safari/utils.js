/**
 * js获取项目根路径
 */
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var path = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = path.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhost =path.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhost+projectName);
}

function rowIsNull(row){
	if(row){
		return false;
	}else{
		top.$.messager.alert("提示","请选中一条记录","info");
		return true;
	}
}

/**
 * 方法描述：清空表单
 * param formId 表单id
 * param text true为清空文本框。false为不清空文本框
 * param hidden true为清空隐藏域。false为不清空隐藏域
 * param select true为清空下拉菜单。false为不清空下拉菜单
 * param textarea true为清空大文本框。false为不清空大文本框
 */
function clearForm(formId,text,hidden,select,textarea) {
	if (typeof $("#" + formId) == undefined) {
		return;
	}
	if(text){
		$("#" + formId + " input:text").each(function() {
			var isClear = $(this).attr("isClear");
			if (typeof isClear == 'undefined' || isClear == true) {
				$(this).val("");
			}
		});
	}
	if(hidden){
		$("#" + formId + " input:hidden").each(function() {
			$(this).val("");
		});
	}
	if(select){
		$("#" + formId + " select").each(function() {
			var isClear = $(this).attr("isClear");
			if (typeof isClear == 'undefined' || isClear == true) {
				$(this).get(0).selectedIndex = 0;
			}
		});
	}
	if(textarea){
		$("#" + formId + " textarea").each(function() {
			var isClear = $(this).attr("isClear");
			if (typeof isClear == 'undefined' || isClear == true) {
				$(this).html("");
			}
		});
	}
}

/**----------------弹出窗口定义开始--------------------------**/
var biframe;//窗口内容的iframe
var dlgNumber =1; //弹出窗口序号1到5
var isMove = false;
/**
*   方法描述：页面弹出窗口方法
*   param url 弹出窗口要显示的页面（相对路径）
*   param dlgId 弹出窗口关闭时需要的id
*   param title 窗口title
*   param w 窗口宽度
*   param h 窗口高度
*   param modal 是否为模式窗口，true为是，false为不是
*   param closable 默认窗口是否处于关闭状态。true 为关闭
*   调用demo：parent.getDlg("url","dlgId","title",宽度,高度)
*						url,dlgId,title,宽度，高度
*/
function getDlg(url,dlgId,title,w,h,modal,closable){
	if(!modal){
		modal=true;
	}
	if(closable==null){
		closable=true;
	}
	if(dlgNumber > 5){
		$.messager.alert('提示', '最多只能同时存在五个弹出窗口！', 'info');
		dlgNumber ==5;
	}else{
		if(dlgNumber==0){
			dlgNumber =1;
		}
		
		var lastChar = dlgId.substring(dlgId.length-1);
		//如果存在相同的dialogid的窗口则关闭之前存在的重新生成窗口
		for(var i=1;i<=dlgNumber;i++){
			if($("#" + dlgId).attr("closeFlag") == "dlgId"){
				return;
			}
		}
		
		//初始化窗口
		$("#" + dlgId).dialog({
			title: title,
			height: h+30,
			width: w,
			modal:modal,
			shadow:false,
			closable:closable,
			closed:true,
			onMove:function(left,top){
			},
			onClose:function(){
				$("#iframe" + dlgId).attr("src","");
				$("#" + dlgId).attr("closeFlag","");
				$("#iframe"+lastChar).empty();
				this.parentNode.removeChild(this.parentNode.firstChild);
				if(dlgNumber > 1){
					dlgNumber = dlgNumber-1;
				}
			}
		});
		
		$("#"+dlgId).dialog("header").mousedown(function(){
			if('panel-tool-close' != event.srcElement.className){
				isMove = true;
			}
		});
		
		$("#"+dlgId).dialog("header").mousemove(function(){
			if (isMove) {
				$("#loaderDiv").css("display","");
			}
		});
		
		$("#"+dlgId).dialog("header").mouseup(function(){
			isMove = false;
			$("#loaderDiv").css("display","none");
		});
		$("#iframe"+lastChar).attr("src",url);
		//为属性closeFlag赋值
		$("#"+dlgId).attr("closeFlag",dlgId);
		//打开窗口
		$("#"+dlgId).dialog("open");
		dlgNumber += 1;
	}
}

/**
 * 根据窗口id关闭指定的窗口
 * @param dlgId
 */
function closeDlg(dlgId){
	$("#iframe" + dlgId).attr("src","");
	$("#" + dlgId).attr("closeFlag","");
	var lastChar = dlgId.substring(dlgId.length-1);
	$("#iframe"+lastChar).empty();
	$("#" + dlgId).dialog("close");
	if(dlgNumber >= 1){
		dlgNumber=dlgNumber-1;
	}
}

//刷新主页面
function reloadDatagrid(url, elementId) {
	var frame = parent.$("iframe");
	for ( var i = 0; i < frame.length; i++) {
		if (frame[i].src.indexOf(url) != -1) {
			frame[i].contentWindow.$(elementId).datagrid("reload");
			frame[i].contentWindow.$(elementId).datagrid("clearSelections");
		}
	}
}

//刷新主页面
function reloadTreegrid(url, elementId) {
	var frame = parent.$("iframe");
	for ( var i = 0; i < frame.length; i++) {
		if (frame[i].src.indexOf(url) != -1) {
			frame[i].contentWindow.$(elementId).treegrid("reload");
			frame[i].contentWindow.$(elementId).treegrid("clearSelections");
		}
	}
}
/** ---------------------弹出窗口定义结束---------------------------*/

var Utils = {
	/** 空判断 */
	isEmpty: function(o){
		// 这里对类型和空字串以及null和'null'字串做判断，那么'null'可能并不需要判断，以主要业务逻辑来看吧。  Base@20151105
		return (typeof(o) == "undefined" || o == null || o == "" || o == 'null' || o == 'undefined');
	},
	/** 事件冒泡终止 */
	stopBubble: function(e){
	    if (e && e.stopPropagation)
	        e.stopPropagation();
	    else
	        window.event.cancelBubble = true;
	},
	/** 保留首尾三个缩写 */
	ab: function(s){
		return s.substring(0, 3) + ".." + s.substring(s.length - 3, s.length);
	}
}


/**************************选择控件接口--开始************************************/
choose = {
	hiddenid : '',
	inputid : '',
	
	//inputid 显示域值的id   hiddenid 显示人员对应的id的隐藏文本框的id
	openWindow:function(inputid,hiddenid,url,title){
		this.inputid = document.getElementById(inputid);
		this.hiddenid = document.getElementById(hiddenid);
		top.getDlg(url, "dlge", title, 280, 360, true);
	},
	
	setName:function(name){
		if(this.inputid!=null){
			if(!Utils.isEmpty(name)){
				this.inputid.value=name;
			}
		}
	},
	setNames:function(name){
		if(this.inputid!=null){
			if(!Utils.isEmpty(name)){
				var oldName = this.inputid.value;
				if(!Utils.isEmpty(oldName)){
					this.inputid.value = oldName + "," + name;
				}else{
					this.inputid.value = name;
				}
			}
		}
	},
	resetName : function() {
		this.inputid.value = '';
	},
	setId:function(id){
		if(this.hiddenid!=null){
			if(!Utils.isEmpty(id)){
				this.hiddenid.value= id;
			}
		}
	},
	setIds:function(id){
		if(this.hiddenid!=null){
			if(!Utils.isEmpty(id)){
				var oldId = this.hiddenid.value;
				if(!Utils.isEmpty(oldId)){
					this.hiddenid.value = oldId + "," + id;
				}else{
					this.hiddenid.value = id;
				}
			}
		}
	},
	resetId : function() {
		this.hiddenid.value = '';
	},
	
	//inputid 显示域值的id   hiddenid 显示人员对应的id的隐藏文本框的id
	showWindow : function(inputid, hiddenid,url, title) {
		this.inputid = document.getElementById(inputid);
		this.hiddenid = document.getElementById(hiddenid);
		this.hiddenid.value='';
		top.getDlg(url, "dlge", title, 280, 360, true);
	},
	
	set : function(inputid, hidden) {
		var value = '';
		var ids = '';
		var innerId = this.hiddenid.value + '';
		for (i = 0; i < hidden.length; i++) {
			if (innerId.indexOf(hidden[i]) < 0 || innerId == '') {
				obj += '<span style="cursor: pointer" title="删除" onclick="choose.remove(\''
						+ inputid[i]
						+ '\', \''
						+ hidden[i]
						+ '\')">'
						+ inputid[i]
						+ '&nbsp;</span>';
				ids += ',' + hidden[i];
			}
		}
		if (innerId == '') {
			ids = ids.substring(1);
		}
		this.inputid.innerHTML += obj;
		this.hiddenid.value += ids;
	},
	reset : function() {
		this.inputid.innerHTML = '';
		this.hiddenid.value = '';
	},
	remove : function(name, id) {
		this.inputid.innerHTML = this.inputid.innerHTML.replace(
				'<span style="cursor: pointer" title=删除 onclick="choose.remove(\''
						+ name + '\', \'' + id + '\')">' + name
						+ '&nbsp;</span>', '');
		if (this.hiddenid.value.indexOf(',') == -1) {
			this.hiddenid.value = this.hiddenid.value.replace(id, '');
		} else {
			if (this.hiddenid.value.indexOf(id) == 0) {
				this.hiddenid.value = this.hiddenid.value.replace(id + ',', '');
			} else {
				this.hiddenid.value = this.hiddenid.value.replace(',' + id, '');
			}
		}
	}
}
/**************************选择控件接口--结束************************************/

