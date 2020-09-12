package com.ray.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.beans.Sexo;
import com.ray.beans.User;
import com.ray.repository.DaoFactory;
import com.ray.repository.UserRepository;

/**
 * Servlet implementation class CadastroUserServlet
 */
@WebServlet("/CadastrarUser")
public class CadastroUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserRepository repository;

    public CadastroUserServlet() {
	super();
	repository = DaoFactory.createUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String acao = request.getParameter("acao");
	if (acao != null) {
	    Long id = Long.valueOf(request.getParameter("userId"));
	    if (acao.equals("excluir")) {
		repository.deleteById(id);
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
	    request.setAttribute("usuarios", repository.findAll());
	    dispatcher.forward(request, response);
	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
	String name = request.getParameter("name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String sexo = request.getParameter("sexo").toUpperCase().substring(0, 1);
	System.out.println(name + username + password + email);
	System.out.println(sexo);
	User user = new User(null, name, username, password, email, Sexo.valueOf(sexo));
	repository.save(user);

	// dispatcher serve pra redirecionar a partir daqui redirecionando
	RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
	request.setAttribute("usuarios", repository.findAll());
	dispatcher.forward(request, response);
    }

}
