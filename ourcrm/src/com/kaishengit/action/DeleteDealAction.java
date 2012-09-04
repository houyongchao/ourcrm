package com.kaishengit.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.service.DealService;

@Namespace("/deal")
public class DeleteDealAction {
	private DealService service;
	private String dealid;

	@Action(value="deletedeals",results={@Result(name="success",type="redirectAction",params={"actionName","deal"})})
	public String deletedeal(){
		service.deleteDeal(dealid);
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
}