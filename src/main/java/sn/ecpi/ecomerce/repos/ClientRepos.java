package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.Client;

import java.util.UUID;

public interface ClientRepos extends JpaRepository<Client, UUID> {
}
