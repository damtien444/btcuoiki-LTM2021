<%@page import="java.util.ArrayList"%>
<%@page import="model.BO.*"%>
<%@page import="model.Bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<style>
table, th, td {
  border:1px solid black;
}
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	Account a = (Account) request.getSession().getAttribute("account");
	if (a != null) {
	%>
	<h1>MY HOME</h1>
	Id :
	<%=a.getId()%>
	<br>
	<br>
	<br>
	<br> Upload some video under 100Mb:
	<form method="post" action="FileUploadServlet"
		enctype="multipart/form-data">
		<div>
			<label for="file">Choose video file to upload</label> <br> <input
				type="file" id="fileUploaded" name="fileUploaded" accept="video/*" >
		</div>
		<div>
			<button>Submit</button>
		</div>
	</form>
	<a href="LogoutServlet"> Log out</a>
	<%
	} else {
		response.sendRedirect("Login.jsp");
	}
	%>
	<form action="UploadHistoryServlet" method="get">
    <input type="submit" value="View History" 
         name="Submit" id="frm1_submit" />
	</form>
</body>
</html>