package com.ray.db;

import java.sql.Connection;
import java.sql.DriverManager;

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

}
