package com.epi.gestion.controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.epi.gestion.entities.Produit;
import com.epi.gestion.service.CategorieService;
import com.epi.gestion.service.ProduitService;

@Controller
@RequestMapping("/produit/")
public class ProduitController {
	
	@Autowired
	ProduitService produitService;
	@Autowired
	CategorieService categorieService;
	
	private String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\photos";
	
	@GetMapping("add")
	public String showForm(Model model) {
		model.addAttribute("categories", categorieService.getAllCategories());
		model.addAttribute("produit", new Produit());
		return "addProduit";
	}
	
	@PostMapping("add")
	public String addProduit(@Validated Produit p, BindingResult result, Model model, @RequestParam("file") MultipartFile multipartFile) {
		
		if(result.hasErrors()) {
			model.addAttribute("categories", categorieService.getAllCategories());
			return "addProduit";
		}
		
		String fileName = multipartFile.getOriginalFilename();
		Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
		
		try {
			Files.write(fileNameAndPath, multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.setPhoto(fileName);
		produitService.persistProduit(p);
		return "redirect:/produit/all";
	}
	
	@GetMapping("delete/{id}")
	public String suppProduit(@PathVariable Long id) {
		produitService.deleteProduit(id);
		return "redirect:/produit/all";
	}
	
	@GetMapping("edit/{id}")
	public String editProduit(@PathVariable Long id, Model model) {
		model.addAttribute("produit", produitService.findProduitById(id));
		model.addAttribute("categories", categorieService.getAllCategories());
		return "editProduit";
	}
	
	@PostMapping("update")
	public String updateProduit(@Validated Produit p, BindingResult result, Model model, @RequestParam("file") MultipartFile multipartFile) {
		
		if(result.hasErrors()) {
			model.addAttribute("categories", categorieService.getAllCategories());
			model.addAttribute("produit", p);
			return "editProduit";
		}
		String fileName = multipartFile.getOriginalFilename();
		if(fileName!="") {
			Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
			
			try {
				Files.write(fileNameAndPath, multipartFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			p.setPhoto(fileName);
		}
		else {
			Produit oldp = produitService.findProduitById(p.getId());
			p.setPhoto(oldp.getPhoto());
		}
		
		produitService.persistProduit(p);
		return "redirect:/produit/all";
	}
	
	
	@PostMapping("all")
	public String updateProduit(@RequestParam String nom, Model model) {
		model.addAttribute("produits", produitService.findProduitsByNom(nom));
		model.addAttribute("categories", categorieService.getAllCategories());
		model.addAttribute("nombre", produitService.findProduitsByNom(nom).size());
		
		return "index";
	}
	
	@GetMapping("all")
	public String index(Model model ) {
		model.addAttribute("produits", produitService.getAllproduits());
		model.addAttribute("nombre", produitService.getAllproduits().size());
		model.addAttribute("categories", categorieService.getAllCategories());
		return "index";
	}
	
	@PostMapping("parcat")
	public String findProduitParCat(@RequestParam Long idcat, Model model) {
		if(idcat!=0L) {
			model.addAttribute("produits", produitService.findProduitsByCat(idcat));
			model.addAttribute("nombre", produitService.findProduitsByCat(idcat).size());
		}
		else {
			model.addAttribute("produits", produitService.getAllproduits());
			model.addAttribute("nombre", produitService.getAllproduits().size());
		}		
		model.addAttribute("categories", categorieService.getAllCategories());
		model.addAttribute("id", idcat);
		return "index";
	}
	
	@GetMapping("show/{id}")
	public String showProduit(@PathVariable Long id, Model model) {
		model.addAttribute("produit", produitService.findProduitById(id));
		return "showProduit";
	}
}
