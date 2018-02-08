package com.kk.valueObjects;

public class Admin {
	private int admin_id=0;
	private int superUser=0;
	private String adminName=null;
	private String adminPassword=null;
	private String adminFirst=null;
	private String adminLast=null;
	private String adminEmail=null;
	
	public int getAdminID(){
		return admin_id;
	}
	public void setAdminID(int admin_id){
		this.admin_id = admin_id;
	}
	
	public int getSuperUser(){
		return superUser;
	}
	public void setSuperUser(int superUser){
		this.superUser = superUser;
	}
	
	public String getAdminName(){
		return adminName;
	}
	public void setAdminName(String adminName){
		this.adminName = adminName;
	}
	
	public String getAdminPassword(){
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword){
		this.adminPassword = adminPassword;
	}
	
	public String getAdminFirst(){
		return adminFirst;
	}
	public void setAdminFirst(String adminFirst){
		this.adminFirst = adminFirst;
	}
	
	public String getAdminLast(){
		return adminLast;
	}
	public void setAdminLast(String adminLast){
		this.adminLast = adminLast;
	}
	
	public String getAdminEmail(){
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail){
		this.adminEmail = adminEmail;
	}

}
