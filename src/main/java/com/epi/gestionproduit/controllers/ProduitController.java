package com.epi.gestionproduit.controllers;

import com.epi.gestionproduit.entities.Produit;
import com.epi.gestionproduit.services.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProduitController {
    private ProduitService service;
    @GetMapping("/produits")
    public List<Produit> getAllProduits() {
        return service.getAllProduits();
    }

    @GetMapping("/produits/{id}")
    public Produit getProduit(@PathVariable int id){
        return service.getProduit(id);
    }

    @GetMapping(path="/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable int id){
        return service.getImage(id);
    }

}
