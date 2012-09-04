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
@Table(name = "t_commentnote")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealnoteComment {

	private String id;
	private String content;
	private String createtime;
	private User user;
	private Dealnote dealnote;
	private List<Dealreplycomment> replyList;
	
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
	@JoinColumn(name="userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="noteid")
	public Dealnote getDealnote() {
		return dealnote;
	}
	public void setDealnote(Dealnote dealnote) {
		this.dealnote = dealnote;
	}
	@OneToMany(mappedBy="noteComment")
	public List<Dealreplycomment> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Dealreplycomment> replyList) {
		this.replyList = replyList;
	}
	
	
	
}
