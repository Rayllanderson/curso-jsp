package com.ray.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.beans.Product;
import com.ray.db.jdbc.ProdutoExistenteException;
import com.ray.repository.DaoFactory;
import com.ray.repository.ProductRepository;

/**
 * Servlet implementation class CdastroProdutoServlet
 */
@WebServlet("/CadastrarProduto")
public class CadastroProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductRepository repository;

    public CadastroProdutoServlet() {
	super();
	this.repository = DaoFactory.createProdutoDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    if (acao.equals("listartodos")) {
		listarTodos(request, response);
	    } else if (acao.equals("delete")) {
		Long id = Long.valueOf(request.getParameter("produtoId"));
		repository.deleteById(id);
		listarTodos(request, response);
	    } else if (acao.equals("editar")) {
		Long id = Long.valueOf(request.getParameter("produtoId"));
		Product p = repository.findById(id);
		// entao esse cara ai, só vai servir pra ser colocado no layout lá
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastro-produto.jsp");
		request.setAttribute("produto", p);
		dispatcher.forward(request, response);
	    }
	    ;
	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null && acao.equals("reset")) {
	    listarTodos(request, response);
	} else {
	    saveProduct(request);
	    listarTodos(request, response);
	}
    }

    private void listarTodos(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-produto.jsp");
	request.setAttribute("produtos", repository.findAll());
	dispatcher.forward(request, response);
    }

    public void saveProduct(HttpServletRequest request) {
	String nome = request.getParameter("nome");
	Product p = null;
	try {
	    String id = request.getParameter("id");
	    Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
	    BigDecimal valor = new BigDecimal(request.getParameter("valor").replace(",", "."));
	    p = new Product(!id.isEmpty() ? Long.parseLong(id) : null, nome, quantidade, valor);
	    if (p.getId() == null) {
		repository.save(p);
	    } else {
		repository.update(p);
	    }
	} catch (ProdutoExistenteException e) {
	    request.setAttribute("produto", p);
	    request.setAttribute("error", e.getMessage());
	}
    }

}
