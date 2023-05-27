package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.Categorie;

import java.util.UUID;

public interface CategorieRepos extends JpaRepository<Categorie, UUID> {
}
