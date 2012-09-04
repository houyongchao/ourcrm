<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'edit.jsp' starting page</title>
  </head>
  
  <body>
    <div class="container">
		<div class="row">			
			<div class="span10"  style="margin-left:0px; ">
				<form class="form-horizontal" method="post" action="${basePath }/task/update.action">
				<fieldset style="width:800px;">
				  <legend> 修改任务:</legend>
				  <div class="control-group">
					<label class="control-label" for="input01">任务名称</label>
					<div class="controls">
					<input type="hidden" name="task.id" value="${task.id}"/>
					
					<%-- <c:choose>
					<c:when test="${task.contact.id != ''}">						
						<input type="hidden" name="task.contact.id" value="${editcid}"/>
					</c:when>
					<c:when test="${task.deals.id != ''}">						
						<input type="hidden" name="task.deals.id" value="${task.deals.id}"/>
					</c:when>
					<c:when test="${task.cases.id != ''}">						
						<input type="hidden" name="task.deals.id" value="${task.cases.id}"/>
					</c:when>
				
					</c:choose>
					<input type="hidden" name="task.user.id" value="${eidtuid}"/> --%>
					
					
					
					<input type="hidden" name="task.createtime" value="${task.createtime }">				
					<input type="text" class="span5" id="input01" name="task.taskname" value="${task.taskname }" x-webkit-speech="undefined">          
					</div>
				  </div>
					 <div class="control-group">
					<label class="control-label" for="input01">任务完成的时间</label>
					<div class="controls">
					  <input type="text" class="span5 Wdate" id="input01" name="task.enddate" value="${task.enddate }" id="d412" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2012-08-01 00:00:00',maxDate:'2019-01-01 00:00:00'})" value="2012-08-01 00:00:00" >          
					</div>
				  </div>
				  <div class="control-group" >
				  		<div class="controls">				  		
				  			<c:choose>
				  				<c:when test="${fn:contains(task.quanxian,'all')}">
					  				 <label class="radio"><input type="radio"  value="all" name="task.quanxian"  checked="checked" id="optionsRadios1">对所有人可见<br/></label>
					  				 <label class="radio"><input type="radio"  value="me" name="task.quanxian"  id="optionsRadios1">仅对自己可见<br/></label>
					  				 <label class="radio">
					  				 	<input type="radio" id="scan optionsRadios1" name="task.quanxian" >选择一个组...
					  					<select style='width:100px;' id='group' name="task.quanxian" >
					  						<c:forEach items="${sessionScope.glist}" var="g">
					  							<option value="${g.groupname}"> ${g.groupname}</option>
					  						</c:forEach>				  						
					  					</select>
					  													
					  				 </label>
					  				 
				  				</c:when>
				  				<c:when test="${fn:contains(task.quanxian,'group')}">
					  				<label class="radio"><input type="radio"  value="all" name="task.quanxian" id="optionsRadios1" >对所有人可见<br/>	</label>
					  				 <label class="radio"><input type="radio"  value="me" name="task.quanxian" id="optionsRadios1" >仅对自己可见<br/></label>
					  				 <label class="radio">
					  				 	<input type="radio" id="scan optionsRadios1" name="task.quanxian" checked="checked" >选择一个组...
					  					<select style='width:100px;'name='task.quanxian'  id='group' >
					  						<c:forEach items="${sessionScope.glist}" var="g">
					  							<option value="${g.groupname}"> ${g.groupname}</option>
					  						</c:forEach>				  						
					  					</select>
					  				 </label>
				  				</c:when>
				  				<c:when test="${fn:contains(task.quanxian,user.username)}">
					  				<label class="radio"><input type="radio"  value="all" name="task.quanxian" id="optionsRadios1" >对所有人可见<br/></label>
					  				 <label class="radio"><input type="radio" value="me" name="task.quanxian" checked="checked" id="optionsRadios1">仅对自己可见<br/></label>
					  				 <label class="radio">
					  				 	<input type="radio" id="scan optionsRadios1" name="task.quanxian">选择一个组...
					  					<select style='width:100px;'name='task.quanxian'  id='group' >
					  						<c:forEach items="${sessionScope.glist}" var="g">
					  							<option value="${g.groupname}"> ${g.groupname}</option>
					  						</c:forEach>				  						
					  					</select>
					  				 </label>
				  				</c:when>
				  			</c:choose>  												
							<%-- <span>
							<input type="radio" id="optionsCheckbox" value="all" name="task.quanxian" id="qx">是否对所有人可见							
							</span>
							<span> 			
							<input type="radio" id="optionsCheckbox" value="${sessionScope.user.username }" name="task.quanxian" id="qx">仅对自己可见	  			
					  		</span>  --%>
				  		</div>
				  </div>				          
				  <div class="control-group">
					<label class="control-label" for="input01">任务的type</label>
					<div class="controls">
					  	 <select id="select01"  name="tasktype.type">
						 	<option>Task</option>
						 	<option>Case</option>
						 	<option>Deals</option>						 
						  </select>				
					</div>
				  </div>                         
				  <div class="form-actions">
					<button type="submit" class="btn btn-primary">保存</button>
					
					  <a  class="btn"  href="javascript:;">返回</a>
				  </div>
				</fieldset>
			 </form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".btn").click(function(){
				
			
				window.location.href = "task.action";
			});
			
			
			
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
</html>
