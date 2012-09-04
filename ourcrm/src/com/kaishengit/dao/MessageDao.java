package com.kaishengit.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.kaishengit.pojo.Message;
import com.kaishengit.util.Pager;

@Repository
public class MessageDao extends BaseDao<Message, String> {
	private HibernateTemplate template;

	public List<Message> findAllMessage(String userid,String groupid,int startNum,int perSize, String username, String groupname) {
		//Pager pager = new Pager(getTotalCount(), perSize, startNum);
		String hql = "from Message  where view = 'all' or view = 'me:'+? or view = 'group:'+? or view = 'members:'+? or view = 'group:'+? or view = ? order by createtime desc";
		List<Message> list =  findByHqlMe(hql,startNum,perSize,"%"+userid+"%,","%"+groupid+"%,","%"+userid+"%","%"+groupname+"%",username);
		//pager.setResult(list);
		return list;
	}
	public List<Message> findAllmsg(String title) {
		return findByContent("title", title);
	}
	
	public int getTotalCount(){
		String hql = "from Message where view = 'all'";
		return template.find(hql).size();
         
    }
	
	public List<Message> findByHqlMe(String hql,int start,int perNum, Object... args) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(perNum);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query.list();
	}
	
	@Autowired
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}  
	
	
	

}
