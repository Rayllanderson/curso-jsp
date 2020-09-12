<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<link href="css/style1.css" type="text/css" rel="stylesheet" />
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>

	<form class="form-horizontal" action="CadastrarUser" method="post">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">Cadastro de Cliente</div>

				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-11 control-label">
							<p class="help-block">
								<h11>*</h11>
								Campo Obrigatório
							</p>
						</div>
					</div>

					<!-- NOME-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Nome <h11>*</h11></label>
						<div class="col-md-8">
							<input id="name" name="name" class="form-control input-md"
								required="" type="text" style="width: 23.3%">
						</div>
					</div>



					<!-- USERNAME-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Username
							<h11>*</h11>
						</label>
						<div class="col-md-2">
							<input id="username" name="username"
								class="form-control input-md" required="" type="text"
								style="width: 100%;">
						</div>

						<!-- Multiple Radios (inline) -->

						<label class="col-md-1 control-label" for="radios">Sexo <h11>*</h11></label>
						<div class="col-md-4">
							<label required="" class="radio-inline" for="radios-0"> <input
								name="sexo" id="sexo" value="feminino" type="radio" required>
								Feminino
							</label> <label class="radio-inline" for="radios-1"> <input
								name="sexo" id="sexo" value="masculino" type="radio">
								Masculino
							</label>
						</div>
					</div>


					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Password
							<h11>*</h11>
						</label>
						<div class="col-md-2">
							<input id="password" name="password"
								class="form-control input-md" required="" type="password"
								style="width: 100%;">
						</div>
					</div>




					<!-- Prepended text-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="prependedtext">Email
							<h11>*</h11>
						</label>
						<div class="col-md-5">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input id="email"
									name="email" class="form-control" placeholder="email@email.com"
									required="" type="text"
									pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
									style="width: 34%;">
							</div>
						</div>
					</div>

					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" class="btn btn-success"
								type="Submit">Cadastrar</button>
							<button id="Cancelar" name="Cancelar" class="btn btn-danger"
								type="Reset">Cancelar</button>
						</div>
					</div>

				</div>
			</div>
		</fieldset>
	</form>


	<table >

		<c:forEach items="${usuarios}" var="user">
			<tr >
				<td style="width: 150px;"><c:out value="${user.name}"></c:out></td>
				<td><c:out value="${user.username}"></c:out></td>
				<td><c:out value="${user.sexo}"></c:out></td>
				<td><a href="CadastrarUser?acao=delete&userId=${user.id}" style="margin-left: 50px">Excluir</a></td>
				<td><a href="CadastrarUser?acao=editar&userId=${user.id}" style="margin-left: 10px">Editar</a></td>
				
			</tr>
		</c:forEach>

	</table>

</body>
</html>