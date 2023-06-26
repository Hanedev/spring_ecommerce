package sn.ecpi.ecomerce.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.ProduitDTO;
import sn.ecpi.ecomerce.entite.Produit;
import sn.ecpi.ecomerce.pojo.ProduitPOJO;
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
    public ResponseEntity<List<ProduitPOJO>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduit();
        List<ProduitPOJO> produitPOJOs = produits.stream()
                .map(produit -> modelMapper.map(produit, ProduitPOJO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(produitPOJOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProduitPOJO> getProduitByUUID(@PathVariable("uuid") UUID uuid) {
        Produit produit = produitService.findByUuid(uuid);
        if (produit != null) {
            ProduitPOJO produitPOJO = modelMapper.map(produit, ProduitPOJO.class);
            return ResponseEntity.ok(produitPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProduitPOJO> createProduit(@RequestBody ProduitDTO produitDTO) {

        Produit createdProduit = produitService.createProduit(produitDTO);
        ProduitPOJO createdProduitPOJO = modelMapper.map(createdProduit, ProduitPOJO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduitPOJO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProduitPOJO> updateProduit(
            @PathVariable("uuid") UUID uuid,
            @RequestBody ProduitDTO produitDTO) {

        Produit updatedProduit = produitService.updateProduit(uuid, produitDTO);
        if (updatedProduit != null) {
            ProduitPOJO updatedProduitPOJO = modelMapper.map(updatedProduit, ProduitPOJO.class);
            return ResponseEntity.ok(updatedProduitPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteProduit(@PathVariable("uuid") UUID uuid) {
        produitService.deleteProduit(uuid);
    }
}

