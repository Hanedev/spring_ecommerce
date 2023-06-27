package sn.ecpi.ecomerce.entite;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Table(name = "utilisateur")
@Data
@Entity
public class User {
    @Id
    private UUID uuid = UUID.randomUUID();


    private String prenom;


    private String nom;


    private String username;


    private String password;

    @Column(name = "email")
    private String mail;


    private String tel;

    @Column(name = "date_naissance")
    private Date dateNaissance;


    private String role;

    public User(String prenom, String nom, String username, String password, String mail, String tel, Date dateNaissance, String role) {
        this.prenom = prenom;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.role = role;
    }

    public User() {

    }


}
