package com.ray.db.jdbc;

import java.util.List;

import com.ray.beans.Product;

public class Tetste {
    
    public static void main(String[] args) {
	ProductDaoJDBC  p = new ProductDaoJDBC();
	List<Product> p2 = p.findAll();
	
	p2.forEach(System.out::println);
	
    }

}
