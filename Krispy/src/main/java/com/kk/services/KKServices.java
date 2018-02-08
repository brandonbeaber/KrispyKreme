package com.kk.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.kk.databaseConnectors.MySQLConnector;
import com.kk.valueObjects.*;
// import com.kk.services.*;
//Add AdminService.  Then add a function in AdminServices to return the current "store" from the session attributes

public class KKServices {

	private MySQLConnector conn = null;

	public KKServices() throws SQLException {
		conn = new MySQLConnector();
	}
	
	
//--------------------------------------------------------------------------------------
//								START FUNCTIONS HERE 
//--------------------------------------------------------------------------------------
	
	public void addMenuItem( MenuItem item, String store ) throws SQLException {
		//Use the AdminServices function to store the "store" session attribute value
		String query = "INSERT INTO " + store + "menuitem"
				+ " (itemName, itemPrice, itemDesc, itemTax, itemPos)"
				+ " VALUES"
				+ " ('" + item.getItemName() + "', '" + item.getItemPrice() + "', '" + item.getItemDesc() + "', '" + item.getItemTax() + "',  '" + item.getItemPos() + "') ";
		System.out.println(query);
		conn.runUpdate(query);
	}
	
	
	public void addHeader( MenuHeader item, String store ) throws SQLException {
		//Use the AdminServices function to store the "store" session attribute value
		String query = "INSERT INTO " + store + "menuheader"
				+ " (headerName, headerPos)"
				+ " VALUES"
				+ " ('" + item.getHeaderName() + "', '"+ item.getHeaderPos() + "') ";
		System.out.println(query);
		conn.runUpdate(query);
	}

	public void deleteDup( int id, String name ) throws SQLException {
		
		//Remove the Backup entry from the "dupmenus" table
		String query = "DELETE FROM dupmenus"
				+ " WHERE dup_id=" + id + "";
		System.out.println(query);
		conn.runUpdate(query);
		
		//Delete the Header Table Backup
		String query2 = "DROP TABLE `" + name +"menuheader`";
		System.out.println(query2);
		conn.runUpdate(query2);
		
		//Delete the Menu Items Table Backup
		String query3 = "DROP TABLE `" + name +"menuitem`";
		System.out.println(query3);
		conn.runUpdate(query3);
		
	}
	
	public void deleteMenuItem( int id, String store ) throws SQLException {
		//Use the AdminServices function to store the "store" session attribute value
		String query = "DELETE FROM " + store + "menuitem"
				+ " WHERE item_id=" + id + "";
		System.out.println(query);
		conn.runUpdate(query);
	}
	
	
	public void deleteMenuItemsPos( int pos, String store ) throws SQLException {
		//Use the AdminServices function to store the "store" session attribute value
		String query = "DELETE FROM " + store + "menuitem"
				+ " WHERE itemPos=" + pos + "";
		System.out.println(query);
		conn.runUpdate(query);
	}
	
	
	public void deleteMenuHeader( int id, String store ) throws SQLException {
		//Use the AdminServices function to store the "store" session attribute value
		String query = "DELETE FROM " + store + "menuheader"
				+ " WHERE header_id=" + id + "";
		System.out.println(query);
		conn.runUpdate(query);
	}
	
	
	public List<Duplicates> getDups(String store) throws Exception {
		ResultSet results = null;
		List<Duplicates> list = new ArrayList<Duplicates>();

		results = conn.runQuery("SELECT * FROM dupmenus ORDER BY 'dupStore'");

		if (results != null) {
			while (results.next()) {
				Duplicates dup = new Duplicates();
				dup.setDupID(results.getInt("dup_id"));
				dup.setDupName(results.getString("dupName"));
				dup.setDupStore(results.getString("dupStore"));
				dup.setDupDate(results.getDate("dupDate"));
				list.add(dup);
			}
		}
		return list;
	}
	
	
	public MenuItem getMenuItem(int id, String store) throws Exception {
		ResultSet results = null;
		results = conn.runQuery("SELECT * FROM "+ store +"menuitem where item_id=" + id);

		if (results != null) {
			while (results.next()) {
				MenuItem item = new MenuItem();

				item.setItemID(results.getInt("item_id"));
				item.setItemName(results.getString("itemName"));
				item.setItemDesc(results.getString("itemDesc"));
				item.setItemPrice(results.getDouble("itemPrice"));
				item.setItemTax(results.getDouble("itemTax"));
				item.setItemPos(results.getInt("itemPos"));
				return item;
			}
		}
		return null;
	}
	
