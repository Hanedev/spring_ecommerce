package sn.ecpi.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Data
public class UserDTO {
    private UUID uuid;
    private String prenom;
    private String nom;
    private String username;
    private String password;
    private String mail;
    private String tel;
    private Date dateNaissance;
    private String role;
}
