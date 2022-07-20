package com.codetech.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.codetech.model.IntrospectResponse;
import com.codetech.model.LoginRequest;
import com.codetech.model.LoginResponse;
import com.codetech.model.Response;
import com.codetech.model.TokenRequest;

@Service
public class LoginService {
	
	@Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String loginUrl;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
    private String grantType;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
    private String clientId;
	
	@Autowired
	RestTemplate restTemplate;
	
	public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "admin_user");
        map.add("password", "admin");
        map.add("client_id", "oauth2-client");
        map.add("client_secret", "8TOxvaRWI1dFAjzeipPQ6S7IYsMo5yTV");
        map.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
 ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity("http://localhost:8180/auth/realms/oauth2-realm/protocol/openid-connect/token", httpEntity, LoginResponse.class);
		return new ResponseEntity<>(loginResponse.getBody(),HttpStatus.OK);
		// return ResponseEntity.status(200).body(loginResponse.getBody());
	}

	public ResponseEntity<IntrospectResponse> introspect(TokenRequest request) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
       
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("token", request.token);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
 ResponseEntity<IntrospectResponse> introspectResponse = restTemplate.postForEntity("http://localhost:8180/auth/realms/oauth2-realm/protocol/openid-connect/token/introspect", httpEntity, IntrospectResponse.class);
		return new ResponseEntity<>(introspectResponse.getBody(),HttpStatus.OK);
		
		
	}

	public ResponseEntity<Response> logout(TokenRequest token) {
		

		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
       
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", token.token);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
ResponseEntity<Response> response = restTemplate.postForEntity("http://localhost:8180/auth/realms/oauth2-realm/protocol/openid-connect/logout", httpEntity, Response.class);
Response res = new Response();
 if(response.getStatusCode().is2xxSuccessful()) {
	
	 res.setMessage("Logged out successfully");
	 return new ResponseEntity<>(res,HttpStatus.OK);
	 
 }

return new ResponseEntity<>(response.getBody(),response.getStatusCode());
		
		
		
	}

}
