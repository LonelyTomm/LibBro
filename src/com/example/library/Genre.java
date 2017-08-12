package com.example.library;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Genre")
public class Genre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Genre() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String genre=request.getParameter("gn");
		if(genre!=null&&(!genre.equals(""))) {
			DBConnector dbcon=new DBConnector();
			dbcon.connect();
			List<BOOK> bookls=dbcon.getGnBOOKS(genre);
			if(bookls!=null) {
				request.setAttribute("booklist", bookls);
				request.getRequestDispatcher("/genre.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "No books with that genre!");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("message", "Genre can't be null or empty");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
