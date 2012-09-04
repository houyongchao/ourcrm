package com.kaishengit.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Dealnote;
import com.kaishengit.pojo.DealnoteComment;
import com.kaishengit.service.DealService;

@Namespace("/deal")
public class ConmmentdealAction {
	private DealService service;
	private String dealnoteid;
	private Dealnote dealnote;
	private int start;
	private int nowstart;
	private int count;
	private List<DealnoteComment> commentList;
	
	@Action(value="commentdeal",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/commentdeal.jsp")})
	public String comment(){
		dealnote = service.findByIdDealnote(dealnoteid);
		
		start = nowstart;
		commentList = service.findAllcomment(start, dealnoteid);
		int counts = service.findAllcomment(dealnoteid).size();
		count = (counts/4)*4;
		
		return "success";
	}
	
	//get set
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	public String getDealnoteid() {
		return dealnoteid;
	}
	public void setDealnoteid(String dealnoteid) {
		this.dealnoteid = dealnoteid;
	}

	public Dealnote getDealnote() {
		return dealnote;
	}

	public void setDealnote(Dealnote dealnote) {
		this.dealnote = dealnote;
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

	public List<DealnoteComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<DealnoteComment> commentList) {
		this.commentList = commentList;
	}
	
}
