<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>notedeal</title>
    <style type="text/css">
    	.wall{
    		min-height:800px;
			padding-top:15px;
		}
    	.active{
			font-size:20px;
			margin-top:10px;
			margin-left:10px;
		}
		.control-group{
			margin-left:20px;
		}
		.controls textarea{
			width:95%;
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
    	.span9{
    		width:775px;
    	}
    	.span5{
    		width:450px;
    	}
    	.span3{
    		width:240px;
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
			<div class = "span2" style="font-size:14px;margin-right:5px;margin-top:15px;">
				<ul class="nav nav-list" style="font-size:14px;" >					 					
				  <li ><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
				   <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
				  <li ><a href="${basePath}/contact/contact.action"><i class="icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
				  <li><a href="${basePath }/task/task.action"><i class="icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
				  <li><a href="${basePath}/case/case.action""><i class="icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
				  <li class="active"><a href="${basePath }/deal/deal.action"><i class="icon-white icon-user"></i>&nbsp;&nbsp;交 易</a></li>
				  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
				</ul>	
			</div>
			<div class="span9">
				<ul class="breadcrumb" style="margin-bottom:0px;">
					<li class="active">
						<div style="margin-left:10px;margin-right:10px;height:50px;">
							<div class="span1" style="margin-left:0px;height:50px;">
								<img src="${basePath }/img/dollar.png"/>
							</div>
							<div class="span4" style="margin-left:5px;">
								<span style="font-size:18px;font-weight:bold">${deals.dealsname }</span>
								<c:if test="${fn:startsWith(deals.state,'cheng') }">
									<span style="font-size:16px;font-weight:bold;">($&nbsp;${deals.money })</span><br/>
								</c:if>
								<p>${deals.dealWith }负责该交易</p>
							</div>
							<div class="span3" style="margin-left:40px;">
								<c:choose>
									<c:when test="${fn:startsWith(deals.state,'gen') }">
										<div class="btn-group">
											<button class="btn btn-success">跟踪</button>
											<button class="btn">成功</button>
											<button class="btn">丢失</button>
										</div>
									</c:when>
									<c:when test="${fn:startsWith(deals.state,'cheng') }">
										<div class="btn-group">
											<button class="btn">跟踪</button>
											<button class="btn btn-success">成功</button>
											<button class="btn">丢失</button>
										</div>
									</c:when>
									<c:when test="${fn:startsWith(deals.state,'diu') }">
										<div class="btn-group">
											<button class="btn">跟踪</button>
											<button class="btn">成功</button>
											<button class="btn btn-success">丢失</button>
										</div>
									</c:when>
								</c:choose>
							</div>
						</div>
					</li>
				</ul>
				<div class="wall">
					<div class="span5" style="margin-left:0px;">
						<form action="${basePath }/deal/savenotedeal.action?dealid=${deals.id }" method="post">
							<fieldset>
								<div class="control-group">
									<label class="control-label"><h3>写点关于这个交易的笔记吧</h3></label>
									<div class="controls">
										<textarea  rows="4" id="input03" name = "dealnote.content">请输入一些关于交易的事情...</textarea>
									</div>
								</div>
								<div style="margin-left:85%;">
									<input type="submit" class="btn btn-primary" id="demo12" value="保存"/>
								</div>
							</fieldset>
						</form>
						<hr style="height:1px;width:90%;background-color:#ccc;margin-left:25px;"/>
						<div style="margin-left:20px;margin-right:10px;">
							<c:if test="${fn:length(dealnoteList) == 0 }">
								<div class="alert alert-success">
									<button class="close" data-dismiss="alert">×</button>
									<p>还没有人为这个交易添加笔记，赶快去写点关于这个交易的感受吧！</p>
								</div>
							</c:if>
							<c:if test="${fn:length(dealnoteList) != 0 }">
									<c:forEach var="dealnote" items="${dealnoteList }">
										<c:choose>
											<c:when test="${dealnote.user.username == deals.user.username }">
												<div class="alert alert-success">
													<button class="close" data-dismiss="alert">×</button>
													<span class="label label-success">NOTE</span>
													<span><small>${deals.user.username }发表于&nbsp;&nbsp;${dealnote.createtime }</small></span>
													<span style="margin-left:30px;"><a href="${basePath }/deal/commentdeal.action?dealnoteid=${dealnote.id}">Go to comment</a></span>
													<p style="margin-top:5px;">${dealnote.content }</p>
												</div>
											</c:when>
										</c:choose>
									</c:forEach>
									<c:if test="${fn:length(dealnoteList) >= 4 }">
										<div class="btn-toolbar" style="margin-left:40px;">
											<div class="btn-group">
													<a href="${basePath }/deal/notedeal.action?dealid=${deals.id }&start=0"><button>首页</button></a>
												<c:if test="${start < 4 }">
													<button>上一页</button>
												</c:if>
												<c:if test="${start >= 4 }">
													<a href="${basePath }/deal/notedeal.action?dealid=${deals.id }&nowstart=${start - 4}"><button>上一页</button></a>
												</c:if>
												<c:if test="${start < count }">
													<a href="${basePath }/deal/notedeal.action?dealid=${deals.id }&nowstart=${start + 4}"><button>下一页</button></a>
												</c:if>
												<c:if test="${start >= count }">
													<button>下一页</button>
												</c:if>
												<c:if test="${start == count }">
													<button>尾页</button>
												</c:if>
												<c:if test="${start < count }">
													<a href="${basePath }/deal/notedeal.action?dealid=${deals.id }&nowstart=${count}"><button>尾页</button></a>
												</c:if>
											</div>
										</div>
									</c:if>
							</c:if>
						</div>
						
					</div>
					
					<div class="span3" style="border-left:1px solid #DDD; padding-left:25px;">
						<div id="mm" class="modal hide fade">
							<form class="form-horizontal" action="${basePath}/task/adddealstask.action">
								<fieldset>
								  <legend>添加新任务</legend>
								  <div class="control-group">
									<label class="control-label" for="input01" >任务名称</label>
									<div class="controls">
									  <input type="text"  name="task.taskname" class="span3" id="input01" x-webkit-speech="undefined"> 
									     
									</div>
								  </div>					
								 <div class="control-group">
									<label class="control-label" for="select01">type</label>
									<div class="controls" id="taskselect">
							  
										 <select id="select01"  name="tasktype.type" >
										 	<option >Task</option>
										 	<option>Case</option>
										 	<option>Deals</option>						 
										  </select>
				
									  <a href="${ basePath}/task/tocolor.action">color</a>
									</div>
								  </div> 
								 
								  <div class="control-group">
									<label class="control-label" for="select01">When's it due?</label>
									<div class="controls">
										<input type="text" name="task.enddate"  class="Wdate" id="d412" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2012-08-01 00:00:00',maxDate:'2019-01-01 00:00:00'})" value="2012-08-01 00:00:00"/>
								  </div>  
								  </div>
									<div class="control-group">
									<label class="control-label" for="select01">谁可以看见</label>					
									<div class="controls">
									<input type="radio"  value="all" name="task.quanxian" >是否对所有人可见<br/>
										<input type="radio" value="me" name="task.quanxian" >仅对自己可见<br/>	
									
											<input type="radio" id="scan" name="task.quanxian"/>选择一个组...
												<span id = "scangroup"></span>			
							
									</div>				 
								
								  </div> 
								  <div class="form-actions">
									<button type="submit" class="btn btn-primary">保存</button>
									<button class="btn "  data-dismiss="modal" >返回</button>
								  </div>
								</fieldset>
							</form>
							
						  </div>			
					
					
						<div>
							<h4>你未来可能的任务</h4>

							<a  data-toggle="modal" href="#mm">添加新任务</a>关于这个交易
								<c:if test="${fn:length(dealtasklist) == 0}">
									还没有任务
								</c:if>
									<c:forEach items="${dealtasklist}" var="dl"> 
									<div>
										&nbsp;&nbsp;
										<span class="label label-important" >${cl.tasktype.type}</span>&nbsp;
										
										<span>${dl.taskname }</span>
									</div>	
									</c:forEach>

						</div>
						
						
						<div style="margin-top:15px;">
							<span style="font-weight:bold;font-size:14px;">这个交易的信息</span>
							<c:if test="${user.username == deals.user.username }">
								<span style="padding-left:20px;">
									<a href="${basePath }/deal/editdeals.action?dealid=${deals.id}">
										<i class="icon-edit"></i>编辑
									</a>|
								</span>
								<a href="${basePath }/deal/deletedeals.action?dealid=${deals.id}">
									<i class="icon-trash"></i>删除
								</a>
							</c:if>
							<c:if test="${user.username != deals.user.username }">
								<i class="icon-edit"></i>编辑&nbsp;&nbsp;|
								<i class="icon-trash"></i>删除
							</c:if>
							
							<p class="alert alert-success">
								<span class="label label-success">DEAL</span>
								${deals. descs}
							</p>
						</div>
						<div>
							<h4>和交易相关的人</h4>
							<div class="alert alert-success">
								<button class="close" data-dismiss="alert">×</button>
								<input type="checkbox" /> &nbsp;
								<i class="icon-user"></i> ${contact.contactname }<br>
								<span style = "padding-left:25px;">电话：${contact.tels} </span><br/>
								<span style = "padding-left:25px;">公司：${contact.companyname }</span><br/>
								<span style = "padding-left:25px;">职位：${contact.position }</span><br/>
								<span style = "padding-left:25px;">QQ：${contact.qq }</span>
							</div>
						</div>
					
					</div>
					
				</div>
			
			</div>
			
		</div>
	
	</div>
    <script type="text/javascript">
   		$(document).ready(function(){
   			/*---------------侯永超的代码-------------------------------*/
	   		$('#demo12').click(function() { 
				$.growlUI('祝贺您','添加笔记成功',2000); 
			}); 
	   		
	   		/*----------------------------------------------------------*/
    	
   		
   		
   		
   		/*-------------------------彭恩静添加新任务---------------------------------*/
   		
   		$("#scan").click(function(){
	    	 	$.get("searchgrouptask.action",function(result){
	    	 	$("#scangroup").text("");
	    	 		$("#scangroup").append($("<select style='width:100px;'name='task.quanxian';  id='group' ></select>"));
	    	 		$(result).each(function(){
	    	 			$("#group").append($("<option value="+this.groupname+">"+this.groupname+"</option>"));
	    	 		});
	    	 		
	    	 	});
    	 	
    	 	});
   		
   		
   		
   		
   		});
    
    </script>
    
    
  </body>
</html>
