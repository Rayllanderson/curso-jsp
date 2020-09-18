package com.ray.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import com.ray.beans.Foto;
import com.ray.beans.User;
import com.ray.db.jdbc.UsernameExistenteException;
import com.ray.repository.DaoFactory;
import com.ray.repository.UserRepository;

/**
 * Servlet implementation class CadastroUserServlet
 */
@WebServlet("/CadastrarUser")
@MultipartConfig
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
	    if (acao.equals("delete")) {
		Long id = Long.valueOf(request.getParameter("userId"));
		repository.deleteById(id);
		listarTodos(request, response);
	    } else if (acao.equals("editar")) {
		Long id = Long.valueOf(request.getParameter("userId"));
		User userc = repository.findById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
		request.setAttribute("user", userc);
		dispatcher.forward(request, response);
	    } else if (acao.equals("listartodos")) {
		listarTodos(request, response);
	    }
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
	String acao = request.getParameter("acao");
	if (acao != null && acao.equals("reset")) {
	    listarTodos(request, response);
	} else {
	    saveUser(request);
	    listarTodos(request, response);
	}
    }

    private void saveUser(HttpServletRequest request) {
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String telefone = request.getParameter("telefone");
	System.out.println(name + username + password + email);
	System.out.println(telefone);

	// id é diferente de vazio ? seta id, : (senao) null
	User user = new User(!id.isEmpty() ? Long.parseLong(id) : null, name, username, password, email, telefone);
	uploadImage(request, user);
	try {
	    if (user.getId() == null) {
		repository.save(user);
	    } else {
		repository.update(user);
	    }
	} catch (UsernameExistenteException e) {
	    request.setAttribute("user", user);
	    request.setAttribute("msg", e.getMessage());
	}
    }

    private void listarTodos(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
	request.setAttribute("usuarios", repository.findAll());
	dispatcher.forward(request, response);
    }

    private void uploadImage(HttpServletRequest request, User user) {
	try {
	    /* file upload */
	    if (ServletFileUpload.isMultipartContent(request)) {// validando de form é de upload
		Part imagem = request.getPart("foto");
		new Base64();
		String fotoBase64 = Base64.encodeBase64String(streamToByte(imagem.getInputStream()));
		Foto foto = new Foto(fotoBase64, imagem.getContentType());
		System.out.println(fotoBase64);
		user.setFoto(foto);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // converte a entrada de fluxo de dados para um array de byte
    private byte[] streamToByte(InputStream imagem) throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int reads = imagem.read();
	while (reads != -1) {
	    baos.write(reads);
	    reads = imagem.read();
	}
	return baos.toByteArray();
    }

}
