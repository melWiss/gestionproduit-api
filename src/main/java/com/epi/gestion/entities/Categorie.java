package com.epi.gestion.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorie {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "categorie")
	List<Produit> produits;
	
	
}
