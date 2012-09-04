package com.kaishengit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.CasehuifuDao;
import com.kaishengit.pojo.Casehuifu;

@Service
@Transactional
public class CasehuifuService {

	private CasehuifuDao dao ;

	
	
	

	public List<Casehuifu> findById(String id) {
		return dao.findBy("casenote.id", id);
	}

	
		public void save(Casehuifu casehuifu) {
		
				dao.save(casehuifu);
			}

	
	
	
	@Autowired
	public void setDao(CasehuifuDao dao) {
		this.dao = dao;
	}




	

	
	
	
}
