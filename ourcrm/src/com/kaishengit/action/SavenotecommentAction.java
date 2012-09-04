package com.kaishengit.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Dealnote;
import com.kaishengit.pojo.DealnoteComment;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;
import com.kaishengit.util.DateUtil;

@Namespace("/deal")
public class SavenotecommentAction implements SessionAware{
	private Map<String,Object> session;
	private DealService service;
	private DealnoteComment comment;
	private String dealnoteid;
	private Dealnote dealnote;
	
	private int start;
	private int nowstart;
	private int count;
	private List<DealnoteComment> commentList;
	
	@Action(value="savenotecomment",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/deals/commentdeal.jsp")})
	public String savenotecomment(){
		
		comment.setCreatetime(DateUtil.getTime());
		User user = (User) session.get("user");
		comment.setUser(user);
		dealnote = service.findByIdDealnote(dealnoteid);
		comment.setDealnote(dealnote);
		service.savenotecomment(comment);
	
		
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
	public DealnoteComment getComment() {
		return comment;
	}
	public void setComment(DealnoteComment comment) {
		this.comment = comment;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
