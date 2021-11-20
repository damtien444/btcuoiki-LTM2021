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
	<br> UserName :
	<%=a.getName()%>
	<br> Desc :
	<%=a.getStatus()%>
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
		<div class="preview">
		
			<% 	
				ArrayList<String> fileNameList = (ArrayList<String>) request.getSession().getAttribute("fileNameList");
				if (fileNameList != null && fileNameList.size() > 0){
					response.setIntHeader("Refresh", 3);
					out.print("<table style=width:75% >");
					out.print("<tr>");
					out.print("   <th>Video name</th>");
					out.print("   <th>Prediction result</th>");
					out.print("</tr>");
					for(String fileName : fileNameList){
						out.print("<tr>");
						out.print("	 <td> " + fileName + "</td>");
						out.print("	 <td>");
						out.print(ModelPredictorBO.getInstance().getCurrentProgress());
						out.print("	 </td>");
						out.print("</tr>");
					}
					out.print("</table>");
				}
				else{
					out.print("<p>You haven't uploaded anything</p>");
				}
			%>

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