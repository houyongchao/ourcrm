package com.kaishengit.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kaishengit.pojo.Tasktype;
import com.kaishengit.service.TasktypeService;


public class tasktypeAction {

	
	  //private List<Tasktype> tasktypeList;
	  private TasktypeService tasktypeservice;
	  
	public String execute() throws Exception{
		
		List<Tasktype> tasktypeList = tasktypeservice.findAll();
		
		
		for(Tasktype t : tasktypeList){
			System.out.println(t.getId());
			System.out.println("tom");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("1");
		response.setContentType("application/json;charset=UTF-8");
		System.out.println("2");
        PrintWriter writer = response.getWriter();
        System.out.println("3");
        Gson gson = new Gson();
        String son = gson.toJson(tasktypeList);
        System.out.println("4");
        writer.print(son);
       
        System.out.println("str");
        System.out.println(gson.toJson(tasktypeList));
        writer.flush();
        writer.close();
		
		
		
		
		//sendJson(tasktypeList);
		
		
		
		
		
		return null;
	}
	
	
	/*  public void sendJson(Object o) {
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
*/



	public TasktypeService getTasktypeservice() {
		return tasktypeservice;
	}


	@Autowired
	public void setTasktypeservice(TasktypeService tasktypeservice) {
		this.tasktypeservice = tasktypeservice;
	}


	/*public List<Tasktype> getTasktypeList() {
		return tasktypeList;
	}


	public void setTasktypeList(List<Tasktype> tasktypeList) {
		this.tasktypeList = tasktypeList;
	}*/
	  
	  
	  
}
