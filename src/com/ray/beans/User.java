package com.ray.beans;

public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Sexo sexo;

    public User(Long id, String name, String username, String password, String email, Sexo sexo) {
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
	this.email = email;
	this.sexo = sexo;
    }

    public User() {
	// TODO Auto-generated constructor stub
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Sexo getSexo() {
	return sexo;
    }

    public void setSexo(Sexo sexo) {
	this.sexo = sexo;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
		+ email + ", sexo=" + sexo + "]";
    }
}
