package sn.ecpi.ecomerce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ecpi.ecomerce.entite.Order;

import java.util.UUID;

public interface OrderRepos extends JpaRepository<Order, UUID> {
}
