package com.epi.gestionproduit.services;

import com.epi.gestionproduit.entities.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface ProduitService {
    public List<Produit> getAllProduits();
    public Produit getProduit(int id);
    public byte[] getImage(int id);
    public List<Produit> supprimerProduit(List<Integer> id);
    public Produit addProduit(Produit produit, MultipartFile file);
    public String uploadImage(MultipartFile file);
}
