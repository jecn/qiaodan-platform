var indexTitle ="首页";//不允许关闭的标签的标题
var btnMenu = null;

$(function(){
	//refreshTabs();
	initTimer();
    bindButtonEvent();
    bindTabsButtonEvent();
	rightTabs();
})

/**
 * 初始化左侧菜单
 * @param menus
 */
function initLeftMenu(menus) {
	$("#nav").accordion({animate:false,fit:true,border:false});

	var selectedPanelname = '';
    $.each(menus, function(i, n) {
		var menulist = '<ul class="navlist">';
		var child = n.child;
		
		if(undefined != child && null != child && child.length > 0){
			menulist +=loop(child,0);
		}else{
			menulist += '<li><div><a ref="'+n.id+'" href="#" rel="' + n.url + '" ><span class="icon '+n.icon+'" >&nbsp;</span><span class="nav">' + n.name + '</span></a></div></li>';
		}
		menulist += '</ul>';

		$('#nav').accordion('add', {
            title: n.name,
            content: menulist,
		    border:false,
            iconCls: 'icon ' + n.icon
        });

		if(i==0){
			selectedPanelname =n.name;
		}
    });

	$('#nav').accordion('select',selectedPanelname);

	$('.navlist li a').click(function(){
        var title = $(this).children('.nav').text();
		var url = baseAdmin + $(this).attr("rel");
		var id = $(this).attr("ref");
		var icon = $(this).find('.icon').attr('class');

		find(menus,id);
		if(btnMenu && btnMenu.child && btnMenu.child.length>0){
			var cl = $(this).parent().next().attr("class");
			cl = cl.split(" ")[1];
			$("."+cl).slideUp();   //隐藏

			var ul =$(this).parent().next();
			if(ul.is(":hidden")){
				ul.slideDown();
			}else{
				ul.slideUp();
			}
		}else{
			openTab(title,url,icon);
			$('.navlist li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	/*var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);*/
}

/*
  循环遍历节点
*/
function loop(childMenus,num){
	var menulist = "";
	$.each(childMenus, function(j, p) {
		menulist += '<li><div><a ref="'+p.id+'" href="#" rel="' + p.url + '" ><span class="icon '+p.icon+'" >&nbsp;</span><span class="nav">' + p.name + '</span></a></div> ';
		var child = p.child;
		if(undefined != child && null != child && child.length > 0){
			menulist += '<ul class="third_ul ul' + num + '">';
			menulist += loop(child,++num);
			num = 0;
			menulist += '</ul>';	
		}

		menulist+='</li>';
	});
	return menulist;
}

function find(data,id) {
    var type = typeof data;
    if (type == "object") {
        for (var key in data) {
            if(key == "id" && data[key] == id){
				btnMenu = data;
				return false;
			}else{
				find(data[key],id)
			}
        }
    }
}

/**
 * 打开TAB选项卡
 * @param title
 * @param url
 * @param icon
 */
function openTab(title,url,icon){
	if(!$('#mainTabs').tabs('exists',title)){
		$('#mainTabs').tabs('add',{
			title:title,
			cache:false, 
			content:createFrame(url),
			closable:true,
			icon:icon
		 });
	}else{
		$('#mainTabs').tabs('select',title);
	}
	$('#mainTabs').tabs('getTab', title).css('width', 'auto');
	tabBind();
}

/**
 * 为TAB选项卡绑定事件
 */
function tabBind(){
	
	//双击关闭
	$(".tabs-inner").dblclick(function(){
		var title = $(this).children(".tabs-closable").text();
		if(null != title && "" != title && indexTitle != title){
			$('#mainTabs').tabs('close',title);
		}
	});
	
	//为选项卡绑定右键菜单
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#right-hand').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		var title =$(this).children("span").text();
		$('#right-hand').data("currtab",title);
		return false;
	});
}

/**
 * 选择TAB时刷新内容
 */
function refreshTabs(){
	 var currentTab = $('#mainTabs').tabs('getSelected');
	 if (currentTab && currentTab.find('iframe').length > 0) {
		 var curTabFrame = currentTab.find('iframe')[0];
		 var urlTab = curTabFrame.src;
		 
		 if(urlTab){
			$('#mainTabs').tabs('update', {
	            tab: currentTab,
	            options: {
	                content: createFrame(urlTab),
	            }
	        });
			currentTab.panel('refresh');
		}
    }
}

/**
 * 右键事件
 */
function rightTabs() {
    $('#right-hand').menu({
        onClick: function (item) {
        	var action = item.id;
        	var alltabs = $('#mainTabs').tabs('tabs');//所有tab页
            var currentTab = $('#mainTabs').tabs('getSelected'); //当前tab页
            var currentTabTitle = currentTab.panel('options').title; //当前tab页的标题
           
            var allTabtitle = [];
        	$.each(alltabs,function(i,n){
        		allTabtitle.push($(n).panel('options').title);
        	});
        	
            switch (action) {
                case "close": // 关闭当前活动tab页
                    $('#mainTabs').tabs('close', currentTabTitle);
                    break;
                case "closeall": // 全部关闭
                    $.each(allTabtitle, function (i, n) {
                        if (n != indexTitle){
                            $('#mainTabs').tabs('close', n);
        				}
                    });
                    break;
                case "closeother"://除此之外全部关闭
                    $.each(allTabtitle, function (i, n) {
                        if (n != currentTabTitle && n != indexTitle){
                            $('#mainTabs').tabs('close', n);
        				}
                    });
                    break;
                case "closeright"://当前页右侧全部关闭
                    var tabIndex = $('#mainTabs').tabs('getTabIndex', currentTab);
                    if (tabIndex == alltabs.length - 1){
                        return false;
                    }
                    $.each(allTabtitle, function (i, n) {
                        if (i > tabIndex) {
                            if (n != indexTitle){
                                $('#mainTabs').tabs('close', n);
        					}
                        }
                    });

                    break;
                case "closeleft"://当前页左侧全部关闭
                    var tabIndex = $('#mainTabs').tabs('getTabIndex', currentTab);
                    if (tabIndex == 1) {
                        return false;
                    }
                    $.each(allTabtitle, function (i, n) {
                        if (i < tabIndex) {
                            if (n != indexTitle){
                                $('#mainTabs').tabs('close', n);
        					}
                        }
                    });
                    break;
            }
        }
    });
    return false;
}

function createFrame(url){
	var frame = '<iframe id="mainFrame" scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:100%;padding: 0px;"></iframe>';
	return frame;
}

//时钟初始化
function initTimer() {
    var interval = function () { 
    	$("#timerSpan").text($.date.toLongDateTimeString(new Date())); 
    };
    window.setInterval(interval, 1000);
};

//绑定按钮事件
function bindButtonEvent () {
	
	var themeData = $.array.filter($.easyui.theme.dataSource, function (val) {
	    return val.disabled ? false : true;
	});
	
	//界面皮肤风格
    var theme = $.easyui.theme(), themeName = $.cookie("themeName");
    var themeCombo = $("#themeSelector").combobox({
        width: 100,
        editable: false,
        data: themeData,
        valueField: "path", 
        textField: "name",
        value: themeName || themeData[0].path,
        onSelect: function (record) {
            var opts = $(this).combobox("options");
            setTheme(record[opts.valueField]);
        }
    });

    // 全屏
    $("#btn-screen").click(function () { 
        if ($.util.supportsFullScreen) {
            if ($.util.isFullScreen()) {   //是否为全屏
                $.util.cancelFullScreen();
            } else {
                $.util.requestFullScreen();
            }
        } else {
            $.easyui.messager.show("当前浏览器不支持全屏 API，请更换至最新的 Chrome/Firefox/Safari 浏览器或通过 F11 快捷键进行操作。");
        }
    });
    
    //修改密码
    $("#btn-uppwd").click(function () {
        
    });

    //注销
    $("#btn-exit").click(function () {
        $.easyui.messager.confirm("操作提醒", "您确定要退出当前程序并关闭该网页？", function (data) {
            if (data) {
                window.location.href = baseAdmin + "/logout";
            }
        });
    });
}

/**
 * 设置当前系统主界面的界面皮肤风格
 * @param theme 界面皮肤风格名称
 * */
function setTheme(theme) {
    var setCookie = false;
    var themeDefault = $.easyui.theme(); //默认
    var themeName = $.cookie("themeName");  //cookie值
    
    if(!themeName && themeDefault != theme){
    	setCookie = true;
    }else if(themeName && themeName != theme){
    	setCookie = true;
    }
    
    $.easyui.theme(true, theme, function (item) {
        if (setCookie) {  //是否将theme保存至cookie名为themeName
            $.cookie("themeName", theme, { expires: 30 });
            var msg = $.string.format("您设置了新的系统主题皮肤为：{0}，{1}。", item.name, item.path);
            $.easyui.messager.show(msg);
        }
    });
};



//绑定Tabs页按钮事件
function bindTabsButtonEvent() {
	// 跳转到主页
	$("#mainTabs_jumpHome").click(function () {
	  $('#mainTabs').tabs('select',indexTitle);
	});
	
  // 展开/折叠面板
  $("#mainTabs_toggleScreen").click(function () { 
	  $("#mainLayout").layout("toggleAll", "collapse");
  });
  
  // 刷新当前Tabs页
  $("#mainTabs_refreshTab").click(function () { 
	 var currentTab = $('#mainTabs').tabs('getSelected');
	 if (currentTab && currentTab.find('iframe').length > 0) {
		 var curTabFrame = currentTab.find('iframe')[0];
		 var urlTab = curTabFrame.src;
		 
		 if(urlTab){
			$('#mainTabs').tabs('update', {
	            tab: currentTab,
	            options: {
	                content: createFrame(urlTab)
	            }
	        });
		}
     }
  });
  
  // 关闭当前Tabs页
  $("#mainTabs_closeTab").click(function () { 
	  var currentTab = $('#mainTabs').tabs('getSelected'); // 当前tab页
      var currentTabTitle = currentTab.panel('options').title; // 当前tab页的标题
      $('#mainTabs').tabs('close', currentTabTitle);
  });
}
