package com.kk.services;

import java.util.List;
import java.io.File;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.kk.services.*;
import com.kk.valueObjects.*;
import com.kk.jsp.*;
import com.kk.databaseConnectors.*;

public class AdminServices extends BaseJSP {

	private MySQLConnector conn = null;
	private KKServices kks = null;
	private HTMLServices hs = null;
	private Object adminKey = "abcWST6kks76bE73MmAA72Z3khabFlAkbvlKjFhvlibaiu3480510hDJGkwfh345dkKJHF48373737373737gbiOYv1g874Bv184";
	private Object userKey = "abcWST6kks76bEkjhsKHhJK48DHh3748fDFHW73MmAA72KHhJK48Z3";

	
	public AdminServices(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, JspWriter stream) throws Exception {
		super(session, request, response, stream);
		kks = new KKServices();
		hs = new HTMLServices(session, request, response, stream);
	    conn = new MySQLConnector();
	}
	
	
//--------------------------------------------------------------------------------------
//					ALL REQUESTS will go under this function call 
//--------------------------------------------------------------------------------------
	public void executeAdminServices() throws Exception {
		
		
		//Duplicate Menu Call
		if (request.getParameter("dupMenu") != null) {

			//Set the Store and Duplicate menu Name
			String store = getStore();
			String dupName = request.getParameter("dupMenu");
			System.out.print(dupName);
			
			//Call the Duplication Func
			kks.setTableBackup(dupName, store);
			
			//May NOT want to redirect here
			redirect(store+"Menu.jsp");
			return;
		}
		
		//Admin Authentication
		if (request.getParameter("login") != null) {			   
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			if (kks.validateLogin(user, pass) ) {
				loginAdmin();
				
				//conn.closeConnection();		
				return;
			}
			redirect("landing.jsp");
		}
		
		//Admin Logout
		if (request.getParameter("logout") != null) {			   	
				logoutAdmin();
				redirect("landing.jsp");
				//conn.closeConnection();		
				return;
		}
		
		
		//Add Menu Item
		if (request.getParameter("addItem") != null) {
			//int position = Integer.parseInt(request.getParameter("addItem"));
			//System.out.println(position);
			String store = getStore();
			
			MenuItem m = new MenuItem();
			m.setItemName(request.getParameter("txtName"));
			m.setItemPrice(Double.parseDouble(request.getParameter("txtPrice")));
			m.setItemDesc(request.getParameter("txtDesc"));
			m.setItemTax(Double.parseDouble(request.getParameter("txtTax")));
			m.setItemPos(Integer.parseInt(request.getParameter("txtPos")));
			kks.addMenuItem(m, store);
			
			redirect(store+"Menu.jsp");
			return;
		}		
	
		
		//Edit Menu Item
		if (request.getParameter("editItem") != null) {
			int item = Integer.parseInt(request.getParameter("editItem"));
			String store = getStore();
			
			MenuItem m = kks.getMenuItem(item, store);
			m.setItemName(request.getParameter("txtName"));
			m.setItemPrice(Double.parseDouble(request.getParameter("txtPrice")));
			m.setItemDesc(request.getParameter("txtDesc"));
			m.setItemTax(Double.parseDouble(request.getParameter("txtTax")));
			m.setItemPos(Integer.parseInt(request.getParameter("txtPos")));
			kks.updateMenuItem(m, store);
			
			redirect(store+"Menu.jsp");
			return;
		}

		//Delete Duplicate
		if (request.getParameter("deleteDup") != null) {
			System.out.println("Called DELETE Duplicate");
			int dup_id = Integer.parseInt(request.getParameter("deleteDup"));
			String dupName = request.getParameter("dupName");
			kks.deleteDup(dup_id, dupName);
			
			redirect("backups.jsp");
			return;
		}
		
		//Delete Menu Item
		if (request.getParameter("deleteItem") != null) {
			System.out.println("Called DELETE ITEM");
			int item_id = Integer.parseInt(request.getParameter("deleteItem"));
			String store = getStore();
			kks.deleteMenuItem(item_id, store);
			redirect(store+"Menu.jsp");
			return;
		}
		
	
		//Add Header
		if (request.getParameter("addHeader") != null) {
			//int position = Integer.parseInt(request.getParameter("addItem"));
			//System.out.println(position);
			String store = getStore();
			
			MenuHeader m = new MenuHeader();
			m.setHeaderName(request.getParameter("addHeader"));
			m.setHeaderPos(getNextHeaderPos());
			kks.addHeader(m, store);
			
			redirect(store+"Menu.jsp");
			return;
		}	
		
		
		//Delete Menu Header
		if (request.getParameter("deleteHeader") != null) {
			int header_id = Integer.parseInt(request.getParameter("deleteHeader"));
			String store = getStore();
			MenuHeader header = kks.getMenuHeader(header_id, store);
			//int pos = Integer.parseInt(header.getHeaderPos());
			int pos = header.getHeaderPos();
			kks.deleteMenuItemsPos(pos, store);
			kks.deleteMenuHeader(header_id, store);		
			redirect(store+"Menu.jsp");
			return;
		}
		
		
		//Select Image for Menu Item
		if (request.getParameter("itemImage") != null) {
			System.out.println("Successfully called the Add Image Func");
			JFileChooser fc = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "svc", "jpeg");
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
	        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(true);
	        int result = fc.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fc.getSelectedFile();
	            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
	        }
			return;
		}
		
	}
	
	
	public void setStore(String store) {
		session.setAttribute("store", store);
		System.out.println("Store set to: "+ store);
		//conn.closeConnection();
	}
	public String getStore() {
		String store = (String) session.getAttribute("store");
		System.out.println("Returning: "+ store);
		//conn.closeConnection();
		return store;
	}
	
	
	public void loginAdmin() {
		session.setAttribute("adminLogin", adminKey);
		//conn.closeConnection();
	}
	
	public void logoutAdmin() {
		session.removeAttribute("adminLogin");
		//conn.closeConnection();
	}

	public boolean isLoggedIn() {
		if (session.getAttribute("adminLogin") == null){
			//conn.closeConnection();
			return false;
		}
		if (session.getAttribute("adminLogin").equals(adminKey)) {
			//conn.closeConnection();
			return true;
		}
		//conn.closeConnection();
		return false;
	}
	
	public int getNextHeaderPos() throws Exception {		
		String store = (String)session.getAttribute("store");
		List<MenuHeader> header = kks.getMenuHeaders(store);
		int nextPos = 0;
		
		for (MenuHeader h : header) {
			if(nextPos < h.getHeaderPos()){
				nextPos = h.getHeaderPos();
			}
		}
		nextPos = nextPos + 1;
		return nextPos;
	}
	
	
	
}
