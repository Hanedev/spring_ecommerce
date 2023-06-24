package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.entite.Client;
import sn.ecpi.ecomerce.repos.ClientRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class ClientService {
    public final ClientRepos clientRepos;

    public List<Client> getAllClients(){
        return clientRepos.findAll();
    }

    public Client findByUuid(UUID uuid){
        Optional<Client> result = clientRepos.findById(uuid);
        if(result.isPresent()){
            return result.get();

        }else {
            throw new ResourceAccessException("Client not Found");
        }


    }

    public  Client createClient(Client client){
        return clientRepos.save(client);
    }

    public Client updateClient(UUID uuid, Client clientResquest){
        Client client = clientRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Client not found"));
        client.setNom(clientResquest.getNom());
        client.setPrenom(clientResquest.getPrenom());
        client.setMail(clientResquest.getMail());
        client.setTel(clientResquest.getTel());
        client.setUsername(clientResquest.getUsername());
        client.setPassword(clientResquest.getPassword());
        return clientRepos.save(client);
    }

    public void deleteClient(UUID uuid){
        Client client = clientRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Client not found"));
        clientRepos.delete(client);
    }

}
