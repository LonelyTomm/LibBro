package com.example.library;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Modify() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");
		if(sid==null||sid.equals("")) {
			request.setAttribute("message", "Bid haven't been passed!");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		int id=-1;
		try {
			id=Integer.parseInt(sid);
			if(id>0) {
				DBConnector dbconn=new DBConnector();
				dbconn.connect();
				BOOK book=dbconn.getBOOK(id);
				if(book.getTitle()==null) {
					request.setAttribute("message", "Book with such bid doesn't exist!");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("book", book);
					request.getRequestDispatcher("/modify.jsp").forward(request, response);
					return;
				}
			}else {
				request.setAttribute("message", "Bid format is wrong!");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
