package com.ray.repository;

import java.util.List;

import com.ray.beans.Product;

public interface ProductRepository {

    public void save(Product product);

    public Product findById(Long id);

    public List<Product> findAll();

    public void update(Product product);

    public void deleteById(Long id);

}
