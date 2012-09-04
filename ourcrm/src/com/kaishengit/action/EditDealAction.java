package com.kaishengit.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Deals;
import com.kaishengit.service.DealService;

@Namespace("/deal")
public class EditDealAction {
	private DealService service;
	private String dealid;
	private Deals deal;
	
	
	@Action(value="editdeals",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/editdeal.jsp")})
	public String editDeal(){
		deal = service.findById(dealid);
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
	public Deals getDeal() {
		return deal;
	}
	public void setDeal(Deals deal) {
		this.deal = deal;
	}
	
}
