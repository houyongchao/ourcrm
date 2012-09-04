package com.kaishengit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Group;

@Repository
public class ContactDao extends BaseDao<Contact, String> {
	
	public List<Contact> findContact(String userid, String groupid) {
		String hql = "from Contact where isenable = '1' and (view = 'all' or view = 'me:'+? or view = 'group:'+? or view = 'members:'+?) order by createtime desc";
		return findByHql(hql,"%"+userid+"%,","%"+groupid+"%,","%"+userid+"%");
	}

	
	public List<Contact> findAllCompanys(String userid, String groupid) {
		String hql = "from Contact where isenable = '1' and (view = 'all' or view = 'me:'+? or view = 'group:'+? or view = 'members:'+?) and contactname is null order by createtime desc";
		return findByHql(hql,"%"+userid+"%,","%"+groupid+"%,","%"+userid+"%");
	}
	public List<Contact> findAllCompany(){
		String hql = "from Contact where isenable = '1' and contactname is null order by createtime desc";
		return findByHql(hql);
	}
	
	public void deleteContact(String contactid) {

		Contact c  = get(contactid);
		if(c != null){
			c.setIsenable(false);
			save(c);
		}
		
	}

	public List<Contact> findAllContacts(String userid, String groupid) {
		String hql = "from Contact where isenable = '1' and (view = 'all' or view = 'me:'+? or view = 'group:'+? or view = 'members:'+?) and contactname is not null";
		return findByHql(hql,"%"+userid+"%,","%"+groupid+"%,","%"+userid+"%");
	}


	public List<Contact> findCreateByMe(String userid) {
		String hql = "from Contact where isenable = '1' and userid = ?";
		return findByHql(hql,userid);
	}


	public List<Contact> search(String search, String userid, String groupid) {
		String hql = "from Contact where isenable = '1' and (view = 'all' or view = 'me:'+? or view = 'group:'+? or view = 'members:'+?) and (contactname like ? or companyname like ?)";
		return findByHql(hql,"%"+userid+"%,","%"+groupid+"%,","%"+userid+"%","%"+search+"%","%"+search+"%");
	}
	
	

	
}
