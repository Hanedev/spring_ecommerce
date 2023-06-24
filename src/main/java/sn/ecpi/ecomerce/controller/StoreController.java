package sn.ecpi.ecomerce.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.StoreDTO;
import sn.ecpi.ecomerce.entite.Store;
import sn.ecpi.ecomerce.service.StoreService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final ModelMapper modelMapper;
    private final StoreService storeService;

    @Autowired
    public StoreController(ModelMapper modelMapper, StoreService storeService) {
        this.modelMapper = modelMapper;
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<Store> stores = storeService.getAllStore();
        List<StoreDTO> storeDTOs = stores.stream()
                .map(store -> modelMapper.map(store, StoreDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(storeDTOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<StoreDTO> getStoreByUUID(@PathVariable("uuid") UUID uuid) {
        Store store = storeService.findByUuid(uuid);
        if (store != null) {
            StoreDTO storeDTO = modelMapper.map(store, StoreDTO.class);
            return ResponseEntity.ok(storeDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO) {
        Store store = modelMapper.map(storeDTO, Store.class);
        Store createdStore = storeService.createStore(store);
        StoreDTO createdStoreDTO = modelMapper.map(createdStore, StoreDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStoreDTO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<StoreDTO> updateStore(
            @PathVariable("uuid") UUID uuid,
            @RequestBody StoreDTO storeDTO) {
        Store store = modelMapper.map(storeDTO, Store.class);
        Store updatedStore = storeService.updateStore(uuid, store);
        if (updatedStore != null) {
            StoreDTO updatedStoreDTO = modelMapper.map(updatedStore, StoreDTO.class);
            return ResponseEntity.ok(updatedStoreDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteStore(@PathVariable("uuid") UUID uuid) {
        storeService.deleteStore(uuid);
    }
}

