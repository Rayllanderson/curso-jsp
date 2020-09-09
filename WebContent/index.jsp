<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>whatever</title>
<style>
</style>
</head>
<body>
	<h2 id="titulo">Java avançado, desu ne...</h2>
	
	<%= "fds otário" %> <!-- é a mesma coisa de < out.print("fds otário"); %> -->
	
	<form action="nome.jsp">
	<input type="text" id="nome" name ="nome">
	<input type="submit" value="Search Google">
	</form>
		
	<%! public int somarNum(int n1, int n2){ // com <%! eu posso declarar métodos , variaveis java
	        return n1 + n2;
	    }
	
	    %>
</body>
</html>