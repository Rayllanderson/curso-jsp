package com.ray.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ray.beans.Arquivo;
import com.ray.beans.User;
import com.ray.db.DB;
import com.ray.db.DbException;
import com.ray.repository.UserRepository;

public class UserDaoJDBC implements UserRepository {

    private Connection conn;

    public UserDaoJDBC() {
	this.conn = DB.getConnection();
    }

    @Override
    public boolean login(String username, String password) throws DbException {
	String sql = "select username, password from users where username = ? and password = ?";
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = conn.prepareStatement(sql);
	    st.setString(1, username);
	    st.setString(2, password);
	    rs = st.executeQuery();
	    return rs.next();
	} catch (SQLException e) {
	    e.printStackTrace();
	    try {
		conn.rollback();
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }

    @Override
    public void save(User user) throws UsernameExistenteException {
	String sql = "INSERT INTO users(username, password, name, telefone, email, foto_base64, foto_content_type, foto_miniatura, curriculo_base64, curriculo_content_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement st = null;
	try {
	    if (!this.usernameExistente(user.getUsername())) {
		st = conn.prepareStatement(sql);
		st.setString(1, user.getUsername());
		st.setString(2, user.getPassword());
		st.setString(3, user.getName());
		st.setString(4, user.getTelefone());
		st.setString(5, user.getEmail());
		st.setString(6, user.getFoto().getArquivoBase64());
		st.setString(7, user.getFoto().getContentType());
		st.setString(8, user.getMiniatura());
		st.setString(9, user.getCurriculo().getArquivoBase64());
		st.setString(10, user.getCurriculo().getContentType());
		st.execute();
		conn.commit();
	    } else {
		throw new UsernameExistenteException("Não foi possível cadastrar. Username já existente!");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    try {
		conn.rollback();
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
    }

    @Override
    public List<User> findAll() {
	String sql = "select * from users;";
	PreparedStatement st = null;
	ResultSet rs = null;
	List<User> list = new ArrayList<>();
	try {
	    st = conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    while (rs.next()) {
		String name = rs.getString("name");
		String username = rs.getString("username");
		String telefone = rs.getString("telefone");
		String email = rs.getString("email");
		String curriculo64 = rs.getString("curriculo_base64");
		String curriculoCT = rs.getString("curriculo_content_type");
		list.add(new User(rs.getLong("id"), name, username, rs.getString("password"), email, telefone,
			null, new Arquivo(curriculo64, curriculoCT), rs.getString("foto_miniatura")));
	    }
	    return list;
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return null;
    }

    private User instanciarUser(ResultSet rs) throws SQLException {
	String name = rs.getString("name");
	String username = rs.getString("username");
	String telefone = rs.getString("telefone");
	String email = rs.getString("email");
	String foto64 = rs.getString("foto_base64");
	String contentType = rs.getString("foto_content_type");
	String curriculo64 = rs.getString("curriculo_base64");
	String curriculoCT = rs.getString("curriculo_content_type");
	return new User(rs.getLong("id"), name, username, rs.getString("password"), email, telefone,
		new Arquivo(foto64, contentType), new Arquivo(curriculo64, curriculoCT), rs.getString("foto_miniatura"));
    }

    @Override
    public void deleteById(Long id) {
	String sql = "delete from users where id = " + id;
	PreparedStatement st = null;
	try {
	    st = conn.prepareStatement(sql);
	    st.execute();
	    conn.commit();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public User findById(Long id) {
	String sql = "select * from users where id = " + id;
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    if (rs.next()) {
		return instanciarUser(rs);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
	return null;
    }

    @Override
    public void update(User user) {
	PreparedStatement st = null;
	String username = user.getUsername();
	try {
	    //tudo isso deveria ser feito no service, but whatever...
	    if ((usernameExistente(user.getUsername()))) {
		// Verificando se o username atual é igual ao username dele mesmo
		if (username.equals(this.findById(user.getId()).getUsername())) {
		    username = this.findById(user.getId()).getUsername();
		} else {
		    throw new UsernameExistenteException("Username já existente!");
		}
	    }
	    
	    if (user.getFoto().getArquivoBase64().equals("") && findById(user.getId()).getFoto()!= null) {
		user.setFoto(findById(user.getId()).getFoto());
	    }
	    
	    if (user.getCurriculo().getArquivoBase64().equals("") && findById(user.getId()).getCurriculo()!= null) {
		user.setCurriculo(findById(user.getId()).getCurriculo());
	    }
	    
	    if (user.getMiniatura().equals("") && findById(user.getId()).getMiniatura()!= null) {
		user.setMiniatura(findById(user.getId()).getMiniatura());
	    }
	    
	    // o método deveria ser só isso, af
	    st = conn.prepareStatement(
		    "update users set name = ?, username = ?, password = ?, email = ?, telefone = ?, foto_base64 = ?, "
			    + "foto_content_type = ?, curriculo_base64 = ?, curriculo_content_type = ?, foto_miniatura = ? where id = ?");
	    st.setString(1, user.getName());
	    st.setString(2, username);
	    st.setString(3, user.getPassword());
	    st.setString(4, user.getEmail());
	    st.setString(5, user.getTelefone());
	    st.setString(6, user.getFoto().getArquivoBase64());
	    st.setString(7, user.getFoto().getContentType());
	    st.setString(8, user.getCurriculo().getArquivoBase64());
	    st.setString(9, user.getCurriculo().getContentType());
	    st.setString(10, user.getMiniatura());
	    st.setLong(11, user.getId());
	    st.executeUpdate();
	    conn.commit();

	} catch (SQLException e) {
	    e.printStackTrace();
	    try {
		conn.rollback();
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeStatement(st);
	}
    }

    /**
     * 
     * @param username
     * @return true caso o usuario já exista
     */
    private boolean usernameExistente(String username) {
	Statement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.createStatement();
	    rs = st.executeQuery("select username from users");
	    while (rs.next()) {
		if (rs.getString("username").equals(username)) {
		    return true;
		}
	    }
	    return false;
	} catch (SQLException e) {
	    throw new DbException(e.getMessage());
	} finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }
}
