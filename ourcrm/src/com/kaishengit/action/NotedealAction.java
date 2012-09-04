package com.kaishengit.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Dealnote;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;

@Namespace("/deal")

public class NotedealAction implements SessionAware{

	private DealService service;
	private Map<String, Object> session;
	private String dealid;
	private Deals deals;
	private Contact contact;
	private User user;
	
	private List<Dealnote> dealnoteList;
	private int start;
	private int nowstart;
	private int count;
	
	
	@Action(value="notedeal",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/notedeal.jsp")})
	public String notedeal(){
		deals = service.findById(dealid);
		session.put("dealid", dealid);

		contact = service.findByContactName(deals.getDealWith());
		user = (User) session.get("user");
		
		start = nowstart;
		dealnoteList = service.findAllnote(start, dealid);
		int counts = service.findAllnote(dealid).size();
		count = (counts/4)*4;
		
		//这一段须写在deal.action中；
				System.out.println(dealid);
				System.out.println("这个集合执行了....");
				List<Task> taskdeallist = service.findByDealId(dealid);
				session.put("dealtasklist", taskdeallist);
				System.out.println(taskdeallist);
				System.out.println("----------------");
		
		
		return "success";
	}
	
	//get set
	public String getDealid() {
		return dealid;
	}
	public void setDealid(String dealid) {
		this.dealid = dealid;
	}
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	public Deals getDeals() {
		return deals;
	}
	public void setDeals(Deals deals) {
		this.deals = deals;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Dealnote> getDealnoteList() {
		return dealnoteList;
	}

	public void setDealnoteList(List<Dealnote> dealnoteList) {
		this.dealnoteList = dealnoteList;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
