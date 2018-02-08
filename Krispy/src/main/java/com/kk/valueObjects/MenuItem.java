package com.kk.valueObjects;

public class MenuItem {
	private int item_id=0;
	private double itemPrice=0.0;
	private double itemTax=0.0;
	private String itemName=null;
	private String itemDesc=null;
	private int itemPos=0;

	
	
	public int getItemID(){
		return item_id;
	}
	public void setItemID(int item_id){
		this.item_id = item_id;
	}
	
	public double getItemPrice(){
		return itemPrice;
	}
	public void setItemPrice(double itemPrice){
		this.itemPrice = itemPrice;
	}
	
	public double getItemTax() {
		return itemTax;
	}
	public void setItemTax(double itemTax) {
		this.itemTax = itemTax;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	public int getItemPos(){
		return itemPos;
	}
	public void setItemPos(int itemPos){
		this.itemPos = itemPos;
	}
	
}
