<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加评论</title>
    <style type="text/css">
    	.span2,span10{
			margin-top:15px;
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
			<div class = "span2" style="font-size:14px;margin-right:20px">
				<ul class="nav nav-list" style="font-size:14px;" >					 					
				  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
				   <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
				  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
				  <li><a href="${basePath}/task/task.action"><i class=" icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
				  <li><a href="${basePath}/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
				  <li class="active"><a href="${basePath }/deal/deal.action"><i class="icon-white icon-user"></i>&nbsp;&nbsp;交 易</a></li>
				  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
				</ul>
			</div>
			<div class="span10" style="margin-left:0px;">
					<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:40px;" class="breadcrumb">
						<li class="active" style="font-size:20px;font-weight:bold;margin-top:10px;margin-left:10px;">
							回复 ${dealnotecomment.user.username }的评论
						</li>
					</ul>
				<div class="wall" style="height:700px;margin-top:0px;padding-top:15px;">
					<div class="alert alert-error" style="margin-left:20px;margin-right:20px;max-height:100px;overflow:hidden; ">
						<button class="close" data-dismiss="alert">×</button>
							<span><small>${dealnotecomment.user.username }发表于${dealnotecomment.createtime }</small></span>
							<p style="margin-right:5px;">
								${dealnotecomment.content }
							</p>
						
					</div>
					<c:if test="${fn:length(replyList) == 0 }">
						<div class="alert alert-error" style="margin-left:20px;margin-right:20px;max-height:100px;overflow:hidden; ">
						<button class="close" data-dismiss="alert">×</button>
						还没有人为这个评论添加回复，赶快去添加你的回复吧！
						</div>
						<hr style="height:2px;width:90%;background-color:#ccc;margin-left:25px;"/>
						<form action="${basePath }/deal/savereplycomment.action?commentid=${dealnotecomment.id}" method="post">
							<div class="control-group" style="margin-left:20px;">
								<label class="control-label">添加回复</label>
								<div class="controls">
									<textarea name="replycomment.content" id="" rows="4" style="width:720px;">请输入你的回复....</textarea>
								</div>
							</div>
							<div style="margin-left:620px;">
								<input type="submit" value="发表" id="demo12" class="btn btn-primary"/>
							</div>
						</form>
					
					</c:if>
					<c:if test="${fn:length(replyList) != 0 }">
						<hr style="height:2px;width:90%;background-color:#ccc;margin-left:25px;"/>
						<form action="${basePath }/deal/savereplycomment.action?commentid=${dealnotecomment.id}" method="post">
							<div class="control-group" style="margin-left:20px;">
								<label class="control-label">添加回复</label>
								<div class="controls">
									<textarea name="replycomment.content" id="" rows="4" style="width:720px;">请输入你的回复....</textarea>
								</div>
							</div>
							<div style="margin-left:620px;">
								<input type="submit" value="发表" id="demo12" class="btn btn-primary"/>
							</div>
						</form>
						<c:forEach var="reply" items="${replyList }">
							<div class="alert alert-info" style="margin-left:20px;margin-right:20px;max-height:100px;overflow:hidden;">
								<button class="close" data-dismiss="alert">×</button>
								<span class="label label-info">Reply</span>
								<span><small>${reply.user.username }发表于&nbsp;&nbsp;${reply.createtime }</small></span>
								<p style="margin-top:5px;">${reply.content }</p>
							</div>
						</c:forEach>
						<c:if test="${fn:length(replyList) >= 4 }">
							<div class="btn-toolbar" style="margin-left:40px;">
								<div class="btn-group">
									<c:if test="${start < 4 }">
										<button>首页</button>
									</c:if>
									<c:if test="${start > 4 }">
										<a href="${basePath }/deal/addcomment.action?commentid=${dealnotecomment.id }&start=0"><button>首页</button></a>
									</c:if>
									<c:if test="${start < 4 }">
										<button>上一页</button>
									</c:if>
									<c:if test="${start >= 4 }">
										<a href="${basePath }/deal/addcomment.action?commentid=${dealnotecomment.id }&nowstart=${start - 4}"><button>上一页</button></a>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/deal/addcomment.action?commentid=${dealnotecomment.id }&nowstart=${start + 4}"><button>下一页</button></a>
									</c:if>
									<c:if test="${start >= count }">
										<button>下一页</button>
									</c:if>
									<c:if test="${start == count }">
										<button>尾页</button>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/deal/addcomment.action?commentid=${dealnotecomment.id }&nowstart=${count}"><button>尾页</button></a>
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
				$.growlUI('祝贺您','回复成功',2000); 
			}); 
	   		
	   		/*----------------------------------------------------------*/
    	
   		
   		});
    
    </script>
    
    
  </body>
</html>
