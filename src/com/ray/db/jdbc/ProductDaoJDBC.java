package com.ray.db.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ray.beans.Product;
import com.ray.db.DB;
import com.ray.db.DbException;
import com.ray.repository.ProductRepository;

public class ProductDaoJDBC implements ProductRepository {

    private Connection conn;

    public ProductDaoJDBC() {
	this.conn = DB.getConnection();
    }

    @Override
    public void save(Product product) throws UsernameExistenteException {
	String sql = "INSERT INTO products(nome, quantidade, valor, categoria) VALUES (?, ?, ?, ?)";
	PreparedStatement st = null;
	try {
	    if (!this.produtoExistente(product.getNome())) {
		st = conn.prepareStatement(sql);
		st.setString(1, product.getNome());
		st.setInt(2, product.getQuantidade());
		st.setBigDecimal(3, product.getValor());
		st.setString(4, product.getCategoria());
		st.execute();
		conn.commit();
	    } else {
		throw new ProdutoExistenteException("Não foi possível cadastrar. " + "Produto de nome "
			+ product.getNome() + " já está cadastrado!");
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
    public List<Product> findAll() {
	String sql = "select * from products;";
	PreparedStatement st = null;
	ResultSet rs = null;
	List<Product> list = new ArrayList<>();
	try {
	    st = conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    while (rs.next()) {
		list.add(instanciarProduct(rs));
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

    private Product instanciarProduct(ResultSet rs) throws SQLException {
	String name = rs.getString("nome");
	Integer quantidade = rs.getInt("quantidade");
	BigDecimal valor = rs.getBigDecimal(4);
	String categoria = rs.getString("categoria");
	return new Product(rs.getLong("id"), name, quantidade, valor, categoria);
    }

    @Override
    public void deleteById(Long id) {
	String sql = "delete from products where id = " + id;
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
    public Product findById(Long id) {
	String sql = "select * from products where id = " + id;
	PreparedStatement st = null;
	ResultSet rs = null;
	try {
	    st = conn.prepareStatement(sql);
	    rs = st.executeQuery();
	    if (rs.next()) {
		return instanciarProduct(rs);
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
    public void update(Product product) {
	PreparedStatement st = null;
	String name = product.getNome();
	try {
	    if (produtoExistente(name)) {
		if (name.equals(this.findById(product.getId()).getNome())) {
		    name = this.findById(product.getId()).getNome();
		} else {
		    throw new ProdutoExistenteException(
			    "Produto de nome " + product.getNome() + " já está cadastrado!");
		}
	    }
	    String sql = "update products set nome = ?, quantidade = ?, valor = ?, categoria = ? where id = ?";
	    st = conn.prepareStatement(sql);
	    st = conn.prepareStatement(sql);
	    st.setString(1, product.getNome());
	    st.setInt(2, product.getQuantidade());
	    st.setBigDecimal(3, product.getValor());
	    st.setString(4, product.getCategoria());
	    st.setLong(5, product.getId());
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
     * @param nome
     * @return true caso o produto já exista
     */
    private boolean produtoExistente(String nome) {
	Statement st = null;
	ResultSet rs = null;
	try {
	    st = this.conn.createStatement();
	    rs = st.executeQuery("select nome from products");
	    while (rs.next()) {
		if (rs.getString("nome").equals(nome)) {
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
