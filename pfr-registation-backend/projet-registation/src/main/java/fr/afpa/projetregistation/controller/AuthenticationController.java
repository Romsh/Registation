package fr.afpa.projetregistation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.projetregistation.security.model.AuthenticationRequest;
import fr.afpa.projetregistation.security.model.JwtToken;
import fr.afpa.projetregistation.security.service.JwtTokenService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				authenticationRequest.username, authenticationRequest.password);
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (authentication != null && authentication.isAuthenticated()) {
			JwtToken tokens = jwtTokenService.createTokens(authentication);
			return ResponseEntity.ok().body(tokens);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
	}
	
	
}
