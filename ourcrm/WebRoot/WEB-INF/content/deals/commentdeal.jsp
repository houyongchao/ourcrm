<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>commentdeal</title>
    <style type="text/css">
    	.wall{
			height:850px;
			padding-top:15px;
		}
		.active{
			margin-top:10px;
			margin-left:10px;
		}
		.control-group{
			margin-left:20px;
		}
		.btn-group{
			margin-left:0px;
			margin-top:10px;
		}
		.btn-group button{
			height:30px;
			width:70px;
			font-size:16px;
		}
		.alert{
			margin-left:20px;
			margin-right:20px;
		}
		div.growlUI { 
			background: url(${basePath}/img/check48.png) no-repeat 10px 10px 
		}
		div.growlUI h1{
			color: white; 
			padding: 5px 5px 5px 75px; 
			text-align: left
		}
    </style>
  </head>
  <body>
    <div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px;margin-top:15px;">
				<ul class="nav nav-list" style="font-size:14px;" >					 					
				  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
				   <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
				  <li ><a href="${basePath}/contact/contact.action"><i class="icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
				  <li><a href="${basePath }/task/task.action"><i class="icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
				  <li><a href="${basePath }/case/case.action"><i class="icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
				  <li class="active"><a href="${basePath }/deal/deal.action"><i class="icon-white icon-user"></i>&nbsp;&nbsp;交 易</a></li>
				  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
				</ul>	
			</div>
			<div class="span9">
				<ul class="breadcrumb" style="margin-bottom:0px;">
					<li class="active">
						<h3 style="margin-top:-5px;">Note about <a href="#" style="color:green">${dealnote.deals.dealsname }</a></h3>
					</li>
				</ul>
				<div class="wall">
					<div class="alert alert-success">
						<button class="close" data-dismiss="alert">×</button>
						<span class="label label-success">NOTE</span>
						<span><small>${dealnote.user.username }于&nbsp;&nbsp;${dealnote.createtime }添加了这个笔记</small></span>
						<p>${dealnote.content }</p>
					</div>
					<c:if test="${fn:length(commentList) == 0 }">
						<div class="alert alert-error">
							<button class="close" data-dismiss="alert">×</button>
							还没有人为这个笔记添加评论，赶快去添加评论吧！
						</div>
						<form action="${basePath }/deal/savenotecomment.action?dealnoteid=${dealnote.id}" method="post">
							<div class="control-group" style="margin-left:20px;">
								<label class="control-label">添加评论：</label>
								<div class="controls">
									<textarea name="comment.content" id="" rows="4" style="width:645px;">请输入你的评论....</textarea>
								</div>
							</div>
							<div style="margin-left:620px;">
								<input type="submit" value="发表" id="demo11" class="btn btn-primary"/>
							</div>
						</form>
						
					</c:if>
					<c:if test="${fn:length(commentList) != 0 }">
						<form action="${basePath }/deal/savenotecomment.action?dealnoteid=${dealnote.id}" method="post">
							<div class="control-group" style="margin-left:20px;">
								<label class="control-label">添加评论：</label>
								<div class="controls">
									<textarea name="comment.content" id="" rows="4" style="width:645px;">请输入你的评论....</textarea>
								</div>
							</div>
							<div style="margin-left:620px;">
								<input type="submit" value="发表" id="demo12" class="btn btn-primary"/>
							</div>
						</form>
						<hr style="height:1px;width:90%;background-color:#ccc;margin-left:25px;"/>
						<c:forEach var="comment" items="${commentList }">
							<div class="alert alert-error">
								<button class="close" data-dismiss="alert">×</button>
								<span class="label label-important">Comment</span>
									<span><small>${comment.user.username }于&nbsp;&nbsp;${comment.createtime }添加了这个评论</small></span>
									<span style="margin-left:30px;"><a href="${basePath }/deal/addcomment.action?commentid=${comment.id}">回复</a></span>
									<p style="margin-top:5px;">${comment.content }</p>
							</div>
						</c:forEach>
						<c:if test="${fn:length(commentList) >= 4 }">
							<div class="btn-toolbar" style="margin-left:100px;">
								<div class="btn-group">
									<c:if test="${start < 4 }">
										<button>首页</button>
									</c:if>
									<c:if test="${start > 4 }">
										<a href="${basePath }/deal/commentdeal.action?dealnoteid=${dealnote.id }&start=0"><button>首页</button></a>
									</c:if>
									<c:if test="${start < 4 }">
										<button>上一页</button>
									</c:if>
									<c:if test="${start >= 4 }">
										<a href="${basePath }/deal/commentdeal.action?dealnoteid=${dealnote.id }&nowstart=${start - 4}"><button>上一页</button></a>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/deal/commentdeal.action?dealnoteid=${dealnote.id }&nowstart=${start + 4}"><button>下一页</button></a>
									</c:if>
									<c:if test="${start >= count }">
										<button>下一页</button>
									</c:if>
									<c:if test="${start == count }">
										<button>尾页</button>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/deal/commentdeal.action?dealnoteid=${dealnote.id }&nowstart=${count}"><button>尾页</button></a>
									</c:if>
								</div>
							</div>
						</c:if>
					</c:if>
				</div>
			</div>
			
		</div>
	</div>
     <script type="text/javascript">
   		$(document).ready(function(){
   			/*---------------侯永超的代码-------------------------------*/
	   		$('#demo12').click(function() { 
				$.growlUI('祝贺您','评论成功',2000); 
			}); 
	   		$('#demo11').click(function() { 
				$.growlUI('祝贺您','评论成功',2000); 
			}); 
	   		
	   		/*----------------------------------------------------------*/
    	
   		
   		});
    
    </script>
    
  </body>
</html>
