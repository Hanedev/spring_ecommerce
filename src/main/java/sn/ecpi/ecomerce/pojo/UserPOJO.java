package sn.ecpi.ecomerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPOJO {
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
