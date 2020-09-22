<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="com.ray.beans.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ray.beans.Product" %>
<%@ page import= "java.io.IOException" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar Produtos</title>

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


</head>
<body>
	<form class="form-horizontal" action="CadastrarProduto" method="post"
		id="formUser">
		<fieldset>
			<div class="panel panel-primary">
				<div class="panel-heading">Cadastro de Produtos</div>

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
						<label class="col-md-2 control-label" for="Nome">ID</label>
						<div class="col-md-8">
							<input id="id" name="id" class="form-control input-md"
								type="text" style="width: 23.3%" value="${produto.id}"
								readonly="readonly">
						</div>
					</div>

					<!-- NOME-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Nome <h11>*</h11></label>
						<div class="col-md-8">
							<input id="nome" name="nome" class="form-control input-md"
								required type="text" style="width: 23.3%"
								value="${produto.nome}" maxlength="100">
						</div>
					</div>


					<!-- USERNAME-->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Quantidade
							<h11>*</h11>
						</label>
						<div class="col-md-2">
							<input id="quantidade" name="quantidade"
								class="form-control input-md" required type="text"
								style="width: 100%;" value="${produto.quantidade}" maxlength="7">
						</div>
					</div>



					<div class="form-group">
						<label class="col-md-2 control-label" for="Nome">Valor <h11>*</h11>
						</label>
						<div class="col-md-2">
							<input id="valor" name="valor" class="form-control input-md"
								required type="text" style="width: 100%;" maxlength="10"
								value="${produto.valor}">
						</div>
					</div>



			<%!public void setSelectedIfEquals(JspWriter out, HttpServletRequest request, String option) throws IOException {
								Product prod = (Product) request.getAttribute("produto");
								if (prod != null) {
									    if (prod.getCategoria().equals(option)) {
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
								}
							}
							%>


					<div class="form-group">
					<label class="col-md-2 control-label" for="Nome">Categoria
						</label>
						<div class="col-md-2">
						<select id="categoria" name="categoria" style="margin-top: 7.5px; width: 50%; border-radius: 5px">
							
							
							
							
							<option value="Alimentos"
							
							<%setSelectedIfEquals(out, request, "Alimentos"); %>
						
							>Alimentos</option>
							<option value="Brinquedos"		
							<%setSelectedIfEquals(out, request, "Brinquedos"); %>
							>Brinquedos</option>
							
							<option value="Eletrônicos"
							<%setSelectedIfEquals(out, request, "Eletrônicos"); %>
							>Eletrônicos</option>
							
							<option value="Útils"
							<%setSelectedIfEquals(out, request, "Útils"); %>
							>Útils</option>
							
							<option value="Outros"
							<%setSelectedIfEquals(out, request, "Outros"); %>
							>Outros</option>
						</select>
						</div>
					</div>

					<!-- Button (Double) -->
					<div class="form-group">
						<label class="col-md-2 control-label" for="Cadastrar"></label>
						<div class="col-md-8">
							<button id="Cadastrar" name="Cadastrar" class="btn btn-success"
								type="Submit">Salvar</button>
							<button id="Cancelar" name="Cancelar" class="btn btn-danger"
								onclick=history.go(-1)>Cancelar</button>
						</div>
					</div>


				</div>
			</div>
		</fieldset>
	</form>




	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<tr class="row100 head">
									<th class="cell100 column1">Nome</th>
									<th class="cell100 column2">Quantidade</th>
									<th class="cell100 column3">Valor</th>
									<th class="cell100 column4">Excluir</th>
									<th class="cell100 column5">Editar</th>
								</tr>
							</thead>
						</table>
					</div>

					<div class="table100-body js-pscroll" style="margin-top: 0px;">
						<c:forEach items="${produtos}" var="produto">
							<table>
								<tbody>
									<tr class="row100 body">
										<td class="cell100 column1"><c:out
												value="${produto.nome}"></c:out></td>
										<td class="cell100 column2"><c:out
												value="${produto.quantidade}"></c:out></td>
										<td class="cell100 column3">R$ <c:out
												value="${produto.valor}"></c:out></td>
										<td class="cell100 column4"><a
											href="CadastrarProduto?acao=delete&produtoId=${produto.id}"><img
												src="resource/img/excluir.png"
												onclick="return confirm('tem certeza que deseja excluir?')"
												width="30px" height="30px" title="Excluir"></a></td>
										<td class="cell100 column5"><a
											href="CadastrarProduto?acao=editar&produtoId=${produto.id}"><img
												src="resource/img/edit.png" width="30px" height="30px"
												title="Editar"></a></td>
									</tr>
								</tbody>
							</table>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="javascript/index2.js"></script>
	<script type="text/javascript">
		const msg = "${error}"
		if (!msg == '') {
			alert(msg)
		}
	</script>
</body>
</html>