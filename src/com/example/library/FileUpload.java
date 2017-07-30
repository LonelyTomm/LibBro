package com.example.library;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet(
        name = "FileUpload",
        urlPatterns = "/FileUpload"
)
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String dirup="/home/vsevolod/ProgramFiles/apache-tomcat-8.5.16/webapps/data";
		Part filePart=request.getPart("file");
		BOOK book=new BOOK();
		if(filePart!=null) {
			String fileName=Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream fileContent=filePart.getInputStream();
			String baseName=FilenameUtils.getBaseName(fileName);
			String extName=FilenameUtils.getExtension(fileName);
			if((!extName.toLowerCase().equals("png"))&&(!extName.toLowerCase().equals("jpg"))&&(!extName.toLowerCase().equals("jpeg"))) {
				request.setAttribute("ext", extName.toLowerCase());
				request.setAttribute("message", "Sorry, that type of file is unsupported.It should be png or jpg!");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
			String finName=baseName+System.currentTimeMillis()+"."+extName;
			String finPath=dirup+File.separator+finName;
			File tgFile=new File(finPath);
			FileUtils.copyInputStreamToFile(fileContent, tgFile);
			book.setImgpath(finName);
		}
		book.setTitle(request.getParameter("title"));
		book.setRetprice(Double.valueOf(request.getParameter("price")));
		book.setPublisher(request.getParameter("publisher"));
		book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		book.setAuthor(request.getParameter("author"));
		book.setDescription(request.getParameter("description"));
		String genre="";
		String[] Genres=request.getParameterValues("genreB");
		for(int i=0;i<Genres.length;i++) {
			genre=genre+Genres[i]+";";
		}
		book.setGenre(genre);
		DBConnector dbs=new DBConnector();
		dbs.connect();
		dbs.addBOOK(book);
		request.setAttribute("message", "Added successfully!");
		request.getRequestDispatcher("/success.jsp").forward(request, response);
		
	}
	
	

}
