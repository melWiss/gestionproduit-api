package com.epi.gestionproduit.repositories;

import com.epi.gestionproduit.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}
