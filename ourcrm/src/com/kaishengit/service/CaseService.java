package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.CaseDao;
import com.kaishengit.dao.TaskDao;
import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.util.DateUtil;

@Service
@Transactional
public class CaseService {

	private CaseDao dao;
	private MessagerService messageservice;
	private TaskDao taskdao;
	
	public List<Case> findAll() {
		return dao.findAll();
	}
	
	
	
	
	public Case findById(String id) {
		return dao.findByUnique("id", id);
	}

	
	public void save(Case cases,User user) {

		dao.save(cases);
		
		
		Message m = new Message();
		m.setCreatetime(DateUtil.getTime());
		m.setTitle(cases.getCasename());
		m.setType("casese");
		m.setUser(user);
		StringBuffer sb = new StringBuffer();
		sb.append(user.getUsername() + "添加了一个新事件" );
		m.setContent(sb.toString());
		m.setView("all");
		m.setRef(cases.getId());
		messageservice.save(m);
		
	}
	
	
	public List<Task> findByCaseId(String casesid,User user) {
		return taskdao.findByCaseId(casesid,user);
	}
	
	
	
	@Autowired
	public void setDao(CaseDao dao) {
		this.dao = dao;
	}


	@Autowired
	public void setMessageservice(MessagerService messageservice) {
		this.messageservice = messageservice;
	}


	@Autowired
	public void setTaskdao(TaskDao taskdao) {
		this.taskdao = taskdao;
	}



	
	


	




	
}
