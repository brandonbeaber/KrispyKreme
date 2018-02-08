package com.kk.jsp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.kk.services.*;
import com.kk.valueObjects.*;


public class HTMLServices extends BaseJSP {

	public HTMLServices(HttpSession session, HttpServletRequest request, HttpServletResponse response, JspWriter stream) throws Exception {
		super(session, request, response, stream);
		kks = new KKServices();
	}
	
	private KKServices kks = null;
	
	
	
// --------------------------------------------------------------------------------------------
//									USER FUNCTIONS
//--------------------------------------------------------------------------------------------
	
	
	
	
// --------------------------------------------------------------------------------------------
//									MENU FUNCTIONS
//--------------------------------------------------------------------------------------------
	
	/* Displays a pre-selected menu item; allows an Admin to EDIT MENU ITEM*/
	public void buildAdminMenuItem(int id) throws Exception {
		//if (request.getParameter("id") == null)
			//return;
		
		String store = (String)session.getAttribute("store");
		MenuItem m = kks.getMenuItem(id, store);
		StringBuilder out = new StringBuilder();
		
		out.append("<form role='form' action='"+ store +"Menu.jsp' method='post'><input type='hidden' name='editItem' value='" + m.getItemID() + "'><div class='container-fluid'><div class='row'><div class='col-md'>");
		out.append("<dl>");
	
		//out.append("<dt>ID:</dt>");
		//out.append("<dd>" + m.getItemID() + "</dd>");
		
		out.append("<dt>Name:</dt>");
		out.append("<dd><input name='txtName' value='" + m.getItemName() + "' width='300px'></dd>");
		
		out.append("<dt>Price:</dt>");
		out.append("<dd><input name='txtPrice' value='" + m.getItemPrice() + "'  width='300px'></dd>");
		
		out.append("<dt>Tax: ex.) 7 = 7%</dt>");
		out.append("<dd><input name='txtTax' value='" + m.getItemTax() + "'  width='300px'></dd>");
		
		out.append("<dt>Description:</dt>");
		out.append("<dd><textarea name='txtDesc' rows='5' cols='150'>" + m.getItemDesc() + "</textarea></dd>");
		
		out.append("<dt>Position:</dt>");
		out.append("<dd><input name='txtPos' value='" + m.getItemPos() + "'  width='300px'></dd>");
		
		out.append("</dl>");
		out.append("</div></div></div><button type='submit' class='btn btn-primary'>Save</button> </form>");
		out.append("</br></br><button class='btn btn-primary' onclick=\"history.back(-1)\">Back</button>");
		
		stream.print(out.toString());
	}
	
	/* Displays a blank menu item template to allow additions of a NEW MENU ITEM*/
	public void buildAdminNewMenuItem(int id) throws Exception {
		
		String store = (String)session.getAttribute("store");
		MenuHeader m = kks.getMenuHeaderPos(id, store);
		StringBuilder out = new StringBuilder();
		
		out.append("<form role='form' action='"+ store +"Menu.jsp' method='post'><input type='hidden' name='addItem' value='new'><div class='container-fluid'><div class='row'><div class='col-md'>");
		out.append("<dl>");

		out.append("<dt>Name:</dt>");
		out.append("<dd><input name='txtName' value='' width='300px'></dd>");
		
		out.append("<dt>Price:</dt>");
		out.append("<dd><input name='txtPrice' value=''  width='300px'></dd>");
		
		out.append("<dt>Tax: ex.) 7 = 7%</dt>");
		out.append("<dd><input name='txtTax' value=''  width='300px'></dd>");
		
		out.append("<dt>Description:</dt>");
		out.append("<dd><textarea name='txtDesc' rows='5' cols='150'></textarea></dd>");
		
		//Needs to be a drop down menu of all the Main Sections
		out.append("<dt>Position:</dt>");
		out.append("<dd><input name='txtPos' value='" + m.getHeaderPos() + "'  width='300px'></dd>");
		
		out.append("</dl>");
		out.append("</div></div></div><button type='submit' class='btn btn-primary'>Save</button> </form>");
		out.append("</br></br><button class='btn btn-primary' onclick=\"history.back(-1)\">Back</button>");
		
		stream.print(out.toString());
	}
	
