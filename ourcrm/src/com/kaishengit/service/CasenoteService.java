package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.CasenoteDao;
import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Casenote;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.User;
import com.kaishengit.util.DateUtil;

@Service
@Transactional
public class CasenoteService {

	private CasenoteDao dao;
	private MessagerService messageservice;
	
	
	
	public List<Casenote> findByidList(String id) {
		return dao.findBy("cases.id", id);
	}

	
	@Autowired
	public void setDao(CasenoteDao dao) {
		this.dao = dao;
	}


	public Casenote findById(String id) {
		// TODO Auto-generated method stub
		return dao.findByUnique("case.id", id);
	}


	public void save(Casenote casesnote,User user,Case cases) {
			dao.save(casesnote);
			
			Message m = new Message();
			m.setCreatetime(DateUtil.getTime());
			m.setTitle(cases.getCasename());
			m.setType("Casenote");
			m.setUser(user);			
			m.setContent(user.getUsername() + "�����һ����Ϊ" + cases.getCasename()+ "������");
			m.setView("all");
			m.setRef(cases.getId());
			messageservice.save(m);
		
	}


	
	public Casenote findbyid(String id) {
		
		return dao.findByUnique("id", id);
	}


	@Autowired
	public void setMessageservice(MessagerService messageservice) {
		this.messageservice = messageservice;
	}

	
	
}
