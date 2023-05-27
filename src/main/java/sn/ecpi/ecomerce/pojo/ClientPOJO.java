package sn.ecpi.ecomerce.pojo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPOJO {
    private UUID uuid;

    private String nom;

    private String prenom;

    private String mail;

    private String username;

    private String password;

    private String tel;
}
