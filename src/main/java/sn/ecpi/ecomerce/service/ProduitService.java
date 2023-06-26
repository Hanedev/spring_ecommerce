package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.dto.ProduitDTO;
import sn.ecpi.ecomerce.entite.Produit;
import sn.ecpi.ecomerce.repos.ProduitRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class ProduitService {

    private final ModelMapper modelMapper;

    public final ProduitRepos produitRepos;

    public List<Produit> getAllProduit(){
        return produitRepos.findAll();
    }

    public Produit findByUuid(UUID uuid){
        Optional<Produit> result = produitRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("Produit not Found");
        }
    }

    public  Produit createProduit(ProduitDTO produitDTO){
        Produit produit = modelMapper.map(produitDTO, Produit.class);
        return produitRepos.save(produit);
    }

    public Produit updateProduit(UUID uuid, ProduitDTO produitDTO){
        Produit produitResquest = modelMapper.map(produitDTO, Produit.class);
        Produit produit = produitRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Produit not found"));
        produit.setDescription(produitResquest.getDescription());
        produit.setPrix(produitResquest.getPrix());
        produit.setStore(produitResquest.getStore());
        produit.setLibelle(produitResquest.getLibelle());
        produit.setQuantite(produitResquest.getQuantite());
        produit.setSousCategorie(produitResquest.getSousCategorie());

        return produitRepos.save(produit);
    }

    public void deleteProduit(UUID uuid){
        Produit produit = produitRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("produit not found"));
        produitRepos.delete(produit);
    }
}
