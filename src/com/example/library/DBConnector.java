package com.example.library;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DBConnector {
	private static Connection connection;
	private static final String DATABASE_URL="jdbc:mysql://localhost:3306/library";
	private static final String USERNAME="root";
	private static final String PASSWORD="reduction1997";
	
	public DBConnector(){
		
	}
	
	public void connect(){
		if(connection==null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect(){
		if(connection!=null){
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}	
	}
	
	public List<BOOK> getBOOKS(){
		List<BOOK> Itlist=new ArrayList<BOOK>();
		Statement stmt;
		ResultSet rs;
		try{
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select bid,imgpath,title,description from book;");
			while(rs.next()){
				BOOK it=new BOOK();
				it.setBid(rs.getInt(1));
				it.setImgpath(rs.getString(2));
				it.setTitle(rs.getString(3));
				it.setDescription(rs.getString(4));
				Itlist.add(it);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Itlist;
	}
}
