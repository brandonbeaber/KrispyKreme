package com.kk.databaseConnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


public class MySQLConnector {
	
	private Connection conn = null;	
	
	public MySQLConnector(){
		performConnection();
	}
	
	//Connect to Database
	public void performConnection() {		
		String url = "jdbc:mysql://127.0.0.1:3306/kk";
		String username = "root";
		String password = "rue!!!";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
	
	//Run a Query
	public ResultSet runQuery(String query) throws SQLException {
		System.out.println("Execute Query: '" + query + "'");
		Statement stmt = conn.createStatement();
		
		return stmt.executeQuery(query);
	}
	
	
	public int runUpdate(String query) throws SQLException {
		System.out.println("Execute update: '" + query + "'");;
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		
		//New Primary Key
		if(rs.next()){
			int newId = rs.getInt(1);
			return newId;
		}
		
		return -1;
	}
	

	//Close Connection to Database
	public void closeConnection() { 
		try{
			conn.close();
		}catch(Exception e) {
			System.out.print("Error closing the DB");
		}
	}
	
/*	
	public ArrayList<String> returnUser() throws SQLException{
		ArrayList<String> ls = new ArrayList<String>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ls.add(rs.getString("username"));
		}
		rs.close();
		return ls;		
	}
*/
	
}
