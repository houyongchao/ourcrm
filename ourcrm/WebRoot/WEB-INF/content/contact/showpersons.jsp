<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file = "../include/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'show-person.jsp' starting page</title>
   
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script> 
	<script type="text/javascript" src="https://raw.github.com/HPNeo/gmaps/master/gmaps.js"></script>

  </head>
  
  <body>&nbsp; 
    <div class="container" >
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					   <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li class = "active"><a href="${basePath}/contact/contact.action"><i class=" icon-flag icon-white"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li><a href="${basePath}/task/task.action"><i class=" icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li><a href="${basePath}/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>
					
				
			</div>
			<div class="span10" style="margin-left:0px;">
					<!--------------------------- 添加任务------------------------------>
					 <div id="mm" class="modal hide fade">
						<form class="form-horizontal" action="${basePath}/task/addcontacttask.action">
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
			
		
				
				<div class="wall" style="margin-top:0px;padding-top:15px;background-color:white">
					<div class="page-header">
						<h3 style = "margin-left:20px">		
						<c:choose>
							<c:when test="${contact.contactname != null }">
								<p>姓名：${contact.contactname}</p>
								（<a href="editContact.action"><i class = "icon-pencil"></i></a> &nbsp;&nbsp;<a href="deleteContact.action?contactid=${contact.id }"><i class = "icon-trash"></i></a>）
							</c:when>
							<c:otherwise>
								<p>公司名称:${contact.companyname}</p>
								（<a href="editContact.action"><i class = "icon-pencil"></i></a> &nbsp;&nbsp;<a href="deleteContact.action?contactid=${contact.id }"><i class = "icon-trash"></i></a>）
							</c:otherwise>
						</c:choose></h3>
					</div>
					
					
					<div style="margin-bottom: 18px;margin-top:10px" class="tabbable">
						<ul class="nav nav-tabs" id = "myTab">
						  <li class="active"><a data-toggle="tab" href="#tab1">详细信息</a></li>
						  <li class=""><a data-toggle="tab" href="#tab2">笔记</a></li>
						  <li class=""><a data-toggle="tab" href="#tab3">交易</a></li>
						  <li class=""><a data-toggle="tab" href="#tab4">任务</a></li>
						</ul>
						<div style="padding-bottom: 9px; margin-left:20px" class="tab-content">
							<div id="tab1" class="tab-pane active">
								<c:choose>
									<c:when test="${contact.contactname != null }">
										<p>姓名：${contact.contactname}</p>
										
									</c:when>
									<c:otherwise>
										<p>公司名称:${contact.companyname}</p>
									</c:otherwise>
								</c:choose>
								<p>电话:</p>
								<c:forEach items = "${telList}" var = "t">
									<p style = "margin-left:30px">${t.type}:${t.num }</p>
								</c:forEach>
								
								<p>地址:${contact.address }</p>
								<p>主页:${contact.websites }</p>
								<div>简介:${contact.descs }</div>
								
								<p>是否在地图上显示：<input type = "button" id = "btn" value = "show"/></p>
								<input type = "hidden" value = "${contact.address}" id = "address"/>
								<div id = "map" style = "width:400px;height:200px;"></div>
								<img alt="" src="${basePath}/img/${contact.id }.png"/>
							</div>
							<div id="tab2" class="tab-pane ">
								
									<textarea name="comment" id="comment" style = "width:500px" rows="5"></textarea></br>
									<button class = "btn btn-info" style = "margin-left:450px" id = "fabiao">发表</button>
									
									<c:forEach items = "${comments}" var = "com">
										<blockquote>
											<p>${com.content }</p>
											<small>${com.user.username }发表于${com.createtime }</small>
										</blockquote>
									</c:forEach>
								<div id = "comments"></div>
								<div id = "huifu" style = "display:none">
									<textarea name="replay" id="replay" style = "width:500px" rows="5"></textarea></br>
									<button class = "btn btn-info" style = "margin-left:450px;" id = "fabiaohuifu">发表</button>
								</div>
							</div>
							<div id="tab3" class="tab-pane">
								<p>
									<a class = "btn btn-success" href="${basePath }/deal/adddeal.action"><i class = "icon-plus icon-white"></i>添加与其的交易</a>
									
									<div class="page-header">
										<h4>与其有关的交易</h4>
									</div>
									<c:if test="${fn:length(contact.dealList) == 0}">
										还没有交易
									</c:if>
									<ul>
										<c:forEach items = "${contact.dealList}" var = "deal">
											<li>${deal.dealsname }</li>
										</c:forEach>
									</ul>
								</p>
							</div>
							<div id = "tab4" class = "tab-pane">
								

									
									<a class = "btn btn-success" data-toggle="modal" href="#mm"><i class = "icon-plus icon-white"></i>添加与其的任务</a>

									
									<div class="page-header">
										<h4>与其有关的任务</h4>
										<c:if test="${fn:length(contacttasklist) == 0}">
											还没有任务
										</c:if>
										<c:forEach items="${contacttasklist}" var="ctlist"> 
										<div>
											&nbsp;
											<span class="label label-important">${ctlist.tasktype.type}</span>&nbsp;
											<span>${ctlist.taskname }</span>
										</div>	
										</c:forEach>
									</div>


						
							</div>
							
						</div>
					</div>
					
				
					
				</div>
			</div>
		
		</div>	
	</div>
	<script type="text/javascript">
		//var map;
		$(document).ready(function(){
			//切换标签
			$('#myTab a').click(function (e) {
		
				e.preventDefault();
				$(this).tab('show');
			});
			//map
			/*var map;
			var map = new GMaps({
					div: '#map',
					lat: 35.2193612,
					lng: 113.2482923
					
			});*/
			
			$("#btn").click(function(){
				GMaps.geocode({
				  address: $('#address').val(),
				  callback: function(results, status) {
					if (status == 'OK') {
					  var latlng = results[0].geometry.location;
					  map.setCenter(latlng.lat(), latlng.lng());
					  map.addMarker({
						lat: latlng.lat(),
						lng: latlng.lng()
					  });
					}
				  }
				});
			});
			//发表评论
			$("#fabiao").click(function(){
				var comment = $("#comment").val();
				$("#comment").val("");	
				$.post("comments.action",{"contactnote.content":comment},function(result){
					$("<blockquote class = 'note'><blockquote>").appendTo($("#comments"));
					$("<p>").text(result.content).appendTo($(".note"));
					$("<small>").text(result.username+"发表于"+result.createtime).appendTo($(".note"));
				});
				
				

			});
			
			/* //回复评论
			$(".replay").click(function(){
				$("#huifu").show();
				$("#fabiaohuifu").click(function(){
					var huifu = $("#replay").val();
					$.post("replay.action",{"replay":huifu},function(result){
						//动态显示回复
					});
				});
			}); */

			
			
			/**********************************  彭恩静添加任务 ***********************************/
			
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
