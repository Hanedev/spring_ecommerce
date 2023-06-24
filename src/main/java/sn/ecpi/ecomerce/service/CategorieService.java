package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.entite.Categorie;
import sn.ecpi.ecomerce.repos.CategorieRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class CategorieService {
    public final CategorieRepos categorieRepos;

    public List<Categorie> getAllCategories(){
        return categorieRepos.findAll();
    }

    public Categorie findByUuid(UUID uuid){
        Optional<Categorie> result = categorieRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("Categorie not Found");
        }


    }

    public  Categorie createCategorie(Categorie categorie){
        return categorieRepos.save(categorie);
    }

    public Categorie updateCategorie(UUID uuid, Categorie categorieResquest){
        Categorie categorie = categorieRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Categorie not found"));
        categorie.setCategorie(categorieResquest.getCategorie());
        categorie.setNom(categorieResquest.getNom());
        categorie.setDescription(categorieResquest.getDescription());
        return categorieRepos.save(categorie);
    }

    public void deleteCategorie(UUID uuid){
        Categorie categorie = categorieRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Categorie not found"));
        categorieRepos.delete(categorie);
    }

}
