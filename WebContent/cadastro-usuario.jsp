<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

html, body {
	height: 100%;
}

form {
	text-align: center;
	width: 96%;
	max-width: 600px;
	margin: 0 auto;
	background: rgb(0, 120, 229);
	border-radius: 10px; 
}

form fieldset {
	padding: 20px 30px;
}

form legend {
	text-align: left;
	font-size: 17px;
	color: white;
}

form input[type=text], form input[type="password"] {
	display: inline-block;
	margin-top: 30px;
	width: 30%;
	padding: 5px 13px;
}

form select {
	width: calc(80% + 26px);
	margin-top: 30px;
	padding: 5px 13px;
	display: inline-block;
}

form h3 {
	margin-top: 20px;
}

.input {
	display: inline-block;
	margin-top: 10px;
	text-align: left;
}

.input {
	margin-left: 13px;
	font-size: 16px;
}

textarea {
	margin-top: 30px;
	width: 80%;
	resize: none;
	border: 1px solid;
	height: 120px;
}

form input[type=submit] {
	margin-top: 30px;
	width: 100px;
	height: 40px;
	cursor: pointer;
}
</style>
</head>
<body>
	<form>
		<fieldset>
			<legend>Cadastrar Usuário</legend>
			<div>
				<input type="text" id="login" name="login" placeholder="Username" />
			</div>
			<div>
				<input type="password" id="password" name="password"
					placeholder="Password" />
			</div>
			<div>
				<input type="submit" />
			</div>
		</fieldset>
	</form>

</body>
</html>