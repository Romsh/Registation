package fr.afpa.projetregistation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.projetregistation.entity.MaterielEntity;

@Repository
public interface IMaterielDao extends PagingAndSortingRepository<MaterielEntity, Integer> {

	@Override
	List<MaterielEntity> findAll();

	Optional<MaterielEntity> findByRef(String pRef);

	@Query(value = "SELECT ref FROM materiel order by matricule desc LIMIT 1", nativeQuery = true)
	public int getMaxIdMat();

	@Query(value = "select m.* from materiel as m left outer join type_materiel as tm on (m.id_type_materiel = tm.id_type_materiel ) where tm.libelle_materiel= ?1  ", countQuery = "SELECT count(*) FROM materiel as m left outer join type_materiel as tm on (m.id_type_materiel = tm.id_type_materiel ) where tm.libelle_materiel= ?1 ", nativeQuery = true)
	Page<MaterielEntity> findAllByType(String pType, Pageable page);

}
