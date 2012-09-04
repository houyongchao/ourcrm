<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file = "../include/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
					<h3>编辑公司
				</div>
				<form class="form-horizontal alert alert-success" action = "saveContact.action?method=edit" method = "post" style = "margin-top:-18px">
				    <fieldset>
					    <div class="control-group">
						    <label class="control-label" for="input01">名称</label>
						    <input type = "hidden" value = "${contact.id }" name = "contact.id"/>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="company"  x-webkit-speech="undefined" name = "contact.companyname" value = "${contact.companyname }"/><span id = "enable"></span><img alt="" src="${basePath}/img/loading.gif" id = "loading" style = "display:none">
					    	</div>
					    </div>
						
					
					     <div class="control-group">
						    <label class="control-label" for="input01">电话</label>
						    <div class="controls">
						    	<c:forEach items = "${telList}" var = "t">
						    		<div><input type='text' name='telnum' class='input-xlarge' value = "${t.num}"/>
						    		<select style = 'display:inline;width:85px' name = 'teltype'>
						    			<c:choose>
						    				<c:when test="${t.type == '公司'}">
						    					<option selected = "selected">公司</option>
						    				</c:when>
						    				<c:otherwise>
							    				<option>公司</option>
						    				</c:otherwise>
						    			</c:choose>
						    		
						    			<c:choose>
						    				<c:when test="${t.type == '工作'}">
						    					<option selected = "selected">工作</option>
						    				</c:when>
						    				<c:otherwise>
							    				<option>工作</option>
						    				</c:otherwise>
						    			</c:choose>
						    			<c:choose>
						    				<c:when test="${t.type == '手机'}">
						    					<option selected = "selected">手机</option>
						    				</c:when>
						    				<c:otherwise>
							    				<option>手机</option>
						    				</c:otherwise>
						    			</c:choose>
						    		
						    		</select><img class='del-text' src = '${basePath}/img/del.png'/></div>
						    	</c:forEach>
						    
						    	<div id = "tels"></div>
						    	<a href = "javascript:;" id = "addTel">添加电话号码</a>	
						    	
						    
					    	</div>
					    </div>
					     <div class="control-group">
						    <label class="control-label" for="input01">邮箱</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.email" value = "${contact.email }"/>
					    	</div>
					    </div>
						
						   <div class="control-group">
						    <label class="control-label" for="input01">IM</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.qq" value = "${contact.qq }"/>
					    	</div>
					    </div>
						
						
						<div class="control-group">
						    <label class="control-label" for="input01">主页</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.websites" value = "${contact.websites }"/>
					    		
					    	</div>
					    </div>
					  
					  
					     <div class="control-group">
						    <label class="control-label" for="input01">地址</label>
						    <div class="controls">
						    	<input type="text" class="input-xlarge" id="input01"  x-webkit-speech="undefined" name = "contact.address" value = "${contact.address }"/>
					    	</div>
					    </div>
					 
					    
					    <div class="control-group">
				            <label for="textarea" class="control-label">公司简介</label>
				            <div class="controls">
				              <textarea rows="3" id="textarea" class="input-xlarge" name = "contact.descs">${contact.descs}</textarea>
				            </div>
				         </div>
						<div  style = "margin-left:80px">
							<h3>谁可以看见这个公司</h3>
							<hr />
						</div>
						<div style = "margin-left:80px;font-size:14px">
								<c:choose>
									<c:when test="${contact.view == 'all' }">
										<input type="radio" name="contact.view" value = "all" checked = "checked"/>所有人<br/>
									</c:when>
									<c:otherwise>
										<input type="radio" name="contact.view" value = "all" />所有人<br/>
									</c:otherwise>
								
								</c:choose>
							
								<c:choose>
									<c:when test="${contact.view == 'my' }">
										<input type="radio" name="contact.view" value = "me" checked = "checked"/>只有我<br/>
									</c:when>
									<c:otherwise>
										<input type="radio" name="contact.view" value = "me" />只有我<br/>
									</c:otherwise>
								</c:choose>
								
								<div  class="control-group" >
								<c:choose>
									<c:when test="${contact.view.contains('group') }">
											<label class="control-label" for="select01" style="margin-left:-50px; color:green;"><input type="radio" name="contact.view" value = "group" checked = "checked"/>选择一个组...</label>
									</c:when>
									<c:otherwise>
										<label class="control-label" for="select01" style="margin-left:-50px; color:green;"><input type="radio" name="contact.view" value = "group" />选择一个组...</label>
									</c:otherwise>
								</c:choose>
								<div class="controls" style="margin-left:0px;">							
									<select name = "chosegroup" id="e1" style="width:200px;">
										<option value = "" >请选择</option>
											<c:forEach items = "${groupList }" var = "g">
												<c:choose>
													<c:when test="${contact.view.contains(g.id) && contact.view.contains('group')}">
														<option value = "${g.id }" selected = "selected">${g.groupname }</option>
													</c:when>
													<c:otherwise>
														<option value = "${g.id }">${g.groupname }</option>
													</c:otherwise>
												</c:choose>
												
											</c:forEach> 
									</select>
								</div>
							</div>
							<c:choose>
								<c:when test="${contact.view.contains('members') }">
									<input type="radio" name="contact.view" value = "specialmember" checked = "checked"/>选择用户..
								</c:when>
								<c:otherwise>
									<input type="radio" name="contact.view" value = "specialmember">选择用户..
								</c:otherwise>
							</c:choose>
							 <select name = "members"   id="e9"  multiple="" class="populate" style="width:200px; margin-bottom:0px;">
								<option>请选择</option>
								<c:forEach items = "${userList }" var = "u">
									<c:choose>
										<c:when test="${contact.view.contains(u.id) }">
											<option value = "${u.id }" selected = "selected">${u.username }</option>
										</c:when>
										<c:otherwise>
											<option value = "${u.id }">${u.username }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						
					     <div class="form-actions alert alert-success" style = "border:none">
				            <button class="btn btn-primary" type="submit">保存此公司</button>
				         </div>
				    </fieldset>
				 </form> 
			</div>
			
		</div>	
	</div>
	<script type="text/javascript">
		
		$(document).ready(function(){
		
		$()
		//tel
			var i = 0;
			$("#tel").keydown(function(){
				$("#addTel").html("Add Another");
				
			});
			
			
			$("#addTel").click(function(){
			
				if(i < 4){
					 $("#tels").append("<div><input type='text' name='telnum' class='input-xlarge'/><select style = 'display:inline;width:85px' name = 'teltype'><option>公司</option><option>工作</option><option>手机</option><option>住宅</option></select><img class='del-text' src = '${basePath}/img/del.png'/></div>");
  					 i++;
				}else{
					$("#addTel").html("");
				}
			});
			
			$(".del-text").live("click",function(){ 
				$(this).parent().remove(); 
				i--; 
			}); 
							
			//select2
			$("#e1").select2(); 
			
			$("#e9").select2();
			
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
					
		})
	</script>
  </body>
</html>
