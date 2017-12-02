<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>

<%
String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
request.setAttribute("error", error);
%>
<!DOCTYPE html>
<html>
<head>
	<title>${fns:getCompanyName()}</title>
	<script src="${ctxStatic}/plugins/jquery/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/login.css" />
	<script>
	var captcha;
	function refreshCaptcha(){  
	    document.getElementById("img_captcha").src="${ctxStatic}/images/kaptcha.jpg?t=" + Math.random();  
	}  
	
	//判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，    
	//这样就可以让登陆窗口显示在整个窗口了    
	function loadTopWindow(){    
	    if (window.top!=null && window.top.document.URL!=document.URL){    
	        window.top.location= document.URL;     
	    }    
	}    
	</script>
	
</head>
<body onload="loadTopWindow()">
	<div>
	<form id="loginForm" action="${ctxAdmin}/login" method="post">
		<div class="login_top">
			<div class="login_title">
				后台管理系统登录页 
			</div>
		</div>
		<div style="float:left;width:100%;">
			<div class="login_main">
				<div class="login_main_top"></div>
				<div class="login_main_errortip">&nbsp;</div>
				<div class="login_main_ln">
					<input type="text" id="username" name="username" value="superadmin"/>
				</div>
				<div class="login_main_pw">
					<input type="password" id="password" name="password" value="123456"/>
				</div>
					<%-- <c:if test="${isValidateCodeLogin}">
						<div class="login_main_yzm">
							<div>
								<input type="text" id="captcha" name="captcha" /> <img alt="验证码"
									src="${ctxStatic}/images/kaptcha.jpg" title="点击更换"
									id="img_captcha" onclick="javascript:refreshCaptcha();"
									style="height:45px;width:85px;float:right;margin-right:98px;" />
							</div>
						</div>
					</c:if> --%>
					<div class="login_main_remb">
					<!-- <input id="rm" name="rememberMe" type="hidden"/> --><!-- <label for="rm"><span>记住我</span></label> -->
				</div>
				<div class="login_main_submit">
					<button onclick=""></button>
				</div>
			</div>
		</div>
	</form>
	</div>
	<c:choose>
		<%-- <c:when test="${error eq 'com.tianyu.jty.system.utils.CaptchaException'}">
			<script>
				$(".login_main_errortip").html("验证码错误，请重试");
			</script>
		</c:when> --%>
		<c:when test="${error eq 'org.apache.shiro.authc.UnknownAccountException'}">
			<script>
				$(".login_main_errortip").html("帐号或密码错误，请重试");
			</script>
		</c:when>
		<c:when test="${error eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
			<script>
				$(".login_main_errortip").html("用户名不存在，请重试");
			</script>
		</c:when>
	</c:choose>
</body>
</html>
