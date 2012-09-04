<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'case-success.jsp' starting page</title>
  </head>
  
  <body>
    <div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					   <li><a href="${basePath}/message/message.action"><i class=" icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li ><a href="#"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li ><a href="${basePath }/task/task.action"><i class="  icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li class="active"><a href="${basePath }/case/case.action"><i class="icon-white icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>
					 <ul class="nav" style="margin-top:5px;">
					 <li class="divider" style="background-color:#c0c0ff;width:span2;height:1px;"></li>
					</ul>
					
					
			</div>
			<div class="span9"  style="margin-left:0px;">
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:40px;" class="breadcrumb">
					<li class="active" style="font-size:20px;margin-top:10px;margin-left:10px;">
						你的事件：
					</li>
					<li style="float:right;margin-top:10px;" ><a href="toaddnewcase.action" style="font-size:20px;" class="btn btn-primary">添加新事件</a></li>
				</ul>
				<div class="wall" style="height:400px;margin-top:0px;padding-top:15px;">
				
				<c:forEach items="${caseList}" var="clist">
					
						<div style="float:left; margin-left:20px;margin-top:20px;">
						<img src="${basePath}/img/case.jpg" alt="" />&nbsp;&nbsp;
						</div>
						<div style="float:left; margin-top:25px;">
								<a href="${basePath}/case/miaoshu.action?id=${clist.id}">${clist.casename }</a><br/>
								update ${clist.createtime}；
						</div>
					<div style="clear:both;"></div >
				</c:forEach>					
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
