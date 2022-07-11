package com.epi.gestionproduit.controllers;

import com.epi.gestionproduit.entities.Produit;
import com.epi.gestionproduit.services.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @DeleteMapping(path = "/produits/{ids}")
    public List<Produit> supprimerProduit(@PathVariable List<Integer> ids){
        return service.supprimerProduit(ids);
    }

    @PostMapping(path = "/produits/add")
    public Produit addProduit(@RequestBody Produit produit, @RequestParam("photo") MultipartFile file) {
        Produit prod = service.addProduit(produit,file);
        return prod;
    }
}
