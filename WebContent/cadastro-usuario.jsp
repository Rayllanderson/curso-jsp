
<%@page import="com.ray.beans.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<script>
	$(document).ready(
			function() {
				function limpa_formulário_cep() {
					// Limpa valores do formulário de cep.
					$("#rua").val("");
					$("#bairro").val("");
					$("#cidade").val("");
					$("#uf").val("");
				}
				//Quando o campo cep perde o foco.
				$("#cep").blur(
						function() {

							//Nova variável "cep" somente com dígitos.
							var cep = $(this).val().replace(/\D/g, '');

							//Verifica se campo cep possui valor informado.
							if (cep != "") {

								//Expressão regular para validar o CEP.
								var validacep = /^[0-9]{8}$/;

								//Valida o formato do CEP.
								if (validacep.test(cep)) {

									//Preenche os campos com "..." enquanto consulta webservice.
									$("#rua").val("...");
									$("#bairro").val("...");
									$("#cidade").val("...");
									$("#uf").val("...");

									//Consulta o webservice viacep.com.br/
									$.getJSON("https://viacep.com.br/ws/" + cep
											+ "/json/?callback=?", function(
											dados) {

										if (!("erro" in dados)) {
											//Atualiza os campos com os valores da consulta.
											$("#rua").val(dados.logradouro);
											$("#bairro").val(dados.bairro);
											$("#cidade").val(dados.localidade);
											$("#uf").val(dados.uf);
										} //end if.
										else {
											//CEP pesquisado não foi encontrado.
											limpa_formulário_cep();
											alert("CEP não encontrado.");
										}
									});
								} //end if.
								else {
									//cep é inválido.
									limpa_formulário_cep();
									alert("Formato de CEP inválido.");
								}
							} //end if.
							else {
								//cep sem valor, limpa formulário.
								limpa_formulário_cep();
							}
						});
			});
