package sn.ecpi.ecomerce.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class Position {
    @Id
    private UUID uuid = UUID.randomUUID();

    private String localisation;

    private Long longitude;

    private Long latitude;
}

