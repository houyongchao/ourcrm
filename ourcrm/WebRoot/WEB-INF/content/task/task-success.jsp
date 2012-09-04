<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Task</title>
  </head>
  
  <body>
    <div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					   <li><a href="${basePath}/message/message.action"><i class=" icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li class="active"><a href="${basePath }/task/task.action"><i class=" icon-white icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li><a href="${basePath}/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
						
					</ul>
					
			</div>
			<div class="span9"  style="margin-left:0px;">
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:40px;" class="breadcrumb">
					<li class="active" style="font-size:20px;margin-top:10px;margin-left:10px;">
						任务列表;
					</li>
					<%-- <li style="float:right;margin-top:10px;" ><a href="${basePath}/task/toadd.action" style="font-size:20px;">添加新任务</a></li> --%>
				</ul>
				<div class="wall" style="height:600px;margin-top:0px;padding-top:15px;">	
					<table class="table " style="border:0px;">
					<thead>
						<tr>
							<th width="30px"></th>
							<th width="20%">TASK NAME</th>
							<th width="30%">计划完成时间</th>
							<th>任务所属类型</th>
							<th></th>
						</tr>
					</thead>
						<c:if test="${fn:length(taskListUser) == 0 }">
							<tr>
								
								<td colspan="5">还没有任务，赶快添加新任务吧</td>							
								
							</tr>
						</c:if>
					<tbody>
						<c:forEach items="${taskListUser}" var="tuser">
							
							<tr>
								<td>							
									<a title="删除"  class="del_link" rel="${tuser.id }" href="javascript:;" ><input type="checkbox" value="${task.id }" /></a>
								</td>
								<td><span class="label label-info">${tuser.taskname}</span>&nbsp;&nbsp;<span>${tuser.user.username}</span><br/><span style="color:green;">${tuser.createtime }</span></td>
								<td ><span class="label babel-success" style="color:blue;">${tuser.enddate}</span></td>
								<td><span style="color:#${tuser.tasktype.color};">${tuser.tasktype.type}</span>
								
							
								</td>
								<td><a href="${basePath}/task/edit.action?id=${tuser.id}" class="btn btn-inverse">修改</a></td>
							</tr>
							
						</c:forEach>
						
					<%-- 	<c:forEach items="${taskListContact}" var="tcontact">
							
							<tr>
								<td>							
									<a  class="del_link" rel="${tcontact.id }" href="javascript:;"><input type="checkbox" value="${task.id }" /></a>
								</td>
								<td>${tcontact.taskname}</td>
								<td >${tcontact.enddate}${task.tasktype.type}</td>
								<td><span style="color:${tcontact.tasktype.color};">${tcontact.tasktype.type}</span>							
								</td>
								<td><a href="${basePath}/task/edit.action?id=${tcontact.id}"><i class="icon-pencil"></i></a></td>
							</tr>
							
						</c:forEach> --%>
										
					</tbody>
				</table>
					
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$(".del_link").click(function(){
				var id = $(this).attr("rel");
    			if(confirm("确定要删除此数据？")) {
    				window.location.href = "del.action?id=" + id;
    			}
			});
		});
	</script>
  </body>
</html>
