package com.example.usuarios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST")
				.exposedHeaders("*")
				.allowCredentials(true)
				.allowedHeaders("POST", "Content-Type","X-Requested-With","accept","Origin",
						"Access-Control-Request-Method","Access-Control-Request-Headers");
				
				registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowCredentials(true)
				.allowedHeaders("GET","POST", "PUT", "DELETE","Content-Type","X-Requested-With","accept","Origin",
						"Authorization","Access-Control-Request-Method","Access-Control-Request-Headers");
			}
		};
	}
}
