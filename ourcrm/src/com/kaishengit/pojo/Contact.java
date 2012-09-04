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
@Table(name = "t_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact {
	
	private String id;
	private String contactname;
	private String position;//ְλ
	private String companyname;
	private String email;
	private String qq;
	private String websites;
	private String address;
	private String descs;
	private String view;
	private String type;
	private String tels;
	private String companyid;
	private User user;
	private List<Deals> dealList;
	private List<Task> taskList;
	private List<Contactnote> contactnoteList;
	private boolean isenable;
	private String createtime;

	
	@Id
	@GenericGenerator(name = "pk",strategy = "uuid")
	@GeneratedValue(generator = "pk")
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWebsites() {
		return websites;
	}
	public void setWebsites(String websites) {
		this.websites = websites;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getTels() {
		return tels;
	}
	public void setTels(String tels) {
		this.tels = tels;
	}
	
	
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	@ManyToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(mappedBy = "contact")
	public List<Task> getTaskList() {
		return taskList;
		
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	@OneToMany(mappedBy = "contact")
	public List<Contactnote> getContactnoteList() {
		return contactnoteList;
	}
	public void setContactnoteList(List<Contactnote> contactnoteList) {
		this.contactnoteList = contactnoteList;
	}
	@OneToMany(mappedBy="contact")
	public List<Deals> getDealList() {
		return dealList;
	}
	public void setDealList(List<Deals> dealList) {
		this.dealList = dealList;
	}
	public boolean isIsenable() {
		return isenable;
	}
	public void setIsenable(boolean isenable) {
		this.isenable = isenable;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
	
}
