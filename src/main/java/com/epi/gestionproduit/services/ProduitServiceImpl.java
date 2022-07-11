package com.epi.gestionproduit.services;


import com.epi.gestionproduit.entities.Produit;
import com.epi.gestionproduit.repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @Override
    public List<Produit> supprimerProduit(List<Integer> ids) {
        List<Produit> prod = pr.findAllById(ids);
        pr.deleteAllById(ids);
        return prod;
    }

    
    @Override
    public String uploadImage(MultipartFile file) {
        String nomPhoto = file.getOriginalFilename();
        String tab[] = nomPhoto.split("\\.");
        String newname = tab[0]+System.currentTimeMillis()+"."+tab[1];
        Path p = Paths.get(System.getProperty("user.home")+"/Photos/angular/", newname);
        try {
            Files.write(p, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newname;
    }

    @Override
    public Produit addProduit(Produit produit, MultipartFile file) {
        String photo = uploadImage(file);
        produit.setPhoto(photo);
        Produit prod = pr.save(produit);
        return prod;
    }
}
