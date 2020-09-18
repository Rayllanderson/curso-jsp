package com.ray.beans;

public class Foto {

    private String fotoBase64;
    private String contentType;

    public Foto(String fotoBase64, String contentType) {
	super();
	this.fotoBase64 = fotoBase64;
	this.contentType = contentType;
    }

    public String getFotoBase64() {
	return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
	this.fotoBase64 = fotoBase64;
    }

    public String getContentType() {
	return contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }
}
