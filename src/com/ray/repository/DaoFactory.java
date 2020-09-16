package com.ray.repository;

import com.ray.db.jdbc.ProductDaoJDBC;
import com.ray.db.jdbc.UserDaoJDBC;


public class DaoFactory {
    public static UserRepository createUserDao() {
	return new UserDaoJDBC();
    }
    
    public static ProductRepository createProdutoDao() {
	return new ProductDaoJDBC();
    }

}
