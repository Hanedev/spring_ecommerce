package sn.ecpi.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ecpi.ecomerce.entite.Client;
import sn.ecpi.ecomerce.entite.Position;
import sn.ecpi.ecomerce.entite.Produit;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID uuid;


    private Client client;


    private Produit produit;

    private Date date;


    private Position position;
}
