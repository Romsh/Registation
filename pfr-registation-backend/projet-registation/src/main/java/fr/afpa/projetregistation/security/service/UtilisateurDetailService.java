package fr.afpa.projetregistation.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.afpa.projetregistation.dao.IUtilisateurDao;
import fr.afpa.projetregistation.entity.ConnexionEntity;
import fr.afpa.projetregistation.entity.UtilisateurEntity;

@Service
public class UtilisateurDetailService implements UserDetailsService {

	@Autowired
	private IUtilisateurDao utilisateurDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("matricule is empty");
		}

		Optional<UtilisateurEntity> userOpt = this.utilisateurDao.findById(username);

		if (!userOpt.isPresent()) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}

		UtilisateurEntity uEnt = userOpt.get();
		ConnexionEntity user = uEnt.getConnexion();
		return new User(user.getMatricule(), user.getPassword(),
				getGrantedAuthorities(uEnt));
	}

	private List<GrantedAuthority> getGrantedAuthorities(UtilisateurEntity uEnt) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		boolean role = uEnt.isResponsable();
		if (role == true) {
			authorities.add(new SimpleGrantedAuthority("ROLE_RESPONSABLE"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYE"));
		}
		return authorities;
	}


	
}
