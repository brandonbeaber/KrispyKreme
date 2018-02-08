package com.kk.valueObjects;

public class MenuHeader {
	private int header_id=0;
	private int headerPos=0;
	private String headerName=null;	
	
	public int getHeaderID(){
		return header_id;
	}
	public void setHeaderID(int header_id){
		this.header_id = header_id;
	}
	
	public int getHeaderPos(){
		return headerPos;
	}
	public void setHeaderPos(int headerPos){
		this.headerPos = headerPos;
	}

	public String getHeaderName(){
		return headerName;
	}
	public void setHeaderName(String headerName){
		this.headerName = headerName;
	}
	

}
