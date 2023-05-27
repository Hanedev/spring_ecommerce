package sn.ecpi.ecomerce.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@Data
public class Client {
    @Id
    private UUID uuid = UUID.randomUUID();

    private String nom;

    private String prenom;

    @Column(name = "email")
    private String mail;

    private String username;

    private String password;

    private String tel;




}

