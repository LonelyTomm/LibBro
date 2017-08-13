package com.example.library;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Path("/BookManage")
@ApplicationPath("/webservice")
public class RestService extends Application{
	
	//http://localhost:8080/LibBro/webservice/BookManage/getHello
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
