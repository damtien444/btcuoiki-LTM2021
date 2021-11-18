<%@page import="model.BO.*"%>
<%@page import="model.Bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
	<br> UserName :
	<%=a.getName()%>
	<br> Desc :
	<%=a.getStatus()%>
	<br>
	<br>
	<br>
	<br>
	<form method="post" action = "UploadFile.jsp" enctype="multipart/form-data">
		<div>
			<label for="file">Choose image file to upload</label>
			<br>
			<input
				type="file" id="fileUploaded" name="fileUploaded" accept="video/*" 
				multiple>
		</div>
		<div class="preview">
			<p>No files currently selected for upload</p>
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
</body>
</html>