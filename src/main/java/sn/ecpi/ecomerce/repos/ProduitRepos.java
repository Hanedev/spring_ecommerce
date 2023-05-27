package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.Produit;

import java.util.UUID;

public interface ProduitRepos extends JpaRepository<Produit, UUID> {
}
