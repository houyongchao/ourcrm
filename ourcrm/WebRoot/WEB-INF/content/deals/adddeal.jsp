<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/base.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>adddeals</title>
	<style type="text/css">
		.span8{
			margin-top:15px;
			margin-bottom:0px;
			background-color:#C8D9E9;
			height:40px;
			margin-left:0px;
		}
		.active{
			font-size:20px;
			font-weight:bold;
			margin-top:10px;
			margin-left:10px;
		}
		.control-group{
			margin-left:25px;
		}
		.controls input,.controls textarea{
			width:80%;
		}
		div.growlUI { 
			background: url(${basePath}/img/check48.png) no-repeat 10px 10px 
		}
		div.growlUI h1{
			color: white; 
			padding: 5px 5px 5px 75px; 
			text-align: left
		}
	
	</style>
  </head>
  
  <body>
    <div class="container">
		<div class="row">
			<div class = "span2" style="font-size:14px;margin-right:20px;margin-top:15px;">
				
				<ul class="nav nav-list" style="font-size:14px;" >					 					
				  <li ><a href="#"><i class="icon-home"></i>&nbsp;&nbsp;欢 迎</a></li>
				   <li><a href="${basePath}/message/message.action"><i class="icon-cog"></i>&nbsp;&nbsp;最 新 动 态</a></li>
				  <li ><a href="${basePath}/contact/contact.action"><i class="icon-flag"></i>&nbsp;&nbsp;联 系 人</a></li>
				  <li><a href="${basePath }/task/task.action"><i class="icon-ok"></i>&nbsp;&nbsp;任 务</a></li>
				  <li><a href="${basePath }/case/case.action"><i class="icon-briefcase"></i>&nbsp;&nbsp;事 件</a></li>
				  <li class="active"><a href="${basePath }/deal/deal.action"><i class="icon-white icon-user"></i>&nbsp;&nbsp;交 易</a></li>
				  <li><a href="${basePath }/search/search.action"><i class=" icon-search"></i>&nbsp;&nbsp;Search notes</a></li>	
				</ul>	
			</div>
			<div class="span8">
				<ul class="breadcrumb" style="margin-bottom:0px;">
					<li class="active">
						添加一个新交易
					</li>
					
				</ul>
				<div class="wall">
					<form action="${basePath }/deal/savedeal.action" method="post">
						<fieldset>
							<div class="control-group">
								 <label class="control-label"><h3>和谁进行交易？</h3></label>
								 <label class="control-label">填写交易人的名字</label>
								<div class="controls">
									<input type="text" id="input01"  x-webkit-speech="undefined" name = "deals.dealWith"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><h3>交易的名字</h3></label>
								<div class="controls">
									<input type="text" id="input02"  x-webkit-speech="undefined" name = "deals.dealsname"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><h3>交易的描述</h3></label>
								<div class="controls">
									<textarea rows="5" id="input03" name = "deals.descs">请输入一些关于交易的事情...</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><h3>交易的金额</h3></label>
								<div>
									<div class="input-prepend input-append">
										<span class="add-on">￥</span>
										<input type="text" id="input04" class="span2" name = "deals.money"/>
										<span class="add-on">.00</span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><h3>交易的状态</h3></label>
								<div class="controls">
									<select name="deals.state">
										<option value="genzong" selected="selected">跟踪</option>
										<option value="chenggong">成功</option>
										<option value="diushi">丢失</option>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><h3>交易的类型</h3></label>
								<div class="controls">
									<select name="deals.dealtype.type" id="">
										<option value="None">None</option>
										<option value="Copywriting">Copywriting</option>
										<option value="Print Project">Print Project</option>
										<option value="Strategy Consulting">Strategy Consulting</option>
										<option value="Web Site Design">Web Site Design</option>
									</select>
									<a href="#">编辑类型</a>
								</div>
							</div>
							<div  style = "margin-left:25px">
								<h3>谁可以看见这个联系人</h3>
								<hr />
							</div>
							<div style = "margin-left:25px;font-size:14px">
								<input type="radio" name="deals.view" value="all"/>所有人<br/><br/>
								<input type="radio" name="deals.view" value="me"/>只有我能看见<br/>
								<input type="radio" name="deals.view" value="group" id="scan"/>选择指定的组
								<span id = "scangroup" style="margin-left:34px;">
									<select style="width:180px;" name="deals.view" id="group" multiple="multiple"></select>
								</span><br/>
								<input type="radio" name="deals.view" value="members" id="onlyuser"/>选择指定的用户
								<span id="chooseuser" style="margin-left:20px;">
									<select style="width:180px;" name="deals.view" id="alluser" multiple="multiple"></select>
								</span><br/>
							</div>
							<hr style="height:2px;width:90%;background-color:#ccc;margin-left:25px;"/>
							<div style="margin-left:30px;">
								<input type="submit" class="btn btn-primary" id="demo12" value="保存"/>&nbsp;&nbsp;&nbsp;
								<a class="btn btn-info" href="${basePath }/deal/deal.action">返回</a>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="span2" style="margin-top:15px;">
				<ul class="breadcrumb" style="margin-bottom:0px;">
					<li class="active">
						关于交易
					</li>
				</ul>
				<div class="wall" style="max-height:320px;">
					<p style="margin:5px;">A deal is a money-based project you have up for bid or contract.
						Deals let you keep track of proposals, bids, RFPs, and project sales 
						right inside Highrise. Highrise has always been great for keeping track 
					</p>
				</div>
			</div>
		</div>
	</div>
    <script type="text/javascript">
    	$(document).ready(function(){
    		/*---------------侯永超的代码-------------------------------*/
    		$('#demo12').click(function() { 
				$.growlUI('祝贺您','保存成功',2000); 
			}); 
    		
    	 	/*----------------------选择一个组----------------------------------------*/
    	 	$("#scan").click(function(){
	    	 	$("#group").text("");
	    	 	$.post("searchgroup.action",function(result){
	    	 		//循环json数据
	    	 		$(result).each(function(){
	    	           
	    	            var t = this.groupname;
	    	 			$("#group").append($("<option value="+t+">"+t+"</option>"));
	    	 		});
	    	 	});
    	 	});
   	 		
    	 /*-------------------------选择指定的用户-------------------------*/
    	 	$("#onlyuser").click(function(){
    	 		$("#alluser").text("");
    	 		$.getJSON("searchuser.action",function(result){
    	 			//循环json数据
    	 			$(result).each(function(){
	    	 			$("#alluser").append($("<option value="+this.username+">"+this.username+"</option>"));
	    	 		});
    	 		
    	 		});
    	 	});
    	 /*--------------------------select2插件的配置----------------------*/
    	 	$("#alluser").select2({
    	 		placeholder: "请点击这里选择用户"
    	 	});
    		$("#group").select2({
    			placeholder: "请点击这里选择组"
    		});
    	});
    
    </script>
    
    
  </body>
</html>
