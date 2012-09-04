package com.kaishengit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaishengit.pojo.Contactnote;

@Repository
public class ContactnoteDao extends BaseDao<Contactnote,String> {

	public List<Contactnote> findAllComments(String contactid) {

		String hql = "from Contactnote where contactid = ?";
		return findByHql(hql, contactid);
	}
	


}
