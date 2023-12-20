package com.example.usuarios.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


public class TokenUtils {
	
	private final static String ACCESS_TOKEN_SECRET = "TBS}x-:1Y_M/Z8xqkrvByr3U{%7f06";
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	@SuppressWarnings("deprecation")
	public static String createToken(String nombre, String email) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		
		byte[] apiKeySecretBytes = ACCESS_TOKEN_SECRET.getBytes();
		Key signingKey = createSigningKey(apiKeySecretBytes);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(signingKey)
				.compact();
	}
	
	@SuppressWarnings("deprecation")
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
					
			String email = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}
	}
	
	private static Key createSigningKey(byte[] apiKeySecretBytes) {
		try {
			KeySpec spec = new PBEKeySpec(new String(apiKeySecretBytes).toCharArray(), apiKeySecretBytes, 65536, 256);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] keyBytes = factory.generateSecret(spec).getEncoded();
			return new SecretKeySpec(keyBytes, "HmacSHA256");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("Error al generar la clave de firma", e);
		}
	}
}
