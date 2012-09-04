package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;
import com.kaishengit.service.UserService;


public class RegUserAction {
	private DealService service;
	private UserService userService;
	private String name;
	private String password;
	private String zu;
	
	@Action(value="searchusername")
	public String findUser() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		User user = userService.findUser(name);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");
		
		
		if(user == null){
			out.print(0);
		}else{
			out.print(1);
		}
		out.flush();
		out.close();
		
		return null;
	}
	
	
	
	@Action(value="saveuser",results={@Result(name="success",type="redirect", location="/WEB-INF/content/tiaozhuan-success.jsp")})
	public String saveuser(){
		User user = new User();
		user.setUsername(name);
		user.setPassword(password);
		Group group = service.findGroupByName(zu);
		user.setGroup(group);
		userService.saveOrUpdate(user);
		return "success";
	}

	@Action(value="reguser",results={@Result(name="success",location="/WEB-INF/content/account/reguser.jsp")})
	public String regUser(){
		return "success";
	}


	//get set
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getZu() {
		return zu;
	}



	public void setZu(String zu) {
		this.zu = zu;
	}
	
}
