package com.system.serviceImpl;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private String secertKey = "";
	
	public JWTService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secertKey =    Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public String generateToken(String username) {
		// TODO Auto-generated method stub
		
		Map<String, Object> claims = new HashMap<>();
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
				.signWith(getKey())
                .compact();
				
//		return "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI"
//				+ "6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.NHVaYe26MbtOYhSKkoKYdFVomg4i"
//				+ "8ZJd8_-RU8VNbftc4TSMb4bXP3l3YlNWACwyXPGffz5aXHc6lty1Y2t4SWRqGteragsVdZufDn5BlnJl9pdR_kdV"
//				+ "FUsra2rWKEofkZeIC4yWytE58sMIihvo9H1ScmmVwBcQP6XETqYd0aSHp1gOa9RdUPDvoXQ5oqygTqVtxaDr6wUFKrKI"
//				+ "tgBMzWIdNZ6y7O9E0DhEPTbE9rfBo6KTFsHAZnMg4k68CDp2woYIaXbmYTWcvbzIuHO7_37GT79XdIwkm95QJ7hYC9R"
//				+ "iwrV7mesbY4PAahERJawntho0my942XheVLmGwLMBkQ";
	}

	private Key getKey() {
		// TODO Auto-generated method stub
	
		byte[] keyBytes = Decoders.BASE64.decode(secertKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
   public String extractUserName(String token) {
       return extractClaim(token, Claims::getSubject);
   }
   private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
       final Claims claims = extractAllClaims(token);
       return claimResolver.apply(claims);
   }
     private Claims extractAllClaims(String token) {
     return Jwts.parserBuilder()
         .setSigningKey(getKey())
         .build()
         .parseClaimsJws(token)
         .getBody() ;
 }

   public boolean validateToken(String token, UserDetails userDetails) {
       final String userName = extractUserName(token);
       return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   private boolean isTokenExpired(String token) {
       return extractExpiration(token).before(new Date());
   }

   private Date extractExpiration(String token) {
       return extractClaim(token, Claims::getExpiration);
   }

	
}


