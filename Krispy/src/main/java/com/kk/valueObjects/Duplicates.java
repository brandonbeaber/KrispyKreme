package com.kk.valueObjects;
import java.util.Date;

public class Duplicates {
	private int dup_id=0;
	private String dupName=null;
	private String dupStore=null;
	private Date dupDate = null;

	
	
	public int getDupID(){
		return dup_id;
	}
	public void setDupID(int dup_id){
		this.dup_id = dup_id;
	}
	
	public String getDupName() {
		return dupName;
	}
	public void setDupName(String dupName) {
		this.dupName = dupName;
	}
	
	public String getDupStore() {
		return dupStore;
	}
	public void setDupStore(String dupStore) {
		this.dupStore = dupStore;
	}
	
	public Date getDupDate(){
		return dupDate;
	}
	public void setDupDate(Date dupDate){
		this.dupDate = dupDate;
	}
	
}
