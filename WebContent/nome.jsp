<%@page import="com.sun.net.httpserver.HttpsParameters"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% response.sendRedirect("https://www.google.com/search?client=opera&q=" + request.getParameter("nome") + "&sourceid=opera&ie=UTF-8&oe=UTF-8"); %>
	
</body>
</html>