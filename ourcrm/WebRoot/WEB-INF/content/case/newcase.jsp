<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'newcase.jsp' starting page</title>
  </head>
  
  <body>
    <div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				<ul class="nav nav-list" style="font-size:14px;" >					 					
				  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
				   <li><a href="${basePath}/message/message.action"><i class=" icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
				  <li ><a href="${basePath }/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
				  <li  ><a href="${basePath}/task/task.action"><i class="  icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
				  <li class="active"><a href="${basePath}/case/case.action"><i class=" icon-white icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
				  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
				  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
				</ul>
			</div>
			<div class="span8"  style="margin-left:0px;">
					<ul style="margin-top:10px ; margin-bottom:0px; background-color:#8EB5C9; height:40px;" class="breadcrumb">
						<li class="active" style="font-size:20px;margin-top:10px;margin-left:10px;">
							添加一个新事件
						</li>					
					</ul> 
				<div class="wall" style="height:250px;padding-top:15px; padding-left:30px;">
				   <form class="form-horizontal" style="margin-top:20px;" method="post" action="${basePath}/case/addnewcase.action">
						<p style="font-size:20px;">Name of the Case：</p>
						<input type="text" class="span5" id="input01" name="cases.casename"> <br/><br/>
						<button type="submit" class="btn btn-primary">保存</button><span>Or</span>
						<a class="btn" href="javascript:;">返回</a>
				   </form>
				
				</div>			
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".btn").click(function(){
				window.location.href ="case.action";
			});
		});
	
	</script>
  </body>
</html>
