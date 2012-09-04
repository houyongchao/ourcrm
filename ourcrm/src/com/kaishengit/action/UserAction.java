package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.opensymphony.xwork2.ActionSupport;


public class UserAction  extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private User user;
	private UserService userService;
	
	
	
	
	
	
	  @Action(value="loginout",results={@Result(name="tologin",type="redirectAction",params={"actionName","tiaozhuan","namespace","/"})})
      public String loginout() {
              
              if(session != null) {
                      session.remove("user");
              }
           return "tologin";
              
      }
	 public String validateLogin(){
		if(user == null ){
			addActionError("用户名或密码错误");
		}
			return "success";
		}
	
	@Action(value = "user",results={@Result(name = "input",type = "dispatcher",location = "tiaozhuan-success.jsp")})
	public String login() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		User u = userService.login(user);
		if(u != null){
			session.put("user", u);
			pw.print("1");
		
		} else {
			pw.print("0");
		}
		pw.flush();
		pw.close();
		return null;
	}

	
	
	
	public Map<String, Object> getSession() {
		return session;
	}




	//get set
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	

	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}



	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}




}
