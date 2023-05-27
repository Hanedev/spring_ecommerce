package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.Position;

import java.util.UUID;

public interface PositionRepos extends JpaRepository<Position, UUID> {
}
