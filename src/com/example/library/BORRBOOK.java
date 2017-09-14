package com.example.library;

import java.io.Serializable;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;


@XmlType(name = "BORRBOOK", propOrder = {

})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BORRBOOK implements Serializable{
	@XmlElement(name = "bbid")
	private int bbid;
	@XmlElement(name = "studname")
	private String studname;
	@XmlElement(name = "studno")
	private int studno;
	@XmlElement(name = "returndate")
	private String returndate;
	@XmlElement(name = "borrowdate")
	private String borrowdate;
	@XmlElement(name = "bookid")
	private int bookid;
	
	
	public BORRBOOK() {
		
	}
	
	public int getBbid() {
		return bbid;
	}
	public void setBbid(int bbid) {
		this.bbid = bbid;
	}
	public String getStudname() {
		return studname;
	}
	public void setStudname(String studname) {
		this.studname = studname;
	}
	public int getStudno() {
		return studno;
	}
	public void setStudno(int studno) {
		this.studno = studno;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	
	
}
