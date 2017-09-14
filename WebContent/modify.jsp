<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						BOOK book=(BOOK)request.getAttribute("book");
						if(book.getTitle()!=null){
					%>
					<form action="ApplyModifToDb" method="post" enctype="multipart/form-data"
					class="addbookform">			
						<input type="file" accept="image/*" name="file">
      					<input type="hidden" name="id" value="<%= book.getBid() %>" required><br>
      					<span>Book title:</span><br>
      					<input type="text" name="title" value="<%= book.getTitle() %>" required><br>
      					<span>Retail price($):</span><br>
      					<input type="number" name="price" step="0.1" value="<%= book.getRetprice() %>" required><br>
      					<span>Publisher:</span><br>
      					<input type="text" name="publisher" value="<%= book.getPublisher() %>" required><br>
      					<span>Quantity:</span><br>
      					<input type="number" name="quantity" value="<%= book.getQuantity() %>" required><br>
      					<span>Author:</span><br>
      					<input type="text" name="author" value="<%= book.getAuthor() %>" required><br>
      					<span>Description:</span><br>
      					<input type="text" name="description" value="<%= book.getDescription() %>" required><br>
      					<%
      						List<String> allGenres=new ArrayList<String>();
      						allGenres.add("History");
      						allGenres.add("Thriller");
      						allGenres.add("Romance/Erotica");
      						allGenres.add("Satire");
      						allGenres.add("Horror");
      						allGenres.add("Religious/Inspirational");
      						allGenres.add("Health/Medicine");
      						allGenres.add("Children's books");
      						allGenres.add("Dictionary");
      						String[] chGenres=null;
      						if(book.getGenre()!=null){
      							chGenres=book.getGenre().split(";");
      							for(int i=0;i<chGenres.length;i++){
      								%>
      									<input type="checkbox" name="genreB" value="<%= chGenres[i]%>" checked="true"><span><%= chGenres[i]%></span>
      								<%
      								if(i==4){
      									%>
      									<br/>
      								<%	
      								}
      								allGenres.remove(chGenres[i]);
      							}
      						}
      						for(int i=0;i<allGenres.size();i++){
      						%>
      							<input type="checkbox" name="genreB" value="<%= allGenres.get(i) %>"><span><%= allGenres.get(i) %></span>
      						<%
      							if((i==(allGenres.size()-1))||(8-(allGenres.size()-1)+i==4)){
      								%>
      									<br/>
      								<% 
      							}
      						}
      					%>
  						<input type="submit" value="Modify Book">
					</form>
					<% 
						}else{
							request.setAttribute("message","Error!Nothing has been passed!");
							request.getRequestDispatcher("/error.jsp").forward(request, response);
						}
					%>
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