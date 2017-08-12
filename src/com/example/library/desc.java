package com.example.library;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


@WebServlet("/desc")
public class desc extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public desc() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookidstr=request.getParameter("id");
		int bookid=-1;
		if(bookidstr!=null) {
			try {
				bookid=Integer.parseInt(bookidstr);
				DBConnector dbcon=new DBConnector();
				dbcon.connect();
				BOOK book=dbcon.getBOOK(bookid);
				request.setAttribute("book", book);
				request.getRequestDispatcher("/description.jsp").forward(request, response);
			}catch(ParseException e) {
				e.printStackTrace();
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
