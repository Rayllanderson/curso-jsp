package com.ray.beans;

public class User {

    private String username;
    private String password;
    
    public boolean validateLoginAndPassword(String username, String password) {
	return (username.equals("ray") && password.equals("123"));
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
