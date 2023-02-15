package fr.afpa.projetregistation.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.projetregistation.entity.ConnexionEntity;

@Repository
public interface IConnexionDao extends CrudRepository<ConnexionEntity, String> {

	Optional<ConnexionEntity> findByMatricule(String matricule);

}
