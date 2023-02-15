package fr.afpa.projetregistation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.projetregistation.entity.AdresseEntity;

@Repository
public interface IAdresseDao extends CrudRepository<AdresseEntity, String> {

}
