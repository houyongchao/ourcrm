package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.CaseDao;
import com.kaishengit.dao.DealDao;
import com.kaishengit.dao.GroupDao;
import com.kaishengit.dao.TaskDao;
import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.Task;

import com.kaishengit.pojo.User;
import com.kaishengit.util.DateUtil;

@Service
@Transactional
public class TaskService {

	private TaskDao taskDao;
	private MessagerService messageservice;
	private GroupDao groupdao;
	private DealDao dealdao;
	private CaseDao casedao;
	
	public List<Task> findAll(User u) {
		return taskDao.findUserBy(u);
	}

	

	
	

	
	
	
	public void save(Task task,User user) {
		
		taskDao.save(task);
		
		Message m = new Message();
		m.setCreatetime(DateUtil.getTime());
		m.setTitle(task.getTaskname());
		m.setType("task");
		m.setUser(user);		
		m.setContent(user.getUsername() + "添加了一个名为" + task.getTaskname() + "的任务");;
		m.setView(task.getQuanxian());
		
		messageservice.save(m);
	}
	
	
	public Task findById(String id) {
		return taskDao.findByUnique("id", id);
	}
	
	
	
	public void update(Task task,User user) {
		
		taskDao.save(task);
		
		Message m = new Message();
		m.setCreatetime(DateUtil.getTime());
		m.setTitle(task.getTaskname());
		m.setType("task");
		m.setUser(user);
		m.setContent(user.getUsername() + "修改了名为" + task.getTaskname() + "的任务");
		m.setView(task.getQuanxian());
		messageservice.save(m);
		
		
	}
	
	
	

	
	
	public Task findById(User user) {
		return taskDao.findByUnique("user.id", user.getId());
	}
	
	
	public void del(String id, User user, Task t) {
		
		
		taskDao.save(t);
		
		Message m = new Message();
		m.setCreatetime(DateUtil.getTime());
		m.setTitle(t.getTaskname());
		m.setType("task");
		m.setUser(user);
		m.setContent(user.getUsername() + "删除了一个名为" + t.getTaskname() + "的任务");
		m.setView(t.getQuanxian());
		messageservice.save(m);
		
		
	}
	
	
	
	
	public List<Group> findAllGroup() {
		return groupdao.findAll();
	}

	
	
	public Deals findByDealsId(String dealsid) {
		return dealdao.findByUnique("deals.id", dealsid);
	}
	
	
	//get set 

	@Autowired	
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}


	@Autowired
	public void setMessageservice(MessagerService messageservice) {
		this.messageservice = messageservice;
	}


	@Autowired
	public void setGroupdao(GroupDao groupdao) {
		this.groupdao = groupdao;
	}

	@Autowired
	public void setDealdao(DealDao dealdao) {
		this.dealdao = dealdao;
	}



	@Autowired
	public void setCasedao(CaseDao casedao) {
		this.casedao = casedao;
	}




	














	




	
	
}
