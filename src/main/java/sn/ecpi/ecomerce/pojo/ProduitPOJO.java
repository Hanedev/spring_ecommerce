package sn.ecpi.ecomerce.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ecpi.ecomerce.entite.Store;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitPOJO {

    private UUID uuid;

    private String libelle;

    private String description;

    private String sousCategorie;

    private BigDecimal prix;

    private Integer quantite;

    private Store store;
}
