package com.example.library;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/BookManage")
@ApplicationPath("/webservice")
public class RestService extends Application{
	//http://localhost:8080/LibBro/webservice/BookManage/getBook/{id}
	@GET
	@Path("/getBook/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public BOOK getBook(@PathParam("id") String id) {
		int bookId=0;
		BOOK book=new BOOK();
		try {
			bookId=Integer.parseInt(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(bookId>0) {
			DBConnector dbconn=new DBConnector();
			book=dbconn.getBOOK(bookId);
		}
		return book;
	}
	
	//http://localhost:8080/LibBro/webservice/BookManage/getBooks
	@GET
	@Path("/getBooks")
	@Produces(MediaType.APPLICATION_XML)
	public List<BOOK> getHelloMsg() {
		DBConnector dbconn=new DBConnector();
		dbconn.connect();
		return dbconn.getBOOKS();
	}
	
	//http://localhost:8080/LibBro/webservice/BookManage/addBbook
	@POST
	@Path("/addBbook")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String addBORRBOOK(BORRBOOK bbook) {
		DBConnector dbconn=new DBConnector();
		dbconn.connect();
		return dbconn.addBORRBOOK(bbook);
	}
}
