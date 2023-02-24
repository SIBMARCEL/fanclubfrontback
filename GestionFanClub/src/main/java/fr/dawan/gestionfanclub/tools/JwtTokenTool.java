package fr.dawan.gestionfanclub.tools;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenTool implements Serializable{
	
	//durée de validité du token
	public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60; // 1 jours
	
	//Lecture de ce paramtère à partir de application.properties
	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	//Claims: terme qui désigne les détails du user
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public boolean ignoreTokenExpiration(String token) {
		return false;
	}
	
	public String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}
	
	
}
