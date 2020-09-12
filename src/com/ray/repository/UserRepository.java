package com.ray.repository;

import java.util.List;

import com.ray.beans.User;

public interface UserRepository {
    
    public boolean login (String username, String password);
    
    public void save(User user);
    
    public List<User> findAll();
    
    public User editById(Long id);
    
    public void deleteById(Long id);

}
