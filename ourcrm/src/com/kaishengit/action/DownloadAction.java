package com.kaishengit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaishengit.service.ContactService;


public class DownloadAction {

	private ContactService cs;
	private String fileName;
	
	
	public String execute(){
		System.out.println("aa");
		return "success";
	}

	
	public InputStream getFile() throws Exception {
		
		//fileName = new String("简历.pdf".getBytes("UTF-8"),"ISO8859-1");
		fileName = "contact.xls";
		
		File file = new File("C:/upload/contact.xls");
		InputStream input = new FileInputStream(file);
		
		
		return input;
	}
	
	
	@Autowired
	public void setCs(ContactService cs) {
		this.cs = cs;
	}




	public String getFileName() {
		return fileName;
	}




	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
