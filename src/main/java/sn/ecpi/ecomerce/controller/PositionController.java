package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.PositionDTO;
import sn.ecpi.ecomerce.entite.Position;
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
    public ResponseEntity<List<PositionDTO>> getAllPositions() {
        List<Position> positions = positionService.getAllPositions();
        List<PositionDTO> positionDTOs = positions.stream()
                .map(position -> modelMapper.map(position, PositionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PositionDTO> getPositionByUUID(@PathVariable("uuid") UUID uuid) {
        Position position = positionService.findByUuid(uuid);
        if (position != null) {
            PositionDTO positionDTO = modelMapper.map(position, PositionDTO.class);
            return ResponseEntity.ok(positionDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PositionDTO> createPosition(@RequestBody PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        Position createdPosition = positionService.createPosition(position);
        PositionDTO createdPositionDTO = modelMapper.map(createdPosition, PositionDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPositionDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PositionDTO> updatePosition(
            @PathVariable("uuid") UUID uuid,
            @RequestBody PositionDTO positionDTO) {
        Position position = modelMapper.map(positionDTO, Position.class);
        Position updatedPosition = positionService.updatePosition(uuid, position);
        if (updatedPosition != null) {
            PositionDTO updatedPositionDTO = modelMapper.map(updatedPosition, PositionDTO.class);
            return ResponseEntity.ok(updatedPositionDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deletePosition(@PathVariable("uuid") UUID uuid) {
        positionService.deletePosition(uuid);
    }
}

