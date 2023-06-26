package sn.ecpi.ecomerce.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.StoreDTO;
import sn.ecpi.ecomerce.entite.Store;
import sn.ecpi.ecomerce.pojo.StorePOJO;
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
    public ResponseEntity<List<StorePOJO>> getAllStores() {
        List<Store> stores = storeService.getAllStore();
        List<StorePOJO> storePOJOs = stores.stream()
                .map(store -> modelMapper.map(store, StorePOJO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(storePOJOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<StorePOJO> getStoreByUUID(@PathVariable("uuid") UUID uuid) {
        Store store = storeService.findByUuid(uuid);
        if (store != null) {
            StorePOJO storePOJO = modelMapper.map(store, StorePOJO.class);
            return ResponseEntity.ok(storePOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StorePOJO> createStore(@RequestBody StoreDTO storeDTO) {

        Store createdStore = storeService.createStore(storeDTO);
        StorePOJO createdStorePOJO = modelMapper.map(createdStore, StorePOJO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStorePOJO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<StorePOJO> updateStore(
            @PathVariable("uuid") UUID uuid,
            @RequestBody StoreDTO storeDTO) {

        Store updatedStore = storeService.updateStore(uuid, storeDTO);
        if (updatedStore != null) {
            StorePOJO updatedStorePOJO = modelMapper.map(updatedStore, StorePOJO.class);
            return ResponseEntity.ok(updatedStorePOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    public void deleteStore(@PathVariable("uuid") UUID uuid) {
        storeService.deleteStore(uuid);
    }
}

