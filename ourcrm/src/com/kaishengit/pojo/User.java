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
@Table(name = "t_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

	private String id;
	private String username;
	private String password;
	private Group group;
	private List<Contact> contactList;
	private List<Contactnote> contactnoteList;
	private List<Deals> dealsList;
	private List<Task> taskList;
	private List<Dealnote> dealnoteList;
	private List<Casenote> casenoteList;
	private List<Case> caseList;
	private List<Message> messageList;
	private List<Casehuifu> casehuifuList;
	
	
	@Id
	@GenericGenerator(name = "pk",strategy = "uuid")
	@GeneratedValue(generator = "pk")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToOne
	@JoinColumn(name = "groupid")
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	@OneToMany(mappedBy = "user")
	public List<Contact> getContactList() {
		return contactList;
	}
	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
	@OneToMany(mappedBy = "user")
	public List<Contactnote> getContactnoteList() {
		return contactnoteList;
	}
	public void setContactnoteList(List<Contactnote> contactnoteList) {
		this.contactnoteList = contactnoteList;
	}
	
	@OneToMany(mappedBy = "user")
	public List<Deals> getDealsList() {
		return dealsList;
	}
	public void setDealsList(List<Deals> dealsList) {
		this.dealsList = dealsList;
	}
	
	@OneToMany(mappedBy = "user")
	public List<Task> getTaskList() {
		return taskList;
	}
	
	
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	@OneToMany(mappedBy = "user")
	public List<Dealnote> getDealnoteList() {
		return dealnoteList;
	}
	public void setDealnoteList(List<Dealnote> dealnoteList) {
		this.dealnoteList = dealnoteList;
	}
	@OneToMany(mappedBy = "user")
	public List<Casenote> getCasenoteList() {
		return casenoteList;
	}
	public void setCasenoteList(List<Casenote> casenoteList) {
		this.casenoteList = casenoteList;
	}
	@OneToMany(mappedBy = "user")
	public List<Case> getCaseList() {
		return caseList;
	}
	public void setCaseList(List<Case> caseList) {
		this.caseList = caseList;
	}
	@OneToMany(mappedBy = "user")
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
	@OneToMany(mappedBy = "user")
	public List<Casehuifu> getCasehuifuList() {
		return casehuifuList;
	}
	public void setCasehuifuList(List<Casehuifu> casehuifuList) {
		this.casehuifuList = casehuifuList;
	}
	
	
	
	
}
