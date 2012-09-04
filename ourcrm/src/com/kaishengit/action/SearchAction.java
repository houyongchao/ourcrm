package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kaishengit.dto.MessageDto;
import com.kaishengit.pojo.Message;
import com.kaishengit.service.MessagerService;

@Namespace("/search")
public class SearchAction {
	private MessagerService service;
	private String title;
	
	
	@Action(value="searchall")
	public String findAll() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		List<Message> mList = service.findAllMessages(title);
		
		List<MessageDto> messageDtos = new ArrayList<MessageDto>();
		for (Message message : mList) {
			MessageDto mDto = new MessageDto();
			mDto.setContent(message.getContent());
			mDto.setCreatetime(message.getCreatetime());
			mDto.setTitle(message.getTitle());
			messageDtos.add(mDto);
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String json = gson.toJson(messageDtos);
		System.out.println(json);
		out.print(json);
		
		out.flush();
		out.close();
		
		return null;
	}
	

	
	
	
	@Action(value="search",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/search/search.jsp")})
	public String execute(){
		return "success";
	}

	//get set
	

	@Autowired
	public void setService(MessagerService service) {
		this.service = service;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
