package com.ray.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ray.beans.Sexo;
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
    public void save(User user) {
	String sql = "INSERT INTO users(username, password, name, sexo, email) VALUES (?, ?, ?, ?, ?)";
	PreparedStatement st = null;
	try {
	    if (!this.usernameExistente(user.getUsername())) {
		st = conn.prepareStatement(sql);
		st.setString(1, user.getUsername());
		st.setString(2, user.getPassword());
		st.setString(3, user.getName());
		st.setString(4, user.getSexo().toString());
		st.setString(5, user.getEmail());
		st.execute();
		conn.commit();
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
		list.add(instanciarUser(rs));
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
	String sexo = rs.getString("sexo");
	String email = rs.getString("email");
	return new User(rs.getLong("id"), name, username, rs.getString("password"), email, Sexo.valueOf(sexo));
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

	    if ((usernameExistente(user.getUsername()))) {
		username = this.findById(user.getId()).getUsername();
		
		/*if (username.equals(this.findById(user.getId()).getUsername())) {
		    	//nao fazer nada
		}else{
			//lançar error de username existente
		}*/
	    }
		st = conn.prepareStatement(
			"update users set name = ?, username = ?, password = ?, email = ?, sexo = ? where id = ?");
		st.setString(1, user.getName());
		st.setString(2, username);
		st.setString(3, user.getPassword());
		st.setString(4, user.getEmail());
		st.setString(5, user.getSexo());
		st.setLong(6, user.getId());
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
