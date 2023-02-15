package fr.afpa.projetregistation.security.service;

import org.springframework.security.core.Authentication;

import fr.afpa.projetregistation.security.model.AuthenticationRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;



public interface IAuthenticationService {

	Authentication getAuthentication(Jws<Claims> request);
	Authentication authenticate(AuthenticationRequest authenticationRequest);

}
