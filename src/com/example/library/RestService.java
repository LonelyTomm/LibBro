package com.example.library;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public String addBORRBOOK(String bbook){
		DBConnector dbconn=new DBConnector();
		dbconn.connect();
		StringReader sr=new StringReader(bbook);
		try {
			JAXBContext jaxvContent=JAXBContext.newInstance(BORRBOOK.class);
			Unmarshaller unmarshaller=jaxvContent.createUnmarshaller();
			BORRBOOK borrbook=(BORRBOOK)unmarshaller.unmarshal(sr);
			SimpleDateFormat smp=new SimpleDateFormat("yyyy-MM-dd");
			Date retDate=smp.parse(borrbook.getReturndate());
			Date date=new Date();
			if(retDate.compareTo(date)>=0) {
				borrbook.setBorrowdate(smp.format(date));
				return dbconn.addBORRBOOK(borrbook);
			}else {
				return "Returndate can't be earlier than tomorrow!";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "Error";
		}
		
		
		
	}
}
