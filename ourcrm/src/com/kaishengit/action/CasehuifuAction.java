package com.kaishengit.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Casehuifu;
import com.kaishengit.pojo.Casenote;
import com.kaishengit.pojo.User;
import com.kaishengit.service.CasehuifuService;
import com.kaishengit.service.CasenoteService;
import com.kaishengit.util.DateUtil;

@Namespace("/case")
public class CasehuifuAction implements SessionAware {

	private Map<String, Object> session;
	private Casehuifu casehuifu;
	private Case cases;
	private String id;
	private CasehuifuService casehuifuservice;
	private Casenote casenote;
	private CasenoteService casenoteservice;
	
	
	/**
	 * 跳转到回复界面；
	 * @return
	 */
	@Action(value="tohuifu",results={@Result(name="success",type="dispatcher",location="huifu.jsp")})
	public String tohuifu(){
		List<Casehuifu> casehuifulist = casehuifuservice.findById(id);
		casenote = casenoteservice.findbyid(id);
		session.put("casenote", casenote);
		session.put("casehuifulist",casehuifulist);
		return "success";
	}
	

	
	@Action(value="addhuifu",results={@Result(name="success",type="redirectAction",location="tohuifu.action?id=${casenote.id}")})
	public String addhuifu(){
		casehuifu.setCreatetime(DateUtil.getTime());
		User user = (User) session.get("user");
		casehuifu.setUser(user);
		casenote = (Casenote) session.get("casenote");
		 System.out.println(casenote);
		casehuifu.setCasenote(casenote);
		casehuifuservice.save(casehuifu);
		return "success";
	}
	
	
	
	
	
	
	
	
	public Casehuifu getCasehuifu() {
		return casehuifu;
	}
	public void setCasehuifu(Casehuifu casehuifu) {
		this.casehuifu = casehuifu;
	}



	public Case getCases() {
		return cases;
	}



	public void setCases(Case cases) {
		this.cases = cases;
	}


	
	public void setSession(Map<String, Object> session) {

		this.session = session;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Autowired
	public void setCasehuifuservice(CasehuifuService casehuifuservice) {
		this.casehuifuservice = casehuifuservice;
	}


	
	
	

	@Autowired
	public void setCasenoteservice(CasenoteService casenoteservice) {
		this.casenoteservice = casenoteservice;
	}



	public Casenote getCasenote() {
		return casenote;
	}



	public void setCasenote(Casenote casenote) {
		this.casenote = casenote;
	}







	
	
	
	
}
