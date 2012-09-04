package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kaishengit.dto.UserDto;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;


public class SearchuserAction {

	private DealService service;
	
	
	@Action(value="searchuser")
	public String excute() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		List<User> userList = service.findAllUser();
		
		List<UserDto> dtoList = new ArrayList<UserDto>();
		for (User user : userList) {
			UserDto ud = new UserDto();
			ud.setUsername(user.getUsername());
			dtoList.add(ud);
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String json = gson.toJson(dtoList);
		System.out.println(json);
		out.print(json);
		
		out.flush();
		out.close();
		return null;
	}

	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}


}
