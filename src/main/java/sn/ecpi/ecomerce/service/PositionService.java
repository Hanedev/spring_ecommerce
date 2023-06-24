package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.entite.Position;
import sn.ecpi.ecomerce.repos.PositionRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class PositionService {
    public final PositionRepos positionRepos;

    public List<Position> getAllPositions(){
        return positionRepos.findAll();
    }

    public Position findByUuid(UUID uuid){
        Optional<Position> result = positionRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("Position not Found");
        }


    }

    public  Position createPosition(Position position){
        return positionRepos.save(position);
    }

    public Position updatePosition(UUID uuid, Position positionResquest){
        Position position = positionRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Position not found"));
        position.setLatitude(positionResquest.getLatitude());
        position.setLongitude(positionResquest.getLongitude());
        position.setLocalisation(positionResquest.getLocalisation());
        return positionRepos.save(position);
    }

    public void deletePosition(UUID uuid){
        Position position = positionRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Position not found"));
        positionRepos.delete(position);
    }


}
