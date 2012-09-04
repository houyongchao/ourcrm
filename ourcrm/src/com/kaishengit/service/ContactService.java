package com.kaishengit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kaishengit.dao.ContactDao;
import com.kaishengit.dao.ContactnoteDao;
import com.kaishengit.dao.DealDao;
import com.kaishengit.dao.GroupDao;
import com.kaishengit.dao.TaskDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.Where;
import com.kaishengit.pojo.Contact;
import com.kaishengit.pojo.Contactnote;
import com.kaishengit.pojo.ContactnoteDto;
import com.kaishengit.pojo.Deals;
import com.kaishengit.pojo.Group;
import com.kaishengit.pojo.Message;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.Tel;
import com.kaishengit.pojo.User;
import com.kaishengit.util.DateUtil;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

@Service
@Transactional
public class ContactService {
	
	private ContactDao dao;
	private UserDao udao;
	private GroupDao gdao;
	private ContactnoteDao cnoteDao;
	private Contactnote con;
	private boolean flag = true;
	private Message message;

	private TaskDao taskdao;
	
	
	
	
	private DealDao dealDao;
	private MessagerService ms;
	
	
	public List<Task> findByContactId(String id) {
		return taskdao.findlisttask(id);
	}
	

	
	
	
	@Autowired
	public void setTaskdao(TaskDao taskdao) {
		this.taskdao = taskdao;
	}




	public List<Contact> findAll(User user, String views) {
	
		Group group = user.getGroup();
		String groupid = null;
		if(group != null){
			groupid = group.getId();
		}
		if("allcompany".equals(views)){
			return dao.findAllCompanys(user.getId(),groupid);
		}else if("allcontact".equals(views)){
			return dao.findAllContacts(user.getId(),groupid);
		}else if("me".equals(views)){
			return dao.findCreateByMe(user.getId());
		}else{
			return dao.findContact(user.getId(), groupid);
		}
		
		 
	}
	

	public List<Contact> findAllCompany() {
		return dao.findAllCompany();
		
	}

	
	
	//set
	@Autowired
	public void setDao(ContactDao dao) {
		this.dao = dao;
	}

	
	@Autowired
	public void setUdao(UserDao udao) {
			this.udao = udao;
	}
	

	@Autowired
	public void setGdao(GroupDao gdao) {
		this.gdao = gdao;
	}
	

	@Autowired
	public void setCnoteDao(ContactnoteDao cnoteDao) {
		this.cnoteDao = cnoteDao;
	}
	
	
	
	

	@Autowired
	public void setDealDao(DealDao dealDao) {
		this.dealDao = dealDao;
	}

	@Autowired
	public void setMs(MessagerService ms) {
		this.ms = ms;
	}

	///
	public List<Contact> findAllByWheres(List<Where> list) {
		
		return dao.findByWheres(list);
	}

