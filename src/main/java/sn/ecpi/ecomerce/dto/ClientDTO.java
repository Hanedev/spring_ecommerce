package sn.ecpi.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private UUID uuid;

    private String nom;

    private String prenom;

    private String mail;

    private String username;

    private String password;

    private String tel;
}
