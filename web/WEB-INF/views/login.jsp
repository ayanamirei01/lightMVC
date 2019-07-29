<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>用户登录</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link href="${path}/style/css/base.css" rel="stylesheet" type="text/css">
<link href="${path}/style/css/login.css" rel="stylesheet" type="text/css">
<script src="${path}/jslib/jquery-3.3.1.min.js"></script>
<script>


	$(function(){
	    $("#loginform").submit(function(){
	       var loginname = $("[name='login_name']").val();
	       var password = $("[name='password']").val();
	       $.ajax({
			   dataType:'json',
			   contextType:'application/json;charset=utf-8',
			   type:'POST',
			   url:'${path}/user/login',
               async:false,
			   data:{"login_name":loginname,"password":password},
			   success:function(result) {

			       if(!result.success) {
                       alert(result.error);
                       return false;
                   }
               }
		   })

		});
	})
	var sessionInfo_userId = '${sessionInfo.user.user_id}';

	if (sessionInfo_userId) {//如果登录,直接跳转到index页面

		window.location.href='${path}/user/index';
	}
	

	
	//回车登录
	function enterlogin(){
		if (event.keyCode == 13){
        	event.returnValue=false;
        	event.cancel = true;
        	$('#loginform').submit();
    	}
	}
	
</script>
</head>
<body onkeydown="enterlogin();">
	<div class="login">
		<form action="${path}/user/index" method="post" id="loginform">
		<div class="logo"></div>
	    <div class="login_form">
	    	<div class="user">
	        	<input class="text_value" type="text" name="login_name"  value="" placeholder="admin"></input>
	            <input class="text_value" type="password" name="password" value="" ></input>
	        </div>
	        <button class="button"  type="submit" >登录</button>
	    </div>
	    
	    <div id="tip"></div>
	    
	    </form>
	</div>
	
	<!--[if lte IE 7]>
	<div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
	<a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
	<![endif]-->
	
	<style>
		/*ie6提示*/
		#ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
		#ie6-warning p{width:960px;margin:0 auto;}
	</style>
	</body>
</html>