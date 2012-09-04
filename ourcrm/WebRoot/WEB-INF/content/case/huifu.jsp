<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'huifu.jsp' starting page</title>
  </head>
  
  <body>
  	<div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					   <li><a href="${basePath}/message/message.action"><i class=" icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li ><a href="${basePath}/task/task.action"><i class="  icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li class="active"><a href="${basePath}/case/case.action"><i class="icon-white icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="#"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="#"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>
					 
			</div>
			<div class="span9"  style="margin-left:0px;">
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:40px;" class="breadcrumb">
					<li class="active" style="font-size:20px;margin-top:10px;margin-left:10px;">
						<span style="font-size:25px;">Tom</span>--<small>请在下面输入你的回复</small>
					</li>				
				</ul>
				<div class="wall" style="margin-top:0px;padding-top:15px;">	
					<form class="form-horizontal" method="post" action="${basePath}/case/addhuifu.action">
						<fieldset>
							<div class="control-group">
							<label class="control-label" for="textarea" style="font-size:20px;" >回复内容</label>
							<div class="controls">
							  <textarea class="input-xlarge" id="textarea" rows="9" name="casehuifu.content"  >请输入你的评论内容</textarea>
							  <input type="hidden" name="casenote.id" value="${casenote.id }"/>
							</div>
							</div> 
							<div style="margin-left:450px; ">
							<button type="submit" class="btn">提交</button>
							
							</div>
						</fieldset>
					</form>
					<hr/>
					<div style="margin-left:30px;">
						<c:forEach items="${casehuifulist }" var="huifulist">
						<hr/>
						<div style="float:left; margin-top:20px;">
							<img alt="" src="${basePath}/img/head.png"><br/>
							<span style="font-size:20px; margin-left:20px;">${sessionScope.user.username }</span>
						</div>
						<div style="float:left; margin-top:30px;">
							<p style="font-size:20px;">${huifulist.content }</p>
							<small>发表于${huifulist.createtime }</small>
						</div>
							<div style="clear:both;"></div>
						</c:forEach>
					
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
