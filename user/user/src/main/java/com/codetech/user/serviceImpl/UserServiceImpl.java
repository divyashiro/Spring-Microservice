package com.codetech.user.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codetech.user.exception.InvalidInputException;
import com.codetech.user.exception.NotFoundException;
import com.codetech.user.model.User;
import com.codetech.user.repository.RoleRepository;
import com.codetech.user.repository.UserRepository;
import com.codetech.user.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User registerUser(User user) {
		//check if user already registered 
		//check if role valid
		//hash the password
		//save in db
		
		if (userRepository.findByEmailId(user.getEmailId()) != null ) {
			throw new InvalidInputException("User already registered", "User found");
		}
		
		if (roleRepository.findById(user.getRoleId()).isEmpty()) {
			throw new NotFoundException("Invalid role","Invalid role");
		}
		
		//hashPassword(user.getPassword());
		
		return userRepository.save(user);
		
		
	}

	@Override
	public String hashPassword(String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
