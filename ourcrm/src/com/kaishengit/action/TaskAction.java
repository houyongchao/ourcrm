package com.kaishengit.action;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.pojo.Case;
import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.Tasktype;
import com.kaishengit.pojo.User;
import com.kaishengit.service.CaseService;
import com.kaishengit.service.ContactService;
import com.kaishengit.service.DealService;
import com.kaishengit.service.TaskService;
import com.kaishengit.service.TasktypeService;
import com.kaishengit.util.DateUtil;

@Namespace("/task")
public class TaskAction implements SessionAware{

	private Map<String, Object> session;
	private Task task;	
	private List<Task> taskListUser;
	//private List<Task> taskListContact;
	//private List<Task> taskListDeals;
	private List<Tasktype> tasktypeList;
	private TaskService taskservice;
	private TasktypeService tasktypeservice;	
	private String quan;
	private String id;
	private Tasktype tasktype;
	private Tasktype t1;
	private Tasktype t2;
	private Tasktype t3;
	
	
	
	//下面的属性是存储Case , Deals , Contact的时候用到的；
	
	//private String dealsid;
//	private Deals deals;
	
	private CaseService caseservice;
	private DealService dealservice;
	private ContactService contactservice;
	/**
	 * 显示任务
	 * @return
	 */
	public String execute(){

		User u = (User) session.get("user");		
		
		taskListUser = taskservice.findAll(u);
		
		return "success";
	}

	
	
	
	/**
	 * location="miaoshu.action?id=${casesid }"
	 * 保存一个事件的任务
	 * params={"actionName","case","namespace","/case"}
	 * @return
	 */
	@Action(value="addcasetask",results={
			@Result(name="success",type="redirectAction",params={"actionName","case","namespace","/case"})
		})	
	public String addcasetask(){
		
		
		
		String casesid =(String) session.get("caseid");
		System.out.println(casesid);
		
		
		Case  cases = caseservice.findById(casesid);
		System.out.println("aa");
		task.setCases(cases);
		System.out.println(cases);
		Tasktype t = tasktypeservice.findByN(tasktype.getType());
		 		 
		   if(t == null) {
			   HttpServletResponse response = ServletActionContext.getResponse();
			   response.setCharacterEncoding("UTF-8");
	         String id = tasktypeservice.save(tasktype);
	         tasktype.setId(id);
			} else {
	         tasktype.setId(t.getId());
			}
	  
		task.setTasktype(tasktype);
		
		
		User user = (User)session.get("user");
		task.setUser(user);	
		
		
		String qx = task.getQuanxian();
		
		if (qx.contains("all")) {
			task.setQuanxian("all");
		} else if(qx.contains("me")) {
			task.setQuanxian(user.getUsername());
		} else {
			String[] a = qx.split(",");
			String gname = a[1];
			System.out.println(gname.trim());
			task.setQuanxian("group:" + gname.trim());
		}
		
		task.setCreatetime(DateUtil.getTime());
		taskservice.save(task,user);
		
		System.out.println("------------------");
		System.out.println("save case task");
		System.out.println("------------------");
		
		
	
		/*System.out.println(casesid);
		System.out.println("这个集合执行了....");
		List<Task> taskcaselist = caseservice.findByCaseId(casesid,user);
		session.put("casetasklist", taskcaselist);
		System.out.println(taskcaselist);
		System.out.println("----------------");*/
		
		
		
		return "success";
	}
	
	
	
	
	/**
	 * location="miaoshu.action?id=${casesid }"
	 * 保存一个联系人的任务
	 * @return
	 */
	@Action(value="addcontacttask",results={
			@Result(name="success",type="redirectAction",params={"actionName","contact","namespace","/contact"})
		})	
	public String addcontacttask(){
		
		
		//接受啊神传session传过来联系人；
		Contact c = (Contact) session.get("contact");
		
		System.out.println(c);
		
		task.setContact(c);
		
		Tasktype t = tasktypeservice.findByN(tasktype.getType());
		 		 
		   if(t == null) {
			   HttpServletResponse response = ServletActionContext.getResponse();
			   response.setCharacterEncoding("UTF-8");
	         String id = tasktypeservice.save(tasktype);
	         tasktype.setId(id);
			} else {
	         tasktype.setId(t.getId());
			}
	  
		task.setTasktype(tasktype);
		
		
		User user = (User)session.get("user");
		task.setUser(user);	
		
		
		String qx = task.getQuanxian();
		
		if (qx.contains("all")) {
			task.setQuanxian("all");
		} else if(qx.contains("me")) {
			task.setQuanxian(user.getUsername());
		} else {
			String[] a = qx.split(",");
			String gname = a[1];
			System.out.println(gname.trim());
			task.setQuanxian("group:" + gname.trim());
		}
		
		task.setCreatetime(DateUtil.getTime());
		taskservice.save(task,user);
		
		
		
		System.out.println("------------------");
		System.out.println("save case task");
		System.out.println("------------------");
		
		
		/*//这一段需要写在contact。action中
		System.out.println(c.getId());
		System.out.println("这个集合执行了....");
		List<Task> taskcontactlist = contactservice.findByContactId(c.getId());
		session.put("contacttasklist", taskcontactlist);
		System.out.println(taskcontactlist);
		System.out.println("----------------");*/
		
		
		
		return "success";
	}
	
	

	
	
