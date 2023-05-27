package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.Store;

import java.util.UUID;

public interface StoreRepos extends JpaRepository<Store, UUID> {
}
