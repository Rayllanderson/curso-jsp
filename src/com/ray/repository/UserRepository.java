package com.ray.repository;

import java.util.List;

import com.ray.beans.User;

public interface UserRepository {
    
    public boolean login (String username, String password);
    
    public void save(User user);
    
    public User findById(Long id);
    
    public List <User> findUsersByName(String name);
    
    public List<User> findAll();
    
    public void update(User user);
    
    public void deleteById(Long id);



}
