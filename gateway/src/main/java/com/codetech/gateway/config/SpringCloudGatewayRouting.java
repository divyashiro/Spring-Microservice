package com.codetech.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {
	@Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
       return builder.routes()
      .route("user", r->r.path("/user/**").uri("lb://user-service")) 
      .route("book", r->r.path("/book/**").uri("lb://book-service")) 
      .build();
    }
	
	

}
