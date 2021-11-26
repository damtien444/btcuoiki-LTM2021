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
	<h1>HISTORY</h1>
	<br> Uploaded videos :
	<div class="preview">
		<% 	
			ArrayList<PredictResult> resultList = (ArrayList<PredictResult>) request.getSession().getAttribute("resultList");
			if (resultList != null && resultList.size() > 0){
				response.setIntHeader("Refresh", 3);
				out.print("<table style=width:100% >");
				out.print("<tr>");
				out.print("   <th>Video name</th>");
				out.print("   <th>Prediction result</th>");
				out.print("</tr>");
				for(PredictResult result : resultList){
					out.print("<tr>");
					out.print("	 <td> " + result.getFileName() + "</td>");
					out.print("	 <td> " + result.getResult() + "</td>");
					out.print("</tr>");
				}
				out.print("</table>");
			}
			else{
				out.print("<p>You haven't uploaded anything</p>");
			}
		%>

	</div>
	<form action="MyHome.jsp" method="get">
    <input type="submit" value="Go to Home page" 
         name="Submit" id="frm1_submit" />
	</form>
	<a href="LogoutServlet"> Log out</a>
</body>
</html>