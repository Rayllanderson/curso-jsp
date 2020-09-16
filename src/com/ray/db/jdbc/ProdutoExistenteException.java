package com.ray.db.jdbc;

public class ProdutoExistenteException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ProdutoExistenteException(String msg) {
	super(msg);
    }

}
