package com.kaishengit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Casenote;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.service.CaseService;
import com.kaishengit.service.CasenoteService;
import com.kaishengit.util.DateUtil;

@Namespace("/case")
public class CaseAction  implements SessionAware{

	private CaseService caseservice;
	private Map<String, Object> session;
	private List<Case> caseList;
	private Case cases;
	private String id;
	private Casenote note;
	private CasenoteService noteservice;
	private List<Casenote> casenotelist;
	//private TaskService taskservice;
	
	/**
	 *查找出所有的事件； 
	 * @return
	 */
	public String execute(){
		
		
		//User u = (User) session.get("user");
		
		caseList = caseservice.findAll();
		
		
		
		
		return "success";
	}
	
	
	/**
	 *跳转到添加新事件的页面; 
	 * @return
	 */
	@Action(value="toaddnewcase",results={@Result(name="success",type="dispatcher",location="newcase.jsp")})
	public String toaddnewcase(){
		return "success";
	}
	
	/**
	 * 添加新事件
	 * @return
	 */
	@Action(value="addnewcase",results={@Result(name="success",type="redirectAction",params={"actionName","case","namespace","/case"})})
	public String addnewcase(){
		cases.setCreatetime(DateUtil.getTime());
		User user = (User)session.get("user");
		cases.setUser((User)session.get("user"));
		caseservice.save(cases,user);
		return "success";
	}
	
	/**	
	 * 跳转到添加事件描述的页面；
	 * @return
	 */
	@Action(value="miaoshu",results={
			@Result(name="success",type="dispatcher",location="miaoshu.jsp")
	})
	public String edit(){
		cases = caseservice.findById(id);
		session.put("caseid", id);
		System.out.println(id);
		session.put("cases", cases);
		casenotelist  = noteservice.findByidList(id);//找到所有的评论。
		User user = (User) session.get("user");
		System.out.println("can neng zhi xing ma................");
		
		System.out.println(id);
		System.out.println("这个集合执行了....");
		List<Task> taskcaselist = caseservice.findByCaseId(id,user);
		session.put("casetasklist", taskcaselist);
		System.out.println(taskcaselist);
		System.out.println("----------------");
		
		return "success";
	}

	/**
	 * /miaoshu.action?id=hsoghaho
	 * location="miaoshu.action?id=${id }" zhe li ke yi zhe me xie ma?
	 * 保存添加的对应事件的描述
	 * @return
	 */
	@Action(value="addmiaoshu",results={			
			@Result(name="success",type="redirectAction",location="miaoshu.action?id=${cases.id}")
	})
	public String add(){
		
		User u = (User)session.get("user");
		Case c = (Case) session.get("cases");
		note.setCreatetime(DateUtil.getTime());
		note.setUser((User)session.get("user"));
		
		note.setCases(c);
		
		noteservice.save(note,u,c);
		return "success";
	}
	
/*	*//**
	 * 跳转到回复界面；
	 * @return
	 *//*
	@Action(value="tohuifu",results={@Result(name="success",type="dispatcher",location="huifu.jsp")})
	public String tohuifu(){
		return "success";
	}
	*/
	
	
	   //向前台发送json
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
	
	@Autowired
	public void setCaseservice(CaseService caseservice) {
		this.caseservice = caseservice;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<Case> getCaseList() {
		return caseList;
	}

	public void setCaseList(List<Case> caseList) {
		this.caseList = caseList;
	}


	public Case getCases() {
		return cases;
	}


	public void setCases(Case cases) {
		this.cases = cases;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public Casenote getNote() {
		return note;
	}



	public void setNote(Casenote note) {
		this.note = note;
	}



	@Autowired
	public void setNoteservice(CasenoteService noteservice) {
		this.noteservice = noteservice;
	}



	public List<Casenote> getCasenotelist() {
		return casenotelist;
	}



	public void setCasenotelist(List<Casenote> casenotelist) {
		this.casenotelist = casenotelist;
	}




	
	
	
	
	
	
}
