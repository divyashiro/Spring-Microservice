package com.codetech.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetech.user.model.Role;
import com.codetech.user.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping
	public Role addRole(@RequestBody Role role) {
		
		return roleRepository.save(role);
	}
	
	@GetMapping
	public List<Role> getAllRoles() {
		
		return roleRepository.findAll();
		
	}
	
	
	

}
