package com.epi.gestion.service;

import java.util.List;

import com.epi.gestion.entities.Produit;

public interface ProduitService {
	
	public List<Produit> getAllproduits();
	public Produit persistProduit(Produit p);
	public void deleteProduit(Long id);
	public Produit findProduitById(Long id);
	public List<Produit> findProduitsByNom(String nom);
	public List<Produit> findProduitsByCat(Long id);
}
