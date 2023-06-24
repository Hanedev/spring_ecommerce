package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import sn.ecpi.ecomerce.entite.Store;
import sn.ecpi.ecomerce.repos.StoreRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class StoreService {
    public final StoreRepos storeRepos;

    public List<Store> getAllStore(){
        return storeRepos.findAll();
    }

    public Store findByUuid(UUID uuid){
        Optional<Store> result = storeRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("Store not Found");
        }
    }

    public  Store createStore(Store store){
        return storeRepos.save(store);
    }

    public Store updateStore(UUID uuid, Store storeResquest){
        Store store = storeRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Store not found"));
        store.setNom(storeResquest.getNom());
        store.setUser(storeResquest.getUser());
        return storeRepos.save(store);
    }

    public void deleteStore(UUID uuid){
        Store store = storeRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Store not found"));
        storeRepos.delete(store);
    }
}