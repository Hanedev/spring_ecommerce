package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.User;

import java.util.UUID;

public interface UserRepos extends JpaRepository<User, UUID> {
}
