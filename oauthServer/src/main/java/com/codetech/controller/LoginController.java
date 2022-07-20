package com.codetech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetech.model.IntrospectResponse;
import com.codetech.model.LoginRequest;
import com.codetech.model.LoginResponse;
import com.codetech.model.Response;
import com.codetech.model.TokenRequest;
import com.codetech.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		ResponseEntity<LoginResponse> response = loginService.login(loginRequest);
		return response;
	}
	
	@PostMapping("/introspect")
	public ResponseEntity<IntrospectResponse> introspect(@RequestBody TokenRequest introspectRequest) {
		ResponseEntity<IntrospectResponse> response = loginService.introspect(introspectRequest);
		return response;
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Response> logout(@RequestBody TokenRequest token){
		return loginService.logout(token);
		
		
	}
	
	

    
}