</script>
</head>
<body>

	<form class="form-horizontal" method="post" action="CadastrarUser"
		method="POST" id="formUser" enctype="multipart/form-data">
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
							<div style="display: flex; margin-right: 105%;">
								<div>
									<a href="acesso-liberado.jsp" style="margin-top: -5px">Inicio</a>
								</div>

								<div>
									<a href="index.jsp"
										style="margin-left: 10px; margin-right: 105%; margin-top: -5px">Sair</a>
								</div>
							</div>

						</div>
					</div>

					<div class="form-group">
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
									required type="text" style="width: 23.3%" maxlength="250"
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
									class="form-control input-md" required type="text"
									style="width: 100%;" value="${user.username}" maxlength="50">
							</div>

						</div>


						<div class="form-group">
							<label class="col-md-2 control-label" for="Nome">Password
								<h11>*</h11>
							</label>
							<div class="col-md-2">
								<input id="password" name="password"
									class="form-control input-md" required="" type="password"
									style="width: 100%;" value="${user.password}" maxlength="100">
							</div>
						</div>



						<div class="form-group">
							<label class="col-md-2 control-label" for="telefone">Telefone
								<h11>*</h11>
							</label>
							<div class="col-md-2">
								<input id="telefone" name="telefone"
									class="form-control input-md" required type="text"
									data-mask="(00) 00000-0000" data-mask-selectonfocus="true"
									style="width: 100%;" value="${user.telefone}">
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
										name="email" class="form-control"
										placeholder="email@email.com" required="" type="text"
										pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
										style="width: 34%;" value="${user.email}">
								</div>
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-2 control-label" for="ativo">Ativo </label>
							<div class="col-md-2">
								<input id="ativo" name="ativo" class="form-control input-md"
									type="checkbox" style="width: 10%"
									<%User user = (User) request.getAttribute("user");
											if (user != null) {
												if (user.isAtivo()) {
													out.print(" ");
													out.print("checked=\"checked\"");
													out.print(" ");
												}
											}%>>
							</div>
						</div>

						<p style="margin-left: 13%">Foto do Perfil</p>
						<input type="file" id="foto" name="foto" value="foto" size="50"
							style="margin-left: 13%" />
						<p style="margin-left: 13%; margin-top: 10px;">Curriculo</p>
						<input type="file" id="curriculo" name="curriculo"
							value="curriculo" size="50"
							style="margin-left: 13%; margin-bottom: 10px" />


						<!-- Button (Double) -->
						<div class="form-group">
							<label class="col-md-2 control-label" for="Cadastrar"></label>
							<div class="col-md-8">
								<button id="Cadastrar" name="Cadastrar" class="btn btn-success"
									type="Submit">Salvar</button>
								<button id="Cancelar" name="Cancelar" class="btn btn-danger"
									onclick="document.getElementById('formUser').action = 'CadastrarUser?acao=reset'">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</form>

	<form method="post" action="Pesquisar">
		<ul>
			<li>
				<table style="margin-top: 5%; margin-bottom: -5%">
					<tr>
						<td>
							<div class="input-group">
								<input type="text"
									id="consulta" name="consulta" class="form-control" style="margin-left:400%; border: 1px solid;"  title="Pesquisar contato" >
							</div></td>
						<td><input class="btn btn-success" type="submit"
							value="Pesquisar" style="margin-left: -51%"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>


	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<tr class="row100 head" style="margin-left: 10px;">
									<th class="cell100 column1">Nome</th>
									<th class="cell100 column2">Perfil</th>
									<th class="cell100 column3">Telefone</th>
									<th class="cell100 column4">Excluir</th>
									<th class="cell100 column5">Editar</th>
									<th class="cell100 column5">Ativo</th>
								</tr>
							</thead>
						</table>
					</div>


					<div class="table100-body js-pscroll">
						<c:forEach items="${usuarios}" var="user">
							<table>
								<tbody>
									<tr class="row100 body">
										<td class="cell100 column1" style="margin-left: 10px;"><c:out
												value="${user.name}"></c:out></td>

										<c:if test="${!user.miniatura.isEmpty()}">
											<td><a
												href="CadastrarUser?acao=download&tipo=img&userId=${user.id}"><img
													src='<c:out value="${user.miniatura}"/>'
													alt="Imagem de perfil" width="30px" height="30px"> </a></td>
										</c:if>

										<c:if test="${user.miniatura.isEmpty()}">
											<td><img src="resource\img\User_icon_BLACK-01.png"
												width="30px" height="30px" /></td>
										</c:if>

										<c:if
											test="${!user.getCurriculo().getArquivoBase64().isEmpty()}">
											<td><a
												href="CadastrarUser?acao=download&tipo=curriculo&userId=${user.id}">
													<img alt="" src="resource/img/unnamed.png"
													title="Curriculo" width="30px" height="30px" />
											</a></td>
										</c:if>

										<c:if
											test="${user.getCurriculo().getArquivoBase64().isEmpty()}">
											<td><a> <img alt="" src="resource/img/unnamed.png"
													title="Curriculo" onclick="alert('Sem curriculo')"
													width="30px" height="30px" /></a></td>
										</c:if>

										<td class="cell100 column3"><c:out
												value="${user.telefone}"></c:out></td>
										<td class="cell100 column4"><a
											href="CadastrarUser?acao=delete&userId=${user.id}"><img
												src="resource/img/excluir.png" width="30px" height="30px"
												title="Excluir"
												onclick="return confirm('tem certeza que deseja excluir?')"></a></td>
										<td class="cell100 column5"><a
											href="CadastrarUser?acao=editar&userId=${user.id}"><img
												src="resource/img/edit.png" width="30px" height="30px"
												title="Editar"></a></td>
										<td>
											<div class="col-md-2">
												<c:if test="${user.ativo}">
													<p>Sim</p>
												</c:if>
												<c:if test="${!user.ativo}">
													<p>Não</p>
												</c:if>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		let mensagem = "${msg}";
		console.log(mensagem);
		if (!mensagem == '') {
			alert(mensagem);
		}
		

	    if ( window.history.replaceState ) {
	        window.history.replaceState( null, null, window.location.href );
	    }

	</script>

	<script src="javascript/jquery.mask.js"></script>

</body>
</html>