package com.epi.gestion.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[A-Z]{1,1}[a-z\séè]{2,49}$", message = "le nom du produit doit commencer par une lettre majuscule et sa longueur doit être entre 3 et 50 ")
	private String nom;
	private double prix;
	private int quantite;
	private String photo;
	@ManyToOne
	Categorie categorie;
}
