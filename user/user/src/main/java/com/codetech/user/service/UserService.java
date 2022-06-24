package com.codetech.user.service;

import com.codetech.user.model.User;


public interface UserService {
	
	User registerUser(User user) ;
	
	String hashPassword(String password);

}
