package com.epi.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epi.gestion.entities.Categorie;
import com.epi.gestion.repository.CategorieRepository;
@Service
public class CategorieServiceImp implements CategorieService {

	@Autowired
	CategorieRepository categorieRepository;
	
	@Override
	public List<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}

}
