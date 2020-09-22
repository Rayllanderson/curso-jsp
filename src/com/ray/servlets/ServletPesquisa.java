package com.ray.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.beans.User;
import com.ray.repository.DaoFactory;
import com.ray.repository.UserRepository;

/**
 * Servlet implementation class ServletPesquisa
 */
@WebServlet("/Pesquisar")
public class ServletPesquisa extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserRepository repository = DaoFactory.createUserDao();

    public ServletPesquisa() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	String consulta = request.getParameter("consulta");
	if (consulta != null) {
	    List<User> list = repository.findUsersByName(consulta);
	    if (!list.isEmpty()) {
		request.setAttribute("usuarios", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
		dispatcher.forward(request, response);
	    } else {
		request.setAttribute("msg", "Nenhum usuário encontrado");
		request.setAttribute("usuarios", repository.findAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
		dispatcher.forward(request, response);
	    }
	}
    }

}
