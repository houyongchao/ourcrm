package com.kaishengit.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.DealnoteComment;
import com.kaishengit.pojo.Dealreplycomment;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;
import com.kaishengit.util.DateUtil;

@Namespace("/deal")
public class SaveReplyCommentAction implements SessionAware{
	private Map<String, Object> session;
	private DealService service;
	private String commentid;
	private Dealreplycomment replycomment;
	private DealnoteComment dealnotecomment;
	
	private int start;
	private int nowstart;
	private int count;
	private List<Dealreplycomment> replyList;
	
	
	@Action(value="savereplycomment",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/addcomment.jsp")})
	public String saveReplyComment(){
		
		replycomment.setCreatetime(DateUtil.getTime());
		dealnotecomment = service.findByIdDealnotecomment(commentid);
		replycomment.setNoteComment(dealnotecomment);
		User user = (User) session.get("user");
		replycomment.setUser(user);
		
		service.savereplycomment(replycomment);
	
		
		
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
	public Dealreplycomment getReplycomment() {
		return replycomment;
	}
	public void setReplycomment(Dealreplycomment replycomment) {
		this.replycomment = replycomment;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
