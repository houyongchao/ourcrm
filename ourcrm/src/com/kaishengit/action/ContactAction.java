package com.kaishengit.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.persistence.NamedNativeQueries;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kaishengit.dao.Where;
import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Contactnote;
import com.kaishengit.pojo.ContactnoteDto;
import com.kaishengit.pojo.ContactnoteUtil;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.Tel;
import com.kaishengit.pojo.User;
import com.kaishengit.service.ContactService;
import com.kaishengit.service.MessagerService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Administrator
 *
 */
@Namespace("/contact")
public class ContactAction implements SessionAware{
	
	private ContactService cs;
	private MessagerService ms;
	private List<Contact> contactList;
	private Contact contact;
	private String[] telnum;
	private String[] teltype;
	private String chosegroup;
	private String members;
	private String contactid;
	private String companyname;
	private Map<String,Object> session;
	private List<Group> groupList;
	private List<User> userList;
	private List<Tel> telList;
	private List<Contactnote> contactnoteList;
	private Contactnote contactnote;
	private ContactnoteDto dto;
	private String comments;
	private User user;
	private String method;
	private String views;
	private String show;
	private File document;
	private String documentFileName;
	private String documentContentType;
	private InputStream input;
	//private List<Task> taskList;
	private List<Deals> dealList;

	private String search;
	private String specialname;
	private String replay;
	

	@Action(value = "contact",results = {@Result(name = "success",type = "dispatcher",location = "contact.jsp")})
	public String execute(){
		user = (User) session.get("user");
		if("allcontact".equals(views)){
			show = "allcontact";
		}else if("allcompany".equals(views)){
			show = "allcompany";
		}else{
			show = "all";
		}
		contactList = cs.findAll(user,views);
		return "success";
	}
	

	
	@Action(value = "search",results = {@Result(name = "success",type = "dispatcher",location = "contact.jsp")})
	public String search(){
		if(!"".equals(search)){
			user = (User) session.get("user");
			contactList = cs.search(search,user);
		}
		return "success";
	}

