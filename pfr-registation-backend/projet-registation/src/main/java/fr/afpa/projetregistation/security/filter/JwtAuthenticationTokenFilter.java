package fr.afpa.projetregistation.security.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import fr.afpa.projetregistation.security.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

@Service
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    private static final String BEARER = "BEARER";

    @Autowired
    private JwtTokenService jwtTokenService;
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final Optional<String> token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));

        Authentication authentication;

        if(token.isPresent() && token.get().toUpperCase().startsWith(BEARER)) {

                String bearerToken = token.get().substring(BEARER.length()+1);

                try {
                    Jws<Claims> claims = jwtTokenService.validateJwtToken(bearerToken);
                    authentication = jwtTokenService.getAuthentication(claims);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (ExpiredJwtException exception) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.expired");
                    return;
                } catch (JwtException exception) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.invalid");
                    return;
                }

        }

        chain.doFilter(servletRequest, servletResponse);
        SecurityContextHolder.getContext().setAuthentication(null); // Clean authentication after process

    }

}