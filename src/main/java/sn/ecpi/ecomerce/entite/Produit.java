package sn.ecpi.ecomerce.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produit")
public class Produit {
    @Id
    private UUID uuid = UUID.randomUUID();

    private String libelle;

    private String description;

    @Column(name = "sous_categorie")
    private String sousCategorie;

    private BigDecimal prix;

    private Integer quantite;

    @ManyToOne
    private Store store;




}
