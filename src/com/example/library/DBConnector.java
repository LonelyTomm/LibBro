package com.example.library;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DBConnector {
	private static Connection connection;
	private static final String DATABASE_URL="jdbc:mysql://localhost:3306/library";
	private static final String USERNAME="root";
	private static final String PASSWORD="123456789";
	
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
	
	public void modBook(BOOK book) {
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement("update book set imgpath=?,title=?,retprice=?,publisher=?,quantity=?,author=?,description=?,genre=? where bid=?;");
			stmt.setString(1, book.getImgpath());
			stmt.setString(2, book.getTitle());
			stmt.setDouble(3, book.getRetprice());
			stmt.setString(4, book.getPublisher());
			stmt.setInt(5, book.getQuantity());
			stmt.setString(6, book.getAuthor());
			stmt.setString(7, book.getDescription());
			stmt.setString(8, book.getGenre());
			stmt.setInt(9, book.getBid());
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<BOOK> getGnBOOKS(String genre){
		List<BOOK> Itlist=new ArrayList<BOOK>();
		Statement stmt;
		ResultSet rs;
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select bid,imgpath,title,description,genre from book;");
			while(rs.next()){
				if(rs.getString(5).contains(genre)) {
					BOOK it=new BOOK();
					it.setBid(rs.getInt(1));
					it.setImgpath(rs.getString(2));
					it.setTitle(rs.getString(3));
					it.setDescription(rs.getString(4));
					Itlist.add(it);
				}
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Itlist;
	}
	
	public List<String> getLogs(){
		List<String> LogList=new ArrayList<String>();
		Statement stmt;
		ResultSet rs;
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select * from borrbook order by borrowdate;");
			while(rs.next()) {
				String logString=rs.getString(5)+" "+rs.getString(3)+" "+rs.getString(2)+" borrowed book with ID "+rs.getString(6);
				LogList.add(logString);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return LogList;
	}
	
	public List<BOOK> getFindBooks(String searchQuery){
		List<BOOK> Itlist=new ArrayList<BOOK>();
		Statement stmt;
		ResultSet rs;
		try{
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select bid,imgpath,title,description,quantity,author from book;");
			while(rs.next()){
				BOOK it=new BOOK();
				it.setBid(rs.getInt(1));
				it.setImgpath(rs.getString(2));
				it.setTitle(rs.getString(3));
				it.setDescription(rs.getString(4));
				it.setQuantity(rs.getInt(5));
				it.setAuthor(rs.getString(6));
				if(it.getTitle().toLowerCase().contains(searchQuery.toLowerCase())||it.getAuthor().toLowerCase().contains(searchQuery.toLowerCase()))
					Itlist.add(it);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Itlist;
	}
	
	public List<BOOK> getBOOKS(){
		List<BOOK> Itlist=new ArrayList<BOOK>();
		Statement stmt;
		ResultSet rs;
		try{
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select bid,imgpath,title,description,quantity from book;");
			while(rs.next()){
				BOOK it=new BOOK();
				it.setBid(rs.getInt(1));
				it.setImgpath(rs.getString(2));
				it.setTitle(rs.getString(3));
				it.setDescription(rs.getString(4));
				it.setQuantity(rs.getInt(5));
				Itlist.add(it);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Itlist;
	}
	
	public BOOK getBOOK(int bid) {
		BOOK book=new BOOK();
		Statement stmt;
		ResultSet rs;
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery("select * from book where bid="+bid+";");
			if(rs.next()) {
				book.setBid(rs.getInt(1));
				book.setImgpath(rs.getString(2));
				book.setTitle(rs.getString(3));
				book.setRetprice(rs.getDouble(4));
				book.setPublisher(rs.getString(5));
				book.setQuantity(rs.getInt(6));
				book.setAuthor(rs.getString(7));
				book.setDescription(rs.getString(8));
				book.setGenre(rs.getString(9));
			}
			stmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public String addBORRBOOK(BORRBOOK bbook) {
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement("insert into borrbook(studname,studno,returndate,borrowdate,bookid) values(?,?,?,?,?);");
			stmt.setString(1, bbook.getStudname());
			stmt.setInt(2, bbook.getStudno());
			stmt.setString(3, bbook.getReturndate());
			stmt.setString(4, bbook.getBorrowdate());
			stmt.setInt(5, bbook.getBookid());
			stmt.executeUpdate();
			stmt.close();
			return "Succesfully";
		}catch(SQLException e) {
			e.printStackTrace();
			return "Failed"+e.toString();
		}
	}
	
	public void addBOOK(BOOK book) {
		PreparedStatement stmt;
		try {
			stmt=connection.prepareStatement("insert into book(imgpath,title,retprice,publisher,quantity,author,description,genre) values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, book.getImgpath());
			stmt.setString(2,book.getTitle());
			stmt.setDouble(3, book.getRetprice());
			stmt.setString(4, book.getPublisher());
			stmt.setInt(5, book.getQuantity());
			stmt.setString(6, book.getAuthor());
			stmt.setString(7, book.getDescription());
			stmt.setString(8, book.getGenre());
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