	/**
	 * location="miaoshu.action?id=${casesid }"
	 * 保存一个交易的任务
	 * @return
	 */
	@Action(value="adddealstask",results={
			@Result(name="success",type="redirectAction",params={"actionName","deal","namespace","/deal"})
		})	
	public String adddealstask(){
		
		
		
		String dealid =(String) session.get("dealid");
		System.out.println(dealid);
		
		
		Deals  deal = dealservice.findById(dealid);
		
		System.out.println(deal);
		task.setDeals(deal);
		Tasktype t = tasktypeservice.findByN(tasktype.getType());
		 		 
		   if(t == null) {
			   HttpServletResponse response = ServletActionContext.getResponse();
			   response.setCharacterEncoding("UTF-8");
	         String id = tasktypeservice.save(tasktype);
	         tasktype.setId(id);
			} else {
	         tasktype.setId(t.getId());
			}
	  
		task.setTasktype(tasktype);
		
		
		User user = (User)session.get("user");
		task.setUser(user);	
		
		
		String qx = task.getQuanxian();
		
		if (qx.contains("all")) {
			task.setQuanxian("all");
		} else if(qx.contains("me")) {
			task.setQuanxian(user.getUsername());
		} else {
			String[] a = qx.split(",");
			String gname = a[1];
			System.out.println(gname.trim());
			task.setQuanxian("group:" + gname.trim());
		}
		
		task.setCreatetime(DateUtil.getTime());
		taskservice.save(task,user);
		
		System.out.println("------------------");
		System.out.println("save case task");
		System.out.println("------------------");
		
		
		/*//这一段须写在deal.action中；
		System.out.println(dealid);
		System.out.println("这个集合执行了....");
		List<Task> taskdeallist = dealservice.findByDealId(dealid);
		session.put("dealtasklist", taskdeallist);
		System.out.println(taskdeallist);
		System.out.println("----------------");*/
		
		
		
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//base.jsp弹框添加新任务
	@Action(value="addtask",results={
			@Result(name="success",type="redirectAction",params={"actionName","task","namespace","/task"})
		})	
	public String add(){
		
		Tasktype t = tasktypeservice.findByN(tasktype.getType());
		 		 
		   if(t == null) {
			   HttpServletResponse response = ServletActionContext.getResponse();
			   response.setCharacterEncoding("UTF-8");
	         String id = tasktypeservice.save(tasktype);
	         tasktype.setId(id);
			} else {
	         tasktype.setId(t.getId());
			}
	  
		task.setTasktype(tasktype);
		
		
		User user = (User)session.get("user");
		task.setUser(user);	
		
		
		String qx = task.getQuanxian();
		
		if (qx.contains("all")) {
			task.setQuanxian("all");
		} else if(qx.contains("me")) {
			task.setQuanxian(user.getUsername());
		} else {
			String[] a = qx.split(",");
			String gname = a[1];
			System.out.println(gname.trim());
			task.setQuanxian("group:" + gname.trim());
		}
		
		task.setCreatetime(DateUtil.getTime());
		taskservice.save(task,user);
		return "success";
	}
	
	
	
	
	
	

	
	//请求转发跳转到修改任务的页面；
	@Action(value="edit",results={@Result(name="success",type="dispatcher",location="/WEB-INF/content/task/edit.jsp")})
	public String edit(){
		List<Group> glist = taskservice.findAllGroup();
		session.put("glist", glist);
		task = taskservice.findById(id);		
		return "success";
	}
	
	
	/**
	 * 修改后更新任务
	 * @return
	 */
	@Action(value="update",results={@Result(name="success",type="redirectAction",params={"actionName","task","namespace","/task"})})
	public String update(){
		

		 Tasktype t = tasktypeservice.findByN(tasktype.getType());
		
		 if(t == null) {
			   HttpServletResponse response = ServletActionContext.getResponse();
			   response.setCharacterEncoding("UTF-8");
			  
              String id = tasktypeservice.save(tasktype);
              tasktype.setId(id);
      } else {
    	 
              tasktype.setId(t.getId());
      }
		
		task.setTasktype(tasktype);		
		User user = (User)session.get("user");
		
		String qx = task.getQuanxian();
		System.out.println(qx);
		if (qx.contains("all")) {
			task.setQuanxian("all");
		} else if(qx.contains("me")) {
			task.setQuanxian(user.getUsername());
		} else {
			String[] a = qx.split(",");
			String gname = a[1];
			System.out.println(gname.trim());
			task.setQuanxian("group:" + gname.trim());
		}
		
		task.setUser(user);					
		taskservice.update(task,user);
		return "success";
	}
	
	
	
	/**
	 * 更新颜色；
	 * @return
	 */
	@Action(value="tocolor",results={@Result(name="success",type="redirect",location="/color.jsp")})
	public String tocolor(){
		tasktypeList = tasktypeservice.findAll();
		
		Tasktype t1 = tasktypeList.get(0);
		Tasktype t2 = tasktypeList.get(1);
		Tasktype t3 = tasktypeList.get(2);				
		session.put("t1", t1);
		session.put("t2", t2);
		session.put("t3", t3);
		return "success";
	}
	

	/**
	 * 更新颜色；
	 * @return
	 */
	@Action(value="changecolor",results={@Result(name="success",type="redirectAction",params={"actionName","task","namespace","/task"})})
	public String changecolor(){
		tasktypeservice.update(t1);
	
		tasktypeservice.update(t3);
		
		tasktypeservice.update(t2);
		return "success";
	}
	


	/**
	 * 删除任务；
	 * @return
	 */
	@Action(value="del",results={@Result(name="success",type="redirectAction",params={"actionName","task","namespace","/task"})})
	public String del(){
		
		
         User user = (User)session.get("user");
         Task t = taskservice.findById(id);
         t.setQuanxian(null);
         taskservice.del(id,user,t);
         
		return "success";
	}
	
	
	
	//get set 

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Task getTask() {
		return task;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTaskListUser() {
		return taskListUser;
	}

	public void setTaskListUser(List<Task> taskListUser) {
		this.taskListUser = taskListUser;
	}

	public TaskService getTaskservice() {
		return taskservice;
	}

	@Autowired
	public void setTaskservice(TaskService taskservice) {
		this.taskservice = taskservice;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<Tasktype> getTasktypeList() {
		return tasktypeList;
	}


	public void setTasktypeList(List<Tasktype> tasktypeList) {
		this.tasktypeList = tasktypeList;
	}


	public Tasktype getTasktype() {
		return tasktype;
	}


	public void setTasktype(Tasktype tasktype) {
		this.tasktype = tasktype;
	}




	public TasktypeService getTasktypeservice() {
		return tasktypeservice;
	}

	@Autowired
	public void setTasktypeservice(TasktypeService tasktypeservice) {
		this.tasktypeservice = tasktypeservice;
	}





	public String getQuan() {
		return quan;
	}


	public void setQuan(String quan) {
		this.quan = quan;
	}


	public Tasktype getT1() {
		return t1;
	}


	public void setT1(Tasktype t1) {
		this.t1 = t1;
	}


	public Tasktype getT2() {
		return t2;
	}


	public void setT2(Tasktype t2) {
		this.t2 = t2;
	}


	public Tasktype getT3() {
		return t3;
	}


	public void setT3(Tasktype t3) {
		this.t3 = t3;
	}





	@Autowired
	public void setCaseservice(CaseService caseservice) {
		this.caseservice = caseservice;
	}



	@Autowired
	public void setDealservice(DealService dealservice) {
		this.dealservice = dealservice;
	}



	@Autowired
	public void setContactservice(ContactService contactservice) {
		this.contactservice = contactservice;
	}


	

}
