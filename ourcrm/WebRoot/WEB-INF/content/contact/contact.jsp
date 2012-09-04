<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "../include/base.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>我们的orm</title>
	
	
	<style type="text/css">
		
		
		.alert{
			margin-left:20px;
			margin-right:20px;
		}
		.wall a{
			text-decoration:none;
		}
		
	</style>
</head>
<body>
	
	
	<div class="container" >
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-left:5px;margin-right:20px;margin-top:15px;">
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
			<div class="span10" style="margin-left:0px;">
				<ul style="margin-top:10px; margin-bottom:0px; height:40px;" class="breadcrumb">
					<li>
						<form action="search.action" method="post" class="navbar-search" style="margin-left:5px;margin-bottom:10px;">
							<input type="text" name="search" placeholder="查找公司或个人"  style="border-radius:15px" value="${search }"/>
							<input type="submit" class="btn btn-info" value="搜索" style="margin-top:-7px;"/>
						</form>
					</li>
					
					<li style="margin-top:-5px;margin-left:240px;">
						<div class="control-group success pull-right">
							<select id="selectError" style="margin-top:14px;" name = "views">
								<c:choose>
									<c:when test="${show == 'all' }">
										<option value = "" selected = "selected">所有联系人和公司</option>
									</c:when>
									<c:otherwise>
										<option value = "">所有联系人和公司</option>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${show == 'allcontact' }">
										<option value = "allcontact" selected = "selected">所有联系人</option>
									</c:when>
									<c:otherwise>
										<option value = "allcontact">所有联系人</option>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${show == 'allcompany' }">
										<option value = "allcompany" selected = "selected">所有公司</option>
									</c:when>
									<c:otherwise>
										<option value = "allcompany">所有公司</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${show == 'me' }">
										<option value = "me" selected = "selected">我所创建的联系人和公司</option>
									</c:when>
									<c:otherwise>
										<option value = "me">我所创建的联系人和公司</option>
									</c:otherwise>
								</c:choose>
							
							
							</select>
						</div>
					</li>
				</ul>
				<div class="wall" style="margin-top:0px;padding-top:15px;">
			
					<c:forEach items = "${contactList}" var = "con">
						<div class="alert alert-success">
						<a href = "javascript:;" rel = "${con.id }" class = "del_link close" data-dismiss="alert">×</a>
						<a href = "showPerson.action?contactid=${con.id }">
							<input type="checkbox" name = "son"/> &nbsp;
							<c:choose>
								<c:when test="${con.contactname != null}">
									<img src="${basePath }/img/touxiang.png" alt="" /> ${con.contactname }<br>
								</c:when>
								<c:when test="${con.contactname == null }">
									<img src="${basePath }/img/gongsi.png" alt="" /> ${con.companyname }<br>
								</c:when>
							</c:choose>
							
						</a>
						
						<span style = "padding-left:25px;margin-right:20px">信息简介</span>
							<c:choose>
								<c:when test="${con.email.isEmpty() || con.email == null}">
									<span style = "margin-left:15px">邮箱：暂无</span>
								</c:when>
								<c:otherwise>
									<span style = "margin-left:15px">邮箱：${con.email}</span>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${con.descs == null || con.descs.isEmpty()}">
									<span style = "margin-left:25px">描述：暂无信息描述</span>
								</c:when>
								<c:otherwise>
									<span style = "margin-left:25px">描述：${con.descs}</span>
								</c:otherwise>
							</c:choose>
							
						
					</div>
					</c:forEach>
				
				<input type="checkbox" name="" id="father" />全选
				
					
				
					
				</div>
			</div>
			
		</div>	
		
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			//$(".alert").alert();
			$(".del_link").click(function(){
				var id = $(this).attr("rel");
				if(confirm("确定要删除？")){
					window.location.href = "deleteContact.action?contactid="+id;
					
				}
			});
			
			$("#selectError").change(function(){
				var v = $("#selectError").val();
				window.location.href = "contact.action?views="+v;
			});
			
			 $("#father").click(function(){
				var sons = $("input[name = 'son']");
				for( var i = 0;i<sons.length;i++){
					sons[i].checked = this.checked;
				}
			}); 
			
		});
		
		
	
	
	</script>
</body>
</html>