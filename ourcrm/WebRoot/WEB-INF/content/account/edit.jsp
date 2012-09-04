<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'edit.jsp' starting page</title>
  </head>
  
  <body>
   	<div class="container">
		<div class="page-header" style="border-bottom:1px #333 solid; margin-top:30px;">
    		<h1>${sessionScope.user.username } <small style="font-size:30px;"> 账户设置</small></h1>
 	 	</div>
 	 	
 	 	<%-- <c:if test="${param.code == '10001' }">
			<div class="alert alert-error">
		        <button data-dismiss="alert" class="close" type="button">×</button>
		       	上传的头像类型不正确..
		     </div>
      </c:if>
      
      <c:if test="${param.code == '10002' }">
	      <div class="alert alert-error">
	        <button data-dismiss="alert" class="close" type="button">×</button>
	       	文件不能超过2M。。
	      </div>
      </c:if> --%>
      
      <c:if test="${param.code == '10000' }">
	     <div class="alert alert-success">
	       <button data-dismiss="alert" class="close" type="button">×</button>
	      	信息修改成功，如果修改了密码请重新登录
	     </div>
	   </c:if>
	   
	<form  method="post" id="modify" enctype="multipart/form-data"  action="${basePath}/account/modify.action" accept-charset="UTF-8" class="form-horizontal"><div style="margin:0;padding:0;display:inline">
		<input type="hidden" value="${sessionScope.user.id }" name="user.id"></div>
	  <div class="row">
			<div class="span8">
				<div class="control-group error">
				<label for="user_name" class="string optional control-label"> 姓名</label>
				<div class="controls"><input type="text" value="${sessionScope.user.username }" size="50" name="user.username" id="username" class="string optional"></div>
				</div>
				
				  <div class="control-group  error">
				  <label for="user_password" class="password optional control-label"> 密码</label>
				  <div class="controls">
				  <input type="password" size="50" name="user.password" id="password" class="password optional">
				  <p class="help-block">如果不想修改密码，则留空。</p>
				  </div>
				  </div>
				  
				  <div class="control-group  error">
				  <label for="user_password_confirmation" class="password optional control-label"> 确认密码</label>
				  <div class="controls">
				  <input type="password" size="50" name="confirm" id="confirm" class="password optional">
				  <p style="color:red;display:none" id="pwdhelp" class="help-block">两次输入的密码不同</p>
				  </div>
				  </div>
				  
				  <div class="control-group  error">
				  <label for="user_current_password" class="password optional control-label"> 当前密码</label>
				  <div class="controls">
				  <input type="password" size="50" name="password" id="currentpassword" class="password optional">
				  <p id="current" class="help-block">输入密码以确认修改</p>
				  </div>
				  </div>
				  
				 <div class="control-group  ">
				 	  <div class="controls">
				  		<input type="button" id="btn" value="保存修改" name="commit" class="btn btn-warning">
				  	 </div>
				  </div>
			</div>
			<div class="span2">
				<%-- <p>
					<span style="display: inline-block;width: 55px; padding-top: 55px;font-size:1px">
					<img src="head.action?id=${user.id }"/>
					</span> <br>
					<a href="javascript:;" id="headmodify">修改头像</a>
				</p>
	
				<p style="display:none;" id="avatar_field">
					从本地电脑选择一张大头照 (不要超过2M) <br>
					
						<input type="file" name="headfile" id="head">
				</p> --%>
				
			</div>
			</div>
		</form>
	</div>
  <script type="text/javascript">
  	$(document).ready(function(){
  		
  		
  			$("#pwdhelp").keydown(function(){
  			$("#pwdhelp").hidden();
  		});
  		
  		
  		var index = 0;
  		$("#currentpassword").blur(function(){
  			$.post("passwordValidate.action",{
  				"password":$("#currentpassword").val()
  			},function(date) {
  				if(date) {
  					index = 1;
  					$("#current").css("color","#333");
  					$("#current").text("密码输入正确");
  				} else {
  					index = 2;
  					$("#current").css("color","red");
  					$("#current").text("密码输入不正确");
  					
  				}
  			  }
  			);
  		});
  		
  	
  		$("#btn").click(function(){
  			if($("#password").val() != $("#confirm").val()) {
  				$("#pwdhelp").show(500);
  			} /*  else if(index == 2) {
  				$("#current").css("color","red");
				$("#current").text("密码输入不正确");
  			} */ else {
  				$("#modify").submit();
  			}
  			
  		});
  		
  	});
  </script>
  </body>
</html>
