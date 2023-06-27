package sn.ecpi.ecomerce.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores")
public class Store {
    @Id
    private UUID uuid =UUID.randomUUID();

    private String nom;

    @ManyToOne
    private User user;




}
