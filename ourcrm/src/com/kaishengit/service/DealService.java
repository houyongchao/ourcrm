package com.kaishengit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaishengit.dao.ContactDao;
import com.kaishengit.dao.DealDao;
import com.kaishengit.dao.DealnoteDao;
import com.kaishengit.dao.DealnotecommentDao;
import com.kaishengit.dao.DealreplycommentDao;
import com.kaishengit.dao.DealtypeDao;
import com.kaishengit.dao.GroupDao;
import com.kaishengit.dao.MessageDao;
import com.kaishengit.dao.TaskDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Dealnote;
import com.kaishengit.pojo.DealnoteComment;
import com.kaishengit.pojo.Dealreplycomment;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Dealtype;
import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
@Service
@Transactional
public class DealService {
	
	private DealDao dealDao;
	private DealtypeDao typeDao;
	private DealnoteDao noteDao;
	private DealnotecommentDao commentDao;
	private DealreplycommentDao replycommentDao;
	private ContactDao contactDao;
	private GroupDao groupDao;
	private UserDao userDao;
	private MessageDao msgDao;
	
	private TaskDao taskdao;
	
	
	
	
	public List<Task> findByDealId(String dealid) {
		return taskdao.finddealtaskBy(dealid);
	}
	
	
	
	
	
	
	
	public void deleteDeal(String dealid){
		Deals deals = dealDao.findByUnique("id", dealid);
		deals.setIsenable("no");
		dealDao.save(deals);
	}
	
	
	
	public void saveDeal(Deals deals){
		dealDao.save(deals);
	}
	public void saveDealtype(Dealtype dealtype){
		typeDao.save(dealtype);
	}
	public void saveDealnote(Dealnote dealnote){
		noteDao.save(dealnote);
	}
	public void savenotecomment(DealnoteComment comment){
		commentDao.save(comment);
	}
	public void savereplycomment(Dealreplycomment replycomment){
		replycommentDao.save(replycomment);
	}
	public void saveMessage(Message message){
		msgDao.save(message);
	}
	

	@Transactional(readOnly=true)
	public Contact findByContactName(String contactName){
		return contactDao.findByUnique("contactname", contactName);
	}
	@Transactional(readOnly=true)
	public List<Deals> findAll(int start){
		return dealDao.findByPageandWhere(start, 6, "isenable", "yes");
	}
	/*
	 *
	 */
	@Transactional(readOnly=true)
	public List<Dealnote> findAllnote(int start,String dealsid){
		String hql = "from Dealnote where dealsid = ? order by createtime desc ";
		return noteDao.findByHql(hql, start, 6, dealsid);
	}
	/*
	 */
	@Transactional(readOnly=true)
	public List<DealnoteComment> findAllcomment(int start,String noteid){
		String hql = "from DealnoteComment where noteid = ? order by createtime desc ";
		return commentDao.findByHql(hql, start, 6, noteid);
	}
	/*
	 *
	 */
	@Transactional(readOnly=true)
	public List<Dealreplycomment> findAllreply(int start,String commentid){
		String hql = "from Dealreplycomment where commentid = ? order by createtime desc ";
		return replycommentDao.findByHql(hql, start, 6, commentid);
	}
	
	@Transactional(readOnly=true)
	public List<Deals> findAll(){
		return dealDao.findBy("isenable", "yes");
	}
	/*
	 * 
	 */
	@Transactional(readOnly=true)
	public List<Dealnote> findAllnote(String dealid){
		String hql = "from Dealnote where dealsid = ?";
		return noteDao.findByHql(hql, dealid);
	}
	/*
	 */
	@Transactional(readOnly=true)
	public List<DealnoteComment> findAllcomment(String noteid){
		String hql = "from DealnoteComment where noteid = ?";
		return commentDao.findByHql(hql, noteid);
	}
	/*
	 * 
	 */
	@Transactional(readOnly=true)
	public List<Dealreplycomment> findAllreply(String commentid){
		String hql = "from Dealreplycomment where commentid = ?";
		return replycommentDao.findByHql(hql, commentid);
	}
	@Transactional(readOnly=true)
	public Deals findById(String dealid){
		return dealDao.findByUnique("id", dealid);
	}
	@Transactional(readOnly=true)
	public Dealnote findByIdDealnote(String dealnoteid){
		return noteDao.findByUnique("id", dealnoteid);
	}
	@Transactional(readOnly=true)
	public DealnoteComment findByIdDealnotecomment(String commentid){
		return commentDao.findByUnique("id", commentid);
		
	}
	//
	public List<Group> findAllGroup(){
		return groupDao.findAll();
	}
	public Group findGroupByName(String groupname){
		return groupDao.findByUnique("groupname", groupname);
	}
	
	public List<User> findAllUser(){
		String hql = "from User";
		return userDao.findByHql(hql);
		
	}
	
	
	//set get
	
	@Autowired
	public void setDealDao(DealDao dealDao) {
		this.dealDao = dealDao;
	}
	@Autowired
	public void setReplycommentDao(DealreplycommentDao replycommentDao) {
		this.replycommentDao = replycommentDao;
	}
	@Autowired
	public void setTypeDao(DealtypeDao typeDao) {
		this.typeDao = typeDao;
	}
	@Autowired
	public void setNoteDao(DealnoteDao noteDao) {
		this.noteDao = noteDao;
	}
	@Autowired
	public void setCommentDao(DealnotecommentDao commentDao) {
		this.commentDao = commentDao;
	}
	@Autowired
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setMsgDao(MessageDao msgDao) {
		this.msgDao = msgDao;
	}





	@Autowired
	public void setTaskdao(TaskDao taskdao) {
		this.taskdao = taskdao;
	}


	
	
	
}
