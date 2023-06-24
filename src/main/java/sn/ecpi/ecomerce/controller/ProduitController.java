package sn.ecpi.ecomerce.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.ProduitDTO;
import sn.ecpi.ecomerce.entite.Produit;
import sn.ecpi.ecomerce.service.ProduitService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    private final ModelMapper modelMapper;
    private final ProduitService produitService;

    @Autowired
    public ProduitController(ModelMapper modelMapper, ProduitService produitService) {
        this.modelMapper = modelMapper;
        this.produitService = produitService;
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduit();
        List<ProduitDTO> produitDTOs = produits.stream()
                .map(produit -> modelMapper.map(produit, ProduitDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(produitDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProduitDTO> getProduitByUUID(@PathVariable("uuid") UUID uuid) {
        Produit produit = produitService.findByUuid(uuid);
        if (produit != null) {
            ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
            return ResponseEntity.ok(produitDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProduitDTO> createProduit(@RequestBody ProduitDTO produitDTO) {
        Produit produit = modelMapper.map(produitDTO, Produit.class);
        Produit createdProduit = produitService.createProduit(produit);
        ProduitDTO createdProduitDTO = modelMapper.map(createdProduit, ProduitDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduitDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProduitDTO> updateProduit(
            @PathVariable("uuid") UUID uuid,
            @RequestBody ProduitDTO produitDTO) {
        Produit produit = modelMapper.map(produitDTO, Produit.class);
        Produit updatedProduit = produitService.updateProduit(uuid, produit);
        if (updatedProduit != null) {
            ProduitDTO updatedProduitDTO = modelMapper.map(updatedProduit, ProduitDTO.class);
            return ResponseEntity.ok(updatedProduitDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteProduit(@PathVariable("uuid") UUID uuid) {
        produitService.deleteProduit(uuid);
    }
}

