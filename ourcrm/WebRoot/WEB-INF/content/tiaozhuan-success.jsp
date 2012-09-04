<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>我们的orm</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
 
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
	<style type="text/css">
		.body{
			background-color:#F3F1EC;
		}
		
		.navbar-inner{			
			height:50px;		
		}
		div .container{
			padding-top:4px;
			
		}
		.row{
			margin-top:60px;
		}
		div .navbar-inner{
			background-color:#106792;
		}
		.wall{
			border:1px solid #DDD;
			background-color:#F2F2F2;
			border-top:0px;
			
		}
		#question{
			display:none; 
			cursor: default;
			margin-bottom:0px;
		}
		h1{
			margin-top:15px;
			margin-bottom:15px;
		}
		label{
			font-size:14px;
		}
		#yes{
			margin-left:30px;
		}
		input{
			height:28px;
		}
	</style>
	</head>
	<body>
	
	
		<div class="navbar navbar-fixed-top" style="background-color:#000; ">
		<div class="navbar-inner" style="background-color:#106792;">
			<div class="container" >&nbsp; 
				 
				<span class="brand" style="color:white; font-size:25px;">Highrise</span>
				<div class="nav-collapse">
					<ul class="nav">
																
					</ul>
				</div>				
				<ul class="nav pull-right" >					
					<li >								
					<a  href="#" id="login" style="font-size:25px; color:white; padding-top:0px; " >Login</a>									
					</li>
					<li >								
					<a  href="${basePath }/reguser.action" style="font-size:24px; color:white; padding-top:0px; " >注册</a>									
					</li>
				</ul>
				
			</div>
		</div>
	</div>	
	
		<div class="slider-wrapper" style = "width:870px;margin-left:auto;margin-right:auto;margin-top:80px">
			<div id="slider" class="nivoSlider">
				<img src="img/1.jpg" alt="" />
				<img src="img/2.jpg" alt="" title="" />
				<img src="img/3.jpg" alt="" title="" />
				<img src="img/4.jpg" alt="" />
				
			</div>
		</div>
		<div id="question" class="breadcrumb"> 
			<form action="user.action" method="post" class="form-horizontal" id="myform">
				<legend><h1 style="color:green">欢迎登陆Highrise</h1></legend>
				<span id = "mes"></span>
				<div class="control-group">
					<label class="control-label" for="input01">用户名:</label>
					<div class="controls">
						<input type="text" class="input-xlarge" name="user.username" id="input01"><br/>
						<p class="help-block" style="color:green" id = "name-error"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">密码:</label>
					<div class="controls">
						<input type="password" class="input-xlarge" name="user.password" id="input02"><br/>
						<span id = "password-error"></span>
						<p class="help-block" style="color:green" id = "password-error"></p>
					</div>
				</div>
				<input class = "btn btn-primary" type="button" id = "btn" value = "进入系统"/>&nbsp;&nbsp;&nbsp;
				<button class="btn btn-info" id="no">取消进入</button>
				
			</form>
		</div> 
		
		
		
	<script type="text/javascript">
	$(document).ready(function(){
		$(window).load(function() {
			$('#slider').nivoSlider({
					directionNav:false,
					controlNav:false,
					pauseOnHover:false,
					pauseTime: 3000,
					controlNav: false // 1,2,3... navigation
			});
		});
		
		$('#login').click(function() { 
            $.blockUI({ message: $('#question'), css: { width: '500px' } }); 
        }); 
 
        $('#no').click(function() { 
            $.unblockUI(); 
            return false; 
        }); 
	
			$("input[name = 'user.username']").keydown(function(){
					$("#name-error").html("");
			});
			$("input[name = 'user.password']").keydown(function(){
				$("#password-error").html("");
			});
			
			$("#btn").click(function(){
				
				var name = $("input[name = 'user.username']").val();
				var password = $("input[name ='user.password']").val();
				if(name.trim() == ""){
					$("#name-error").html("请输入用户名");
					$("input[name ='user.name']").focus();
					return;
				}
				if(password == ""){
					$("#password-error").html("请输入密码");
					$("input[name ='user.password']").focus();
					return;
				}
				$.post("user.action",{"user.username":name,"user.password":password},function(result){
					if(result == 1){
						//成功
						window.location.href = "message/message.action";
					}else{
						//失败
						$("#mes").html("用户名或密码错误");
						//清空文本框中的值
						$("input[name = 'user.username']").val("");
						$("input[name = 'user.password']").val("");
						$("input[name = 'user.username']").focus();
					}
				})
				
			});
		
	});
		
	</script>
	</body>
</html>