	/* Build the basic menu; this is the menu the public will view */
	public void buildMenu() throws Exception {
		StringBuilder out = new StringBuilder();
		String store = (String)session.getAttribute("store");

		List<MenuHeader> header = kks.getMenuHeaders(store);				
		List<MenuItem> items = kks.getMenu(store);				

		for (MenuHeader h : header) {	
				out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
				out.append("<tr><h3 align='center'>" + h.getHeaderName() + "</h3></tr>");
			
				for(MenuItem m : items) {
					if(m.getItemPos() == h.getHeaderPos()){
						out.append("<tr onclick=\"window.document.location='editmenu.jsp?id=" + m.getItemID() + "'\">");
						out.append("<td>" + m.getItemName() + "</td><td>" + m.getItemDesc() + "</td><td>$" + m.getItemPrice()+ "</td>");
						System.out.println(m.getItemPos());
						out.append("</tr>");
					}
				}
				out.append("</table>");
		}
		
		out.append("</br></br><button class='btn btn-primary' onclick=\"history.back(-1)\">Back</button>");
		//Print output to page
		stream.print(out.toString());
	}
	

	
	/*  Build the Editable Menu for the Administrator  */
	public void buildAdminMenu() throws Exception {
		 
		//TEST bulding the Duplicate Menu
		//kks.setTableBackup("firstTry", "midtown");
		
		StringBuilder out = new StringBuilder();
		
		
		//out.append("<tr><th>ID</th><th>Name</th><th>Description</th><th>Price</th><th>Edit</th><th>Delete</th></tr>");
		
		String store = (String)session.getAttribute("store");
		List<MenuHeader> header = kks.getMenuHeaders(store);				
		List<MenuItem> items = kks.getMenu(store);
		System.out.print("Store:"+store);
		for (MenuHeader h : header) {	
			out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
			//out.append("<tr width='100%'><td visibility='hidden' white-space='nowrap' text-align='left'>  </td>");
			out.append("<tr width='100%'><td align-content='center'><h3>" + h.getHeaderName() + "</h3>");
			out.append("<td align-content='right'><form method='post' action='"+ store +"Menu.jsp'><input type='hidden' name='deleteHeader' value='" + h.getHeaderID() + "'><button type=\"submit\" class=\"btn btn-default btn-md\" onclick=\"return confirm('Are you sure you want to delete this header?')\" /><span class=\"glyphicon glyphicon-pencil\"></span> Delete Header</button></form></td></tr>");

			
			for(MenuItem m : items) {
				if(m.getItemPos() == h.getHeaderPos()){
					//The JFileChooser does not realize we are in a Servlet-Container;  this will try to select a file from the Server NOT the user OS
					//out.append("<tr onclick=\"window.document.location='editmenu.jsp?id=" + m.getItemID() + "'\">");
					out.append("<tr><td>" + m.getItemID() + "</td><td>" + m.getItemName() + "</td><td>" + m.getItemDesc() + "</td><td>$" + m.getItemPrice()+ "</td>");
					out.append("<td><form method='post' action='"+ store +"Menu.jsp'><input type='hidden' name='itemImage' value='" + m.getItemID() + "'> <button type=\"submit\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-pencil\"></span> Add Image</button></form></td>");
					out.append("<td><form method='post' action='editMenuItem.jsp'><input type='hidden' name='editItem' value='" + m.getItemID() + "'> <button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"window.document.location='editMenuItem.jsp?id=" + m.getItemID() + "'\"><span class=\"glyphicon glyphicon-pencil\"></span> Edit Item</button></form></td>");
					out.append("<td><form method='post' action='"+ store +"Menu.jsp'><input type='hidden' name='deleteItem' value='" + m.getItemID() + "'><button type=\"submit\" class=\"btn btn-default btn-sm\" onclick=\"return confirm('Are you sure you want to delete this item?')\" /><span class=\"glyphicon glyphicon-pencil\"></span> Delete Item</button></form></td>");
					out.append("</tr>");
				}
			}			
			/*   BACKUP ---  TESTING ABOVE
			for(MenuItem m : items) {
				if(m.getItemPos() == h.getHeaderPos()){
					//out.append("<tr onclick=\"window.document.location='editmenu.jsp?id=" + m.getItemID() + "'\">");
					out.append("<tr><td>" + m.getItemID() + "</td><td>" + m.getItemName() + "</td><td>" + m.getItemDesc() + "</td><td>$" + m.getItemPrice()+ "</td>");
					out.append("<td><form method='post' action='editMenuItem.jsp'><input type='hidden' name='editItem' value='" + m.getItemID() + "'> <button type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"window.document.location='editMenuItem.jsp?id=" + m.getItemID() + "'\"><span class=\"glyphicon glyphicon-pencil\"></span> Edit Item</button></form></td>");
					out.append("<td><form method='post' action='"+ store +"Menu.jsp'><input type='hidden' name='deleteItem' value='" + m.getItemID() + "'><button type=\"submit\" class=\"btn btn-default btn-sm\" onclick=\"return confirm('Are you sure you want to delete this item?')\" /><span class=\"glyphicon glyphicon-pencil\"></span> Delete Item</button></form></td>");
					out.append("</tr>");
				}
			}*/
		out.append("</table>");
	    out.append("<form method='post' action='addMenuItem.jsp'><input type='hidden' name='addItem' value='" + h.getHeaderPos() + "'><button type=\"submit\" class=\"btn btn-default btn-md\" onclick=\"window.document.location='addMenuItem.jsp?addItem=" + h.getHeaderPos() + "'\"/><span class=\"glyphicon glyphicon-plus\"></span> Add Item</button></form></br></br>");
		}
	    out.append("<div class='form-group'><form method='post' action='"+ store +"Menu.jsp' class='navbar-form' align='center'><input type='text' name='addHeader' class='form-control' placeholder='Header...' value=''><button type='submit' class='btn btn-default'><span class=\"glyphicon glyphicon-plus\"></span> Add Header</button></form></div>");
	    out.append("</br></br><div class='form-group'><form method='post' action='"+ store +"Menu.jsp' class='navbar-form' align='center'><input type='text' name='dupMenu' class='form-control' placeholder='Backup Name' value=''><button type='submit' class='btn btn-default'><span class=\"glyphicon glyphicon-plus\"></span> Backup Menu</button></form></div>");
	    out.append("</br></br><button class='btn btn-primary' onclick=\"history.back(-1)\">Back</button>");
	    //Print output to page
		stream.print(out.toString());
	}
	

