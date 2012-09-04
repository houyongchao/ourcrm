package com.kaishengit.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.service.MessagerService;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.Pager;
import com.kaishengit.util.PagerNum;

/**
 * @author Administrator
 *
 */
@Namespace("/message")
public class MessageAction extends BaseAction{
	private MessagerService ms;
	private User user;
	private List<Message> messageList;
	private int start;
	private int perNum = 4;
	private Pager pager;
	private TaskService taskservice;
	private String id;
	private int nowstart;
	private int count;
	private int totalCounts;
	
	public String execute(){
		start = nowstart;
		user = (User) getSession().get("user");
		messageList = ms.findAll(user, start, perNum);
		
		totalCounts = ms.findCount();
		System.out.println(totalCounts);
		count = (totalCounts/4)*4;
		
		
		List<Task> listt = taskservice.findAll(user);
		getSession().put("listt", listt);
		return "success";
	}
	
	@Action(value="deltask",results={@Result(name="success",type="redirectAction",params={"actionName","message","namespace","/message"})})
	public String deltask(){
		
			User user =(User)getSession().get("user");
	         Task t = taskservice.findById(id);
	         t.setQuanxian(null);
	         taskservice.del(id,user,t);
		return "success";
	}
	
	//set get
	@Autowired
	public void setMs(MessagerService ms) {
		this.ms = ms;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	


	public Pager getPager() {
		return pager;
	}



	public void setPager(Pager pager) {
		this.pager = pager;
	}


	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Autowired
	public void setTaskservice(TaskService taskservice) {
		this.taskservice = taskservice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNowstart() {
		return nowstart;
	}

	public void setNowstart(int nowstart) {
		this.nowstart = nowstart;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}



	
	
	
}
	