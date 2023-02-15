package fr.afpa.projetregistation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_materiel")
public class TypeMaterielEntity {

	/**
	 * Permet de déterminer le type de matériel. Chaque nombre correspondant à un
	 * type précis.
	 */
	@Id
	@Column(name = "id_type_materiel")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTypeMateriel;

	/**
	 * Nom du type de matériel auquel le matériel en cours correspond. Exemple :
	 * pompe, cuve, machine à café...
	 */
	@Column(name = "libelle_materiel")
	private String libelleMateriel;

	public TypeMaterielEntity(String pLibelleMateriel) {
		this.libelleMateriel = pLibelleMateriel;
	}
}
