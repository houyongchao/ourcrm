<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>deals</title>
  </head>
  
  <body>
   	<div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px;margin-top:15px;">
				
				<ul class="nav nav-list" style="font-size:14px;" >					 					
				  <li ><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
				  <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
				  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
				  <li><a href="${basePath }/task/task.action"><i class=" icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
				  <li><a href="${basePath }/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
				  <li class="active"><a href="${basePath }/deal/deal.action"><i class="icon-white icon-user"></i>&nbsp;&nbsp;交 易</a></li>
				  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
					
				</ul>
				
			</div>
			<div class="span7" style="margin-left:0px;">
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:40px;" class="breadcrumb">
					<li class="active" style="font-size:20px;font-weight:bold;margin-top:10px;margin-left:10px;">
						所有的交易如下：
					</li>
					<li style="float:right;">
						<a href="${basePath }/deal/adddeal.action" class="btn btn-info" style="margin-top:6px;padding-left:1px;padding-right:1px;width:100px;height:16px;">
							<img src="${basePath }/img/plus_circle.png" style="margin-top:-2px;" />&nbsp;添加新交易
						</a>
					</li>
					
				</ul>
				<div class="wall" style="height:550px;margin-top:0px;padding-top:15px;">
					<c:if test="${fn:length(dealsList) == 0 }">
						<div class="alert alert-success" style="margin-left:20px;margin-right:20px;max-height:100px;overflow:hidden; ">
							还没有交易，赶快去完成你的第一笔交易吧！
						</div>
					</c:if>
					<c:if test="${fn:length(dealsList) != 0 }">
						
						<c:forEach var="deal" items="${dealsList}">
							<div class="alert alert-success" style="margin-left:20px;margin-right:20px;max-height:80px;overflow:hidden; ">
								<button class="close" data-dismiss="alert" id="${deal.id}">×</button>
								<div class="span1" style="margin-left:0px;height:100px;">
									<img src="${basePath }/img/dollar.png"/>
								</div>
								<div class="span5" style="margin-left:0px;">
									<a href="${basePath }/deal/notedeal.action?dealid=${deal.id}" style="font-size:18px;font-weight:bold">${deal.dealsname }</a>
									<c:if test="${fn:startsWith(deal.state,'gen') }">
										<span>【跟踪状态】</span>
									</c:if>
									<c:if test="${fn:startsWith(deal.state,'cheng') }">
										<span>【已成功交易】</span>
									</c:if>
									<c:if test="${fn:startsWith(deal.state,'diu') }">
										<span>【丢失交易】</span>
									</c:if>
									<c:if test="${fn:startsWith(deal.state,'cheng') }">
										<span style="font-size:16px;font-weight:bold;">($&nbsp;${deal.money })</span><br/>
									</c:if>
									<p style="margin-right:5px;">
										${deal.descs }
									</p>
									<p>---${deal.user.username }发表于${deal.createtime } </p>
								</div>
							</div>
						</c:forEach>
						<c:if test="${fn:length(dealsList) >= 4 }">
							<div class="btn-toolbar" style="margin-left:150px;">
								<div class="btn-group">
										<a href="${basePath }/deal/deal.action?start=0"><button>首页</button></a>
									<c:if test="${start < 4 }">
										<button>上一页</button>
									</c:if>
									<c:if test="${start >= 4 }">
										<a href="${basePath }/deal/deal.action?nowstart=${start - 4}"><button>上一页</button></a>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/deal/deal.action?nowstart=${start + 4}"><button>下一页</button></a>
									</c:if>
									<c:if test="${start >= count }">
										<button>下一页</button>
									</c:if>
									<c:if test="${start == count }">
										<button>尾页</button>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/deal/deal.action?nowstart=${count}"><button>尾页</button></a>
									</c:if>
									
								</div>
							</div>
						</c:if>
					</c:if>
					
				</div>
			</div>
			<div class="span3">
			
				<ul style="margin-top:10px;padding-top:5px;padding-bottom:3px; margin-bottom:0px; background-color:#F0F5F8" class="breadcrumb">
						<li class="active">
							<img src="${basePath }/img/dollar-min.png"/>&nbsp;&nbsp;<span style="font-weight:bold;font-size:14px;">你的交易</span>
						</li>
						
				</ul>
				<div class="wall" >
					
					<div style="height:200px; margin-left:10px;padding-top:10px;" >
						<div style="padding-right:3px;">
							<h4>Deal Categories</h4>
							<p>You can edit the available deal categories.</p>
						</div>
						<div style="padding-right:3px;">
							<h4>Create deals via email</h4>
							<p>You can create deals via email. Add/deal New Deal to the top 
								of your message, then send it to your dropbox.
							</p>
						</div>
					</div>
				</div>
			</div>
		
		</div>
	
	
	
	</div>
   <script type="text/javascript">
   		$(document).ready(function(){
   			$(".close").click(function(){
   				var dealsid = $(this).attr("id");
   				$.post("deletedeals.action",{dealid:dealsid});
   			});
   		
   		
   		
   		});
   
   </script>
   
   
   
  </body>
</html>
