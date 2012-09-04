<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file = "../include/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>


    
    <title>My JSP 'MyJsp.jsp' starting page</title>
   
	
   

    <title></title>

</head>
  
  <body>
    <div class="container" >
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					  <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li class = "active"><a href="${basePath}/contact/contact.action"><i class=" icon-flag icon-white"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li><a href="${basePath}/task/task.action"><i class=" icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li><a href="${basePath}/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>
			</div>
			<div class="span9" >
				<div class="page-header alert alert-info">
					<h3>添加公司</h3><a href = "addMany.action">批量添加公司</a>
				</div>
				<form class="form-horizontal alert alert-success" action = "saveContact.action" method = "post" style = "margin-top:-18px" id = "myform">
				    <fieldset>
					    <div class="control-group">
						    <label class="control-label" for="input01">名称</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="company"  x-webkit-speech="undefined" name = "contact.companyname"/><span id = "enable"></span><img alt="" src="${basePath}/img/loading.gif" id = "loading" style = "display:none">
					    	</div>
					    </div>
						
						
					     <div class="control-group">
						    <label class="control-label" for="input01">电话</label>
						    <div class="controls">
						    	<div id = "tels"></div>
						    	<a href = "javascript:;" id = "addTel">添加电话号码</a>
					    	</div>
					    </div>
					     <div class="control-group">
						    <label class="control-label" for="input01">邮箱</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.email"/>
					    	</div>
					    </div>
						
						   <div class="control-group">
						    <label class="control-label" for="input01">IM</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.qq"/>
					    	</div>
					    </div>
						
						
						<div class="control-group">
						    <label class="control-label" for="input01">主页</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.websites"/>
					    		
					    	</div>
					    </div>
					  
					  
					     <div class="control-group">
						    <label class="control-label" for="input01">地址</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.address"/>
					    	</div>
					    </div>
					 
					    
					    <div class="control-group">
				            <label for="textarea" class="control-label">公司简介</label>
				            <div class="controls">
				              <textarea rows="3" id="textarea" class="input-xlarge" name = "contact.descs"></textarea>
				            </div>
				         </div>
						<div  style = "margin-left:80px">
							<h3>谁可以看见这个公司</h3>
							<hr />
						</div>
						<div style = "margin-left:80px;font-size:14px" class="control-group">
							<div style="margin-bottom:20px; ">
							<input type="radio" name="contact.view" value = "all" checked = "checked"/>所有人<br/><br/>
							<input type="radio" name="contact.view" value = "me"/>只有我能看见<br/>
							</div>							
							<div  class="control-group" >
								<label class="control-label" for="select01" style="margin-left:-50px; color:green;"><input type="radio" name="contact.view" value = "group"/>选择一个组...</label>
								<div class="controls" style="margin-left:0px;">							
								<select name = "chosegroup" id="e1" style="width:200px;">
									<option value = "">请选择</option>
									 <c:forEach items = "${groupList }" var = "g">
										<option value = "${g.id }">${g.groupname }</option>
									</c:forEach> 
								</select>
								</div>
							</div>
							<div  class="control-group" >
							
							<label class="control-label" for="select01" style="margin-left:-70px; color:green;"><input type="radio" name="contact.view" value = "specialmember"/>选择用户</label>
							<div class="controls" style="margin-left:90px;">
							 <select name = "members"   id="e9"  multiple="" class="populate" style="width:200px; margin-bottom:0px;">
									<option>请选择</option>
									 <c:forEach items = "${userList }" var = "u">
										<option value = "${u.id }">${u.username }</option>
									</c:forEach> 
								</select> 
								</div>
							</div>
						</div>
					
						
					     <div class="form-actions alert alert-success" style = "border:none">
					     	<input type = "submit" value = "保存此公司" name = "method" class  = "btn btn-primary"/>
					     	<input type = "submit" value = "保存并添加公司" name = "method" class = "btn btn-primary"/>
					     
				         </div>
				    </fieldset>
				 </form> 
				 				
			</div>
			
		</div>	
	</div>
	<script type="text/javascript">
		
		$(document).ready(function(){
		
		//判断用户名是否为空,为空则不进行提交
		$("#name").keydown(function(){
			$("#name-error").html("");
		})
		$("#btn").click(function(){
			var name = $("#name").val();
			if(name.trim() == ""){
				$("#name-error").html("用户名必填");
				$("#name").focus();
				return;
			}
			$("#myform").submit();
		})
		
		//select2
			$("#e1").select2(); 
			
			$("#e9").select2();
		
		//tel
			var i = 0;
			$("#tel").keydown(function(){
				$("#addTel").html("Add Another");
				
			});
			
			
			$("#addTel").click(function(){
			
				if(i < 4){
					 $("#tels").append("<div><input type='text' name='telnum' class='input-xlarge'/><select style = 'display:inline;width:85px' name = 'teltype'><option>公司</option><option>工作</option><option>手机</option></select><img class='del-text' src = '${basePath}/img/del.png'/></div>");
  					 i++;
				}else{
					$("#addTel").html("");
				}
			});
			
			$(".del-text").live("click",function(){ 
				$(this).parent().remove(); 
				i--; 
			}); 
		
		//判断该公司是否已经存在
		$("#company").blur(function(){
			var companyname = $(this).val();
			$.post("company.action",{"companyname":companyname},function(result){
				if(result == 1){
					//不可用
					$("#enable").text("该公司已经存在");
				}else{
					//可用
					$("#enable").text("√");
				}
				$("#loading").ajaxStart(function(){
					$("#enable").text("");
					$(this).show();
				})
				$("#loading").ajaxComplete(function(){
					$(this).hide();
				})
			})
		})		
		});
	</script>
  </body>
</html>
