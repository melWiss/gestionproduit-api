package com.epi.gestionproduit.services;

import com.epi.gestionproduit.entities.Produit;
import com.epi.gestionproduit.repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService{
    private ProduitRepository pr;
    @Override
    public List<Produit> getAllProduits() {
        return pr.findAll();
    }

    @Override
    public Produit getProduit(int id) {
        return pr.findById(id).get();
    }

    @Override
    public byte[] getImage(int id) {
        String path = System.getProperty("user.home")+"/Images/angular/";
        Path p = Paths.get(path, getProduit(id).getPhoto());
        try {
            return Files.readAllBytes(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
