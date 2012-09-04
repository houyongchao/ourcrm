package com.kaishengit.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Where {

	public Where(){}
	
	public Where(String property, String value, String matchType) {
		this.property = property;
		this.value = value;
		this.matchType = matchType;
	}
	

	private String property; 
	private String value; 
	private String matchType;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	
	
	/**
	 *
	 * q_eq_username
	 * q_like_address
	 * q_like_firstname_OR_lastname 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Where> builderWhereListByRequest(HttpServletRequest request) {
		List<Where> list = new ArrayList<Where>();
		
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String formName = enumeration.nextElement();
			if(formName.startsWith("q_")) {
				//q_like_username
				String[] strs = formName.split("_");
				if(strs.length < 3) {
					throw new IllegalArgumentException("格式不正确:" + formName);
				} else {
					String matchType = strs[1];  
					String property = strs[2];  
					String value = request.getParameter(formName);
					try {
						value = new String(value.getBytes("ISO8859-1"),"UTF-8");
						request.setAttribute(formName, value);
						Where where = new Where(property,value,matchType);
						list.add(where);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
	
}
