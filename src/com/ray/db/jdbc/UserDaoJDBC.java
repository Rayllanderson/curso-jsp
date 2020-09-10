package com.ray.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ray.db.DB;
import com.ray.db.DbException;
import com.ray.repository.UserRepository;

public class UserDaoJDBC implements UserRepository{

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
	    throw new DbException(e.getMessage());
	}finally {
	    DB.closeResultSet(rs);
	    DB.closeStatement(st);
	}
    }
}
