<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>${fns:getCompanyName()}</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/home.css"/>
<%@ include file="/WEB-INF/include/easyui.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/detail.css">
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/sys/index.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-safari/sys/index-startup.js"></script>
</head>
<body>
<noscript>
	<div style="position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
	    <img src="${ctxStatic}/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	</div>
</noscript>

	<!-- 容器遮罩 -->
    <div id="maskContainer">
        <div class="datagrid-mask" style="display: block;"></div>
        <div class="datagrid-mask-msg" style="display: block; left: 50%; margin-left: -52.5px;">
            正在加载...
        </div>
    </div>

    <div id="mainLayout" class="easyui-layout hidden" data-options="fit: true">
    
    	<div id="northPanel" data-options="region: 'north', border: false" style="height: 88px; overflow: hidden;">
            <div id="topbar" class="top-bar">
                <div class="top-bar-left">
                    <div style="width:100%;height:100%">
                    	<span style="position:absolute;left: 10px; bottom:2px;font-size:25px;color: #fff">${fns:getProductName()}</span>
                    </div>
                </div>
                
                <div class="top-bar-right">
                    <div id="timer">
                    	<span class="icon-clock" id="timerSpan" style="padding-left: 25px; background-position: left center;"></span>
                    </div>
                </div>
            </div>
            
            <div id="toolbar" class="panel-header panel-header-noborder top-toolbar">
                <div id="infobar">
                    <span class="icon-user" style="padding-left: 25px; background-position: left center; font-size:14px;">
                       <shiro:principal property="name"/>:${fns:welcome()}
                    </span>
                </div>
               
                <div id="buttonbar">
                    <span>更换皮肤：</span>
                    <select id="themeSelector" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'"></select>
                    
                    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_set',iconCls:'icon-cog'">系统</a>  
                    <div id="layout_north_set" style="display:none">
						<div id="btn-screen" data-options="iconCls:'icon-arrow-inout'">全屏切换</div>
						<div id="btn-uppwd" data-options="iconCls:'icon-uppwd'">修改密码</div>
						<div id="btn-exit" data-options="iconCls:'icon-logout'">退出系统</div>
					</div>
                </div>
            </div>
        </div>
    
	    <div data-options="region: 'west', title: '菜单导航', iconCls: 'icon-map', split: true, 
        	minWidth: 200, maxWidth: 400" style="width: 220px; padding: 1px;">
            <div id="nav" class="easyui-accordion" data-options="fit:true,border:false"></div>
        </div>
	    
	    <div data-options="region: 'center'">
            <div id="mainTabs_tools" class="tabs-tool">
                <table>
                    <tr>
                        <td><a id="mainTabs_jumpHome" class="easyui-linkbutton" title="跳转至首页" data-options="plain: true, iconCls: 'icon-home'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
						<td><a id="mainTabs_toggleScreen" class="easyui-linkbutton" title="展开/折叠" data-options="plain: true, iconCls: 'icon-arrow-inout'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_refreshTab" class="easyui-linkbutton" title="刷新当前页" data-options="plain: true, iconCls: 'icon-arrow-refresh'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_closeTab" class="easyui-linkbutton" title="关闭当前页" data-options="plain: true, iconCls: 'icon-application-form-delete'"></a></td>
                    </tr>
                </table>
            </div>
            
            <div id="mainTabs" class="easyui-tabs" data-options="fit: true, border: false, showOption: true, enableNewTabMenu: true, tools: '#mainTabs_tools', enableJumpTabMenu: true">
				<div id="homePanel" data-options="title: '首页', iconCls: 'icon-home'" ></div>
            </div>
        </div>
	    
	    <div data-options="region: 'east', title: '日历', iconCls: 'icon-date', split: true,collapsed: true, minWidth: 160, maxWidth: 500" style="width: 220px;">
            <div id="eastLayout" class="easyui-layout" data-options="fit: true">
                <div data-options="region: 'north', split: false, border: false" style="height: 220px;">
                    <div class="easyui-calendar" data-options="fit: true, border: false"></div>
                </div>
                
                <div id="linkPanel" data-options="region: 'center', border: false, title: '通知', iconCls: 'icon-link', tools: [{ iconCls: 'icon-refresh', handler: function () { window.link.reload(); } }]">
                    
                </div>
            </div>
        </div>
        
	    <div data-options="region: 'south', title: '关于...', collapsed: true, border: false, iconCls: 'icon-about'" style="height: 70px;">
            <div style="color: #4e5766; padding: 6px 0px 0px 0px; margin: 0px auto; text-align: center; font-size:12px; font-family:微软雅黑;">
                
            </div>
        </div>
    
	    <div id="right-hand" class="easyui-menu" style="width:150px;display: none">
			<div id="close">关闭</div>
			<div id="closeall">全部关闭</div>
			<div id="closeother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div id="closeright">当前页右侧全部关闭</div>
			<div id="closeleft">当前页左侧全部关闭</div>
		</div>

		<!-- 以下5个div为5个窗口的div -->
		<div id="dlga" class="easyui-dialog" closeFlag=""  closed="true">
			<iframe width="100%" height="100%" id="iframea" name="iframea" src="" frameborder="0"></iframe>
		</div>
		<div id="dlgb" class="easyui-dialog" closeFlag=""  closed="true">
			<iframe width="100%" height="100%" id="iframeb" name="iframeb" src="" frameborder="0"></iframe>
		</div>
		<div id="dlgc" class="easyui-dialog" closeFlag=""  closed="true">
			<iframe width="100%" height="100%" id="iframec" name="iframec" src="" frameborder="0"></iframe>
		</div>
		<div id="dlgd" class="easyui-dialog" closeFlag=""  closed="true">
			<iframe width="100%" height="100%" id="iframed" name="iframed" src=""  frameborder="0"></iframe>
		</div>
		<div id="dlge" class="easyui-dialog" closeFlag=""  closed="true">
			<iframe width="100%" height="100%" id="iframee" name="iframee" src=""  frameborder="0"></iframe>
		</div>
		
		<!-- 为解决窗口拖动不顺畅问题添加的div，不能删除 -->
		<div id="loaderDiv" class="coverDiv" onmouseup="isMove=false;this.style.display='none';" style="display:none;"></div>

	</div>
	
<script>
var baseAdmin = '${ctxAdmin}';
$.ajax({
	async:false,
	type:"get",
	url:"${ctxAdmin}/sys/privilege/my/menu",
	dataType:"json",
	success: function(data){
		initLeftMenu(data);
	}
});

$(".easyui-linkbutton").on("click", function(){    
	$(".easyui-linkbutton").linkbutton({selected:false}); 
    $(this).linkbutton({selected:true});  
});   
</script>
    
</body>
</html>
