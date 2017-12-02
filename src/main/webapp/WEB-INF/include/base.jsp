<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- CSS >>>>Begin>>>>>>>>>>>>> -->


<!-- CSS >>>>End>>>>>>>>>>>>> -->

<!-- JS >>>>Begin>>>>>>>>>>>>> -->

<!-- JQuery 版本2.1.1-->
<script src="${ctxStatic}/plugins/jquery/jquery-2.1.1.min.js"></script>

<script src="${ctxStatic}/plugins/jquery-safari/utils.js"></script>

<!-- JS >>>>End>>>>>>>>>>>>> -->




<script>
//全局的AJAX访问，处理AJAX清求时SESSION超时
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
          //通过XMLHttpRequest取得响应头，sessionstatus           
          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
          if(sessionstatus=="timeout"){
               //跳转的登录页面
               window.location.replace('${ctx}/a/login');
       		}	
    }
});
</script>
