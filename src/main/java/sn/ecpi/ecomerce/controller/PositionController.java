package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.PositionDTO;
import sn.ecpi.ecomerce.entite.Position;
import sn.ecpi.ecomerce.pojo.PositionPOJO;
import sn.ecpi.ecomerce.service.PositionService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final ModelMapper modelMapper;
    private final PositionService positionService;

    @Autowired
    public PositionController(ModelMapper modelMapper, PositionService positionService) {
        this.modelMapper = modelMapper;
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<PositionPOJO>> getAllPositions() {
        List<Position> positions = positionService.getAllPositions();
        List<PositionPOJO> positionPOJOs = positions.stream()
                .map(position -> modelMapper.map(position, PositionPOJO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionPOJOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PositionPOJO> getPositionByUUID(@PathVariable("uuid") UUID uuid) {
        Position position = positionService.findByUuid(uuid);
        if (position != null) {
            PositionPOJO positionPOJO = modelMapper.map(position, PositionPOJO.class);
            return ResponseEntity.ok(positionPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PositionPOJO> createPosition(@RequestBody PositionDTO positionDTO) {

        Position createdPosition = positionService.createPosition(positionDTO);
        PositionPOJO createdPositionPOJO = modelMapper.map(createdPosition, PositionPOJO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPositionPOJO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PositionPOJO> updatePosition(
            @PathVariable("uuid") UUID uuid,
            @RequestBody PositionDTO positionDTO) {

        Position updatedPosition = positionService.updatePosition(uuid, positionDTO);
        if (updatedPosition != null) {
            PositionPOJO updatedPositionPOJO = modelMapper.map(updatedPosition, PositionPOJO.class);
            return ResponseEntity.ok(updatedPositionPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deletePosition(@PathVariable("uuid") UUID uuid) {
        positionService.deletePosition(uuid);
    }
}

