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
@Table(name = "t_dealtype")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dealtype {

	private String id;
	private String type;
	private String color;
	private List<Deals> dealsList;
	
	@Id
	@GenericGenerator(name = "pk",strategy = "uuid")
	@GeneratedValue(generator = "pk")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	@OneToMany(mappedBy = "dealtype")	
	public List<Deals> getDealsList() {
		return dealsList;
	}
	public void setDealsList(List<Deals> dealsList) {
		this.dealsList = dealsList;
	}
	
	
}
