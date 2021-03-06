<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.library.DBConnector,com.example.library.*,java.util.List,java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Library Management</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style>
		@import url('https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Josefin+Sans');
	</style>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-4 text-center">
					<a href="./index.jsp" id="logo"><h1>LibBro</h1></a>
				</div>
				<div class="col-md-4 text-center">
					<form action="Find" class="searchlog" method="POST">
						<input type="text" name="searchQuery" required>
						<button>Find!</button>
					</form>
				</div>
				<div class="col-md-4 text-center">
				</div>
			</div>
		</div>
	</div>
	<div class="functnav">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 text-center">
					<div class="functitems">
						<a href="./add.jsp">Add</a>
						<a href="./logs.jsp">Borrow Log</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="content-body">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div class="sidebar-menu">
						<a href="./Genre?gn=History">History</a><br>
						<a href="./Genre?gn=Thriller">Thriller</a><br>
						<a href="./Genre?gn=Romance">Romance/Erotica</a><br>
						<a href="./Genre?gn=Satire">Satire</a><br>
						<a href="./Genre?gn=Horror">Horror</a><br>
						<a href="./Genre?gn=Religious">Religious/Inspirational</a><br>
						<a href="./Genre?gn=Health">Health/Medicine</a><br>
						<a href="./Genre?gn=Children">Children's books</a><br>
						<a href="./Genre?gn=Dictionary">Dictionary</a><br>
					</div>
				</div>
				<div class="col-md-9">
						<%
			List<BOOK> blist=(List<BOOK>)request.getAttribute("booklist");
			String Strpage=(String)request.getAttribute("page");
			String genre=(String)request.getAttribute("genre");
			int inpage=1;
			int firstBook=0;
			if(Strpage!=null){
				try{
					inpage=Integer.parseInt(Strpage);
					int pgCount=(blist.size()-1)/16;
					if((blist.size()-1)>(pgCount*16))
						pgCount++;
					if(inpage<=pgCount){
						if(inpage==1){
							firstBook=0;
						}else{
							firstBook=((inpage-1)*16);
						}	
					}
				}catch(NumberFormatException e){
					request.setAttribute("message","Attribute page is in wrong format. It must be int number.");
					request.getRequestDispatcher("/error.jsp").forward(request,response);
				}finally{
					
				}
			}
			if(blist!=null){
				int lastElement=firstBook+16;
				if(firstBook+16>blist.size()){
					lastElement=blist.size();
				}
				for(int i=firstBook;i<lastElement;i++){
					if(((i+1)%4==0)||(i==0)){
						%>
							<div class="row">
						<%
					}
					BOOK book=new BOOK();
					book=blist.get(i);
					%>
						<div class="col-md-3 text-center">
							<div class="bookprof">
								<a href="http://localhost:8080/LibBro/desc?id=<%= book.getBid() %>">
									<img src="http://localhost:8080/LibBro/imageHandle?img=<%= book.getImgpath() %>" alt="Book">
								</a><br>
								<a href="http://localhost:8080/LibBro/desc?id=<%= book.getBid() %>" id="title"><%= book.getTitle() %></a><br>
								<span><%= book.getDescription() %></span>
							</div>
						</div>
					<%
					if(((i+1)%4==0)||(i==blist.size())){
						%>
							</div>
						<%
					}
				}
			}
		%>
					
					<div class="row">
						<div class="col-md-12 text-center">
							<div class="page-nav-butt">
								<%
								int pgCount=(blist.size()-1)/16;
								if((blist.size()-1)>(pgCount*16))
									pgCount++;
								for(int i=0;i<pgCount;i++){
								%>
									<a href="http://localhost:8080/LibBro/Genre?gn=<%= genre %>&page=<%= i+1 %>"><%= i+1 %></a>
								<% 
								}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<span id="credits">@SwagTeam<br>
					All Rights Reserved<br>
					</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>