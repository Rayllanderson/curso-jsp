<%@page import="com.sun.net.httpserver.HttpsParameters"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css?family=Open+Sans:400,700&display=swap')
	;

:root { --primary-color: rgb(10, 0, 102);
}

* {
	box-sizing: border-box;
	outline: 0;
}

body {
	margin: 0;
	padding: 0;
	background: rgb(0, 153, 204);
	font-family: 'Open sans', sans-serif;
	font-size: 1.1em;
	line-height: 1.3em;
}

.container {
	text-align: center;
	max-width: 640px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border-radius: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h3>Welcome to the system in JSP!</h3>
		<p>What do you wanto to do?</p>
		<a href="CadastrarUser?acao=listartodos"><img
			src="resource/img/user-icon.png" width="40px" height="40px"
			title="Cadastrar Novo Usuário"></a>
		<!-- vai cair no método GET -->
	</div>
</body>
</html>