package sn.ecpi.ecomerce.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.ecomerce.dto.ClientDTO;
import sn.ecpi.ecomerce.dto.PasswordDTO;
import sn.ecpi.ecomerce.entite.Client;
import sn.ecpi.ecomerce.pojo.ClientPOJO;
import sn.ecpi.ecomerce.service.ClientService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ModelMapper modelMapper;
    private final ClientService clientService;

    public ClientController(ModelMapper modelMapper, ClientService clientService) {
        this.modelMapper = modelMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientPOJO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientPOJO> clientPOJOs = clients.stream()
                .map(client -> modelMapper.map(client, ClientPOJO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientPOJOs);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ClientPOJO> getClientByUUID(@PathVariable("uuid") UUID uuid) {
        Client client = clientService.findByUuid(uuid);
        if (client != null) {
            ClientPOJO clientPOJO = modelMapper.map(client, ClientPOJO.class);
            return ResponseEntity.ok(clientPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClientPOJO> createClient(@RequestBody ClientDTO clientDTO) {

        Client createdClient = clientService.createClient(clientDTO);
        ClientPOJO createdClientPOJO = modelMapper.map(createdClient, ClientPOJO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClientPOJO);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ClientPOJO> updateClient(
            @PathVariable("uuid") UUID uuid,
            @RequestBody ClientDTO clientDTO) {

        Client updatedClient = clientService.updateClient(uuid, clientDTO);
        if (updatedClient != null) {
            ClientPOJO updatedClientPOJO = modelMapper.map(updatedClient, ClientPOJO.class);
            return ResponseEntity.ok(updatedClientPOJO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/password/{uuid}")
    public ResponseEntity<ClientPOJO> updatePassword(@PathVariable UUID uuid, @RequestBody PasswordDTO passwordDTO){
        Client client = clientService.updatePassword(uuid, passwordDTO);

        ClientPOJO clientResponse = modelMapper.map(client, ClientPOJO.class);
        return ResponseEntity.ok().body(clientResponse);
    }

    @DeleteMapping("/{uuid}")
    public void deleteClient(@PathVariable("uuid") UUID uuid) {
        clientService.deleteClient(uuid);
    }
}
