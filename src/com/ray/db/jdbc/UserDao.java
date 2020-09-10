package com.ray.db.jdbc;

import java.sql.Connection;

import com.ray.db.DB;

public class UserDao {

    private Connection conn;

    public UserDao() {
	this.conn = DB.getConnection();
    }

}
