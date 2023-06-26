package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.CategorieDTO;
import sn.ecpi.ecomerce.entite.Categorie;
import sn.ecpi.ecomerce.pojo.CategoriePOJO;
import sn.ecpi.ecomerce.service.CategorieService;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategorieController {


    private final ModelMapper modelMapper;
    private final CategorieService categorieService;

    public CategorieController(ModelMapper modelMapper, CategorieService categorieService) {
        this.modelMapper = modelMapper;
        this.categorieService = categorieService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriePOJO>> getAllCategories() {
        List<Categorie> categories = categorieService.getAllCategories();
        List<CategoriePOJO> categorieDTOs = categories.stream()
                .map(categorie -> modelMapper.map(categorie, CategoriePOJO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorieDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoriePOJO> getCategoryByUUID(@PathVariable("uuid") UUID uuid) {
        Categorie categorie = categorieService.findByUuid(uuid);
        if (categorie != null) {
            CategoriePOJO categoriePOJO = modelMapper.map(categorie, CategoriePOJO.class);
            return ResponseEntity.ok(categoriePOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriePOJO> createCategory(@RequestBody CategorieDTO categorieDTO) {

        Categorie createdCategorie = categorieService.createCategorie(categorieDTO);
        CategoriePOJO createdCategoriePOJO = modelMapper.map(createdCategorie, CategoriePOJO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoriePOJO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CategoriePOJO> updateCategory(
            @PathVariable("uuid") UUID uuid,
            @RequestBody CategorieDTO categorieDTO) {

        Categorie updatedCategorie = categorieService.updateCategorie(uuid, categorieDTO);
        if (updatedCategorie != null) {
            CategoriePOJO updatedCategoriePOJO = modelMapper.map(updatedCategorie, CategoriePOJO.class);
            return ResponseEntity.ok(updatedCategoriePOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteCategory(@PathVariable("uuid") UUID uuid) {
        categorieService.deleteCategorie(uuid);
    }
}
