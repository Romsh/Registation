package fr.afpa.projetregistation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.projetregistation.entity.TypeMaterielEntity;

@Repository
public interface ITypeMaterielDao extends PagingAndSortingRepository<TypeMaterielEntity, Integer> {

	Optional<TypeMaterielEntity> findByLibelleMateriel(String pLibelle);

	@Override
	List<TypeMaterielEntity> findAll();

}
