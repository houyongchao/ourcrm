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
				<form class="form-horizontal" method="post" action="${basePath }/task/add.action">
				<fieldset style="width:800px;">
				  <legend> 修改任务:</legend>
				  <div class="control-group">
					<label class="control-label" for="input01">任务名称</label>
					<div class="controls">
				
					<input type="text" class="span5" id="input01" name="task.taskname"  x-webkit-speech="undefined">          
					</div>
				  </div>
					 <div class="control-group">
					<label class="control-label" for="input01">任务完成的时间</label>
					<div class="controls">
					  <input type="text" class="span5 Wdate" id="input01" name="task.enddate"  id="d412" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'2012-08-01 00:00:00',maxDate:'2019-01-01 00:00:00'})" value="2012-08-01 00:00:00" >          
					</div>
				  </div>
				  <div class="control-group">
				  	<label class="checkbox" style="margin-left:140px;">				  		
				  		<span>
						<input type="radio" id="optionsCheckbox" value="all" name="quan" id="qx">是否对所有人可见							
						</span>
						<span> 			
						<input type="radio" id="optionsCheckbox" value="me" name="quan" id="qx">仅对自己可见	  			
				  		</span> 				  						  		
				  	</label>
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
				//window.location.back();
			
				window.location.href = "task.action";
			});
		});
	</script>
  </body>
</html>
