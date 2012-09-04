package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.ContactDao;
import com.kaishengit.dao.MessageDao;
import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.User;
import com.kaishengit.util.Pager;

@Service
@Transactional
public class MessagerService {

	private MessageDao dao;
	private ContactDao cdao;
	
	public void save(Message m) {

		dao.save(m);
	}
	
	
	
	//get set 
	@Autowired
	public void setDao(MessageDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setCdao(ContactDao cdao) {
		this.cdao = cdao;
	}




	public void saveCompany(Contact c, User user) {
		Message m = new Message();
		m.setUser(user);
		m.setContent(c.getDescs());
		m.setCreatetime(c.getCreatetime());
		m.setType("company");
		m.setTitle(c.getCompanyname());
		m.setView(c.getView());
		m.setRef(c.getId());
		dao.save(m);
		
	}


	public List<Message> findAll(User user,int start,int per) {
		Group group = user.getGroup();
		String groupid = null;
		String groupname = null;
		if(group != null){
			groupid = group.getId();
			groupname = group.getGroupname();
		}
		return dao.findAllMessage(user.getId(),groupid,start,per,user.getUsername(),groupname);
	}

	@Transactional(readOnly=true)
	public List<Message> findAllMessages(String title){
		return dao.findAllmsg(title);
	}



	public void saveContact(Contact contact, User user) {
		if(contact != null){
			Message m = new Message();
			m.setContent(contact.getDescs());
			m.setCreatetime(contact.getCreatetime());
			m.setUser(user);
			m.setView(contact.getView());
			m.setRef(contact.getId());
			
			if(!"".equals(contact.getCompanyname()) && contact.getCompanyname() != null){
			
				m.setType("company");
				m.setTitle(contact.getCompanyname());
			}else{
				m.setTitle(contact.getContactname());
				m.setType("contact");
			}
			dao.save(m);
		}
		
		
	}



	public int getCount() {
		return dao.getTotalCount();
	}



	public int findCount() {
		return dao.getTotalCount();
	}

	
	
	

}
