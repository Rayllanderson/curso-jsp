package com.ray.repository;

import com.ray.db.jdbc.UserDaoJDBC;


public class DaoFactory {
    public static UserRepository createUserDao() {
	return new UserDaoJDBC();
    }

}
