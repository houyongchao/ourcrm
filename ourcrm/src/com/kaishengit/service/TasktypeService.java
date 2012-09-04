package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.TasktypeDao;
import com.kaishengit.pojo.Tasktype;

@Service
@Transactional
public class TasktypeService {

	private TasktypeDao tasktypedao;
	
	public List<Tasktype> findAll() {
		return tasktypedao.findAll();
	}

	
	public TasktypeDao getTasktypedao() {
		return tasktypedao;
	}

	@Autowired
	public void setTasktypedao(TasktypeDao tasktypedao) {
		this.tasktypedao = tasktypedao;
	}


	public Tasktype findByName(String taskname) {
		return tasktypedao.findByUnique("type", taskname);
	}


	


	public String save(Tasktype tasktype) {
		
		tasktypedao.save(tasktype);
		return tasktype.getId();
	}


	public Tasktype findByN(String tasktypen) {
		return tasktypedao.findByUnique("type", tasktypen);
	}


	public void update(Tasktype t) {
		tasktypedao.save(t);
		
	}






	
	
	
}
