package com.kaishengit.dao;

import java.util.List;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;

@Repository
public class TaskDao extends BaseDao<Task, String>{


	

	public List<Task> findUserBy(User u) {
		Query query = getSession().createQuery("from Task as t where (t.quanxian like :all or t.quanxian like :me or t.quanxian like :groupname) and userid like :id order by t.enddate");
		
		
		query.setParameter("all", "a%");
		query.setParameter("me", u.getUsername());
		if (u.getGroup().getGroupname() == null) {
			
			query.setParameter("groupname", "4%");
			
		} else {
			query.setParameter("groupname", "%" +  u.getGroup().getGroupname() + "%");
		}
		query.setParameter("id", "%" + u.getId() + "%");
		return query.list();
	}


	public List<Task> findByCaseId(String casesid,User u) {
		System.out.println("这个HQL执行了。。。。。。。");
		Query query = getSession().createQuery("from Task where caseid = :caseid");		
		System.out.println(casesid);
		query.setParameter("caseid", casesid);
		return query.list();
	}

	public List<Task> findlisttask(String id) {
		
		System.out.println("这个HQL执行了。。。。。。。");
		Query query = getSession().createQuery("from Task where contactid = :contactid");
		System.out.println(id);
		query.setParameter("contactid", id);
		return query.list();
	}

	public List<Task> finddealtaskBy(String dealid) {
		
		System.out.println("这个HQL执行了。。。。。。。");
		Query query = getSession().createQuery("from Task where dealsid = :dealsid");
		System.out.println(dealid);
		query.setParameter("dealsid", dealid);
		return query.list();
	}


	
	
	
	
	
}
