package com.kaishengit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;

@Namespace("/deal")
public class DealAction implements SessionAware{
	
	private DealService service;
	private Map<String, Object> session;
	private List<Deals> dealsList;
	private int start;
	private int nowstart;
	private int count;
	
	@Action(value="deal",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/deals.jsp")})
	public String execute(){
		start = nowstart;
		List<Deals> enabledealsList = service.findAll(start);
		List<Deals> dealslist = new ArrayList<Deals>();
		User user = (User) session.get("user");
		for (Deals deals : enabledealsList) {
			if (deals.getView().equals("all")) {
				dealslist.add(deals);
			}else if(deals.getView().contains(deals.getUser().getUsername())){
				dealslist.add(deals);
			}else if (deals.getView().contains(user.getGroup().getGroupname())) {
				dealslist.add(deals);
			}
		}
		dealsList = dealslist;
		
		int counts = getcount();
		count = (counts/4)*4;
		return "success";
	}
	
	public int getcount(){
		List<Deals> list1 = service.findAll();
		List<Deals> list2 = new ArrayList<Deals>();
		User user = (User) session.get("user");
		for (Deals deals : list1) {
			if (deals.getView().equals("all")) {
				list2.add(deals);
			}else if(deals.getView().contains(deals.getUser().getUsername())){
				list2.add(deals);
			}else if (deals.getView().contains(user.getGroup().getGroupname())) {
				list2.add(deals);
			}
		}
		return list2.size();
	}
	
	
	
	
	
	@Action(value="adddeal",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/adddeal.jsp")})
	public String adddeal(){
		return "success";
	}
	
	
	
	//get set
	
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	public List<Deals> getDealsList() {
		return dealsList;
	}
	public void setDealsList(List<Deals> dealsList) {
		this.dealsList = dealsList;
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
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
}
