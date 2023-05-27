package sn.ecpi.ecomerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionPOJO {
    private UUID uuid;

    private String localisation;

    private Long longitude;

    private Long latitude;
}
