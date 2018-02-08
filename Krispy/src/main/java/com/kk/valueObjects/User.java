package com.kk.valueObjects;

public class User {
	private int user_id=0;
	private int userStatus=0;
	private String username=null;
	private String userPassword=null;
	private String userQ1=null;
	private String userA1=null;
	private String userQ2=null;
	private String userA2=null;
	private String userQ3=null;
	private String userA3=null;
	
	
	public int getUserID(){
		return user_id;
	}
	public void setUserID(int user_id){
		this.user_id = user_id;
	}
	
	public int getUserStatus(){
		return userStatus;
	}
	public void setUserStatus(int userStatus){
		this.userStatus = userStatus;
	}
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUserPassword(){
		return userPassword;
	}
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	public String getUserQ1(){
		return userQ1;
	}
	public void setUserQ1(String userQ1){
		this.userQ1 = userQ1;
	}
	public String getUserA1(){
		return userA1;
	}
	public void setUserA1(String userA1){
		this.userA1 = userA1;
	}
	
	public String getUserQ2(){
		return userQ2;
	}
	public void setUserQ2(String userQ2){
		this.userQ2 = userQ2;
	}
	public String getUserA2(){
		return userA2;
	}
	public void setUserA2(String userA2){
		this.userA2 = userA2;
	}
	
	public String getUserQ3(){
		return userQ3;
	}
	public void setUserQ3(String userQ3){
		this.userQ3 = userQ3;
	}
	public String getUserA3(){
		return userA3;
	}
	public void setUserA3(String userA3){
		this.userA3 = userA3;
	}
	
	

}
