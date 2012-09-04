package com.kaishengit.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class BaseAction implements SessionAware{
	private Map<String,Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	
	public Map<String, Object> getSession() {
		return session;
	}
	
}
