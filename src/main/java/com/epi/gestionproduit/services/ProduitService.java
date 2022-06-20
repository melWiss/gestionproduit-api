package com.epi.gestionproduit.services;

import com.epi.gestionproduit.entities.Produit;

import java.util.List;

public interface ProduitService {
    public List<Produit> getAllProduits();
    public Produit getProduit(int id);
    public byte[] getImage(int id);
}
