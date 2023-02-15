package fr.afpa.projetregistation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.projetregistation.entity.DocumentEntity;

@Repository
public interface IDocumentDao extends PagingAndSortingRepository<DocumentEntity, Integer> {

	@Query("SELECT coalesce(max(ch.idDocument), 0) FROM DocumentEntity ch")
	public int getMaxId();

	@Query(value = "SELECT * FROM document_entity WHERE nom_document = 'nom'", nativeQuery = true)
	public Optional<DocumentEntity> getDocumentByNom(@Param("nom") String pNomDocument);
}
