package sn.ecpi.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ecpi.ecomerce.entite.Store;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProduitDTO {
    private UUID uuid;

    private String libelle;

    private String description;

    private String sousCategorie;

    private BigDecimal prix;

    private Integer quantite;

    private Store store;
}
