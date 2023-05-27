package sn.ecpi.ecomerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ecpi.ecomerce.entite.User;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StorePOJO {
    private UUID uuid;

    private String nom;


    private User user;
}