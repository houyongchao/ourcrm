<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'miaoshu.jsp' starting page</title>
  </head>
  
  <body>
  	<div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					   <li><a href="${basePath}/message/message.action"><i class=" icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li ><a href="${basePath }/task/task.action"><i class="  icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li class="active"><a href="${basePath}/case/case.action"><i class="icon-white icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>


			</div>
			<div class="span7" style="margin-left:0px;">
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:80px;" class="breadcrumb">
					<li class="active" style="font-size:20px;margin-top:10px;margin-left:10px;">
						<a href=""><img src="${basePath}/img/case.jpg" alt="" /></a>&nbsp;&nbsp;${cases.casename }
					</li>					
				</ul>
							
				<div class="wall" style="height:automatic;margin-top:0px;padding-top:15px; padding-left:30px;">
					<form class="form-horizontal" style="margin-left:20px;" method="post" action="${basePath}/case/addmiaoshu.action">
					   添加一个有关这个事件的描述<br/>
					   	<input type="hidden" name="cases.id" value="${cases.id }"/>					   	
					
					   <textarea class="span6" rows="5" name="note.content" id="content"></textarea><br/><br/>
					   
						<input type="submit" class="btn " value="保存这个描述" id="miaoshu" />
					</form>
					 <ul class="nav" style="margin-top:5px;">
					 <li class="divider" style="background-color:#4F5048;width:span2;height:1px;"></li>
					</ul>
					<c:if test="${fn:length(casenotelist) == 0 }">
							<div>还没有评论，赶快发表吧</div>
					</c:if>
					<c:forEach items = "${casenotelist}" var = "caseN">
						<blockquote>
							<p>${caseN.content }</p>
							<small>${sessionScope.user.username }发表于${caseN.createtime }<a href = "${basePath}/case/tohuifu.action?id=${caseN.id}" style = "margin-left:10px">回复</a></small>
						</blockquote>
					</c:forEach>
					<!--<c:choose>
						<c:when test="${cases.descs != null }">
							<div id="showMiaoshu" style=" margin-left:10px;">
								<p>${cases.descs}</p><small>给这个描述添加一个评论</small>
							</div>					
						</c:when>
					</c:choose>
					
					
					-->
					<div id="showMiaoshu" style=" margin-left:10px;">
						
					</div>
						
					<div id="showPinglun" style="margin-left:10px;"></div>				
				</div>
				
			</div>
			
			
			<div class="span3">
				 <div id="mm" class="modal hide fade">
			<form class="form-horizontal" action="${basePath}/task/addcasetask.action" method="post">
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
					<button type="submit" class="btn btn-primary" id="baocun">保存</button>
					<button class="btn "  data-dismiss="modal" >返回</button>
				  </div>
				</fieldset>
			</form>
			
		  </div>			
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#F0F5F8" class="breadcrumb">
						<li class="active">
							<a href="#" style="font-weight:bold"><i class="icon-ok" ></i>&nbsp;&nbsp;你可以为这个事件添加一个任务</a>
						</li>
						
				</ul>
				<div class="wall" >
					
					
					
					
					
					
					
					<div style="height:200px; margin-left:10px;padding-top:10px;" >
						
						<a  data-toggle="modal" href="#mm">添加新任务</a>
						<c:if test="${fn:length(casetasklist) == 0}">
							还没有任务
						</c:if>
						<c:forEach items="${casetasklist}" var="cl"> 
						<div>
							<a title="删除"  class="del_link" rel="${cl.id }" href="javascript:;" ><input type="checkbox"  /></a>&nbsp;
							<span class="label label-important">${cl.tasktype.type}</span>&nbsp;
							<span>${cl.taskname }</span>
						</div>	
						</c:forEach>
						<div id="xianshirenwu">
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		/*  写到这里,还没写完*/
		$(document).ready(function(){
			$("#scan").click(function(){
	    	 	$.get("searchgrouptask.action",function(result){
	    	 	$("#scangroup").text("");
	    	 		$("#scangroup").append($("<select style='width:100px;'name='task.quanxian';  id='group' ></select>"));
	    	 		$(result).each(function(){
	    	 			$("#group").append($("<option value="+this.groupname+">"+this.groupname+"</option>"));
	    	 		});
	    	 		
	    	 	});
    	 	
    	 	});
    	 	
    	 	
    	 	
    	 	
    	 	$(".del_link").click(function(){
				var id = $(this).attr("rel");
    			if(confirm("确定要删除此数据？")) {
    				window.location.href = "task/deltask.action?id=" + id;
    			}
			});
    	 	
    	 	
    	 	
		});
	</script>
  </body>
</html>