	public void buildBackups() throws Exception {
		
		StringBuilder out = new StringBuilder();
				
		String store = (String)session.getAttribute("store");
		List<Duplicates> dup = kks.getDups(store);	
		
		out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
		out.append("<tr><h3 align='center'>Backups</h3></tr></br>");

		for(Duplicates d : dup) {
			// ******************************      MUST BE UPDATED IF A NEW STORE IS ADDED     *************************************
			//Need to remove the "dup_%store%" from the Backup name
			String dupName = d.getDupName();
			dupName = dupName.replace("dup_","");
			if (dupName.toLowerCase().contains("midtown")){
				dupName = dupName.replace("midtown","");
			}
			if (dupName.toLowerCase().contains("west")){
				dupName = dupName.replace("west","");
			}
			if (dupName.toLowerCase().contains("council")){
				dupName = dupName.replace("council","");
			}
			if (dupName.toLowerCase().contains("lincoln")){
				dupName = dupName.replace("lincoln","");
			}
			
			out.append("<tr><td></td><td>" + dupName + "</td><td>" + d.getDupStore() + "</td><td>$" + d.getDupDate()+ "</td>");
			out.append("<td><form method='post' action='backups.jsp'><input type='hidden' name='deleteDup' value='" + d.getDupID() + "'><input type='hidden' name='dupName' value='" + d.getDupName() + "'><button type=\"submit\" class=\"btn btn-default btn-sm\" onclick=\"return confirm('Are you sure you want to delete this backup?')\" /><span class=\"glyphicon glyphicon-pencil\"></span> Delete Backup</button></form></td>");
			out.append("</tr>");
		}
		out.append("</table>");
	    out.append("</br></br><button class='btn btn-primary' onclick=\"history.back(-1)\">Back</button>");
	    
		//Print output to page
		stream.print(out.toString());
	}
  
	
	
// --------------------------------------------------------------------------------------------
//									STORE FUNCTIONS
//--------------------------------------------------------------------------------------------

	
	public void buildMenuBACKUP() throws Exception {
		StringBuilder out = new StringBuilder();
		out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
		out.append("<tr><th>Name</th><th>Description</th><th>Price</th></tr>");
		String store = (String)session.getAttribute("store");
		
		List<MenuItem> items = kks.getMenu(store);

		for(MenuItem m : items) {
			out.append("<tr onclick=\"window.document.location='editmenu.jsp?id=" + m.getItemID() + "'\">");
			out.append("<td>" + m.getItemName() + "</td><td>" + m.getItemDesc() + "</td><td>$" + m.getItemPrice()+ "</td>");
		    out.append("</tr>");
		}
		out.append("</br></br><button class='btn btn-primary' onclick=\"history.back(-1)\">Back</button>");
		
		out.append("</table>");
		
		//Print output to page
		stream.print(out.toString());
	}	
	
}
