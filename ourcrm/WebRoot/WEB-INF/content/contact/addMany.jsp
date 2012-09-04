<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file = "../include/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'addMany.jsp' starting page</title>
    
	

  </head>
  
  <body>
  <div style = "margin-top:80px" class="span5 offset3 well">
	    <a href = "${basePath}/contact/download.action">下载一个模板</a>
	  	</hr>
	  	<h3>上传一个excel表格</h3>
	  	<form action="upload.action" enctype = "multipart/form-data" method = "post">
	  		<input type = "file" name = "document"/>
	  		<input type = "submit" value = "上传"/>
	  	</form>	
  </div>
  </body>
</html>
