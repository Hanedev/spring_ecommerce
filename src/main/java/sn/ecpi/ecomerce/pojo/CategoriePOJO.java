package sn.ecpi.ecomerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriePOJO {
    private UUID uuid;

    private String nom;

    private String description;

    private String categorie;
}
