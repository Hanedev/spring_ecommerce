package sn.ecpi.ecomerce.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import sn.ecpi.ecomerce.dto.ClientDTO;
import sn.ecpi.ecomerce.dto.PasswordDTO;
import sn.ecpi.ecomerce.entite.Client;
import sn.ecpi.ecomerce.repos.ClientRepos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class ClientService {

    private final ModelMapper modelMapper;

    public final ClientRepos clientRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public  Client createClient(ClientDTO clientDTO){
        Client client = modelMapper.map(clientDTO, Client.class);
        return clientRepos.save(client);
    }

    public Client updateClient(UUID uuid, ClientDTO clientDTO){
        Client clientResquest = modelMapper.map(clientDTO, Client.class);
        Client client = clientRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Client not found"));
        client.setNom(clientResquest.getNom());
        client.setPrenom(clientResquest.getPrenom());
        client.setMail(clientResquest.getMail());
        client.setTel(clientResquest.getTel());
        client.setUsername(clientResquest.getUsername());

        return clientRepos.save(client);
    }

    public Client updatePassword(UUID uuid, PasswordDTO passwordDTO)  {
        Client clientRequest = clientRepos.findById(uuid).orElseThrow();
        if(passwordEncoder.matches(passwordDTO.getOldPassword(),clientRequest.getPassword())) {
            clientRequest.setPassword(passwordEncoder.encode(passwordDTO.getNewPassord()));
            return clientRepos.save(clientRequest);
        } else throw new ResourceAccessException("Password already exists");


    }

    public void deleteClient(UUID uuid){
        Client client = clientRepos.findById(uuid)
                .orElseThrow(()->new ResourceAccessException("Client not found"));
        clientRepos.delete(client);
    }

}
