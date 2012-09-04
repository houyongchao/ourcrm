<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>color</title>
  	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />	
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
	<script src="js/jquery/jquery.js" type="text/javascript"></script>
	<script src="js/jquery/ifx.js" type="text/javascript"></script>
	<script src="js/jquery/idrop.js" type="text/javascript"></script>
	<script src="js/jquery/idrag.js" type="text/javascript"></script>
	<script src="js/jquery/iutil.js" type="text/javascript"></script>
	<script src="js/jquery/islider.js" type="text/javascript"></script>
	
	<script src="js/jquery/color_picker/color_picker.js" type="text/javascript"></script>


	<link href="js/jquery/color_picker/color_picker.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.body{
			background-color:#F3F1EC;
		}
		div .container{
			padding-top:4px;
			
		}
		.row{
			margin-top:60px;
		}
		.wall{
			border:1px solid #DDD;
			background-color:#f2f2f2;
			border-top:0px;
			padding:30px;
			font-size:17px;
		}
		
	</style>
</head>

  </head>
  
  <body>
	<div class="container">
	<div class="row" style="margin-left:200px;">			
		<div class="span6"  style="margin-right:-20px;">
			<ul style="margin-top:10px ; margin-bottom:0px; background-color:yellow; height:40px;" class="breadcrumb">
				<li style="font-size:25px;margin-top:10px;margin-left:10px; ">
					修改颜色
				</li>				
			</ul>
			<div class="wall" style="height:300px;margin-top:0px;padding-top:15px;">	
				<p style="font-size:16px; color:#000; margin-bottom:20px;">这里所设置的颜色是你工程中任务类型显示的颜色，如果你想设置所需要的颜色，请点击以下各颜色框进行修改</p>
				<form class="form-horizontal" action = "${basePath}/task/changecolor.action" method = "post" >
					 <fieldset>
		
							<%-- <tr>
								<td>
									<c:forEach items="${tasktypeList }" var="t">
										<div>
											<form name="fcp">
										${t.type}:<div style="float:left;width:65px;display:block">
												<input type="hidden" name="t.id" value="${t.id }"/>
												<input type="hidden" name="t.type" value="${t.type}"/>
												<input type="text" id="${t.id}" value="${t.color}"  name="t.color" style="width:60px;">
												</div>
												<div style="float:left ; ">
													<a href="javascript:void(0);" rel="colorpicker&amp;objcode=${t.id}&amp;objshow=${t.id}2&amp;showrgb=1&amp;" style="text-decoration:none;" attached="true">
													<div id="${t.id}2" style="width: 100px; height: 15px; border: 1px solid black; background-color: #${t.color}; ">&nbsp;
													</div>
													</a>
											  </div>
											</form>
										</div>
									</c:forEach>
								</td>
														
							</tr> --%>
							
				

									
				<div  class="control-group">																			
				<label class="control-label" for="input01">${sessionScope.t1.type }</label>
				 <div class="controls">
				 	<input type="hidden" name="t1.id" value="${sessionScope.t1.id }"/>
				 	<input type="hidden" name="t1.type" value="${sessionScope.t1.type}"/>
				 	<input type="text" id="${t1.id}" value="${sessionScope.t1.color}"  name="t1.color" style="width:60px;">
				 			<a href="javascript:void(0);" rel="colorpicker&amp;objcode=${t1.id}&amp;objshow=${t1.id}2&amp;showrgb=1&amp;" style="text-decoration:none;" attached="true">
							<div id="${t1.id}2" style="width: 100px; height: 15px; border: 1px solid black; background-color: #${t1.color}; ">&nbsp;
							</div>
							</a>
				 </div>
							
						
				</div>										
												
				<div  class="control-group">
					<label class="control-label" for="input01">${sessionScope.t2.type }</label>
					<div class="controls">
						<input type="hidden" name="t2.id" value="${sessionScope.t2.id }"/>
						<input type="hidden" name="t2.type" value="${sessionScope.t2.type}"/>
						<input type="text" id="${t2.id}" value="${sessionScope.t2.color}"  name="t2.color" style="width:60px;">
							<a href="javascript:void(0);" rel="colorpicker&amp;objcode=${t2.id}&amp;objshow=${t2.id}2&amp;showrgb=1&amp;" style="text-decoration:none;">
							<div id="${t2.id}2" style="width: 100px; height: 15px; border: 1px solid black; background-color: #${t2.color}; ">&nbsp;
							</div>
							</a>
					</div>																			
				</div>																						
										
				<div  class="control-group">
					<label class="control-label" for="input01">${sessionScope.t3.type }</label>
					<div class="controls">
						<input type="hidden" name="t3.id" value="${sessionScope.t3.id }"/>
						<input type="hidden" name="t3.type" value="${sessionScope.t3.type}"/>
						<input type="text" id="${t3.id}" value="${sessionScope.t3.color}"  name="t3.color" style="width:60px;">
							<a href="javascript:void(0);" rel="colorpicker&amp;objcode=${t3.id}&amp;objshow=${t3.id}2&amp;showrgb=1&amp;" style="text-decoration:none;" >
							<div id="${t3.id}2" style="width: 100px; height: 15px; border: 1px solid black; background-color: #${t3.color}; ">&nbsp;
							</div>
							</a>
					</div>

				</div>									

				<div class="control-group">
					<div style="margin-top:50px;" class="controls">
						<input type="submit" class="btn btn-primary" value="保存"/>
						
					</div>
				</div>
				</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>


<script language="Javascript">

	

	//init colorpicker:
	$(document).ready(
		function()
		{
			$.ColorPicker.init();
			
			
			
			
		
			
			
			
		}
	);

</script>
</body>
</html>
