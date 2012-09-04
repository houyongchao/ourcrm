package com.kaishengit.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_dealnote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dealnote {

	private String id;
	private String content;
	private String createtime;	
	private User user;
	private Deals deals;
	private List<DealnoteComment> commentList;
	
	@Id
	@GenericGenerator(name = "pk",strategy = "uuid")
	@GeneratedValue(generator = "pk")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@ManyToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name = "dealsid")
	public Deals getDeals() {
		return deals;
	}
	public void setDeals(Deals deals) {
		this.deals = deals;
	}
	@OneToMany(mappedBy="dealnote")
	public List<DealnoteComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<DealnoteComment> commentList) {
		this.commentList = commentList;
	}
	
	
}
