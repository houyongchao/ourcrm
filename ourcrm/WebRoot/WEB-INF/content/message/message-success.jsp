<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Message</title>

  </head>
  <body>
  		<div class="container" >
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px;margin-top:15px;">
				
					<ul class="nav nav-list" style="font-size:14px;" >					 					
					  <li ><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
					  <li class="active"><a href = "javascript:;"><i class="icon-white icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
					  <li ><a href="${basePath}/contact/contact.action"><i class=" icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
					  <li><a href="${basePath }/task/task.action"><i class=" icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
					  <li><a href="${basePath}/case/case.action"><i class=" icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
					  <li><a href="${basePath }/deal/deal.action"><i class="icon-user"></i>&nbsp;&nbsp;交 易</a></li>
					  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
					</ul>	
			</div>
			<div class="span7" style="margin-left:0px;">
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#C8D9E9; height:40px;" class="breadcrumb">
					<li class="active" style="font-size:20px;font-weight:bold;margin-top:10px;margin-left:10px;">
						最新动态
					</li>
					
				</ul>
				<div class="wall" style="margin-top:0px;padding-top:15px;">
					

						
						<c:forEach items = "${messageList}" var = "mes">
							<c:if test="${mes.type == 'contact' }">
								<div class="alert alert-success alert-block fade in" style="margin-left:20px;margin-right:20px;">
									<img src="${basePath }/img/touxiang.png" alt="" /><a href = "${basePath}/contact/showPerson.action?contactid=${mes.ref }"> ${mes.title }<a></a><br>
									<span>${mes.content}</span>
								</div>
							</c:if>
							
							<c:if test="${mes.type == 'company' }">
								<div class="alert alert-success alert-block fade in" style="margin-left:20px;margin-right:20px;">
									<img src="${basePath }/img/gongsi.png" alt="" /><a href = "${basePath }/contact/showPerson.action?contactid=${mes.ref}">${mes.title }</a><br>
									<span>${mes.content}</span>
								</div>
							</c:if>
							
							<c:if test="${mes.type == 'task' }">
								<div class="alert alert-success alert-block fade in" style="margin-left:20px;margin-right:20px;">
									<img src="" alt="" /> ${mes.title }<br>
									<span>${mes.content}</span>
								</div>
							</c:if>
							
							<c:if test="${mes.type == 'casese' }">
								<div class="alert alert-success alert-block fade in" style="margin-left:20px;margin-right:20px;">
									<img src="${basePath }/img/case.jpg" alt="" /><a href = "${basePath }/case/miaoshu.action?id=${ mes.ref}"> ${mes.title }</a><br>
									<span>${mes.content}</span>
								</div>
							</c:if>
							<c:if test="${mes.type == 'deals' }">
								<div class="alert alert-success alert-block fade in" style="margin-left:20px;margin-right:20px;">
									<img src="${basePath }/img/dollar.png" alt="" /> <a href = "${basePath }/deal/notedeal.action?dealid=${mes.ref}">${mes.title }</a><br>
									<span>${mes.content}</span>
								</div>
							</c:if>
							
							
						</c:forEach>
							<c:if test="${fn:length(messageList) >= 4 }">
							<div class="btn-toolbar" style="margin-left:150px;">
								<div class="btn-group">
										<a href="${basePath }/message/message.action?start=0"><button>首页</button></a>
									<c:if test="${start < 4 }">
										<button>上一页</button>
									</c:if>
									<c:if test="${start >= 4 }">
										<a href="${basePath }/message/message.action?nowstart=${start - 4}"><button>上一页</button></a>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/message/message.action?nowstart=${start + 4}"><button>下一页</button></a>
									</c:if>
									<c:if test="${start >= count }">
										<button>下一页</button>
									</c:if>
									<c:if test="${start == count }">
										<button>尾页</button>
									</c:if>
									<c:if test="${start < count }">
										<a href="${basePath }/message/message.action?nowstart=${count}"><button>尾页</button></a>
									</c:if>
									
									
								</div>
							</div>
						</c:if>
					
					
					
					
					

				</div>
			</div>
			<div class="span3">
			
				<ul style="margin-top:10px ; margin-bottom:0px; background-color:#F0F5F8" class="breadcrumb">
						<li class="active">
							<a href="#" style="font-weight:bold;font-size:14px;"><i class="icon-ok" ></i>&nbsp;&nbsp;你的任务</a>
						</li>
						
				</ul>
				<div class="wall" >
					
					<div style="height:200px; margin-left:10px;padding-top:10px;" >
						<c:if test="${fn:length(listt) == 0 }">
							<div> 还没有任务</div>
						</c:if>
						<c:forEach items="${listt}" var="tlu">
							<div>
								<a title="删除"  class="del_link" rel="${tlu.id }" href="javascript:;" ><input type="checkbox" /></a>
								<span class="label label-important">${tlu.tasktype.type }</span>&nbsp;
								<span>${tlu.taskname}</span>
								<br/>
							
							</div>	
						
						</c:forEach>
						
						
					</div>
				</div>
			</div>
		</div>	
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".del_link").click(function(){
				var id = $(this).attr("rel");
    			if(confirm("确定要删除此数据？")) {
    				window.location.href = "deltask.action?id=" + id;
    			}
			});
		});
	</script>
	
  </body>
</html>
