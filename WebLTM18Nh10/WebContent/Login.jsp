<%@page import="model.bean.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đăng nhập</title>
</head>
<body>
<%
	Account a = (Account)request.getSession().getAttribute("account");
	if (a==null){
%>
		<h1>LOGIN</h1>
		<form action="GotoMyHomeServlet" method="post">
			id <input type="text" name="id" value = "abc"><br>
			pw <input type="password" name="pw" value = "123"><br>
			<input type="submit" value="Đăng nhập"> 
		</form>
<%
	} else{
		response.sendRedirect("MyHome.jsp");
	}
%>
</body>
</html>