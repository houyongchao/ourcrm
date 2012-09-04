<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/select2.js"></script>
    <script type="text/javascript" src="js/jquery.blockUI.js"></script>
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
		#question{
			display:none; 
			cursor: default;
			margin-bottom:0px;
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
					
				</ul>
				
			</div>
		</div>
	</div>	
   <div class="container">
   	<div class="row">
   		<div class="span7 offset3" style="margin-top:100px;">
   			<form action="" method="post" class="well form-horizontal">
   				<fieldset>
   					<legend>欢迎注册</legend>
   					<div class="control-group">
				      <label class="control-label">用户名：</label>
				      <div class="controls">
				        <input type="text" class="input-xlarge" id="input04">
				        <p class="help-block1" style="color:red;"></p>
				      </div>
				    </div>
   					<div class="control-group">
				      <label class="control-label">密码：</label>
				      <div class="controls">
				        <input type="text" class="input-xlarge" id="input05">
				        <p class="help-block2" style="color:red;"></p>
				      </div>
				    </div>
   					<div class="control-group">
				      <label class="control-label">组名：</label>
				      <div class="controls">
				        <select class="input-xlarge" id="input06">
				        
				        </select>
				      </div>
				    </div>
				    <button style="margin-left:150px;" class="btn btn-info" id="reg">注册</button>
				    <button style="margin-left:10px;" class="btn">返回</button>
   				</fieldset>
   			</form>
   		</div>
   		
   		<div id="question" class="breadcrumb"> 
			<form action="user.action" method="post" class="form-horizontal" id="myform">
				<legend><h1 style="color:green">欢迎登陆Highrise</h1></legend>
				<span id = "mes"></span>
				<div class="control-group">
					<label class="control-label" for="input01">用户名:</label>
					<div class="controls">
						<input type="text" class="input-xlarge" name="user.username" id="input01"><br/>
						<p class="help-block" style="color:red" id = "name-error"></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">密码:</label>
					<div class="controls">
						<input type="password" class="input-xlarge" name="user.password" id="input02"><br/>
						<span id = "password-error"></span>
						<p class="help-block" style="color:red" id = "password-error"></p>
					</div>
				</div>
				<input class = "btn btn-primary" type="button" id = "btn" value = "进入系统"/>&nbsp;&nbsp;&nbsp;
				<button class="btn btn-info" id="no">取消进入</button>
				
			</form>
		</div> 
   	</div>
   
   </div>
   <script type="text/javascript">
   	$(document).ready(function(){
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
			$("#reg").click(function(){
				var username = $("#input04").val();
				var pwd = $("#input05").val();
				var zuming = $("#input06").val();
				if(username.trim() == ""){
					$(".help-block1").html("请输入用户名");
					$("#input04").focus();
					return;
				}
				if(pwd.trim() == ""){
					$(".help-block2").html("请输入密码");
					$("#input05").focus();
					return;
				}
				
				$.post("saveuser.action",{name:username,password:pwd,zu:zuming});
			});
			$("#input04").click(function(){
				$("#input06").text("");
				$.post("deal/searchgroup.action",function(result){
					$(result).each(function(){
						$("#input06").append($("<option value="+this.groupname+">"+this.groupname+"</option>"));
					});
				});
			});
			$("#input04").blur(function(){
				var username = $("#input04").val();
				$.post("searchusername.action",{name:username},function(result){
					if(result == 0){
						$(".help-block1").html("用户名可用");
					}else if(result == 1){
						$(".help-block1").html("用户名已被注册");
					}
				});
			});
			$("#input04").focus(function(){
				$(".help-block1").html("");
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
