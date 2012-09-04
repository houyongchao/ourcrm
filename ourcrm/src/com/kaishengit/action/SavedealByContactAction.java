package com.kaishengit.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Dealtype;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.User;
import com.kaishengit.service.DealService;
import com.kaishengit.util.DateUtil;

@Namespace("/deal")
public class SavedealByContactAction implements SessionAware {
	private Map<String,Object> session;
	private DealService service;
	private Deals deals;
	
	@Action(value="savedealbycontact",results={@Result(name="showPerson",type="redirectAction",params={"actionName","contact","namespace","/contact"})})
	public String savedeal(){
		
		Dealtype dealtype = deals.getDealtype();
		service.saveDealtype(dealtype);
		User user = (User) session.get("user");
		
		String views = deals.getView();
		if(views.equals("all")){
			deals.setView(views);
		}else if(views.equals("me")){
			deals.setView(user.getUsername());
		}else if(views.contains("group")) {
			String str = views.replaceFirst(",", ":");
			deals.setView(str);
		}else if(views.contains("user")){
			String str = views.replaceFirst(",", ":");
			deals.setView(str);
		}
		deals.setUser(user);
		deals.setCreatetime(DateUtil.getTime());
		deals.setIsenable("yes");
		
		Contact c = (Contact) session.get("contact");
		if(c != null){
			deals.setContact(c);
		}
		service.saveDeal(deals);
		
		Message msg = new Message();
		msg.setContent(deals.getDescs());
		msg.setCreatetime(deals.getCreatetime());
		msg.setTitle(deals.getDealsname());
		msg.setType("deals");
		msg.setUser(user);
		msg.setView(deals.getView());
		msg.setRef(deals.getId());
		service.saveMessage(msg);
		
		
		return "showPerson";
		
	}
	
	//set get
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Autowired
	public void setService(DealService service) {
		this.service = service;
	}
	public Deals getDeals() {
		return deals;
	}
	public void setDeals(Deals deals) {
		this.deals = deals;
	}
}
