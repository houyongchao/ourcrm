package com.kaishengit.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.DealnoteComment;
import com.kaishengit.pojo.Dealreplycomment;
import com.kaishengit.service.DealService;

@Namespace("/deal")
public class AddnoteCommentAction {
	private DealService service;
	private String commentid;
	private DealnoteComment dealnotecomment;
	private int start;
	private int nowstart;
	private int count;
	private List<Dealreplycomment> replyList;
	
	
	
	@Action(value="addcomment",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/addcomment.jsp")})
	public String goComment(){
		dealnotecomment = service.findByIdDealnotecomment(commentid);
		start = nowstart;
		replyList = service.findAllreply(start, commentid);
		int counts = service.findAllreply(commentid).size();
		count = (counts/4)*4;
		
		return "success";
	}
	
	//get set
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	
	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public DealnoteComment getDealnotecomment() {
		return dealnotecomment;
	}

	public void setDealnotecomment(DealnoteComment dealnotecomment) {
		this.dealnotecomment = dealnotecomment;
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

	public List<Dealreplycomment> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Dealreplycomment> replyList) {
		this.replyList = replyList;
	}
	
}
