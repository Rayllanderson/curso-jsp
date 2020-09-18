package com.ray.beans;

public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String telefone;
    private Foto foto;

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public User(Long id, String name, String username, String password, String email, String telefone) {
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
	this.email = email;
	this.telefone = telefone;
    }
    
    public User(String name, String username, String password, String email, String string) {
	this.name = name;
	this.username = username;
	this.password = password;
	this.email = email;
	this.telefone = string;
    }
    
    public User(Long id, String name, String username, String password, String email, String telefone, Foto foto) {
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
	this.email = email;
	this.telefone = telefone;
	this.foto = foto;
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

    public String getTelefone() {
	return this.telefone;
    }
    
    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    public String getTempFoto() {
	return "data:" + this.foto.getContentType() + ";base64," + this.foto.getFotoBase64();
    }
}
