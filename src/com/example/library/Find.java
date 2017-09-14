package com.example.library;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/Find")
public class Find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Find() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnector dbconn=new DBConnector();
		dbconn.connect();
		if(request.getParameter("searchQuery")!=null) {
			List<BOOK> listBook=dbconn.getFindBooks(request.getParameter("searchQuery"));
			if(listBook.size()<=0){
				request.setAttribute("message", "Nothing has been found!");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}else {
				request.setAttribute("foundList", listBook);
				request.getRequestDispatcher("/found.jsp").forward(request, response);
				return;
			}
		}
	}

}
