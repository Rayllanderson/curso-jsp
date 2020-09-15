package com.ray.db.jdbc;

public class UsernameExistenteException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public UsernameExistenteException(String msg) {
	super(msg);
    }

}
