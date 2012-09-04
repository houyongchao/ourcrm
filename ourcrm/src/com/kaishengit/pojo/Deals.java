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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_deals")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deals {

	private String id;
	private String dealsname;
	private String descs;
	private String dealWith;
	private String state;
	private float money;
	private String view;//�ɼ���
	private String createtime;
	private String isenable;
	private User user;
	private Dealtype dealtype;
	private Contact contact;
	private List<Dealnote>  dealnoteList;
	private List<Task> taskList;

	
	@Id
	@GenericGenerator(name = "pk",strategy = "uuid")
	@GeneratedValue(generator = "pk")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDealsname() {
		return dealsname;
	}
	public void setDealsname(String dealsname) {
		this.dealsname = dealsname;
	}
	
	
	public String getIsenable() {
		return isenable;
	}
	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDealWith() {
		return dealWith;
	}
	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	@ManyToOne
	@JoinColumn(name="contactid")
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
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
	@JoinColumn(name = "dealtypeid")
	public Dealtype getDealtype() {
		return dealtype;
	}
	public void setDealtype(Dealtype dealtype) {
		this.dealtype = dealtype;
	}
	@OneToMany(mappedBy="deals")
	public List<Dealnote> getDealnoteList() {
		return dealnoteList;
	}
	public void setDealnoteList(List<Dealnote> dealnoteList) {
		this.dealnoteList = dealnoteList;
	}
	@OneToMany(mappedBy = "deals")
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	
}
