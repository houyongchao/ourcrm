<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>search</title>
  </head>
  
  <body>
	<div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					


					  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					   <li><a href="#"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>

					  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li><a href="${basePath }/task/task.action"><i class=" icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li><a href="${ basePath}/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>					
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li class="active"><a href="${basePath }/search/search.action"><i class="icon-white icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>
					
			</div>
			<div class="span9" style="margin-left:0px;">
				<ul style="margin-top:10px;height:55px;margin-bottom:0px; background-color:#C8D9E9;" class="breadcrumb">
					<li class="active" style="font-size:16px;margin-top:0px;margin-left:10px;">
						Search deals, notes, emails and comments
					</li><br/>
					<li style="margin-top:5px;">
						<input type="text" id="input09" class="input-medium search-query"/>
						<button class="btn btn-info" id="search">搜索</button>
					</li>
				</ul>
				<div class="wall" style="min-height:400px;margin-top:0px;padding:15px 8px ;">
					<p id="information">Highrise will search through all your notes, emails, and comments to find 
						what you type above.If you're looking for a person, use the "Jump to a 
						contact..." field at the top of the screen.
					</p>
					<div id="resultlist">
					
					</div>
					
					
				</div>
			</div>
		</div>
		
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#search").click(function(){
				var titles = $("#input09").val();
				if(titles != null&&titles != ""){
					$("#information").text("");
					$("#resultlist").text("");
					$.post("searchall.action",{title:titles},function(result){
						$(result).each(function(){
							$("#resultlist").append($("<div class='alert alert-success alert-block fade in' style='margin-left:20px;margin-right:20px;' >"+
							"<span class='label label-important'>"+this.title+"</span>"+"<small>"+this.createtime+"</small>"+"<p>"+this.content+"</P>"+"</div>"));
						
						});
					});
				}
			});
		
		
		});
	
	
	
	</script>
  </body>
</html>
