package com.behl.eclair.security.utility;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.behl.eclair.entity.User;
import com.behl.eclair.security.configuration.properties.JwtConfigurationProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@Component
@EnableConfigurationProperties(JwtConfigurationProperties.class)
@AllArgsConstructor
public class JwtUtils {

	private final JwtConfigurationProperties jwtConfiguration;

	public String extractEmail(final String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public UUID extractUserId(final String token) {
		return UUID.fromString((String) extractAllClaims(token).get("user_id"));
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(jwtConfiguration.getJwt().getSecretKey().getBytes(Charset.forName("UTF-8"))).build()
				.parseClaimsJws(token.replace("Bearer ", "")).getBody();
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateAccessToken(final User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user_id", user.getId().toString());
		claims.put("full_name", user.getFullName());
		claims.put("account_creation_date", user.getCreatedAt().toString());
		return createToken(claims, user.getEmailId(), TimeUnit.MINUTES.toMillis(90));
	}

	public String generateRefreshToken(final User user) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, user.getEmailId(), TimeUnit.DAYS.toMillis(15));
	}

	private String createToken(final Map<String, Object> claims, final String subject, final Long expiration) {
		final var key = Keys.hmacShaKeyFor(jwtConfiguration.getJwt().getSecretKey().getBytes(Charset.forName("UTF-8")));
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(key).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String emailId = extractEmail(token);
		return (emailId.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}