	//锟斤拷锟斤拷锟斤拷系锟斤拷
	public Contact saveContact(String[] telnum, String[] teltype, Contact contact, String chosegroup, String members, User user) {

	

		StringBuffer telbuffer = new StringBuffer();
		if(telnum != null){
			for (int i = 0; i < telnum.length; i++) {
				String tel = telnum[i];
				telbuffer.append(tel);
				telbuffer.append(",");
			}
		}
		StringBuffer teltypebuffer = new StringBuffer();
		if(teltype != null ){
			for (int i = 0; i < teltype.length; i++) {
				teltypebuffer.append(teltype[i]);
				teltypebuffer.append(",");
			}
		}
		contact.setType(teltypebuffer.toString());
		contact.setTels(telbuffer.toString());

		contact.setIsenable(true);
		contact.setCreatetime(DateUtil.getYMD());

		contact.setUser(user);
		//锟叫讹拷权锟斤拷

		if("me".equals(contact.getView())){
			contact.setView("me:"+ user.getId());
		}else if("group".equals(contact.getView())){
			contact.setView("group:"+chosegroup);
		}else if("specialmember".equals(contact.getView())){
			contact.setView("members:"+members);
		}
		
		if(!"".equals(contact.getCompanyname()) && contact.getCompanyname() != null){
			//锟斤拷询锟斤拷锟斤拷锟叫的癸拷司锟斤拷锟斤拷匹锟斤拷
			if(contact.getContactname() != null && !"".equals(contact.getCompanyname())){
				List<Contact> contactList = findAllCompany();
				if(contactList != null){
					for(Contact con:contactList){
						if(con.getCompanyname().equalsIgnoreCase(contact.getCompanyname())){
							contact.setCompanyid(con.getId());
							contact.setCompanyname(null);
							dao.save(contact);
							
							flag = false;
						}
					}
				}
			}	
			
			//锟斤拷锟斤拷庸锟剿撅拷慕锟斤拷锟斤拷锟结交锟侥憋拷锟斤拷
			if(contact.getContactname() == null || "".equals(contact.getContactname())){
				flag = false;
				addCompany(contact);
			}
			
			//锟斤拷锟斤拷锟斤拷系锟斤拷时锟饺憋拷锟芥公司
			if(flag){

				//锟斤拷锟芥公司
				
				saveCompany(contact.getCompanyname(),contact.getUser());
			
				Contact c = dao.findByUnique("companyname",contact.getCompanyname());
				contact.setCompanyid(c.getId());
				//锟斤拷锟斤拷锟斤拷系锟斤拷
				contact.setCompanyname("");
				dao.save(contact);
				
				//锟斤拷锟芥到锟斤拷息锟斤拷锟斤拷一锟斤拷
				ms.saveCompany(c,user);
				geterweima(c);
				
			}
			
		}else{
			
			dao.save(contact);
		}
		return contact;
	}

	//鑾峰彇浜岀淮鐮�
	public void geterweima(Contact contact) {
		
		String conString = contact.getAddress()+contact.getCompanyname()+contact.getContactname()+contact.getDescs()+contact.getEmail()+contact.getPosition()+contact.getQq()+contact.getWebsites()+contact.getTels();
		//String paths = getClass().getResource("/").getFile();
		//String p = paths.substring(1, paths.indexOf("/", 6));
		//System.out.println(p);
		try {  
            String str =  conString;
            String path = "C:\\server\\apache-tomcat-7.0.21\\webapps\\ourcrm\\img\\"+contact.getId()+".png";
            BitMatrix byteMatrix;  
            byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("GBK"),"iso-8859-1"),  
                    BarcodeFormat.QR_CODE, 200, 200);  
            File file = new File(path);  
              
            MatrixToImageWriter.writeToFile(byteMatrix, "png", file);  
                  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

	//锟斤拷锟斤拷锟斤拷锟叫的癸拷司锟斤拷锟斤拷匹锟戒，锟斤拷锟斤拷锟斤拷锟较碉拷锟绞憋拷锟斤拷锟侥癸拷司锟角凤拷锟窖撅拷锟斤拷锟斤拷

	public void compareCompany(Contact contact){
		
	}
	
	//锟斤拷庸锟剿�
	private void addCompany(Contact contact) {
		dao.save(contact);
	}

	//锟斤拷锟斤拷锟较碉拷锟绞憋拷锟斤拷婀�
	private void saveCompany(String companyname, User user) {

		Contact c = new Contact();
		c.setCompanyname(companyname);
		c.setUser(user);
		c.setView("all");
		c.setIsenable(true);
		c.setCreatetime(DateUtil.getYMD());
		dao.save(c);
	}


	//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷系锟斤拷
	public Contact findById(String contactid) {
		return dao.get(contactid);
	}

	//锟斤拷锟斤拷锟斤拷锟叫碉拷锟矫伙拷
	public List<User> findAllUser() {
		return udao.findAll(); 
	}

	//锟斤拷锟斤拷锟斤拷锟叫凤拷锟斤拷
	public List<Group> findAllGroup() {

		return gdao.findAll();
	}
	//删锟斤拷锟斤拷系锟斤拷
	public void deleteContact(String contactid) {
		
		dao.deleteContact(contactid);
	}

	//锟斤拷取锟界话
	public List<Tel> spilt(Contact contact) {
		List<Tel> telList = new ArrayList<Tel>();
		String[] tel = contact.getTels().split(",");
		String[] telType = contact.getType().split(",");
		
		for (int i = 0; i < telType.length; i++) {
			for (int j = 0; j < telType.length; j++) {
				if(i == j){
					Tel tels = new Tel();

					tels.setNum(tel[i]);
					tels.setType(telType[i]);
					telList.add(tels);
				}
				
			}
		}
		return telList;
	}