	public MenuHeader getMenuHeader(int id, String store) throws Exception {
		ResultSet results = null;
		results = conn.runQuery("SELECT * FROM "+ store +"menuheader where header_id=" + id);

		if (results != null) {
			while (results.next()) {
				MenuHeader header = new MenuHeader();

				header.setHeaderID(results.getInt("header_id"));
				header.setHeaderPos(results.getInt("headerPos"));
				header.setHeaderName(results.getString("headerName"));
				return header;
			}
		}
		return null;
	}
	
	public MenuHeader getMenuHeaderPos(int id, String store) throws Exception {
		ResultSet results = null;
		results = conn.runQuery("SELECT * FROM "+ store +"menuheader where headerPos=" + id);

		if (results != null) {
			while (results.next()) {
				MenuHeader header = new MenuHeader();

				header.setHeaderID(results.getInt("header_id"));
				header.setHeaderPos(results.getInt("headerPos"));
				header.setHeaderName(results.getString("headerName"));
				return header;
			}
		}
		return null;
	}
	
	public List<MenuItem> getMenu(String store) throws Exception {
		ResultSet results = null;
		List<MenuItem> list = new ArrayList<MenuItem>();

		results = conn.runQuery("SELECT * FROM "+ store +"menuitem ORDER BY 'itemName'");

		if (results != null) {
			while (results.next()) {
				MenuItem item = new MenuItem();
				item.setItemID(results.getInt("item_id"));
				item.setItemName(results.getString("itemName"));
				item.setItemDesc(results.getString("itemDesc"));
				item.setItemPrice(results.getDouble("itemPrice"));
				item.setItemTax(results.getDouble("itemTax"));
				item.setItemPos(results.getInt("itemPos"));
				list.add(item);
			}
		}
		return list;
	}
	
	
	public List<MenuHeader> getMenuHeaders(String store) throws Exception {
		ResultSet results = null;
		List<MenuHeader> list = new ArrayList<MenuHeader>();

		results = conn.runQuery("SELECT * FROM "+ store +"menuheader ORDER BY 'headerPos'");

		if (results != null) {
			while (results.next()) {
				MenuHeader header = new MenuHeader();
				header.setHeaderID(results.getInt("header_id"));
				header.setHeaderName(results.getString("headerName"));
				header.setHeaderPos(results.getInt("headerPos"));
				list.add(header);
			}
		}
		return list;
	}
	
	//Get Header Name when given the Header Position
	public String getHeaderName(String store, int pos) throws Exception {
		ResultSet results = null;
		String headerName="";
		
		results = conn.runQuery("SELECT headerName FROM "+ store +"menuheader WHERE headerName="+ pos);

		if (results != null) {
			while (results.next()) {
				headerName=results.getString(1);
			}
		}
		return headerName;
	}
	
