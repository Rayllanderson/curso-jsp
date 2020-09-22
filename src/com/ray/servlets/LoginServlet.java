package com.ray.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.repository.DaoFactory;
import com.ray.repository.UserRepository;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserRepository userRepository = DaoFactory.createUserDao();

    public LoginServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	RequestDispatcher dispatcher;
	if (userRepository.login(username, password)) {
	    dispatcher = request.getRequestDispatcher("acesso-liberado.jsp");
	} else {
	    request.setAttribute("loginInvalido", "Login ou senha incorretos");
	    dispatcher = request.getRequestDispatcher("index.jsp");
	}
	dispatcher.forward(request, response);
    }

}
