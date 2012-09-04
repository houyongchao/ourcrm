<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>我们的orm</title>
	<link rel="stylesheet" type="text/css" href="${ basePath}/css/bootstrap.css" />
	<script type="text/javascript" src="${basePath }/js/jquery.js"></script>	
	<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath }/js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="../${basePaht }My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src = "${basePath }/js/select2.js"></script>
	<link href="${basePath}/css/select2.css" rel="stylesheet"/>
	<script type="text/javascript" src="${basePath }/js/jquery.blockUI.js"></script>
	
	


	<style type="text/css">
		.body{
			background-color:#F3F1EC;
		}
		
		.navbar-inner{			
			height:50px;		
		}
		div .container{
			padding-top:4px;
			
		}
		.row{
			margin-top:37px;
		}
		div .navbar-inner{
			background-color:#106792;
		}
		.wall{
			border:1px solid #DDD;
			background-color:#F2F2F2;
			border-top:0px;
			
		}
		.span2 li{
			margin-bottom:5px;
		}
		
	</style>
</head>
	
<body>
	<div class="navbar navbar-fixed-top" style="background-color:#000;">
		<div class="navbar-inner" style="background-color:#106792;">
			<div class="container" >
				
				<a href="#" class="brand" style="color:white; font-size:25px;">Highrise</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li >
							<a style="font-size:18px;color:white;margin-top:8px;" href = "${basePath}/contact/addContact.action"  >
								<img src="${basePath }/img/plus_circle.png" style="margin-top:-2px;"/>添加联系人
							</a>

						</li>
						<li >
							<a style="font-size:18px;color:white;margin-top:8px;"  data-toggle="modal" href="#myModal" id="my" >
								<img src="${basePath }/img/plus_circle.png" style="margin-top:-2px;" />添加新任务
							</a>
						</li>											
					</ul>
				</div>
				
				<form action="" class="navbar-search pull-left">
					<input type="text" placeholder="Jump to a contact, case, deal, or tag..." class="search-query span4">
				</form>
				<ul class="nav pull-right">										
					<li ><a  href="${basePath}/account/usereidt.action" style="font-size:25px; color:white;" >${session.user.username }</a>	</li>
					<li><a  href="loginout.action" style="font-size:20px; color:white;">安全退出</a></li>		

					
				</ul>				
			</div>
		</div>
	</div>
	
<div class="container">
	<div class="row">
		<div class="span6">
		 <div id="myModal" class="modal hide fade">
			<form class="form-horizontal" action="${basePath}/task/addtask.action">
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
		</div>
	</div>
</div>	
	<script type="text/javascript">
		
		/*  写到这里,还没写完*/
		$(document).ready(function(){
			$("#scan").click(function(){
	    	 	$("#scangroup").text("");
	    	 	$.getJSON("searchgrouptask.action",function(result){
	    	 		$("#scangroup").append($("<select style='width:100px;'name='task.quanxian';  id='group' ></select>"));
	    	 		$(result).each(function(){
	    	 			$("#group").append($("<option value="+this.groupname+">"+this.groupname+"</option>"));
	    	 		});
	    	 		
	    	 	});
    	 	
    	 	});
		});
	</script>

</body>	