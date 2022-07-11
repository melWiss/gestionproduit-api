package com.epi.gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.epi.gestion.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	public List<Produit> findByNomContains(String nom);
	
	@Query("select p from Produit p where p.categorie.id = ?1")
	public List<Produit> rechercheParCategorie(Long id);
}