	// ****************************   Create TABLE BACKUP BASED ON THE STORE   *****************************************
	//
	// store = The store's tables which we want to take a backup
	//
	// headers = List of all of the Headers table information 
	// items = List of all of the Menu Items table information 
	//
	// *****************************************************************************************************************
	public void setTableBackup( String dupName, String store ) throws Exception {
		// STORE these INTO a BRAND NEW TABLE
		System.out.println("Called DUP Function");
		// Put the Headers table into a List to be duplicated
		List<MenuHeader> headers = new ArrayList<MenuHeader>();
		headers = getMenuHeaders(store);	            
		System.out.println("Got Headers");
		
		// Put the Menu Items table into a List to be duplicated
		List<MenuItem> items = new ArrayList<MenuItem>();
		items = getMenu(store);
		System.out.println("Got Items");
		
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//
		// Need to create another TABLE to keep track of all of the duplicate Tables
		// Variables:
		// dupName = Name of the duplicate table (Decided by the User)
		// dupStore = The store which this table is a duplicate of
		// dupDate = The date when the duplicate was taken 
		//
		// NOTE: To get a count of the table we will just run a SQL to determine which table each duplicate was copied from
		//
		//
		// Use the String "dupName" to create the table.
		// SQL DataBase name will be something like   "store"+"dupName"+ (meunitem OR menuheader)!!!
		// NOTE:  We will need to monitor what characters are allowed in the "dupName" variable!!!
		//
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		// Set the stores Name
		String StoreName = "dup_" + store+dupName;
				
		//Build the duplicate Headers Table using the "dupName" variable
		String queryHeader = "CREATE TABLE `kk`.`" + StoreName +"menuheader` ("
				+ "`header_id` INT NOT NULL AUTO_INCREMENT,"
				+ "`headerName` VARCHAR(45) NOT NULL,"
				+ "`headerPos` INT(11) NOT NULL,"
				+ "PRIMARY KEY (`header_id`))";
		System.out.println(queryHeader);
		conn.runUpdate(queryHeader);
		
		//Build the duplicate Menu Items Table using the "dupName" variable
		// NOTE: May need to add the Schema name (kk)
		String queryItem = "CREATE TABLE `" + StoreName +"menuitem` ("
				+ "`item_id` INT NOT NULL AUTO_INCREMENT,"
				+ "`itemPrice` DOUBLE NOT NULL,"
				+ "`itemTax` DOUBLE DEFAULT 0,"
				+ "`itemName` VARCHAR(45) NOT NULL,"
				+ "`itemDesc` VARCHAR(100),"
				+ "`itemPos` INT(11),"
				+ "PRIMARY KEY (`item_id`))";
		System.out.println(queryItem);
		conn.runUpdate(queryItem);
		
		//Populate the duplicate Headers Table using the "store"+"dupName" variables
		for(MenuHeader m : headers) {
			//Add the Headers to the new table one at a time
			addHeader(m, StoreName);
		}

		
		//Populate the duplicate Menu Items Table using the "store"+"dupName" variables
		for(MenuItem m : items) {
			//Add the Menu Items to the new table one at a time
			addMenuItem(m, StoreName);
		}	
		
		// Update the Duplicate MetaData table
		addDups(StoreName, store);
	}	
	
	
	//**************************************************
	//
	// Metadata Table to keep track of all of the Menu Duplications
	//
	//**************************************************
	public void addDups(String name, String store) throws SQLException {
		// Create a new Date Object
		Date dt = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		// Format the DateTime to match the SQL DATETIME format type (yyyy-MM-dd HH:mm:ss) 
		String currentTime = sdf.format(dt);
				
		// Make note of the table duplication in the "dupmenus" table 
		String query = "INSERT INTO dupmenus"
				+ " (dupName, dupStore, dupDate)"
				+ " VALUES"
				+ " ('" + name + "', '" + store + "',  '" + currentTime + "') ";
		System.out.println(query);
		conn.runUpdate(query);	
	}
	
	
	
	public void updateMenuItem( MenuItem item, String store ) throws SQLException  {
		//Use the newly create AdminServices function to store the "store" session attribute value
		String query = "UPDATE " + store + "menuitem SET"
				+ " itemName='" + item.getItemName() + "', "
				+ " itemPrice='" + item.getItemPrice() + "', "
				+ " itemTax='" + item.getItemTax() + "', "
				+ " itemDesc='" + item.getItemDesc() + "', "
				+ " itemPos='" + item.getItemPos() + "' "
				+ " WHERE item_id='" + item.getItemID() + "'";
		System.out.println(query);
		conn.runUpdate(query);
	}
	
	public boolean validateLogin( String user, String pass) throws SQLException {
		//Problem with the SQL
		String query = "SELECT * FROM users WHERE userPassword='" + pass + "' ";	
		ResultSet result = conn.runQuery(query);
	
		if( result.next() ) {
			System.out.println("Valid User");
			return true;
		}
		System.out.println("Invalid User");
		return false;
	}
	
	
	
}
