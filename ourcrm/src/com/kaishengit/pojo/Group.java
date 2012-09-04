package com.kaishengit.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "t_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group {

	private String id;
	private String groupname;
	//private List<User> userList;
	
	@Id
	@GenericGenerator(name = "pk",strategy = "uuid")
	@GeneratedValue(generator = "pk")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

		
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	/*@OneToMany(mappedBy = "group")
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}*/
	
	
	
	
	
	
	
}