	@Action("findcompany")
	public String findCompanys() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		contactList = cs.findAllCompany();
		Gson g = new Gson();
		String json = g.toJson(contactList);
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();
		return null;
	}
	
	
	
	
	@Action(value = "addContact",results = {@Result(name = "success",type = "dispatcher",location = "addcontact.jsp")})
	public String savePage(){
		
		userList = cs.findAllUser();
		
		
		groupList = cs.findAllGroup();
		return "success";
	}
	
	
	@Action(value = "addCompany",results = {@Result(name = "success",type = "dispatcher",location = "addcompany.jsp")})
	public String companyPage(){
		
		userList = cs.findAllUser();
		
		
		groupList = cs.findAllGroup();
		return "success";
	}
	
	
	
	@Action(value = "saveContact",results = {@Result(name = "success",type = "redirectAction",params = {"actionName","contact"}),
											 @Result(name = "saveadd",type = "redirectAction",params={"actionName","addContact"}),
											 @Result(name = "addCompany",type = "redirectAction",params = {"actionName","addCompany"}),
											 @Result(name = "fail",type = "redirectAction",params = {"actionName","contact"})})
	public String saveContact(){
		
		user = (User) session.get("user");
		if(contact != null){
			
			contact = cs.saveContact(telnum,teltype,contact,chosegroup,members,user);
			
			ms.saveContact(contact,user);
			cs.geterweima(contact);
			
			if("保存此联系人".equals(method)){
				//保存联系人
				return "success";
			}else if("保存并添加联系人".equals(method)){
				return "saveadd";
			}else if("保存此公司".equals(method)){
				return "success";
			}else if("保存并添加公司".equals(method)){
				return "addCompany";
			}else if("edit".equals(method)){
				return "success";
			}else{
				return "success";
			}
			}else{
				return "fail";
			}
	}
	
	

	@Action(value = "addMany",results = {@Result(name = "success",type = "dispatcher",location = "addMany.jsp")})
	public String addMany(){
		return "success";
	}
	
	
	@Action(value = "showPerson",results = {@Result(name = "success",type = "dispatcher",location = "showpersons.jsp"),
			@Result(name = "fail",type = "redirectAction",params = {"actionName","contact"})})
	public String showPerson(){
		contact = cs.findById(contactid);
		if(contact != null){
		
			if(contact.getTels() != null && !"".equals(contact.getTels())){
				telList = cs.spilt(contact);
				session.put("tel", telList);
			}
			
			contactnoteList = cs.findAllComments(contactid);
			if(contactnoteList != null){
				session.put("comments", contactnoteList);
			}

			
			

			
			
		
			List<Task> taskcontactlist = cs.findByContactId(contact.getId());
			session.put("contacttasklist", taskcontactlist);
			
		
			
			
			session.put("contact", contact);
			
			return "success";
		}else{
			return "fail";
		}
		
	}
	
	
	@Action(value = "editContact",results = {@Result(name = "editPerson",type = "dispatcher",location = "editcontact.jsp"),
											 @Result(name = "editCompany",type = "dispatcher",location = "editcompany.jsp")})
	public String editPage(){
		contact = (Contact) session.get("contact");
		user = (User) session.get("user");
		
		String me = "me:"+user.getId();
		if(me.equals(contact.getView())){
			contact.setView("my");
		}

	
		groupList = cs.findAllGroup();
		
		
		userList = cs.findAllUser();
		
		
		telList = (List<Tel>) session.get("tel");
		
		if(contact.getContactname() != null){
			
			String comid = contact.getCompanyid();
			if(comid != null){
				Contact company = cs.findById(comid);
				if(company != null){
					contact.setCompanyname(company.getCompanyname());
				}
			}
			return "editPerson";
		}else{
			
			return "editCompany";
		}
	}
	

	@Action(value = "deleteContact",results = {@Result(name = "success",type = "redirectAction",params={"actionName","contact"})})
	public String del(){
		cs.deleteContact(contactid);
		return "success";
	}
	

	@Action(value = "company")
	public String company() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");///????
		PrintWriter pw = response.getWriter();
		contact = cs.findCompanyName(companyname);
		if(contact == null){
			pw.print("0");
		}else{
			System.out.println(contact.getCompanyname());
			pw.print("1");
		}
		pw.flush();
		pw.close();
		return null;
	}
	

	@Action("comments")
	public String comments() throws IOException{
		contact = (Contact) session.get("contact");
		User user = (User) session.get("user");
		dto = cs.saveContactnote(contactnote,contact,user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		Gson g = new Gson();
		String json = g.toJson(dto);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		return null;
	}
	

	@Action(value = "replay")
	public String replay(){
		System.out.println(replay);
		return null;
	}
	


	
	
	
	@Action(value = "upload",results = {@Result(name = "success",type = "redirectAction",params = {"actionName","contact"})})
	public String upload() throws FileNotFoundException, IOException{
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(document));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:/upload/"+documentFileName));
			byte[] buffer = new byte[512];
			int len = -1;
			try {
				while((len = bis.read(buffer)) != -1){
					bos.write(buffer, 0, len);
				}
				bos.flush();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		user = (User) session.get("user");
		cs.saveMany(documentFileName,user);
		return "success";
	}
	
	
	//set get
	@Autowired
	public void setCs(ContactService cs) {
		this.cs = cs;
	}
	public List<Contact> getContactList() {
		return contactList;
	}
	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String[] getTelnum() {
		return telnum;
	}

	public void setTelnum(String[] telnum) {
		this.telnum = telnum;
	}

	public String[] getTeltype() {
		return teltype;
	}

	public void setTeltype(String[] teltype) {
		this.teltype = teltype;
	}

	public String getContactid() {
		return contactid;
	}

	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public void setChosegroup(String chosegroup) {
		this.chosegroup = chosegroup;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public List<Tel> getTelList() {
		return telList;
	}

	public void setTelList(List<Tel> telList) {
		this.telList = telList;
	}

	public List<Contactnote> getContactnoteList() {
		return contactnoteList;
	}

	public void setContactnoteList(List<Contactnote> contactnoteList) {
		this.contactnoteList = contactnoteList;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public void setContactnote(Contactnote contactnote) {
		this.contactnote = contactnote;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	
	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public File getDocument() {
		return document;
	}

	public void setDocument(File document) {
		this.document = document;
	}

	public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	public String getDocumentContentType() {
		return documentContentType;
	}

	public void setDocumentContentType(String documentContentType) {
		this.documentContentType = documentContentType;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	

	public List<Deals> getDealList() {
		return dealList;
	}

	public void setDealList(List<Deals> dealList) {
		this.dealList = dealList;
	}

	@Autowired
	public void setMs(MessagerService ms) {
		this.ms = ms;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSpecialname() {
		return specialname;
	}

	public void setSpecialname(String specialname) {
		this.specialname = specialname;
	}

	public String getReplay() {
		return replay;
	}

	public void setReplay(String replay) {
		this.replay = replay;
	}

	
	
	
	

	

	
	
	



	
	
	
	
	
	
	
	
}