	//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟较碉拷锟斤拷泄氐锟斤拷锟斤拷锟�
	public List<Contactnote> findAllComments(String contactid) {
		List<Contactnote> contactnote =  cnoteDao.findAllComments(contactid);
		return contactnote;
	}

	//锟叫断癸拷司锟斤拷锟斤拷欠锟斤拷锟斤拷
	public Contact findCompanyName(String companyname) {
		return (Contact) dao.findByUnique("companyname", companyname);
	}
	
	//锟斤拷锟斤拷锟斤拷锟较碉拷说锟斤拷锟斤拷锟�
	public ContactnoteDto saveContactnote(Contactnote contactnote, Contact contact, String username) {
		contactnote.setContact(contact);
		contactnote.setCreatetime(DateUtil.getYMD());
		cnoteDao.save(contactnote);
		ContactnoteDto dto = new ContactnoteDto();
		dto.setContent(contactnote.getContent());
		dto.setCreatetime(contactnote.getCreatetime());
		dto.setUsername(username);
		return dto;
		
	}

	//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷系锟斤拷
	public void saveMany(String documentFileName, User user) throws FileNotFoundException, IOException {
		Workbook wb = new HSSFWorkbook(new FileInputStream("C:/upload/"+documentFileName));
		Sheet st = wb.getSheetAt(0);
		
		for (int i = 1; i <= st.getLastRowNum(); i++) {
			Row row = st.getRow(i);
			Contact con = new Contact();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell c = row.getCell(j);
				
				if(j == 0){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setContactname(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setContactname(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setContactname(null);
						}
					}
				}
				
				if(j == 1){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setCompanyname(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setCompanyname(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setCompanyname(null);
						}
					}
				}
				
				if(j == 2){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setPosition(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setPosition(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setPosition(null);
						}
					}
				}

				if(j == 3){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setEmail(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setEmail(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setEmail(null);
						}
					}
				}
				
				
				
				if(j == 4){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setWebsites(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setWebsites(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setWebsites(null);
						}
					}
				}
				
				if(j == 5){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setAddress(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setAddress(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setAddress(null);
						}
					}
				}
				
				if(j == 6){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setDescs(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setDescs(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setDescs(null);
						}
					}
				}
				
				if(j == 7){
					if(!"".equals(c) && c != null){
						if(c.getCellType() == c.CELL_TYPE_STRING){
							con.setTels(c.getStringCellValue());
						}else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
							con.setTels(String.valueOf(c.getNumericCellValue()));
						}else{
							con.setTels(null);
						}
					}
				}
				
				
			}
			if(con != null){

				//锟斤拷锟斤拷锟斤拷系锟剿碉拷锟斤拷系锟剿憋拷锟斤拷
				con.setView("all");
				saveContact(null, null, con, null, null, user);
				//锟斤拷锟芥到锟斤拷息锟斤拷锟斤拷
				ms.saveContact(con, user);
			}
		}
	}

	//锟侥硷拷锟斤拷锟斤拷
	public InputStream getFile() throws FileNotFoundException {
		File file = new File("C:/upload/contact.xls");
		InputStream input = new FileInputStream(file);
		
		return input;
	}




	
	
	//锟斤拷锟斤拷锟斤拷系锟斤拷
	public List<Contact> search(String search, User user) {
		Group group = user.getGroup();
		String groupid = null;
		if(group != null){
			groupid = group.getId();
		}
		



		return dao.search(search,user.getId(),groupid);
	}
		
	


	//锟斤拷锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷
/*	public void saveMessage(String[] telnum, String[] teltype, Contact contact,
			String chosegroup, String members, User user) {
		if(contact.getContactname() == null){
			//锟斤拷锟斤拷锟斤拷枪锟剿�
			saveContact(telnum, teltype, contact, chosegroup, members, user);
			message = new Message();
			message.setContent(contact.getCompanyname());
			message.setTitle(contact.getId());
			message.setCreatetime(contact.getCreatetime());
			message.setUser(user);
			message.setType("锟斤拷司");
			message.setView(contact.getView());
		}
		//System.out.println(contact.getId());
	}
*/


	
	
	

}
