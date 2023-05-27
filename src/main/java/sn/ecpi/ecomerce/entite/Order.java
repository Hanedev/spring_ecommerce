package sn.ecpi.ecomerce.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private UUID uuid = UUID.randomUUID();

    @ManyToOne
    private Client client;

    @ManyToOne
    private Produit produit;

    private Date date;

    @ManyToOne
    private Position position;



}

