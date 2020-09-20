package com.ray.beans;

public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String telefone;
    private Arquivo foto;
    private String miniatura;
    private Arquivo curriculo;
    private boolean ativo;

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
    
    public User(Long id, String name, String username, String password, String email, String telefone, Arquivo foto, Arquivo curriculo, String miniatura, boolean ativo) {
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
	this.email = email;
	this.telefone = telefone;
	this.foto = foto;
	this.curriculo = curriculo;
	this.miniatura = miniatura;
	this.ativo = ativo;
    }

    public User() {
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
    
    public Arquivo getFoto() {
        return foto;
    }

    public void setFoto(Arquivo arquivo) {
        this.foto = arquivo;
    }
    
    public Arquivo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Arquivo curriculo) {
        this.curriculo = curriculo;
    }
    
    public String getTempFoto() {
	return "data:" + this.foto.getContentType() + ";base64," + this.foto.getArquivoBase64();
    }
    
    public String getTempCurriculo() {
	return "data:" + this.curriculo.getContentType() + ";base64," + this.curriculo.getArquivoBase64();
    }

    public String getMiniatura() {
	return miniatura;
    }

    public void setMiniatura(String miniatura) {
	this.miniatura = miniatura;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(Boolean ativo) {
	this.ativo = ativo;
    }
}
