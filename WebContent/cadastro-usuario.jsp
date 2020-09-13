<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<link href="css/style1.css" type="text/css" rel="stylesheet" />
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/table.css">


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

					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">ID</label>
						<div class="col-md-8">
							<input id="id" name="id" class="form-control input-md"
								type="text" style="width: 23.3%" value="${user.id}"
								readonly="readonly">
						</div>
					</div>

					<!-- NOME-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Nome <h11>*</h11></label>
						<div class="col-md-8">
							<input id="name" name="name" class="form-control input-md"
								required="" type="text" style="width: 23.3%"
								value="${user.name}">
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
								style="width: 100%;" value="${user.username}">
						</div>

						<!-- Multiple Radios (inline) -->

						<label class="col-md-1 control-label" for="radios">Sexo <h11>*</h11></label>
						<div class="col-md-4">
							<label required="" class="radio-inline" for="radios-0"> <input
								name="sexo" id="sexo" value="feminino" type="radio" required
								value="${user.sexo}"> Feminino
							</label> <label class="radio-inline" for="radios-1"> <input
								name="sexo" id="sexo" value="masculino" type="radio"
								value="${user.sexo}"> Masculino
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
								style="width: 100%;" value="${user.password}">
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
									style="width: 34%;" value="${user.email}">
							</div>
						</div>
					</div>

					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" class="btn btn-success"
								type="Submit">Salvar</button>
							<button id="Cancelar" name="Cancelar" class="btn btn-danger"
								type="Reset">Cancelar</button>
						</div>
					</div>

				</div>
			</div>
		</fieldset>
	</form>




	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table">

					<div class="row header">
						<div class="cell">Nome</div>
						<div class="cell">Username</div>
						<div class="cell">Sexo</div>
						<div class="cell">Excluir</div>
						<div class="cell">Editar</div>
						<div class="cell"></div>
					</div>
			<c:forEach items="${usuarios}" var="user">
					<div class="row">
							<div class="cell" data-title="Nome">
								<c:out value="${user.name}"></c:out>
							</div>
							<div class="cell" data-title="Username">
								<c:out value="${user.username}"></c:out>
							</div>
							<div class="cell" data-title="Sexo">
								<c:out value="${user.sexo}"></c:out>
							</div>
							<div class="cell" data-title="Location">
								<a href="CadastrarUser?acao=delete&userId=${user.id}"><img src="resource/img/excluir.png" width="30px" height="30px"></a>
							</div>
							<div class="cell" data-title="Location">
								<a href="CadastrarUser?acao=editar&userId=${user.id}"><img src="resource/img/edit.png" width="30px" height="30px"></a>
							</div>
						</div>
			</c:forEach>
				</div>
			</div>
		</div>
	</div>






</body>
</html>