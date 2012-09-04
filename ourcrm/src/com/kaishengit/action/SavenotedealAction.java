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
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;
import com.kaishengit.util.DateUtil;

@Namespace("/deal")
public class SavenotedealAction implements SessionAware{
	private Map<String,Object> session;
	private DealService service;
	private String dealid;
	private Dealnote dealnote; 
	
	private Deals deals;
	private Contact contact;
	
	
	
	private List<Dealnote> dealnoteList;
	private int start;
	private int nowstart;
	private int count;
	
	
	@Action(value="savenotedeal",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/notedeal.jsp")})
	public String savenotedeal(){
	
		dealnote.setCreatetime(DateUtil.getTime());
		User user = (User) session.get("user");
		dealnote.setUser(user);
		deals = service.findById(dealid);
		dealnote.setDeals(deals);
		service.saveDealnote(dealnote);
		
	
		
		start = nowstart;
		dealnoteList = service.findAllnote(start, dealid);
		int counts = service.findAllnote(dealid).size();
		count = (counts/4)*4;
		
		contact = service.findByContactName(deals.getDealWith());
		
		return "success";
	}
	
	
	
	//get set
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	public String getDealid() {
		return dealid;
	}
	public void setDealid(String dealid) {
		this.dealid = dealid;
	}
	public Dealnote getDealnote() {
		return dealnote;
	}
	public void setDealnote(Dealnote dealnote) {
		this.dealnote = dealnote;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
}
