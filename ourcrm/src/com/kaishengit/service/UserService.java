package com.kaishengit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;

@Service
@Transactional
public class UserService {

	private UserDao dao;
	
	public User findUser(String username){
		return dao.findByUnique("username", username);
	}
	
	/**
	 * 
	 * @param user
	 * @return 
	 */
	public User login(User user) {
		User u = dao.findByUnique("username", user.getUsername());
		if (u != null && u.getPassword().equals(user.getPassword())) {
			return u;
		} else {
			
			return null;
		}
	}

	public UserDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public void saveOrUpdate(User user) {

		dao.save(user);
	}


	
	
}
