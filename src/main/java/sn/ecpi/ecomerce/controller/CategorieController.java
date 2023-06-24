package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.CategorieDTO;
import sn.ecpi.ecomerce.entite.Categorie;
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
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        List<Categorie> categories = categorieService.getAllCategories();
        List<CategorieDTO> categorieDTOs = categories.stream()
                .map(categorie -> modelMapper.map(categorie, CategorieDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorieDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategorieDTO> getCategoryByUUID(@PathVariable("uuid") UUID uuid) {
        Categorie categorie = categorieService.findByUuid(uuid);
        if (categorie != null) {
            CategorieDTO categorieDTO = modelMapper.map(categorie, CategorieDTO.class);
            return ResponseEntity.ok(categorieDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategorieDTO> createCategory(@RequestBody CategorieDTO categorieDTO) {
        Categorie categorie = modelMapper.map(categorieDTO, Categorie.class);
        Categorie createdCategorie = categorieService.createCategorie(categorie);
        CategorieDTO createdCategorieDTO = modelMapper.map(createdCategorie, CategorieDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategorieDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CategorieDTO> updateCategory(
            @PathVariable("uuid") UUID uuid,
            @RequestBody CategorieDTO categorieDTO) {
        Categorie categorie = modelMapper.map(categorieDTO, Categorie.class);
        Categorie updatedCategorie = categorieService.updateCategorie(uuid, categorie);
        if (updatedCategorie != null) {
            CategorieDTO updatedCategorieDTO = modelMapper.map(updatedCategorie, CategorieDTO.class);
            return ResponseEntity.ok(updatedCategorieDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteCategory(@PathVariable("uuid") UUID uuid) {
        categorieService.deleteCategorie(uuid);
    }
}
