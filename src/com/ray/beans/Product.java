package com.ray.beans;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal valor;
    private String categoria;

    public Product(Long id, String nome, Integer quantidade, BigDecimal valor, String categoria) {
	this.id = id;
	this.nome = nome;
	this.quantidade = quantidade;
	this.valor = valor;
	this.setCategoria(categoria);
    }
    
    public Product(String nome, Integer quantidade, BigDecimal valor) {
	this.nome = nome;
	this.quantidade = quantidade;
	this.valor = valor;
    }
    
    public Product() {};

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Integer getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }
}
