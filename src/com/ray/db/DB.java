package com.ray.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private static String url = "jdbc:postgresql://localhost:5432/curso_jsp?autoReconnect=true";
    private static String password = "admin";
    private static String user = "postgres";
    private static Connection conn = null;

    static {
	conectar();
    }

    public DB() {
	conectar();
    }

    private static void conectar() {
	try {
	    if (conn == null) {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false); // pra nao salvar automaticamente, algo com rollback
		System.out.println("conectado com sucesso... duvido muito");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DbException("Erro ao conectar ao banco de dados");
	}
    }

    public static Connection getConnection() {
	return conn;
    }

    public static void closeStatement(Statement st) {
	if (st != null) {
	    try {
		st.close();
	    } catch (SQLException e) {
		throw new DbException(e.getMessage());
	    }
	}
    }

    public static void closeResultSet(ResultSet rs) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (SQLException e) {
		throw new DbException(e.getMessage());
	    }
	}
    }
}
