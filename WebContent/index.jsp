<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css?family=Open+Sans:400,700&display=swap')
	;

:root { --primary-color: rgb(17, 86, 102); 

}

* {
	box-sizing: border-box;
	outline: 0;
}

table {
	margin: 0 auto;
	text-align: left;
	background: rgb(0, 128, 192);
	border-radius: 10px;
}

body {
	margin: 0;
	padding: 0;
	background: var(--primary-color);
	font-family: 'Open sans', sans-serif;
	font-size: 1.1em;
	line-height: 1.6em;
}

.container {
	max-width: 640px;
	margin: 50px auto;
	background: #fff;
	padding: 20px;
	border-radius: 10px;
}

input:focus {
	outline: 1px solid var(--primary-color);
}

label {
	color: rgb(252, 252, 252);
}

</style>
</head>
<body>
	<section class="container">
		<form action="LoginServlet" method="post"
			onsubmit="return validarDados()">
			<table>
				<tr>
					<td><label>Login:</label></td>
					<td><input type="text" id="login" name="login"
						style="border-radius: 7px;"></td>
				</tr>

				<tr>
					<td><label>Senha:</label></td>
					<td><input type="password" id="password" name="password"
						style="border-radius: 7px;"></td>
				</tr>

				<tr>
					<td />
					<td><input type="submit" value="Salvar"
						style="border-radius: 7px; width: 100%; padding: 0 10px;"></td>
				</tr>
			</table>
		</form>
	</section>
	<script type="text/javascript">
		function validarDados() {
			const login = document.getElementById("login").value;
			let senha = ''
			if (document.getElementById("password") != null) {
				senha = document.getElementById("password").value;
			}
			return validarNull(login, senha);
		}

		function validarNull(login, senha) {
			if (login.trim() == '' || senha == '') {
				alert("Um ou mais campos estão vazios")
				return false
			}
			return true;
		}
	</script>

</body>
</html>