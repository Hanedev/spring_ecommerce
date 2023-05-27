package sn.ecpi.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class CategorieDTO {
    private UUID uuid;

    private String nom;

    private String description;

    private String categorie;
}
