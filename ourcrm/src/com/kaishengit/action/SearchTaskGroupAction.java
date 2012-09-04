package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kaishengit.pojo.Group;
import com.kaishengit.service.TaskService;

public class SearchTaskGroupAction {

	private TaskService service;
	
	@Action(value="searchgrouptask")
	public String excute() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		List<Group> groupList = service.findAllGroup();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");
		
		Gson gson = new Gson();
		String json = gson.toJson(groupList);
		System.out.println(json);
		out.print(json);
		
		out.flush();
		out.close();
		return null;
	}

	@Autowired
	public void setService(TaskService service) {
		this.service = service;
	}
	
	
	
}
