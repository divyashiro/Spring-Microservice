package com.codetech.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codetech.user.model.User;
import com.codetech.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup") 
	public User registerUser(@RequestBody User user)  {
		
		
		return userService.registerUser(user);
	}
		
	

}
