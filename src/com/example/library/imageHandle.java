package com.example.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imageHandle
 */
@WebServlet("/imageHandle")
public class imageHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageHandle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename=request.getParameter("img");
		if(filename!=null) {
			String finPath="/home/vsevolod/ProgramFiles/apache-tomcat-8.5.16/webapps/data/"+filename;
			String mimeType=getServletContext().getMimeType(finPath);
			if(mimeType==null) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			response.setContentType(mimeType);
			ServletOutputStream output=response.getOutputStream();
			try {
				FileInputStream fis=new FileInputStream(new File(finPath));
				int a=0;
				while(a!=-1) {
					a=fis.read();
					output.write(a);
				}
				output.flush();
				fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				output.close();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
