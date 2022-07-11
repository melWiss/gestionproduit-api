package com.epi.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epi.gestion.entities.Produit;
import com.epi.gestion.repository.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService{

	@Autowired
	ProduitRepository produitRepository;
	
	@Override
	public List<Produit> getAllproduits() {
		return produitRepository.findAll();
	}

	@Override
	public Produit persistProduit(Produit p) {
		return produitRepository.save(p);
	}

	@Override
	public void deleteProduit(Long id) {
		produitRepository.deleteById(id);		
	}

	@Override
	public Produit findProduitById(Long id) {
		return produitRepository.getById(id);
	}

	@Override
	public List<Produit> findProduitsByNom(String nom) {
		return produitRepository.findByNomContains(nom);
	}

	@Override
	public List<Produit> findProduitsByCat(Long id) {
		return produitRepository.rechercheParCategorie(id);
	}

	

}
