package fr.afpa.projetregistation.security.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.security.model.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Service
public class JwtTokenService {

	@Value("${registation.token.secret}")
	private String secret;

	public JwtToken createTokens(Authentication authentication) {

		String token;

		User user = (User) authentication.getPrincipal();

		token = createToken(user);

		return new JwtToken(token);
	}

	public String createToken(User user) {
		Calendar cldr = Calendar.getInstance();
		cldr.add(Calendar.MINUTE, 30);
		return Jwts.builder().signWith(SignatureAlgorithm.HS512, secret).setClaims(buildUserClaims(user))
				.setExpiration(cldr.getTime()).setIssuedAt(new Date()).compact();
	}

	public Jws<Claims> validateJwtToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
	}

	private Claims buildUserClaims(User user) {
		Claims claims = new DefaultClaims();

		claims.put("username", user.getUsername());
		claims.put("roles", String.join(",", AuthorityUtils.authorityListToSet(user.getAuthorities())));

		return claims;
	}

	public Authentication getAuthentication(Jws<Claims> token) {
		return new UsernamePasswordAuthenticationToken(token.getBody().get("username"), "",
				AuthorityUtils.commaSeparatedStringToAuthorityList(token.getBody().get("roles", String.class)));
	}

	
	
}