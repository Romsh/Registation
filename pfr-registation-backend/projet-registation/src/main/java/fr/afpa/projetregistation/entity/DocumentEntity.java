package fr.afpa.projetregistation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "document_entity")
public class DocumentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_document")
	private int idDocument;
	@Column(name = "nom_document")
	private String nomDocument;
	@Column(name = "categorie_document")
	private String categorieDocument;
	@Column(name = "date_ajout_document")
	private Date dateAjoutDocument;
	@Column(name = "date_derniere_modification_document")
	private Date dateDerniereModificationDocument;
	@Column(name = "description_document")
	private String descriptionDocument;
	@Column(name = "commentaires_document")
	private String commentairesDocument;

	@ManyToOne
	@JoinColumn(name="matricule")
	private UtilisateurEntity user;

}
