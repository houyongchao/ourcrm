package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;

@Namespace("/account")
public class EditUserAction implements SessionAware {

	  private Map<String, Object> session;
	  private User user;
      private UserService userService;
      private String password;
	
	
	public String execute(){
		return "success";
	}
	
	
	
	@Action(value="usereidt",results={@Result(name="success",type="dispatcher",location="edit.jsp")})
	public String editUser(){
		return "success";
	}


	 @Action("passwordValidate")
     public String passwordValidate() {
             boolean temp;
             //判断传入的密码跟当前session中的是否相同
             if(((User)session.get("user")).getPassword().equals(password)) {
                     temp = true;
             } else {
                     temp = false;
             }
             sendJson(temp);
             return null;
     }
	
	
	
	
			 @Action(value="modify",results={		        
		             @Result(name="success",type="redirectAction",params={"actionName","usereidt","code","10000"})
		})
		public String modify() {
		    
				 
		     if(user.getPassword() == null || "".equals(user.getPassword())) {
		             user.setPassword(((User)session.get("user")).getPassword());
		             System.out.println(((User)session.get("user")).getPassword());
		     } else {
		           user.setPassword(password);
		             System.out.println(user.getUsername());
		             System.out.println(user.getPassword());
		     }
		   
		   //为什么null了呢？
		     userService.saveOrUpdate(user);
		    
		     session.put("user", user);
		     return "success";
		}
	
	 public void sendJson(Object o) {
         try {
        	 	HttpServletResponse response = ServletActionContext.getResponse();
                 PrintWriter writer = response.getWriter();
                 response.setContentType("application/json;charset=UTF-8");
                 Gson gson = new Gson();
                 writer.print(gson.toJson(o));
                 
                 writer.flush();
                 writer.close();
         } catch (IOException e) {
                 e.printStackTrace();
         } 
 }
	
	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setSession(Map<String, Object> session) {

		this.session = session;
	}



	public Map<String, Object> getSession() {
		return session;
	}



	public UserService getUserService() {
		return userService;
	}



	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
